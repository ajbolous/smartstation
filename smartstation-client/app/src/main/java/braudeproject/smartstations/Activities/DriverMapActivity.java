package braudeproject.smartstations.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Timer;
import java.util.TimerTask;

import braudeproject.smartstations.Models.Route;
import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.Models.Stop;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.RequestCallback;
import braudeproject.smartstations.Services.RoutesService;
import braudeproject.smartstations.Services.StationsService;

public class DriverMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Route driverRoute;
    private double driverLat;
    private double driverLng;
    private Marker driverMarker;
    private int nextStopIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.driversMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int routeId = 30;

        RoutesService.getRoute(routeId, new RequestCallback<Route>() {
            @Override
            public void onSuccess(Route route) {
                driverRoute = route;

                driverLat = route.getStops()[0].station.getLat();
                driverLng = route.getStops()[0].station.getLng();

                nextStopIndex = 1;

                PolylineOptions line = new PolylineOptions();

                for (Stop stop : route.getStops()) {
                    LatLng coordinates = new LatLng(stop.station.lat, stop.station.lng);

                    mMap.addMarker(
                            new MarkerOptions()
                                    .position(coordinates)
                                    .title(stop.station.name)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    );

                    line.add(coordinates);
                }

                mMap.addPolyline(line);

                Station first = route.getStops()[0].station;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(first.lat, first.lng), 12));

                driverMarker = mMap.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(driverLat, driverLng))
                                .title("Driver")
                );
                startLocationUpdates();
            }
        });
    }


    public void startLocationUpdates() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            if(updateDriverMarkers()) {
                                final View view = findViewById(R.id.driversMap);

                                if(nextStopIndex < driverRoute.getStops().length) {
                                    StationsService.getStationStatus(driverRoute.getStops()[nextStopIndex].stationId, new RequestCallback<Station>() {
                                        @Override
                                        public void onSuccess(Station station) {
                                            Snackbar.make(view, "Arriving at stop: " + station.name + " Passengers: 5",  Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                        }
                                    });
                                } else {
                                    Snackbar.make(view, "Last Stop, End of Route - No Boarding",  Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                }
                            }else{

                            }

                            driverMarker.setPosition(new LatLng(driverLat, driverLng));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(driverLat, driverLng), 16));
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms
    }


    public boolean updateDriverMarkers() {

        Stop nextStop = driverRoute.getStops()[nextStopIndex];
        Stop currStop = driverRoute.getStops()[nextStopIndex - 1];

        double deltaX = nextStop.station.lat - currStop.station.lat;
        double deltaY = nextStop.station.lng - currStop.station.lng;


        driverLat += (deltaX / 50);
        driverLng += (deltaY / 50);

        if (Math.abs(driverLat - nextStop.station.lat) < Math.abs(deltaX / 10) && Math.abs(driverLng - nextStop.station.lng) < Math.abs(deltaY / 10)) {
            nextStopIndex++;
            driverLat = nextStop.station.lat;
            driverLng = nextStop.station.lng;
            return true;
        }

        if (Math.abs(driverLat - nextStop.station.lat) < Math.abs(deltaX / 5) && Math.abs(driverLng - nextStop.station.lng) < Math.abs(deltaY / 5)) {
            return true;
        }

        return false;
    }
}