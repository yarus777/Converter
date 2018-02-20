package com.unit.converter.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.unit.converter.R;
import com.unit.converter.activities.MainActivity;
import com.unit.converter.interfaces.IGraphicConstants;


public class DialogUtils implements IGraphicConstants {

    public static AlertDialog getExitDialog(final MainActivity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, DIALOG_THEME);
        builder.setMessage(context.getString(R.string.quit_text));
        builder.setPositiveButton(context.getString(R.string.ok_dialog_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.finish();
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel_dialog_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }

}
