package com.example.inkpslista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DBHelperItems extends SQLiteOpenHelper {

    public static final String LIST_NAME_TABLE = "LIST_NAME_TABLE";
    public static final String COL_LIST_NAME = "LIST_NAME";
    private static final String COL_GROUP_ID = "GROUP_ID";

    private static final String ITEMS_TABLE = "ITEMS_TABLE";
    private static final String COL_GROUP_ITEM_ID = "COL_GROUP_ITEM_ID";
    private static final String COL_ITEM_ID = "COL_ITEM_ID";
    private static final String COL_ITEM = "COL_ITEM";



    public DBHelperItems(@Nullable Context context) {
        super(context, "items.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //Original code:    String create_table_query = "CREATE TABLE " + LIST_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIST_NAME + " TEXT," + COL_ITEMS + " TEXT)";
//------------------------------------
        //create table containing only list name
        //String create_table_queryLISTNAME = "CREATE TABLE " + LIST_NAME_TABLE + " (" + COL_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIST_NAME + " TEXT)";


//        //update table containing only list name
//        String update_table_queryLISTNAME = "UPDATE TABLE " + LIST_NAME_TABLE + " (" + COL_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIST_NAME + " TEXT)";
//        //create table containing each lists items
//        String update_table_queryITEMS = "UPDATE TABLE " + ITEMS_TABLE + " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ITEM + " TEXT, " + COL_GROUP_ID + ", FOREIGN KEY (COL_GROUP_ID) REFERENCES LIST_NAME_TABLE (COL_GROUP_ID))";

//-------------------------------------

//        String create_table_queryLISTNAME = "CREATE TABLE " + LIST_NAME_TABLE + " (" + COL_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIST_NAME + " TEXT)";
//        db.execSQL(create_table_queryLISTNAME);

//skapar table:
//        String create_table_queryITEMS = "CREATE TABLE " + ITEMS_TABLE + " (" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ITEM + " TEXT)";
//        db.execSQL(create_table_queryITEMS);

//        db.execSQL("create table "+TABLE_WORKDETAILS+"(ID INTEGER PRIMARY KEY , Project TEXT, WorkDescription TEXT, Per Text, TimeIn DATETIME, TimeOut DATETIME,TotalHours DATETIME, TableInfo_id INTEGER, FOREIGN KEY(TableInfo_id)REFERENCES TABLE_INFO(ID)");
//        db.execSQL("create table "+TABLE_WORKDETAILS+"(ID INTEGER PRIMARY KEY , Project TEXT, WorkDescription TEXT, Per Text, TimeIn DATETIME, TimeOut DATETIME,TotalHours DATETIME," +
//                " TableInfo_id INTEGER, FOREIGN KEY(TableInfo_id)REFERENCES TABLE_INFO(ID))");

        //gör nåt i alla fall:
//        String create_table_queryITEMS = " CREATE TABLE " + "ITEMS_TABLE" + "(COL_ITEM_ID INTEGER , COL_ITEM TEXT, COL_GROUP_ID INTEGER, FOREIGN KEY(COL_GROUP_ID) REFERENCES LIST_NAME_TABLE(COL_GROUP_ID))";
//        db.execSQL(create_table_queryITEMS);


        //String create_table_queryITEMS = " CREATE TABLE " + "ITEMS_TABLE" + "(COL_ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT , COL_ITEM TEXT, COL_GROUP_ID INTEGER, FOREIGN KEY(COL_GROUP_ID) REFERENCES LIST_NAME_TABLE(COL_GROUP_ID))";
        String create_table_queryITEMS = " CREATE TABLE " + "ITEMS_TABLE" + "(" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_ITEM + " TEXT , " + COL_GROUP_ID + " INTEGER , " + "FOREIGN KEY(COL_GROUP_ID) REFERENCES LIST_NAME_TABLE(COL_GROUP_ID))";
        db.execSQL(create_table_queryITEMS);
      //String create_table_queryITEMS = "CREATE TABLE " + ITEMS_TABLE + " (" + COL_GROUP_ITEM_ID + " INTEGER, FOREIGN KEY REFERENCES LIST_NAME_TABLE (COL_GROUP_ID), " + COL_ITEM + " TEXT)";


//puts values in both tables, but not group id:
//        String create_table_queryITEMS = "CREATE TABLE " + ITEMS_TABLE + " (" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ITEM + " TEXT, " +
//                " FOREIGN KEY (COL_GROUP_ID) REFERENCES LIST_NAME_TABLE (COL_GROUP_ID))";
//        db.execSQL(create_table_queryITEMS);



        //String create_table_queryITEMS = "CREATE TABLE " + ITEMS_TABLE + " (" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +         COL_ITEM + " TEXT, " +           " FOREIGN KEY (COL_GROUP_ID) REFERENCES LIST_NAME_TABLE (COL_GROUP_ID))";

        //String create_table_queryITEMS = "CREATE TABLE " + ITEMS_TABLE + " (" + COL_GROUP_ITEM_ID + " INTEGER, FOREIGN KEY REFERENCES LIST_NAME_TABLE (COL_GROUP_ID), " + COL_ITEM + " TEXT)";
        //db.execSQL(create_table_queryITEMS);

//        String create_table_queryITEMS = "CREATE TABLE " + ITEMS_TABLE + " (" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ITEM + " TEXT, " + " FOREIGN KEY (COL_GROUP_ID) REFERENCES LIST_NAME_TABLE (COL_GROUP_ID))";
//        db.execSQL(create_table_queryITEMS);

    }



    public void insertItemsInList(String db){

        //create table containing each lists items
        //String create_table_queryITEMS = "CREATE TABLE " + ITEMS_TABLE + " (" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ITEM + " TEXT, " + " FOREIGN KEY (COL_GROUP_ID) REFERENCES LIST_NAME_TABLE (COL_GROUP_ID))";

        //String insert_table_queryITEMS = "INSERT INTO " + ITEMS_TABLE + " + COL_ITEM + " TEXT, " + " COL_GROUP_ID) VALUES + "'" + itemToAddView.getText().toString() + "'" + );


        //db.execSQL(insert_table_queryITEMS);


    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public boolean addListnameToDb(Listname listToAdd) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COL_LIST_NAME, listToAdd.getListName());   //cv.put(LIST_NAME_TABLE, listToAdd.getListName());

        long insertStatus = db.insert(LIST_NAME_TABLE, null, cv);

//            JSONObject json = new JSONObject();
//            json.put("newListToJSON", new JSONArray(listToAdd.getItemsList()));
//              cv.put(COL_ITEM, json.toString());


        if (insertStatus == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }


    public boolean addItemToDb(ListModel itemToAdd) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COL_ITEM, itemToAdd.getItem());  //cv.put(LIST_NAME_TABLE, listToAdd.getListName());

        long insertStatus = db.insert(ITEMS_TABLE, null, cv);

        if (insertStatus == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }



    //            JSONObject json = new JSONObject();
//            json.put("newListToJSON", new JSONArray(listToAdd.getItemsList()));
//              cv.put(COL_ITEM, json.toString());


    //insertStatus = db.insert(ITEMS_TABLE, null, cv);

//            for (int i = 0; i < jsonArrayList.length(); i++){
//            cv.put(COL_ITEM, jsonArrayList.indexOf(i));
//            insertStatus = db.insert(ITEMS_TABLE, null, cv);
//            }

//        for (int i = 0; i < jsonArrayList.length(); i++){
//            cv.put(COL_ITEM, listToAdd.getItemsList().indexOf(i));
//            insertStatus = db.insert(ITEMS_TABLE, null, cv);
//        }

//            for (int i = 0; i < listToAdd.getItemsList().size(); i++){
//            cv.put(COL_ITEM, listToAdd.getItemsList().indexOf(i));
//            insertStatus = db.insert(ITEMS_TABLE, null, cv);
//            }

    //JSON
//        JSONObject json = new JSONObject();
//        json.put("newList", new JSONArray(listToAdd.getItemsList()));

    //cv.put(COL_ITEMS,json.toString());

    //long insertStatus = db.insert(LIST_NAME_TABLE, null, cv);







    public List<ListModel> getAllLists() throws JSONException {

        List<ListModel> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_student_query = "SELECT * FROM " + LIST_NAME_TABLE;

        Cursor cursor = db.rawQuery(get_all_student_query, null);

        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> items = new ArrayList<>();
                int id = cursor.getInt(0);
                String list_name = cursor.getString(1);
                String list_items = cursor.getString(2);

                //Decode
                JSONObject json = new JSONObject(list_items);
                JSONArray raw_items = json.optJSONArray("items");

                //Create Arraylist from jsonArray
                if(raw_items != null){
                    for(int i=0; i< raw_items.length();i++){
                        items.add(raw_items.getString(i));
                    }
                }

//                ListModel tempList = new ListModel(id,list_name,item);
//
//                result.add(tempList);

            } while (cursor.moveToNext());
        } else {
            //manage failure
        }

        cursor.close();
        return result;
    }


    public boolean deleteList(ListModel studentToRemove){
        //SQLiteDatabase db = this.getWritableDatabase();
        //String delete_list_query = "DELETE FROM " + LIST_TABLE + " WHERE " + COL_ID + " = " + studentToRemove.getId();
        //Cursor cursor = db.rawQuery(delete_list_query, null);

//        if(cursor.moveToFirst()){
//            db.close();
//            cursor.close();
//            return false;
//        }else{
//            db.close();
//            cursor.close();
//            return true;
//        }
        return true;
    }




}
