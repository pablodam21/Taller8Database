package com.pablo.u4t8database.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.pablo.u4t8database.model.Task;

import java.util.ArrayList;

public class TodoListDBManager {

    private ArrayList<Task> tasksList;

    private TodoListDBHelper todoListDBHelper;

    public TodoListDBManager(Context context){
        todoListDBHelper = TodoListDBHelper.getInstance(context);
    }

    public void insert(String todo, String when, String description, String priority, String status){
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase != null){
            ContentValues contentValue = new ContentValues();

            contentValue.put(TodoListDBContract.Tasks.TODO,todo);
            contentValue.put(TodoListDBContract.Tasks.TO_ACCOMPLISH,when);
            contentValue.put(TodoListDBContract.Tasks.DESCRIPTION,description);
            contentValue.put(TodoListDBContract.Tasks.PRIORITY,priority);
            contentValue.put(TodoListDBContract.Tasks.STATUS,status);

            sqLiteDatabase.insert(TodoListDBContract.Tasks.TABLE_NAME,null,contentValue);
        }
    }

    public void delete(int id){

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();
        if (sqLiteDatabase != null){
            sqLiteDatabase.delete(TodoListDBContract.Tasks.TABLE_NAME,TodoListDBContract.Tasks.ID + " = " +id,null);
        }

    }

    public void update(String todo, String when, String description, String priority, String status, int taskId){

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();
        if (sqLiteDatabase != null){
            ContentValues contentValue = new ContentValues();

            contentValue.put(TodoListDBContract.Tasks.TODO,todo);
            contentValue.put(TodoListDBContract.Tasks.TO_ACCOMPLISH,when);
            contentValue.put(TodoListDBContract.Tasks.DESCRIPTION,description);
            contentValue.put(TodoListDBContract.Tasks.PRIORITY,priority);
            contentValue.put(TodoListDBContract.Tasks.STATUS,status);

            sqLiteDatabase.update(TodoListDBContract.Tasks.TABLE_NAME,contentValue,"id = " + taskId, null);
        }
    }



    public ArrayList<Task> getTasks(){
        tasksList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getReadableDatabase();

        if (sqLiteDatabase != null){
            String[] projection = new String[]{TodoListDBContract.Tasks.ID,
                    TodoListDBContract.Tasks.TODO,
                    TodoListDBContract.Tasks.TO_ACCOMPLISH,
                    TodoListDBContract.Tasks.DESCRIPTION,
                    TodoListDBContract.Tasks.PRIORITY,
                    TodoListDBContract.Tasks.STATUS};

            Cursor cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null);
            if (cursorTodoList != null){
                int idIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.ID);
                int todoIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TODO);
                int to_AccomplishIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TO_ACCOMPLISH);
                int descriptionIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.DESCRIPTION);
                int priorityIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.PRIORITY);
                int statusIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.STATUS);

                while (cursorTodoList.moveToNext()){
                    Task task = new Task(
                            cursorTodoList.getInt(idIndex),
                            cursorTodoList.getString(todoIndex),
                            cursorTodoList.getString(to_AccomplishIndex),
                            cursorTodoList.getString(descriptionIndex),
                            cursorTodoList.getString(priorityIndex),
                            cursorTodoList.getString(statusIndex));
                    tasksList.add(task);
                }
                cursorTodoList.close();
            }
        }
        return tasksList;
    }

    public void close(){
        todoListDBHelper.close();
    }
}
