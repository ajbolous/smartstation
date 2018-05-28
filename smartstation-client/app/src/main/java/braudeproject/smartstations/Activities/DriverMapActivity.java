package braudeproject.smartstations.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;
import java.util.List;

import braudeproject.smartstations.Models.Route;
import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.Models.Stop;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.RequestCallback;
import braudeproject.smartstations.Services.RoutesService;
import braudeproject.smartstations.Services.StationsService;
import braudeproject.smartstations.Services.WebServices;

public class DriverMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.driversMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int routeId = 30;

        final HashMap<String, Station> hashedStations = null;


        RoutesService.getRoute(routeId, new RequestCallback<Route>() {
            @Override
            public void onSuccess(Route _route) {
                final Route route = _route;

                StationsService.getStationsHashed(new RequestCallback<HashMap<String, Station>>() {
                    @Override
                    public void onSuccess(HashMap<String, Station> stationsMap) {
                        PolylineOptions line = new PolylineOptions();

                        for (Stop stop : route.getStops()) {
                            Station station = stationsMap.get(stop.stationId);
                            LatLng coordinates = new LatLng(station.lat, station.lng);

                            mMap.addMarker(
                                    new MarkerOptions()
                                            .position(coordinates)
                                            .title(station.name)
                                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            );

                            line.add(coordinates);
                        }

                        mMap.addPolyline(line);


                        Station first = stationsMap.get(route.getStops()[0].stationId);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(first.lat, first.lng), 15));
                    }
                });


            }
        });
    }
}
