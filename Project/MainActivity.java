package edu.psu.pop5137.idlecoders;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogMenu.DialogListener {
    int TotNumofClick;  //value will be read in from file, initially will be set to 0
    int TotalEarned;    //value will be read in from file, initially will be set to 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menuBtn = (Button) findViewById(R.id.Menu);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClick();
            }
        });
    }

    public void OnClick(View view) {
        TextView Cash = (TextView) findViewById(R.id.Cash);

        if (TotNumofClick == 0) {
            Toast.makeText(this, "You pressed the button for the first time!", Toast.LENGTH_LONG).show();
        }

        TotalEarned++;
        TotNumofClick++;
        Cash.setText("Cash: " + Integer.toString(TotalEarned));

    }

    public void moveToUpgradeScreen(View view){
        Intent intent = new Intent(this, UpgradesActivity.class);
        startActivity(intent);
    }

    public void onMenuClick() { //Implementation of the interface defined in DialogMenu class
        FragmentManager manager = getFragmentManager();
        DialogMenu dialog = new DialogMenu();
        dialog.show(manager, "MyDialog");
    }


}