package MVC.Views;

import Entities.User;
import Entities.User;
import MVC.Controller.Controller;
import MVC.Model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VacationBuyScene{

    public boolean isConnected = false;
    private User loggedInUser = null;
    public Controller myController;
    private int selectedEventID;
    public MenuButton menubttn_categories = new MenuButton();


    public BorderPane rootPane;

    public Label lbl_currentUser;
    public Button btn_back;
    public Button btn_add;

    public void setAllCategories(){
        List<String> categories = myController.getAllCategories();
        //TODO Remove bottom line and use the one above - test it.
//        List<String> categories = new ArrayList<>(Arrays.asList("Urgent", "Critical", "Normal"));
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

    public void setLoggedInLabel(String username) {
        isConnected = true;
        lbl_currentUser.setText(username);
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

    }

    /**
     * Return to vacationsScene
     * @param actionEvent
     */
    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane pane = loader.load(getClass().getResource("VacationsScene.fxml").openStream());
            rootPane.getChildren().setAll(pane);
            VacationsView vacationsView = loader.getController();
            vacationsView.setController(myController);
//            vacationsView.setLoggedInUser(loggedInUser);
//            vacationsView.setLoggedInLable(loggedInUser.getUsername());
//            vacationsView.setAllVacations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * Return to vacationsScene
//     */
//    public void goToVacationScene() {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            BorderPane pane = loader.load(getClass().getResource("VacationsScene.fxml").openStream());
//            rootPane.getChildren().setAll(pane);
//            VacationsView vacationsView = loader.getController();
//            vacationsView.setController(myController);
//            vacationsView.setLoggedInUser(loggedInUser);
//            vacationsView.setLoggedInLable(loggedInUser.getUsername());
//            vacationsView.setAllVacations();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /***
//     * When user clicks the userName on top right corner you get transfered to UserReadScene
//     */
//    public void openLoginScene() {
//        if (!isConnected) {
//            try {
//                FXMLLoader loader = new FXMLLoader();
////                BorderPane pane = FXMLLoader.load(getClass().getResource("UserCreationScene.fxml"));
//                BorderPane pane = loader.load(getClass().getResource("LoginView.fxml").openStream());
//                rootPane.getChildren().setAll(pane);
//                LoginView loginView = loader.getController();
//                loginView.setController(myController);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                BorderPane pane = loader.load(getClass().getResource("UserReadScene.fxml").openStream());
//                rootPane.getChildren().setAll(pane);
//                UserReadScene userReadScene = loader.getController();
//                userReadScene.setController(myController);
//                //give the selected user's info to the scene
//                userReadScene.setViewTextUserInfo(loggedInUser);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }


}

