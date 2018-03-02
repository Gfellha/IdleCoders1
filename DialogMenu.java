package edu.psu.hkg5050.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by hgfel on 3/1/2018.
 */

public class DialogMenu extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sorry!").setMessage("The menu section isn't finished!")
                .setPositiveButton("Stay on main screen", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int n)
                    {

                    }

                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity MainActivity) {

        super.onAttach(MainActivity);
    }
}
