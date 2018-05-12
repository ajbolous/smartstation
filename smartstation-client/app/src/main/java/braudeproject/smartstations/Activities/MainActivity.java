package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import braudeproject.smartstations.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MainActivity self = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btnGetTicket = findViewById(R.id.btnSearchRoutes);


        btnGetTicket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(self, MapsActivity.class);
                startActivity(myIntent);
            }
        });
    }


}
