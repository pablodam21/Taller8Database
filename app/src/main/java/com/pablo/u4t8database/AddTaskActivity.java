package com.pablo.u4t8database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pablo.u4t8database.data.TodoListDBManager;
import com.pablo.u4t8database.model.Task;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etTodo;

    private EditText etToAccomplish;

    private EditText etDescription;

    private Spinner spinnerPriority;

    private Spinner spinnerStatus;

    private Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent intentTask = getIntent();

        task = (Task) intentTask.getSerializableExtra("TaskId");
        setUI();
        chargeTask();
    }

    private void setUI(){



        etTodo=findViewById(R.id.etTodo);
        etToAccomplish= findViewById(R.id.etToAccomplish);
        etDescription=findViewById(R.id.etDescription);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        spinnerStatus = findViewById(R.id.spinnerStatus);

        ArrayAdapter<CharSequence> spinnerPriorityAdapter = ArrayAdapter.createFromResource(this,R.array.Priority,android.R.layout.simple_spinner_item);
        spinnerPriority.setAdapter(spinnerPriorityAdapter);

        ArrayAdapter<CharSequence> spinnerStatusAdapter = ArrayAdapter.createFromResource(this, R.array.Status,android.R.layout.simple_spinner_item);
        spinnerStatus.setAdapter(spinnerStatusAdapter);



    }

    private void chargeTask(){
        if (task != null) {
            etTodo.setText(task.getTodo());
            etToAccomplish.setText(task.getTo_accomplish());
            etDescription.setText(task.getDescription());
            spinnerPriority.setPrompt(task.getPriority());
            spinnerStatus.setPrompt(task.getStatus());
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.buttonOk){
            if (etTodo.getText().toString().length()>0){
                if (task == null){
                    TodoListDBManager todoListDBManagerInsert = new TodoListDBManager(this);

                    todoListDBManagerInsert.insert(etTodo.getText().toString(),
                            etToAccomplish.getText().toString(),
                            etDescription.getText().toString(),
                            spinnerPriority.getSelectedItem().toString(),
                            spinnerStatus.getSelectedItem().toString());
                }else{
                    TodoListDBManager todoListDBManagerUpdate = new TodoListDBManager(this);

                    todoListDBManagerUpdate.update(etTodo.getText().toString(),
                            etToAccomplish.getText().toString(),
                            etDescription.getText().toString(),
                            spinnerPriority.getSelectedItem().toString(),
                            spinnerStatus.getSelectedItem().toString(),
                            task.getId());
                }
            }
        }else {
            Toast.makeText(this,R.string.task_data_empty,Toast.LENGTH_LONG).show();
        }

        finish();
    }

    public void onClickCancel(View view) {
        if (view.getId() == R.id.buttonCancel){
            TodoListDBManager todoListDBManagerDelete = new TodoListDBManager(this);
            todoListDBManagerDelete.delete(task.getId());
        }

        finish();
    }
}