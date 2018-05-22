package braudeproject.smartstations.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import braudeproject.smartstations.R;
import braudeproject.smartstations.Services.*;

public class DriverLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        final TextView txtDriverId = (TextView) findViewById(R.id.txtDriverId);
        final TextView txtDriverPassword = (TextView) findViewById(R.id.txtPassword);
        final DriverLogin self = this;

        Button fab = (Button) findViewById(R.id.btnLogin);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebServices.login(txtDriverId.getText().toString(), txtDriverPassword.getText().toString(), new RequestCallback<ServerResponse>() {
                    @Override
                    public void onSuccess(ServerResponse result) {
                        if (result.success) {
                            Intent myIntent = new Intent(self, MapsActivity.class);
                            startActivity(myIntent);
                        }else{
                            txtDriverId.setText(result.message);
                        }
                    }
                });

            }
        });
    }

}
