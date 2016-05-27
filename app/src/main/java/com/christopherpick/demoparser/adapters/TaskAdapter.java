package com.christopherpick.demoparser.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.christopherpick.demoparser.R;
import com.christopherpick.demoparser.models.Task;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TaskAdapter  extends  RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    private List<Task> tasks;
    private TaskAdapter.OnItemClickListener onItemClickListener;

    // Pass in the contact array into the constructor
    public TaskAdapter(List<Task> tasks, TaskAdapter.OnItemClickListener onItemClickListener) {
        this.tasks = tasks;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.task_row, parent, false);
        TaskAdapter.ViewHolder viewHolder = new TaskAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {
        if (tasks != null) {
            holder.bind(tasks.get(position), onItemClickListener);
        }
    }

    @Override
    public int getItemCount() {
        if (tasks != null) {
            return tasks.size();
        } else {
            return 0;
        }
    }

    public void swap(List<Task> tasks){
        if (this.tasks != null) {
            this.tasks.clear();
            this.tasks.addAll(tasks);
        } else {
            this.tasks = tasks;
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Task Task);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public ImageView taskIcon;
        public TextView taskName;
        public TextView taskEarnings;


        public ViewHolder(View itemView) {
            super(itemView);
            taskIcon = (ImageView) itemView.findViewById(R.id.task_icon);
            taskName = (TextView) itemView.findViewById(R.id.task_name);
            taskEarnings = (TextView) itemView.findViewById(R.id.task_earnings);
        }

        public void bind(final Task task, final TaskAdapter.OnItemClickListener onItemClickListener) {

            itemView.setEnabled(task.getEnabled());
            itemView.setClickable(task.getEnabled());

            taskName.setText(task.getTaskName());
            taskName.setEnabled(task.getEnabled());

            taskEarnings.setText(String.format( "$%.2f", task.getEarning() ));
            taskEarnings.setEnabled(task.getEnabled());

            Picasso.with(taskName.getContext())
                    .load(task.getIcon())
                    .fit()
                    .centerInside()
                    .into(taskIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(task);
                }
            });
        }

    }

}
