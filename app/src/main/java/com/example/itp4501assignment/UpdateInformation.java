package com.example.itp4501assignment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class UpdateInformation extends AppCompatActivity {
    EditText etDate;
    EditText etName;
    EditText etEmail;
    EditText etPhone;
    DateFormat fmtDate = DateFormat.getDateInstance(DateFormat.MEDIUM); // set the Date Format

    String sharedPetName, sharedPetDate, sharedPetEmail, sharedPetPhone;
    TextView tvUsername;
    public static final String SHARED_PREFS = "RegPrefe";// instance the sharedPreferences
    SharedPreferences sharedPreferences;

    Calendar dtCalendar= Calendar.getInstance(); //set the Date picker
    DatePickerDialog.OnDateSetListener d = new
            DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    dtCalendar.set(Calendar.YEAR, year);
                    dtCalendar.set(Calendar.MONTH, monthOfYear);
                    dtCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_inf_main); // set the design interface of layout/update_inf_main
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE); // getSharedPreferences
        etDate = findViewById(R.id.txtDate);// set variable to connect the editText
        etName = findViewById(R.id.txtName);// set variable to connect the editText
        etEmail = findViewById(R.id.txtEmail);// set variable to connect the editText
        etPhone = findViewById(R.id.txtPhone);// set variable to connect the editText
        tvUsername = findViewById(R.id.lblUserName);// set variable to connect the editText
        sharedPetName =sharedPreferences.getString("regName","") ;
        // get the sharedPreference value of the regName key and store to variable
        sharedPetDate = sharedPreferences.getString("regDate","");
        // get the sharedPreference value of the regDate key and store to variable

        sharedPetEmail = sharedPreferences.getString("regEmail","");
        // get the sharedPreference value of the regEmail key and store to variable

        sharedPetPhone = sharedPreferences.getString("regPhone","");
        // get the sharedPreference value of the regPhone key and store to variable

        etName.setText(sharedPetName); // set the text to the editText Name
        etDate.setText(sharedPetDate);// set the text to the editText Date
        etEmail.setText(sharedPetEmail);// set the text to the editText Email
        etPhone.setText(sharedPetPhone);// set the text to the editText Phone
        tvUsername.setText(sharedPetName+", Hello!"); // set the text to the TextView
        etDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // Date picker
                new DatePickerDialog(UpdateInformation.this, d,
                        dtCalendar.get(Calendar.YEAR),//year
                        dtCalendar.get(Calendar.MONTH),//month
                        dtCalendar.get(Calendar.DAY_OF_MONTH)).show();//day
                updateLabel(); // picked will call the updateLable method
            }
        });

    }
    private void updateLabel() {
        etDate.setText(fmtDate.format(dtCalendar.getTime()));// set the etDate Edittext value

    }

    public void onClick(View view){
        if(view.getId()==R.id.btnUpdate){//if onclick the update button
            SharedPreferences.Editor editor = sharedPreferences.edit(); // instance the sharedPreferences editor
            editor.putString("regName", etName.getText().toString()); // put the new value to the regName key value
            editor.putString("regDate", etDate.getText().toString());// put the new value to the regDate key value
            editor.putString("regEmail", etEmail.getText().toString());// put the new value to the regEmail key value
            editor.putString("regPhone", etPhone.getText().toString());// put the new value to the regPhone key value
            editor.commit();// sharedPreferences commit
            if(etName.getText().toString()!=sharedPetName ||etDate.getText().toString()!=sharedPetDate || etEmail.getText().toString()!=sharedPetEmail ||etPhone.getText().toString()!=sharedPetPhone)
                Toast.makeText(getBaseContext(), "Updated", Toast.LENGTH_LONG).show(); // check the EditText whether change, and when check will display the Toast
            finish(); //Quit the Activity
        }
    }
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);//when Quit the activity will use the animation
    }

}
