package com.majada.jhona.myapplication;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

public class Fecha extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    public Dialog onCreateDialog(Bundle savedInstanceState){

         DatePickerDialog calendario;

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this, mYear, mMonth,(mDay + 7));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText textFecha = (EditText) getActivity().findViewById(R.id.etFecha);
        textFecha.setText((dayOfMonth ) + "/" + ( month + 1) + "/" + year);
    }
}
