package com.game.tictacteo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class LoadingDialog {

    Context context;
    Dialog dialog;
    TextView tvLoadingDailogMes;

    public LoadingDialog(Context context){
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_loading_dialog);
        tvLoadingDailogMes = (TextView) dialog.findViewById(R.id.tvLoadingDialogMes);
    }

    public void show(String message){


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        // Disable click outside then close the loading dialog
        dialog.setCanceledOnTouchOutside(false);
        // set Header
        tvLoadingDailogMes.setText(message);
        dialog.create();
        dialog.show();
    }

    public void hide(){
        dialog.dismiss();
    }
}