package com.evolutiondso.www.db_example;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivityTAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void UploadInfo(View view) {

        EditText mEditText1 = (EditText) findViewById(R.id.a_main_editText_TEAM);
        String TEAM_NAME = mEditText1.getText().toString();
        EditText mEditText2 = (EditText) findViewById(R.id.a_main_editText_GOALS);
        String TEAM_GOALS = mEditText2.getText().toString();
        EditText mEditText3 = (EditText) findViewById(R.id.a_main_editText_LOGO);
        String TEAM_LOGO = mEditText3.getText().toString();


        DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(DBHelper.KEY_TEAM_NAME, String.valueOf(TEAM_NAME));
            values.put(DBHelper.KEY_GOALS, String.valueOf(TEAM_GOALS));
            values.put(DBHelper.KEY_LOGO, String.valueOf(TEAM_LOGO));

            db.insertOrThrow(DBHelper.TABLE_TEAM, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    public void ReadInfo(View view) {
        DBHelper usersDatabaseHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = usersDatabaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DBHelper.TABLE_TEAM,
                null,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        do {
            int id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_TEAM_ID));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TEAM_NAME));
            String goals = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_GOALS));
            String logo = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_LOGO));

            Log.d(TAG, "readMagic: " + id + " " + name + " " + goals + " " + logo);
        }while (cursor.moveToNext());

        cursor.close();
    }


}
