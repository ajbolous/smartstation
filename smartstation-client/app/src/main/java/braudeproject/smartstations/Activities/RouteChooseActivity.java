package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import braudeproject.smartstations.Models.DestinationStation;
import braudeproject.smartstations.Models.ShortestRoutes;
import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.Config;
import braudeproject.smartstations.Services.RequestCallback;
import braudeproject.smartstations.Services.RoutesService;

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

        final String destinationId = getIntent().getStringExtra("destinationId");
        final ListView listView = findViewById(R.id.routesMapList);
        final RouteChooseActivity self = this;

        final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShortestRoutes route = (ShortestRoutes)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(self, PaymentActivity.class);

                intent.putExtra("sourceId", Config.getInstance().stationId);
                intent.putExtra("destinationId", destinationId);
                intent.putExtra("stops", route.stations.length);
                intent.putExtra("cost", route.stations.length);

                startActivity(intent);
            }
        };

        RoutesService.getShortestRoutes(destinationId, new RequestCallback<ShortestRoutes[]>() {
            @Override
            public void onSuccess(ShortestRoutes[] routes) {

                BaseAdapter adapter = new RouteChooseActivity.MyAdapter(routes);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(onItemClickListener);

                int i =0;
                int[] colors = new int[] {Color.GREEN, Color.RED, Color.MAGENTA, Color.BLACK, Color.BLUE};

                HashSet<String> idSet = new HashSet<String>();


                for (ShortestRoutes route : routes) {
                    PolylineOptions line = new PolylineOptions();

                    for (Station station : route.stations) {

                        LatLng coordinates = new LatLng(station.lat, station.lng);

                        if (idSet.contains(station.id) == false) {

                            MarkerOptions options = new MarkerOptions()
                                    .position(coordinates)
                                    .title(station.name)
                                    .snippet(station.description);

                            if (station.id.compareTo(Config.getInstance().stationId) == 0)
                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                            if (station.id.compareTo(destinationId) == 0)
                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

                            Marker m = mMap.addMarker(options);

                            if (station.id.compareTo(Config.getInstance().stationId) == 0)
                                m.showInfoWindow();

                            idSet.add(station.id);
                        }
                        line.add(coordinates);
                    }
                    line.color(colors[i++ % colors.length]);
                    mMap.addPolyline(line);

                }

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(routes[0].stations[0].lat, routes[0].stations[0].lng), 11));
            }
        });
    }


    class MyAdapter extends BaseAdapter {

        private ShortestRoutes[] mRoutes;

        public MyAdapter(ShortestRoutes[] routes) {
            mRoutes = routes;
        }

        @Override
        public int getCount() {
            return mRoutes.length;
        }

        @Override
        public ShortestRoutes getItem(int i) {
            return mRoutes[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.view_route, container, false);
            }

            ShortestRoutes route = getItem(position);
            ((TextView) convertView.findViewById(R.id.routeTextViewName)).setText("Route " + position);
            ((TextView) convertView.findViewById(R.id.routeTextViewDesc)).setText(route.stations.length + " Station, " + route.totalDistance + " KM");

            return convertView;
        }

    }
}