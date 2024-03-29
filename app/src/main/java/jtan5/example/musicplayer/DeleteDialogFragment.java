package jtan5.example.musicplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class DeleteDialogFragment extends DialogFragment {
    public static final String EXTRA_DELETE = "jtan5.exampe.musicplayer.delete";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.delete_dialog)
                .setNegativeButton(R.string.dialog_delete,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sendResult(Activity.RESULT_OK, null);
                            }
                        })
                .setPositiveButton(R.string.dialog_cancle,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                sendResult(Activity.RESULT_CANCELED, null);
                            }
                        }
                )
                .create();
    }
    private void sendResult(int resultCode, String string){
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DELETE, string);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}