package MVC.Views;

import Entities.User;
import MVC.Controller.Controller;
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
        for(String category : categories){
            CheckMenuItem itemToAdd = new CheckMenuItem(category);
            menubttn_categories.getItems().add(itemToAdd);
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
            MainEventsView vacationsView = loader.getController();
            vacationsView.setController(myController);
            vacationsView.setLoggedInUser(loggedInUser);
            vacationsView.setAllCategories();

            // add user and add events to view
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return to vacationsScene
     * @param actionEvent
     */
    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane pane = loader.load(getClass().getResource("MainEventsView.fxml").openStream());
            rootPane.getChildren().setAll(pane);
            MainEventsView vacationsView = loader.getController();
            vacationsView.setController(myController);
            vacationsView.setLoggedInUser(loggedInUser);
            vacationsView.setAllCategories();
//            vacationsView.setLoggedInUser(loggedInUser);
//            vacationsView.setLoggedInLable(loggedInUser.getUsername());
//            vacationsView.setAllVacations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

