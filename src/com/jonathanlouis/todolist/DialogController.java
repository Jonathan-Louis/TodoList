package com.jonathanlouis.todolist;

import com.jonathanlouis.todolist.datamodel.TodoData;
import com.jonathanlouis.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.Optional;

public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    //processes new TodoItem dialog box
    public TodoItem processResults(){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();
        //prompts user if a field was left blank
        if(shortDescription.equals("") || details.equals("") || deadlineValue == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error creating new todo item");
            alert.setHeaderText("Field missing information");
            alert.setContentText("Press OK to continue");
            Optional<ButtonType> result = alert.showAndWait();
            return null;
        }
        TodoItem newItem = new TodoItem(shortDescription, details, deadlineValue);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }

    //loads the current TodoItem to edit dialog box
    public void loadEditTodoItem(TodoItem todoItem){
        shortDescriptionField.setText(todoItem.getShortDescription());
        detailsArea.setText(todoItem.getDetails());
        deadlinePicker.setValue(todoItem.getDeadline());
    }

    //processes edit dialog box
    public TodoItem editResults(TodoItem todoItem){

        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();
        //prompts user if a field was left blank
        if(shortDescription.equals("") || details.equals("") || deadlineValue == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error editing todo item");
            alert.setHeaderText("Field missing information");
            alert.setContentText("Press OK to continue");
            Optional<ButtonType> result = alert.showAndWait();
            return null;
        }
        //delete current item and save edited item
        TodoData.getInstance().deleteTodoItem(todoItem);
        TodoItem newItem = new TodoItem(shortDescription, details, deadlineValue);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }

}
