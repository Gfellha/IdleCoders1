package edu.psu.pop5137.idlecoders;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogMenu.DialogListener {
    SQLiteDatabase db;
    long currentRow;
    long TotNumofClick;  //value will be read in from file, initially will be set to 0
    long TotalEarned;    //value will be read in from file, initially will be set to 0

    TextView Cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menuBtn = (Button) findViewById(R.id.Menu);
        Cash = (TextView) findViewById(R.id.Cash);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClick();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(db == null) {
            IdleDB.getInstance(this).asyncWritableDatabase(new IdleDB.onDBReadyListener() {

                @Override
                public void onReady(SQLiteDatabase database) {

                    db = database;

                    String[] columns = {"totalClicks", "totalEarned"};

                    Cursor c = db.query(
                            "game",
                            columns,
                            null,
                            null,
                            null,
                            null,
                            null
                    );

                    if (c.moveToFirst()) {
                        TotNumofClick = c.getLong(c.getColumnIndexOrThrow("totalClicks"));
                        TotalEarned = c.getLong(c.getColumnIndexOrThrow("totalEarned"));
                        Cash.setText("Cash: " + Long.toString(TotalEarned));
                    } else {
                        TotNumofClick = 0;
                        TotalEarned = 0;
                        Cash.setText("Cash: " + Long.toString(TotalEarned));
                    }
                }
            });
        }
    }

    @Override
    protected void onStop() {
        ContentValues values = new ContentValues();
        values.put("totalClicks", TotNumofClick);
        values.put("totalEarned", TotalEarned);

        int update = db.update("game", values, null, null);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    public void OnClick(View view) {
        Cash = (TextView) findViewById(R.id.Cash);

        if (TotNumofClick == 0) {
            Toast.makeText(this, "You pressed the button for the first time!", Toast.LENGTH_LONG).show();
        }

        TotalEarned++;
        TotNumofClick++;
        Cash.setText("Cash: " + Long.toString(TotalEarned));

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
    public void onMenuClick() { //Implementation of the interface defined in DialogMenu class
        FragmentManager manager = getFragmentManager();
        DialogMenu dialog = new DialogMenu();
        dialog.show(manager, "MyDialog");
    }
}
