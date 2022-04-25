package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DescActivity extends AppCompatActivity {

    EditText txtUpdateId,txtUpdateName;
    Button btnUpdate,btnDelete;
    CalendarView updateCalendrview;
    Spinner updateSpinner;
    String[] cityArray = {"baroda","navsari","surat","amdavad"};
    String UpdateCity;
    String UpdateDob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name=  i.getStringExtra("name");
        String city= i.getStringExtra("city");
        String dob=   i.getStringExtra("dob");

        dbo obj1 = new dbo(getApplicationContext(),null,null,1);
        txtUpdateId= findViewById(R.id.txtUpdateId);
        txtUpdateName = findViewById(R.id.txtUpdateName);
        updateSpinner = findViewById(R.id.Updatespinner);
        updateCalendrview= findViewById(R.id.UpdatecalendarView);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        txtUpdateId.setText(id);
        txtUpdateName.setText(name);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cityArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateSpinner.setAdapter(aa);

        updateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              UpdateCity  = cityArray[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        updateCalendrview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                String  curDate = String.valueOf(day);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month);
                UpdateDob= curDate + "/" + Month + "/" + Year;

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UpdateId = txtUpdateId.getText().toString();
                String Updatename = txtUpdateName.getText().toString();

                obj1.updatedata(UpdateId,Updatename,UpdateCity,UpdateDob);
                Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UpdateId = txtUpdateId.getText().toString();
                obj1.deletedata(UpdateId);
                Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}