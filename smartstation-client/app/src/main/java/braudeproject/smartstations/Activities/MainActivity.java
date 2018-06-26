package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.Config;
import braudeproject.smartstations.Services.WebServices;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MainActivity self = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebServices.initialize(getBaseContext());

        if (Config.getInstance().Mode == Config.AppMode.Driver)
            startActivity(new Intent(self, DriverLogin.class));
        else
            startActivity(new Intent(self, MapsActivity.class));

    }
}
