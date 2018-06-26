package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import braudeproject.smartstations.Models.DestinationStation;
import braudeproject.smartstations.Models.Station;
import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.Config;
import braudeproject.smartstations.Services.RequestCallback;
import braudeproject.smartstations.Services.StationsService;

public class DestinationsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations_list);

        final ListView listView = findViewById(R.id.destinationsListView);
        final DestinationsListActivity self = this;

        final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DestinationStation station = (DestinationStation)adapterView.getItemAtPosition(i);
                Snackbar.make(view, "hello", 5000)
                        .setAction("Action", null)
                        .show();
                Intent intent = new Intent(self, RouteChooseActivity.class);
                intent.putExtra("destinationId", station.station.id);
                startActivity(intent);
            }
        };


        StationsService.getPossibleStations(Config.getInstance().stationId,  new RequestCallback<DestinationStation[]>(){
            @Override
            public void onSuccess(DestinationStation[] stations) {
                BaseAdapter adapter = new MyAdapter(stations);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(onItemClickListener);
            }
        });

    }

    class MyAdapter extends BaseAdapter {

        private DestinationStation[] mStations;

        public MyAdapter(DestinationStation[] stations){
            mStations = stations;
        }

        @Override
        public int getCount() {
            return mStations.length;
        }

        @Override
        public DestinationStation getItem(int i) {
            return mStations[i];
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

            DestinationStation station = getItem(position);
            ((TextView) convertView.findViewById(R.id.routeTextViewName)).setText(station.station.getName());
            ((TextView) convertView.findViewById(R.id.routeTextViewDesc)).setText(station.station.getDescription() + " " + station.distance +" KM");

            return convertView;
        }

    }

}
