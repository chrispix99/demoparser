package com.christopherpick.demoparser.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Task implements Serializable {

    @SerializedName("task_name")
    private String taskName;
    @SerializedName("icon")
    private String icon;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("earning")
    private Double earning;

    /**
     * @return The taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName The task_name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return The enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled The enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return The earning
     */
    public Double getEarning() {
        return earning;
    }

    /**
     * @param earning The earning
     */
    public void setEarning(Double earning) {
        this.earning = earning;
    }

}