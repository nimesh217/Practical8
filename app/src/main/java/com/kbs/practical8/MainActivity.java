package com.kbs.practical8;

import android.support.v7.app.AppCompatActivity;
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

    EditText etName,etSearch;
    //Button btnAdd;
    ListView lvNames;
    //String arrNames[]={"11","22","33","44","55"};
    ArrayList<String> alNames;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.etname);
        etSearch=(EditText)findViewById(R.id.etsearch);
        lvNames = (ListView) findViewById(R.id.lvNames);
        //alNames.addAll(("11","22"));
        alNames=new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alNames);
        lvNames.setAdapter(adapter);
        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,adapter.getItem(i).toString()+ " ", Toast.LENGTH_SHORT).show();
            }
        });
        lvNames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapter.getItem(i).toString();
                adapter.remove(item);
                Toast.makeText(MainActivity.this,item+" is removed.",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void btnAdd_click(View v)
    {
        alNames.add(etName.getText().toString());
        etName.setText("");
        adapter.notifyDataSetChanged();
    }
    public void btnSearch_click(View v)
    {
        adapter.getFilter().filter(etSearch.getText().toString());
    }
}
