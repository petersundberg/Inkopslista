package com.example.inkpslista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String LIST_TABLE = "LIST_TABLE";
    public static final String COL_LIST_NAME = "LIST_NAME";
    public static final String COL_ID = "ID";
    public static final String COL_ITEMS = "ITEMS";

    public DBHelper(@Nullable Context context) {
        super(context, "list.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + LIST_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIST_NAME + " TEXT," + COL_ITEMS + " TEXT)";
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addListToDb(ListModel listToAdd) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_LIST_NAME, listToAdd.getListName());

        //JSON
        JSONObject json = new JSONObject();
        json.put("items", new JSONArray(listToAdd.getItemsList()));

        cv.put(COL_ITEMS,json.toString());

        long insertStatus = db.insert(LIST_TABLE, null, cv);

        if (insertStatus == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public List<ListModel> getAllLists() throws JSONException {

        List<ListModel> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_student_query = "SELECT * FROM " + LIST_TABLE;

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

                ListModel tempList = new ListModel(id,list_name,items);

                result.add(tempList);

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
