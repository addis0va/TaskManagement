package com.example.taskmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.jar.Attributes;

public class HelloController {
    @FXML
    private RadioButton homework;
    @FXML
    private RadioButton meeting;
    @FXML
    private RadioButton shopping;
    @FXML
    private TextField input_name;
    @FXML
    private TextField input_desc;
    @FXML
    private RadioButton low;
    @FXML
    private RadioButton medium;
    @FXML
    private  RadioButton high;
    @FXML
    private DatePicker date;
    @FXML
    private CheckBox completed;

    @FXML
    private ListView<Task> listView;
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    public void initialize() {
        listView.setItems(tasks);
    }
    @FXML
    private Label selectedText;


    @FXML
    protected void addTask() {
        Task newTask = null;
        if (homework.isSelected()) {
            newTask = new HomeworkTask();
        } else if (meeting.isSelected()) {
            newTask = new MeetingTask();
        } else if (shopping.isSelected()) {
            newTask = new ShoppingTask();
        }

        if (newTask != null) {
            newTask.setTaskName(input_name.getText());
            newTask.setTaskDescription(input_desc.getText());
            if (low.isSelected()){
                newTask.setPriority(Priority.LOW);
            } else if (medium.isSelected()) {
                newTask.setPriority(Priority.MEDIUM);
            } else if (high.isSelected()) {
                newTask.setPriority(Priority.HIGH);
            }

            LocalDate selectedDate = date.getValue();
            if (selectedDate != null) {
                Instant instant = Instant.from(selectedDate.atStartOfDay(ZoneId.systemDefault()));
                Date deadlineDate = Date.from(instant);
                newTask.setDeadline(deadlineDate);
            }

            tasks.add(newTask);
            input_name.clear();
            input_desc.clear();
            homework.setSelected(false);
            meeting.setSelected(false);
            shopping.setSelected(false);
            low.setSelected(false);
            medium.setSelected(false);
            high.setSelected(false);
            date.setValue(null);
            completed.setSelected(false);
        }
    }
    @FXML
    protected void onListClicked(){
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = tasks.get(selectedIndex);
            selectedText.setText(selectedTask.toString());
            if (selectedTask instanceof HomeworkTask) {
                shopping.setSelected(true);
            } else if (selectedTask instanceof MeetingTask) {
                meeting.setSelected(true);
            } else if (selectedTask instanceof ShoppingTask) {
                shopping.setSelected(true);
            }

            Priority priority = selectedTask.getPriority();
            if (priority == Priority.LOW) {
                low.setSelected(true);
            } else if (priority == Priority.MEDIUM) {
                medium.setSelected(true);
            } else if (priority == Priority.HIGH) {
                high.setSelected(true);
            }
            input_name.setText("" + selectedTask.getTaskName());
            input_desc.setText("" + selectedTask.getDescription());
            Date deadline = selectedTask.getDeadline();
            if (deadline != null) {
                Instant instant = deadline.toInstant();
                LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                date.setValue(localDate);
            }

        } else {
            selectedText.setText("");
        }
    }
    @FXML
    protected void onTaskCompleted() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = tasks.get(selectedIndex);
            selectedTask.markAsComplete();
            tasks.remove(selectedIndex);
            input_name.clear();
            input_desc.clear();

            homework.setSelected(false);
            meeting.setSelected(false);
            shopping.setSelected(false);
            low.setSelected(false);
            medium.setSelected(false);
            high.setSelected(false);
            date.setValue(null);

            selectedText.setText("Task removed");
            completed.setSelected(false);
        } else {
            selectedText.setText("No task selected");
            completed.setSelected(false);
        }
    }
}
