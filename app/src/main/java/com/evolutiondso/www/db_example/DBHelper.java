package com.evolutiondso.www.db_example;

/**
 * Created by Albrtx on 28/10/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by evin on 4/11/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "teamsDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TEAM = "TEAMS";
    public static final String KEY_TEAM_ID = "ID";
    public static final String KEY_TEAM_NAME = "NAME";
    public static final String KEY_GOALS = "GOALS";
    public static final String KEY_LOGO = "LOGO";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_TEAM +
                "(" +
                KEY_TEAM_ID + " INTEGER PRIMARY KEY," +
                KEY_TEAM_NAME + " TEXT," +
                KEY_GOALS + " TEXT," +
                KEY_LOGO + " TEXT)";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        onCreate(db);
    }
}
