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
import braudeproject.smartstations.Models.ShortestRoutes;
import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.Models.Stop;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.Config;
import braudeproject.smartstations.Services.RequestCallback;
import braudeproject.smartstations.Services.RoutesService;
import braudeproject.smartstations.Services.StationsService;

public class RouteChooseActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.routesMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String destinationId = getIntent().getStringExtra("destinationId");

        RoutesService.getShortestRoutes(destinationId, new RequestCallback<ShortestRoutes>() {
            @Override
            public void onSuccess(ShortestRoutes route) {


                PolylineOptions line = new PolylineOptions();
                line.color(0xaa2211);
                
                for (Station station : route.stations) {
                    LatLng coordinates = new LatLng(station.lat, station.lng);

                    MarkerOptions options =  new MarkerOptions()
                            .position(coordinates)
                            .title(station.name)
                            .snippet(station.description);

                    if (station.id.compareTo(Config.getInstance().stationId) == 0)
                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                    Marker m = mMap.addMarker(options);

                    if (station.id.compareTo(Config.getInstance().stationId) == 0)
                        m.showInfoWindow();

                    line.add(coordinates);
                }

                mMap.addPolyline(line);

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(route.stations[0].lat, route.stations[0].lng), 15));
            }
        });
    }
}