package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import braudeproject.smartstations.Models.Route;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.RequestCallback;
import braudeproject.smartstations.Services.RoutesService;
import braudeproject.smartstations.Services.WebServices;

public class RoutesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        RoutesService.getAvailableRoutes("user1", new RequestCallback<Route[]>(){

            @Override
            public void onSuccess(Route[] routes) {
                BaseAdapter adapter = new MyAdapter(routes);
                ((ListView)findViewById(R.id.routesListView)).setAdapter(adapter);
            }
        });

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Route route = (Route)adapterView.getItemAtPosition(i);
                Snackbar.make(view, "Assigning new Driver (Bus) to Route " + route.getRouteNumber(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(RoutesListActivity.this, DriverMapActivity.class));
            }
        };

        ((ListView)findViewById(R.id.routesListView)).setOnItemClickListener(onItemClickListener);


    }

    class MyAdapter extends BaseAdapter {

        private Route[] mRoutes;
        public MyAdapter(Route[] routes){
            mRoutes = routes;
        }
        // override other abstract methods here

        @Override
        public int getCount() {
            return mRoutes.length;
        }

        @Override
        public Route getItem(int i) {
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

            Route route = getItem(position);
            ((TextView) convertView.findViewById(R.id.routeTextViewName)).setText("Route: " + route.getRouteNumber());
            ((TextView) convertView.findViewById(R.id.routeTextViewDesc)).setText("Stops: " + route.getStops().length + " , Length: " +  route.getStops()[route.getStops().length -1].distanceFromOrigin  + " KM");

            return convertView;
        }

    }

}
