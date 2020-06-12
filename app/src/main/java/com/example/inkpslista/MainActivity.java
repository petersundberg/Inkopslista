package com.example.inkpslista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String LIST_NAME_TABLE = "LIST_NAME_TABLE";
    public static final String COL_LIST_NAME = "LIST_NAME";
    private static final String COL_GROUP_ID = "GROUP_ID";

    private Button createListBtn;
    private Button showLists;
    private DBHelper myDbHelper;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListBtn = findViewById(R.id.createListBtn);
        showLists = findViewById(R.id.showLists);

        //adapter = new ArrayAdapter<String>(CreateListActivity.this, android.R.layout.simple_list_item_1, newList);
        DBHelper myDbHelper = new DBHelper(this);

        //create onClickListener to create new List
        createListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentDate = new SimpleDateFormat("yy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                String nameTime = "Handla " + currentDate + ", " + currentTime;

//                //goto new activity to populate new List
//                Intent i = new Intent(MainActivity.this, CreateListActivity.class);
//                i.putExtra("dateTime", nameTime);
//                startActivity(i);

                //Create database "Listname"
                //declare empty list
                Listname listName;
                //Reference database helper
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                //Init listname
                listName = new Listname(-1, nameTime);

                //Add listname (boolean to check for errors)
                boolean addedListname = false;
                try {
                    addedListname = dbHelper.addListnameToDb(listName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "ADDED LISTNAME: " + addedListname, Toast.LENGTH_SHORT).show();





//                //Add listname (boolean to check for errors)
//                boolean addedListname = false;
//                try {
//                    addedListname = dbHelper.addListnameToDb(listName);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


                //goto new activity to populate new List
                Intent i = new Intent(MainActivity.this, CreateListActivity.class);
                i.putExtra("dateTime", nameTime);
                startActivity(i);



            }
        });

        //create onClickListener on button to goto activity containing all saved lists
        showLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AllListsActivity.class);
                //i.putExtra("dateTime", nameTime);
                startActivity(i);
            }
        });
    }

    //ActionBar menu in Main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu_main, menu);
        return true;
    }
    // create action for each item in ActionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Add ...", Toast.LENGTH_SHORT).show();
                //function to perform here
                return(true);
            case R.id.about:
                aboutApp();
                return(true);
            case R.id.exit:
                Toast.makeText(this, "Exiting app ...", Toast.LENGTH_SHORT).show();
                finish();
                //return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    public void aboutApp(){
        Intent intentAboutApp = new Intent(MainActivity.this, AboutAppActivity.class);
        startActivity(intentAboutApp);


    }


}
