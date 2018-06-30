package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.Config;
import braudeproject.smartstations.Services.RequestCallback;
import braudeproject.smartstations.Services.StationsService;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        final FloatingActionButton btnBuyTicket = findViewById(R.id.buyNewTicket);
        final FloatingActionButton btnFavouriteTicket = findViewById(R.id.buyFavouriteTicket);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.usersMap);

        mapFragment.getMapAsync(this);

        final MapsActivity self = this;
        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, DestinationsListActivity.class);
                intent.putExtra("vtype", "normal" );
                startActivity(intent);
            }
        });

        btnFavouriteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, DestinationsListActivity.class);
                intent.putExtra("vtype", "favourite" );
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        StationsService.getStations(new RequestCallback<Station[]>() {
            @Override
            public void onSuccess(Station[] stations) {

                for (Station station : stations) {

                    LatLng coordinates = new LatLng(station.lat, station.lng);

                    if (station.id.compareTo(Config.getInstance().stationId) == 0) {
                        mMap.addMarker(new MarkerOptions()
                                .position(coordinates).snippet("You are Here")
                                .title(station.name)
                        ).showInfoWindow();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(station.lat, station.lng), 15));

                    } else {
                        mMap.addMarker(new MarkerOptions()
                                .position(coordinates)
                                .title(station.name))
                                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    }
                }

            }
        });
    }
}
