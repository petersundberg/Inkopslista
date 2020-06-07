package com.example.inkpslista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateListActivity extends AppCompatActivity {

    private TextView listName;
    private EditText itemToAddView;
    private Button addItemBtn;
    private ListView listView;
    //private ListViewAdapter adapter;

    private Button saveListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        addItemBtn = findViewById(R.id.addItemBtn);
        itemToAddView = findViewById(R.id.itemToAddTextView);

        listView = findViewById(R.id.newListListView);  //list for items
        //listView.setAdapter();
        registerForContextMenu(listView);   //register context menu to listView

        //skriv listans namn i TextView
        listName = findViewById(R.id.newListNameTextView);
        saveListBtn = findViewById(R.id.saveListBtn);

        Intent nameIntent = getIntent();
        String nameTime = nameIntent.getStringExtra("dateTime");
        listName.setText(nameTime);
//        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
//        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            ArrayList<String> newList = new ArrayList<>();
        //ArrayAdapter adap = new ArrayAdapter(CreateListActivity.this, android.R.layout.simple_list_item_1, newList);




            @Override
            public void onClick(View v) {
                if (itemToAddView.getText().toString().length() > 0) {
                    newList.add(itemToAddView.getText().toString());    //newList.add(itemToAddView.getText().toString());
                    ArrayAdapter adapter = new ArrayAdapter(CreateListActivity.this, android.R.layout.simple_list_item_1, newList);
                    listView.setAdapter(adapter);
                    itemToAddView.setText("");
                    itemToAddView.requestFocus();
                }
            }
        });
        addItemBtn.setEnabled(false);   //avaktivera lägg-till-knappen
        //lägg en TextWatcher på (flera) EditText
        itemToAddView.addTextChangedListener(itemTextWatcher);
    }

    //assign a context menu to list
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.item_context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()){
            case R.id.delete_item:
                Toast.makeText(this, "Ta bort från lista", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option2:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    private TextWatcher itemTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //----------
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String itemCharLength = itemToAddView.getText().toString().trim();

            if (itemCharLength.length() > 0) {
                addItemBtn.setEnabled(true);
            } else {
                addItemBtn.setEnabled(false);
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
            //----------
        }

    };





}



//        //skriv listans namn i TextView
//        listName = findViewById(R.id.newListNameTextView);
//        Intent nameIntent = getIntent();
//        String nameTime = nameIntent.getStringExtra("dateTime");
//        listName.setText(nameTime);

//        listView = (ListView)findViewById(R.id.newListListView);
//        final ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("Lägg till vara här");
//        arrayList.add("Lägg till vara här");
//        arrayList.add("Lägg till vara här");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(CreateListActivity.this, "clicked item: " + i + " " + arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // itemToAddTextView     addItemBtn
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(CreateListActivity.this, "clicked item: " + i + " " + arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
//            }
//        });







































