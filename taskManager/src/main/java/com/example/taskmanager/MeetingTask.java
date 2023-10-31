package com.example.taskmanager;

import java.util.Date;

public class MeetingTask implements Task{
    private String taskName;

    @Override
    public String toString() {
        return "MeetingTask: " + taskName + '\'';
    }

    private String taskDescription;

    @Override
    public Date getDeadline() {
        return deadline;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public String getDescription() {
        return taskDescription;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    private boolean completed;
    private Priority priority;
    private Date deadline;
    @Override
    public void setTask(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    @Override
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void markAsComplete() {
        completed = true;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setDeadline(Date date) {
        this.deadline = date;
    }
}
