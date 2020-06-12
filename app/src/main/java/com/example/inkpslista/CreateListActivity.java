package com.example.inkpslista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CreateListActivity extends AppCompatActivity {

    private TextView listName;
    private EditText itemToAddView;
    private Button addItemBtn;
    private ListView listView;
    private Button saveListBtn;
    private DBHelperItems myDbHelper;
    private ArrayAdapter adapter;
    private ArrayList items;
    ArrayList<String> newList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        items = new ArrayList();
        addItemBtn = findViewById(R.id.addItemBtn);
        itemToAddView = findViewById(R.id.itemToAddTextView);
        listView = findViewById(R.id.newListListView);  //new listView for items
        registerForContextMenu(listView);   //register context menu to listView
        listName = findViewById(R.id.newListNameTextView);
        saveListBtn = findViewById(R.id.saveListBtn);

        adapter = new ArrayAdapter<String>(CreateListActivity.this, android.R.layout.simple_list_item_1, newList);
        DBHelperItems myDbHelperItems = new DBHelperItems(this);       //myDbHelper = new DBHelper(this);    //DBHelper myDbHelper = new DBHelper(this);

        Intent nameIntent = getIntent();
        String nameTime = nameIntent.getStringExtra("dateTime");
        listName.setText(nameTime);
        itemToAddView.requestFocus();
        listView.setAdapter(adapter);

        //set onClickListener on btn which adds each item to list
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemToAddView.getText().toString().length() > 0) {

                    String itemToBuy = itemToAddView.getText().toString();
//                    String name = listName.getText().toString();
//                    String itemToBuy = itemToAddView.getText().toString();

                    //list object
                    //ListModel list = new ListModel("testList", itemToBuy);

                    //add to database
                    //boolean status = myDbHelper.addListToDb(list);
//
//                    newList.add(itemToAddView.getText().toString());
//                    itemToAddView.setText("");
//                    itemToAddView.requestFocus();
//                    listView.setSelection(adapter.getCount() - 1);

//--------------------------------------------------------------
                    //Create database "items"
                    //declare empty items
                    ListModel items;
                    //Reference database helper
                    DBHelperItems dbHelperItems = new DBHelperItems(CreateListActivity.this);

                    //Init listname
                    items = new ListModel(-1, itemToBuy, -1);

                    //boolean status = myDbHelper.addStudentToDb(studentToAdd);

                    boolean addedItem = false;
                    try {
                        dbHelperItems.addItemToDb(items);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(CreateListActivity.this, "ADDED ITEM: " + addedItem, Toast.LENGTH_SHORT).show();



                    newList.add(itemToAddView.getText().toString());
                    itemToAddView.setText("");
                    itemToAddView.requestFocus();
                    listView.setSelection(adapter.getCount() - 1);


                    //add item to list
                    //myDbHelper.insertItemsInList(itemToAddView.getText().toString());




//                    items.add(itemToBuy);
//                    itemToAddView.setText("");
//                    itemToAddView.requestFocus();
//                    listView.setSelection(adapter.getCount() - 1);
                    //updateViews();

                }
            }
        });
        //first disable button for adding items
        addItemBtn.setEnabled(false);
        //assign a textWatcher to EditText View (could to multiple Views) to check for length >0
        itemToAddView.addTextChangedListener(itemTextWatcher);

        //inits
        saveListBtn = findViewById(R.id.saveListBtn);
        listView = findViewById(R.id.newListListView);
        myDbHelperItems = new DBHelperItems(this);

        //Set data to list view
//        try {
//            updateViews();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        //save list
        saveListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info form views

                //list object
               // ListModel listToAdd = new ListModel(-1,listName.getText().toString(), newList);   ///////////////////////////////////////////////////


                //add to db
                boolean status = false;

//                try {
//                    status = myDbHelper.addListToDb(listToAdd);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                //control
                Toast.makeText(CreateListActivity.this, "Status: " + status, Toast.LENGTH_SHORT).show();

                //boolean status = false;


//                try {
//                    myDbHelper.addListToDb(listToAdd);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


//                try {
//                    status = myDbHelper.addListToDb(listToAdd);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


                try {
                    updateViews();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


//        //save list
//        saveListBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //info form views
//
//                //student object
//                ListModel listToAdd = new ListModel(-1,listName.getText().toString(),items);
//
//                //add to db
//                boolean status = false;
//
//                try {
//                    status = myDbHelper.addListToDb(listToAdd);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                //control
//                Toast.makeText(CreateListActivity.this, "Status: " + status, Toast.LENGTH_SHORT).show();
//
////                try {
////                    updateViews();
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
//            }
//        });

        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean status = myDbHelper.deleteList((StudentModal) parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this, "Deleted: " + status, Toast.LENGTH_SHORT).show();
                updateViews();
            }
        });

         */



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
        switch (item.getItemId()) {
            case R.id.delete_item:
                adapter.remove(adapter.getItem(info.position));
                return true;
            case R.id.edit_item:
                String itemValue = (String) adapter.getItem(info.position); //value to edit
                editSelectedItem(itemValue);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void editSelectedItem(String itemValue) {
        itemToAddView.setText(itemValue); //populate editText with selected item
        adapter.remove(itemValue);     //remove item from current list, waiting for edited version
        itemToAddView.setSelection(itemToAddView.getText().length());   //set focus at end of text
        //force keyboard to be visible
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
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

    private void updateViews() throws JSONException {
        adapter = new ArrayAdapter<ListModel>(this, android.R.layout.simple_list_item_1, myDbHelper.getAllLists());
        listView.setAdapter(adapter);
    }
}



//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        // TODO Auto-generated method stub
//        super.onSaveInstanceState(outState);
//
//        String itemEntered, new_list;
//
//        itemEntered = itemToAddView.getText().toString();
//        outState.putString("itemEntered", itemEntered);
//        outState.putStringArrayList("listViewItems",newList);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        newList = savedInstanceState.getStringArrayList("listViewItems");
//        adapter = new ArrayAdapter(CreateListActivity.this, android.R.layout.simple_list_item_1, newList);
//        listView.setAdapter(adapter);
//    }





















