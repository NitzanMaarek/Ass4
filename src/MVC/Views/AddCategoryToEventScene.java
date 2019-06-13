package MVC.Views;

import Entities.User;
import MVC.Controller.Controller;
import MVC.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCategoryToEventScene {

    public boolean isConnected = false;
    public Controller myController;
    private int selectedEventID;
    public MenuButton menubttn_categories = new MenuButton();


    public BorderPane rootPane;

    public Label lbl_currentUser;
    public Button btn_back;
    public Button btn_add;
    public User loggedInUser ;

    public void setAllCategories(){
        List<String> categories = myController.getAllCategories();
        //TODO Remove bottom line and use the one above - test it.
//        List<String> categories = new ArrayList<>(Arrays.asList("Urgent", "Critical", "Normal"));
        for(String category : categories){
            CheckMenuItem itemToAdd = new CheckMenuItem(category);
            menubttn_categories.getItems().add(itemToAdd);
        }

        List<String> eventsCategories = myController.getCategoriesByEventID(this.selectedEventID);
        for(MenuItem menuItem: menubttn_categories.getItems()){
            CheckMenuItem checkMenuItem = (CheckMenuItem)menuItem;
            checkMenuItem.setSelected(false);
            if(eventsCategories.contains(checkMenuItem.getText())){
                checkMenuItem.setSelected(true);
            }
        }
    }



    public void setController(Controller aController){
        if(aController != null){
            this.myController = aController;
        }
    }

    public void setLoggedInLabel(User user) {
        loggedInUser=user;
    }

    public void setLoggedInUser(User loggedInUser){
        this.loggedInUser=loggedInUser;
    }

    public void selectedEvent(int eventID) {
        this.selectedEventID = eventID;
    }

    private List<String> getChosenCategories(){
        List<String> chosenCategories = new ArrayList<>();
        for(MenuItem menuItem: menubttn_categories.getItems()){
            CheckMenuItem checkMenuItem = (CheckMenuItem)menuItem;
            if(checkMenuItem.isSelected()){
                chosenCategories.add(checkMenuItem.getText());
            }
        }
        return chosenCategories;
    }

    public void onAddCategoryClick(ActionEvent actionEvent) {
        List<String> categories = getChosenCategories();
        for(String category: categories){
            myController.insertEventsCateogiresConnections(this.selectedEventID, category);
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane pane = loader.load(getClass().getResource("MainEventsView.fxml").openStream());
            rootPane.getChildren().setAll(pane);
            MainEventsView mainEventsView = loader.getController();
            mainEventsView.setController(myController);
            mainEventsView.setLoggedInUser(loggedInUser);
            mainEventsView.setAllCategories();

            // add user and add events to view
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return to mainEventsView
     * @param actionEvent
     */
    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane pane = loader.load(getClass().getResource("MainEventsView.fxml").openStream());
            rootPane.getChildren().setAll(pane);
            MainEventsView mainEventsView = loader.getController();
            mainEventsView.setController(myController);
            mainEventsView.setLoggedInUser(loggedInUser);
            mainEventsView.setAllCategories();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

