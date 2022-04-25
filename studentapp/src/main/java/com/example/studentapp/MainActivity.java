package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnGoToAdd,btnGoToSearch;
    ListView lst1;
    ArrayList<student> arrayList;
    ArrayAdapter<student> adapter;
    EditText txtSearchName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbo obj1 = new dbo(getApplicationContext(),null,null,1);

        btnGoToAdd = findViewById(R.id.btnGoToAdd);
        btnGoToSearch = findViewById(R.id.btnGoTosearch);
        lst1 = findViewById(R.id.lst1);
        txtSearchName = findViewById(R.id.txtSerachName);

        btnGoToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddActivity.class);
                startActivity(i);
            }
        });

        btnGoToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(i);
            }
        });
        arrayList = new ArrayList<student>();
        adapter = new ArrayAdapter<student>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        lst1.setAdapter(adapter);

        Cursor c=obj1.getdata();
        if (c.moveToFirst()) {
            do {
                arrayList.add(new student(c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
            } while (c.moveToNext());
        }


        txtSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
            }
        });


        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                student s = adapter.getItem(i);     //Remember
                Intent i2 = new Intent(getApplicationContext(),DescActivity.class);
                i2.putExtra("id",s.id);
                i2.putExtra("name",s.name);
                i2.putExtra("city",s.city);
                i2.putExtra("dob",s.dob);
                startActivity(i2);

                Toast.makeText(getApplicationContext(),s.name,Toast.LENGTH_SHORT).show();
            }
        });
    }
}