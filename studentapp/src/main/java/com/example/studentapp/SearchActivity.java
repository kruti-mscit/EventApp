package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    Spinner searchSpinner;
    ListView searchLst;
    String SelectedSearchCity;
    String[] city = {"Baroda","Anand","surat","amdavad"};
    ArrayList<student> arrayList;
    ArrayAdapter<student> adapter;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchSpinner = findViewById(R.id.Searchspinner);
        searchLst = findViewById(R.id.serachLst);
        btnSearch = findViewById(R.id.btnSearch);
        dbo obj1 = new dbo(this,null,null,1);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,city);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(aa);

        searchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedSearchCity = city[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList = new ArrayList<student>();
                adapter = new ArrayAdapter<student>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                searchLst.setAdapter(adapter);

                Cursor c=obj1.getSearchCityData(SelectedSearchCity);
                if (c.moveToFirst()) {
                    do {
                        arrayList.add(new student(c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                        Toast.makeText(getApplicationContext(), c.getString(1), Toast.LENGTH_SHORT).show();
                    } while (c.moveToNext());
                }
            }
         });

    }
}