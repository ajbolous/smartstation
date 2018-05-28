package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.WebServices;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MainActivity self = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebServices.initialize(getBaseContext());
        final Button btnShowMap = findViewById(R.id.btnShowMap);
        final Button btnSearchRoutes = findViewById(R.id.btnSearchRoutes);
        final Button btnFavourites = findViewById(R.id.btnFavourites);


        btnShowMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(self, MapsActivity.class));
            }
        });

        btnSearchRoutes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(self, RoutesListActivity.class));
            }
        });

        btnFavourites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(self, DriverLogin.class));
            }
        });
    }
}
