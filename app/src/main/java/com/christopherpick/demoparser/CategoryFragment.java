package com.christopherpick.demoparser;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.christopherpick.demoparser.adapters.CategoryAdapter;
import com.christopherpick.demoparser.models.Category;
import com.christopherpick.demoparser.rest.DemoRestServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryAdapter.OnItemClickListener {

    private static final String TAG = CategoryFragment.class.getSimpleName();
    private static final String CATEGORIES_KEY = "categories_key";
    private CategoryAdapter adapter;
    private List<Category> categories = null;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.category_fragment, container, false);
        RecyclerView categoryList = (RecyclerView) view.findViewById(R.id.category_list);

        // Create adapter and lazy load data, and then update adapter.
        adapter = new CategoryAdapter(categories, this);
        categoryList.setAdapter(adapter);
        categoryList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if ((savedInstanceState != null) && savedInstanceState.containsKey(CATEGORIES_KEY)) {
            updateUi((List<Category>) savedInstanceState.getSerializable(CATEGORIES_KEY));
        } else {
            if (categories == null) {
                DemoRestServer restAdapter = getDemoRestServer(getActivity(), getString(R.string.root_url));
                restAdapter.getDemoData(new Callback<List<Category>>() {
                    @Override
                    public void success(List<Category> categories, Response response) {
                        updateUi(categories);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(), R.string.something_went_wrong, Toast.LENGTH_LONG).show();
                        Log.e(TAG, "retrofit error: " + error.getMessage());
                    }
                });
            }
        }
    }

    private void updateUi(List<Category> categories) {
        this.categories = categories;
        adapter.swap(categories);
    }

    /**
     * Returns the retrofit rest server for this particular demo. Normally would have this static
     * method in a class, such as a sync service / adapter, but for this demo, just put it in the same
     * class as the fragment for simplicity stake.  I am passing the server in from a string resource to show
     * that with a multi variant app, you could have different endpoints defined in string resources. (Could have placed
     * it directly in the method, but calling it from outside illustrates how it could work being passed into a module,
     * the module only has one build variant, but picks up it's configuration from the calling application.
     * @param context
     * @param server
     * @return
     */
    private static DemoRestServer getDemoRestServer(Context context, String server) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        OkHttpClient client = new OkHttpClient();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(server)
                .build();

        return restAdapter.create(DemoRestServer.class);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Casting, as ArrayList implementation of list is serializable // Could have cast as (ArrayList<Category>)
        if (categories != null) {
            outState.putSerializable(CATEGORIES_KEY, (Serializable) categories);
        }
    }

    @Override
    public void onItemClick(Category category) {
        TaskFragment newFragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putSerializable(TaskFragment.CATEGORY_KEY, category);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}


