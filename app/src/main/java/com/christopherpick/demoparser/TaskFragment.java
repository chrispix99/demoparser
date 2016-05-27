package com.christopherpick.demoparser;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.christopherpick.demoparser.adapters.TaskAdapter;
import com.christopherpick.demoparser.models.Category;
import com.christopherpick.demoparser.models.Task;

public class TaskFragment extends Fragment implements TaskAdapter.OnItemClickListener {

    public static final String CATEGORY_KEY = "task_key";
    private static final String TAG = TaskFragment.class.getSimpleName();
    private TaskAdapter adapter;
    private Category category;
    private Toast toast;

    public TaskFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.task_fragment, container, false);
        RecyclerView taskList = (RecyclerView) view.findViewById(R.id.task_list);

        // It is quite possible that replacing this fragment later will only destroy the view, and
        // the category data will be fine.
        if (category == null) {
            if (savedInstanceState != null) {
                category = (Category) savedInstanceState.getSerializable(CATEGORY_KEY);
            } else {
                Bundle bundle = getArguments();
                if (bundle.containsKey(CATEGORY_KEY)) {
                    category = (Category) bundle.getSerializable(CATEGORY_KEY);
                }
            }
        }

        adapter = new TaskAdapter(category.getTasks(), this);
        taskList.setAdapter(adapter);
        taskList.setLayoutManager(new LinearLayoutManager(view.getContext()));


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CATEGORY_KEY, category);
    }

    @Override
    public void onItemClick(Task task) {
        // This should really be in a resource, but just placing this here now
        // as a way to verify clicking on the task works.
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getActivity(), "Task " + task.getTaskName() + " clicked", Toast.LENGTH_SHORT);
        toast.show();
    }
}
