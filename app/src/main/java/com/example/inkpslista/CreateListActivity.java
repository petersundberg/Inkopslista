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
import android.view.WindowManager;
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
    private String itemEntered;
    private EditText itemToAddView;
    private Button addItemBtn;
    private ListView listView;
    private ArrayAdapter adapter;
    private Button saveListBtn;
    ArrayList<String> newList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        addItemBtn = findViewById(R.id.addItemBtn);
        itemToAddView = findViewById(R.id.itemToAddTextView);

        listView = findViewById(R.id.newListListView);  //new list for items
        registerForContextMenu(listView);   //register context menu to listView

        listName = findViewById(R.id.newListNameTextView);
        saveListBtn = findViewById(R.id.saveListBtn);
        adapter = new ArrayAdapter(CreateListActivity.this, android.R.layout.simple_list_item_1, newList);
        Intent nameIntent = getIntent();
        String nameTime = nameIntent.getStringExtra("dateTime");
        listName.setText(nameTime);
        listView.setAdapter(adapter);

        //set onClickListener on btn which adds each item to list
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemToAddView.getText().toString().length() > 0) {
                    newList.add(itemToAddView.getText().toString());    //newList.add(itemToAddView.getText().toString());
                    itemToAddView.setText("");
                    itemToAddView.requestFocus();
                }
            }
        });
        //first disable button for adding items
        addItemBtn.setEnabled(false);
        //assign a textWatcher to EditText View (could to multiple Views) to check for length >0
        itemToAddView.addTextChangedListener(itemTextWatcher);

        //thos line stops keyboard from pushing up listView
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    //assign a context menu to the new list
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
                adapter.remove(adapter.getItem(info.position));
                return true;
            case R.id.option2:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //textWatcher checking for length >0 in EditText
    private TextWatcher itemTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //----------
        }
        //enables itemToAdd-button if anything has been entered in EditText
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


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        // TODO Auto-generated method stub
//        super.onSaveInstanceState(outState);
//
//        itemEntered = itemToAddView.getText().toString();
//        outState.putString("itemEntered", itemEntered);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        itemToAddView.setText(savedInstanceState.getString("itemEntered"));
//
//    }
}



















