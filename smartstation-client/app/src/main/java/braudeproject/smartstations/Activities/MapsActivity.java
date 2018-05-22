package braudeproject.smartstations.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.WebServices;
import braudeproject.smartstations.Services.RequestCallback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        WebServices.getStations(new RequestCallback<List<Station>>() {
            @Override
            public void onSuccess(List<Station> stations) {

                PolylineOptions line = new PolylineOptions();

                for (Station station : stations) {

                    LatLng coordinates = new LatLng(station.lat, station.lng);

                    mMap.addMarker(
                            new MarkerOptions()
                                    .position(coordinates)
                                    .title(station.name)
                    );
                }

                mMap.addPolyline(line);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(stations.get(0).lat, stations.get(0).lng), 15));
            }
        });
    }
}
