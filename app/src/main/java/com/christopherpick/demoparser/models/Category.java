package com.christopherpick.demoparser.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("category_icon")
    private String categoryIcon;
    @SerializedName("tasks")
    private List<Task> tasks = new ArrayList<Task>();

    /**
     * @return The categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName The category_name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return The categoryIcon
     */
    public String getCategoryIcon() {
        return categoryIcon;
    }

    /**
     * @param categoryIcon The category_icon
     */
    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    /**
     * @return The tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks The tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}