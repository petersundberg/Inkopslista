package com.example.inkpslista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button createListBtn;
    private Button showLists;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListBtn = findViewById(R.id.createListBtn);
        showLists = findViewById(R.id.showLists);

        createListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //skapa dagens datum och tid, och skicka det till activity'n
                //String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());

//                LocalDate date = LocalDate.now();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//                String text = date.format(formatter);
//                LocalDate parsedDate = LocalDate.parse(text, formatter);
                //DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
//                Date currentDate = new Date();
//                String myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new java.util.Date());
//                String todayNow = "Inköpslista " + myFormat;

//                LocalDateTime nowDate = LocalDateTime.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//                LocalDateTime nowTime = LocalDateTime.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),12,24);
//                String sweTime = myOwnTime2.format(DateTimeFormatter.ofPattern("eeee dd MMMM hh:mm"));

//                String currentDate = new SimpleDateFormat("yy-MM-dd", Locale.getDefault()).format(new Date());
//                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                String currentDate = new SimpleDateFormat("yy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("EEE, HH:mm", Locale.getDefault()).format(new Date());
                String nameTime = "Inköpslista " + currentDate + ", " + currentTime;

                //https://stackoverflow.com/questions/15927794/parsing-long-date-in-android-with-simpledateformat



//            DENNA:    String currentTime = new SimpleDateFormat("yy-MM-dd hh:mm", Locale.getDefault()).format(new Date());
//            DENNA:    String nameTime = "Inköpslista " + currentTime;

//--------------------------------------
                Intent i = new Intent(MainActivity.this, CreateListActivity.class);
                i.putExtra("dateTime", nameTime);
                startActivity(i);
            }
        });

        showLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //skapa dagens datum och tid, och skicka det till activity'n
                //String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
                //String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
                //String nameTime = "Inköpslista " + currentTime;

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Add ...", Toast.LENGTH_SHORT).show();
                //function to perform here
                return(true);
            case R.id.reset:
                Toast.makeText(this, "Reset app ...", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Yoy want to know sometinh About the app ...", Toast.LENGTH_SHORT).show();
    }



}
