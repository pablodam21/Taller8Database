package com.pablo.u4t8database.data;

import com.pablo.u4t8database.model.Task;

public class TodoListDBContract {

    public static final String DB_NAME = "TODOLIST.DB";

    public static final int DB_VERSION = 1;

    private TodoListDBContract(){
    }

    public static class Tasks{
        public static final String TABLE_NAME = "TASKS";

        public static final String ID = "id";
        public static final String TODO = "todo";
        public static final String TO_ACCOMPLISH = "to_accomplish";
        public static final String DESCRIPTION = "description";
        public static final String PRIORITY = "priority";
        public static final String STATUS = "status";


        public static final String CREATE_TABLE = "CREATE TABLE " + Tasks.TABLE_NAME
                + " ("
                + Tasks.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Tasks.TODO + " TEXT NOT NULL, "
                + Tasks.TO_ACCOMPLISH + " TEXT, "
                + Tasks.DESCRIPTION + " TEXT, "
                + Tasks.PRIORITY + " TEXT, "
                + Tasks.STATUS + " TEXT"
                + ");";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + Tasks.TABLE_NAME;
    }
}
