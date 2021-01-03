package com.pablo.u4t8database;

import android.annotation.SuppressLint;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pablo.u4t8database.data.TodoListDBManager;
import com.pablo.u4t8database.model.Task;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private  OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClickListener(Task task);
    }

    private TodoListDBManager tLDBManager;
    private ArrayList<Task> myTaskList;

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout itemTask;
        TextView tvId;
        TextView tvTodo;
        TextView tvToAccomplish;
        TextView tvIdDescription;
        TextView tvStatus;
        TextView tvPriority;

        public MyViewHolder(View view){
            super(view);

            this.itemTask = view.findViewById(R.id.itemLayout);
            this.tvId = view.findViewById(R.id.tvId);
            this.tvTodo = view.findViewById(R.id.tvTodo);
            this.tvToAccomplish = view.findViewById(R.id.tvToAccomplish);
            this.tvIdDescription = view.findViewById(R.id.tvDescription);
            this.tvPriority = view.findViewById(R.id.tvPriority);
            this.tvStatus = view.findViewById(R.id.tvStatus);

        }

        @SuppressLint("SetTextI18n")
        public void bind(final Task task, final OnItemClickListener listener){
            this.tvId.setText(String.valueOf(task.getId()));
            this.tvTodo.setText(task.getTodo());
            this.tvToAccomplish.setText(task.getTo_accomplish());
            this.tvIdDescription.setText(task.getDescription());
            this.tvPriority.setText("Priority: "+task.getPriority());
            this.tvStatus.setText(task.getStatus());
            this.itemTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(task);
                }
            });
        }

    }

    public MyAdapter(TodoListDBManager tLDBManager, OnItemClickListener listener) {
        this.tLDBManager = tLDBManager;
        this.listener = listener;
    }

    public void getData(){
        this.myTaskList =  tLDBManager.getTasks();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent, false);

        return new MyViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.bind(myTaskList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return myTaskList.size();
    }
}
