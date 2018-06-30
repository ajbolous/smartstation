package braudeproject.smartstations.Activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import braudeproject.smartstations.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final String destinationId = getIntent().getStringExtra("destinationId");
        final String sourceId = getIntent().getStringExtra("sourceId");
        final int cost = getIntent().getIntExtra("cost", 0);
        final int stops = getIntent().getIntExtra("stops", 0);

        final Button continuePayment = findViewById(R.id.btnContinuePay);

        TextView fromStation = (TextView) findViewById(R.id.txtFromStation);
        TextView toStation = (TextView) findViewById(R.id.txtToStation);
        TextView rcost = (TextView) findViewById(R.id.txtRoutePrice);
        TextView info = (TextView) findViewById(R.id.txtRouteInfo);

        fromStation.setText("From station " + sourceId);
        toStation.setText("To station " + destinationId);
        rcost.setText("Cost " + cost + " $");
        info.setText("Number of stops : " + stops);

        RadioGroup radioPayment = (RadioGroup) findViewById(R.id.radioPayment);

        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        };


        continuePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Payment accepted, Ticket purchased",  Snackbar.LENGTH_LONG).setAction("Action", null).show();
                finish();
            }
        });


    }
}
