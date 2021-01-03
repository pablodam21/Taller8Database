package com.pablo.u4t8database;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pablo.u4t8database.data.TodoListDBManager;
import com.pablo.u4t8database.model.Task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener{

    private RecyclerView rvTodoList;

    private TodoListDBManager todoListDBManager;

    private MyAdapter myAdapter;

    private TextView tvId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoListDBManager = new TodoListDBManager(this);
        myAdapter = new MyAdapter(todoListDBManager,this);

        setUI();
    }

    private void setUI(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddTaskActivity.class));
            }
        });
        tvId = findViewById(R.id.tvId);

        rvTodoList = findViewById(R.id.rvTodoList);
        rvTodoList.setHasFixedSize(true);
        rvTodoList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvTodoList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rvTodoList.setAdapter(myAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        myAdapter.getData();
    }

    @Override
    protected void onDestroy() {
        todoListDBManager.close();

        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(Task task) {
        Intent intent = new Intent(getApplicationContext(),AddTaskActivity.class);
        intent.putExtra("TaskId", task);
        startActivity(intent);
    }
}