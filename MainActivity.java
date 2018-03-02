package edu.psu.hkg5050.myapplication;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int TotNumofClick;
    int TotalEarned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void OnClick(View veiw)
    {

        TextView Cash = findViewById(R.id.Cash);



        if(TotNumofClick == 0)
        {
            Toast.makeText(this, "You pressed the button for the first time!", Toast.LENGTH_LONG).show();
        }


        TotalEarned++;
        TotNumofClick++;
        Cash.setText("Cash: " + Integer.toString(TotalEarned));

    }
    public void MenuOnClick(View view)
    {

        DialogFragment dialogFragment = new DialogMenu();
        dialogFragment.show(getFragmentManager(), "eraseDialog");

    }

}

