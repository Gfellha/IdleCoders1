package edu.psu.pop5137.idlecoders;

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
    public interface DialogListener{    //Define the interface
        public void onMenuClick();
    }

    DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Sorry!")
                .setMessage("The menu section isn't finished! Be sure to check back soon!")
                .setPositiveButton("Stay on main screen", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int n)
                    {
                        //Does nothing since clicking the button will only close the dialog
                    }

                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity MainActivity) {
        super.onAttach(MainActivity);

        try{
            listener = (DialogListener) MainActivity;
        }
        catch (ClassCastException e){
         throw e;
        }
    }
}
