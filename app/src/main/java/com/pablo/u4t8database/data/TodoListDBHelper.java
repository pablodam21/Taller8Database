package com.pablo.u4t8database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoListDBHelper extends SQLiteOpenHelper {
    private static TodoListDBHelper instanceDBHelper;

    public static synchronized TodoListDBHelper getInstance(Context context){
        if (instanceDBHelper == null){
            instanceDBHelper = new TodoListDBHelper(context.getApplicationContext());
        }
        return instanceDBHelper;
    }

    public TodoListDBHelper(Context context) {
        super(context, TodoListDBContract.DB_NAME, null, TodoListDBContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoListDBContract.Tasks.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(TodoListDBContract.Tasks.DELETE_TABLE);
        onCreate(db);
    }
}
