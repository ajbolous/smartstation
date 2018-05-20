package braudeproject.smartstations.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

import braudeproject.smartstations.R;

public class DriverLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        TextView txtDriverId = (TextView) findViewById(R.id.txtDriverId);
        TextView txtDriverPassword = (TextView) findViewById(R.id.txtPassword);

        Button fab = (Button) findViewById(R.id.btnLogin);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
