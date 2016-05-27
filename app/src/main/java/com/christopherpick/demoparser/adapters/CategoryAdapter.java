package com.christopherpick.demoparser.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.christopherpick.demoparser.R;
import com.christopherpick.demoparser.models.Category;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private List<Category> categories;
    private OnItemClickListener onItemClickListener;

    // Pass in the contact array into the constructor
    public CategoryAdapter(List<Category> categories, OnItemClickListener onItemClickListener) {
        this.categories = categories;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.category_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (categories != null) {
            holder.bind(categories.get(position), onItemClickListener);
        }
    }

    @Override
    public int getItemCount() {
        if (categories != null) {
            return categories.size();
        } else {
            return 0;
        }
    }

    public void swap(List<Category> categories) {
        if (this.categories != null) {
            this.categories.clear();
            this.categories.addAll(categories);
        } else {
            this.categories = categories; // FIXME: see if this reference works
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Category category);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView categoryIcon;
        public TextView categoryName;


        public ViewHolder(View itemView) {
            super(itemView);
            categoryIcon = (ImageView) itemView.findViewById(R.id.category_icon);
            categoryName = (TextView) itemView.findViewById(R.id.category_name);
        }

        public void bind(final Category category, final OnItemClickListener onItemClickListener) {

            categoryName.setText(category.getCategoryName());

            Picasso.with(categoryName.getContext())
                    .load(category.getCategoryIcon())
                    .fit()
                    .centerInside()
                    .into(categoryIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(category);
                }
            });
        }

    }

}
