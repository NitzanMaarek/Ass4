package MVC.Views;
import Entities.Event;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import MVC.Controller.Controller;

import java.io.IOException;
import java.util.*;

public class VacationsView implements IView {

    public Label login_create;

    @FXML
    Button btn_home;
    @FXML
    Button btn_sell;
    @FXML
    Button btn_buy;
    @FXML
    Button btn_search;

    @FXML
    TextField txtfld_search;

    @FXML
    TableView<ObservableEventTuple> tbl_events;
    @FXML
    TableColumn<ObservableEventTuple, String> clmn_title;
    @FXML
    TableColumn<ObservableEventTuple, String> clmn_published_on;
    @FXML
    TableColumn<ObservableEventTuple, String> clmn_rep_user;
    @FXML
    TableColumn<ObservableEventTuple, String> clmn_status;
    @FXML
    TableColumn<ObservableEventTuple, String> clmn_organization;


    @FXML
    BorderPane rootPane;
    private boolean isConnected = false;
//    private AUser loggedInUser =null;


    public void setAllEvents(){
        List<ObservableEventTuple> eventsTuples = getEventsTuples();
        tbl_events.setItems(FXCollections.observableArrayList(eventsTuples));
    }



    public Controller myController;

        /**
     * initialize data columns in the table.
     */
    @FXML
    private void initialize(){
        clmn_title.setCellValueFactory(cellData -> cellData.getValue().title);
        clmn_published_on.setCellValueFactory(cellData -> cellData.getValue().published_on);
        clmn_rep_user.setCellValueFactory(cellData -> cellData.getValue().rep_user);
        clmn_status.setCellValueFactory(cellData -> cellData.getValue().status);
        clmn_organization.setCellValueFactory(cellData -> cellData.getValue().organization);
    }


//    public void home(ActionEvent actionEvent) {
//        Alert alert = new Alert(Alert.AlertType.WARNING, "The home screen is not supported in this demo.");
//        alert.setHeaderText("Not Supported");
//        alert.setTitle("Not Supported");
//        alert.showAndWait();
//    }

    public void buyVacation(ActionEvent actionEvent) {
        if(!isConnected){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please log in or create an account to buy and sell vacations.");
            alert.setHeaderText("You are not logged in");
            alert.setTitle("Not Logged In");
            alert.showAndWait();
        }
        // If user clicked "Buy" without selecting a vacation
        else if(tbl_events.getSelectionModel().getSelectedIndex() <= -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a vacation in order to buy one.");
            alert.setHeaderText("Vacation not selected.");
            alert.setTitle("Vacation not selected");
            alert.showAndWait();
        }
        // If user tries to buy his own vacation
//        else if(loggedInUser.getUsername().equals(getSelectedVacationFromTable().seller.getValue())){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cannot buy your own vacation.\nPlease select a different vacation.");
//            alert.setHeaderText("Select other vacation please.");
//            alert.setTitle("Selection error");
//            alert.showAndWait();
//        }
        else {
            // Send transaction request (pending transaction is added to DB)
//            int vacationID = Integer.parseInt(getSelectedEventFromTable().vacationID.getValue());
//            myaddTransactionToDatabase(vacationID);

            try {
                FXMLLoader loader = new FXMLLoader();
//                BorderPane pane = loader.load(getClass().getResource("VacationBuyScene.fxml").openStream());
//                rootPane.getChildren().setAll(pane);
//                VacationBuyScene vacationBuyScene = loader.getController();
//                vacationBuyScene.setController(myController);
//                //give the selected user's info to the scene
//                vacationBuyScene.setLoggedInLabel(loggedInUser.getUsername());
//                vacationBuyScene.setLoggedInUser(loggedInUser);
//                vacationBuyScene.setTable();
//                String value = getSelectedVacationFromTable().vacationID.toString();
//                vacationBuyScene.setVacationIDBought(Integer.parseInt(getSelectedVacationFromTable().vacationID.getValue()));
//                vacationBuyScene.setLbl_vacationInfo("Price: " + getSelectedVacationFromTable().price.get() + " on " + getSelectedVacationFromTable().departure.get() + " to " + getSelectedVacationFromTable().destination.get());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

//    /**
//     *
//     * @return a tuple with the fields of the selected row from the displayed table.
//     */
//    private void processTransaction(String cNum, ObservableEventTuple vacation) {
//        if(!cNum.matches("[0-9]+") || cNum.length() != 16){ //not digits only
//            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid credit card number. \n" +
//                    "Please a valid credit card number with 16 digits and no dashes.\n" +
//                    "Please try again.");
//            alert.setHeaderText(null);
//            alert.setTitle("Invalid credit card number");
//            alert.showAndWait();
//        }
//        else{ //valid number
//            myController.processTransaction(vacation, cNum, loggedInUser);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "We will inform the seller that you are interested in buying this vacation. Should the seller approve the sale, your card will be charged and you will receive an email with your receipt.\n"
//                    +"Enjoy your vacation!");
//            alert.setHeaderText(null);
//            alert.setTitle("Vacation Purchase Request Sent");
//            alert.show();
//        }
//    }


//    public void sellVacation(ActionEvent actionEvent) {
//        if(!isConnected){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please log in or create an account to buy and sell vacations.");
//            alert.setHeaderText("You are not logged in");
//            alert.setTitle("Not Logged In");
//            alert.showAndWait();
//        }
//        else {
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                BorderPane pane = loader.load(getClass().getResource("VacationCreateScene.fxml").openStream());
//                rootPane.getChildren().setAll(pane);
//                VacationCreateScene vacationCreateScene = loader.getController();
//                vacationCreateScene.setController(myController);
//                vacationCreateScene.changeDateFormat();
//                //give the selected user's info to the scene
//                vacationCreateScene.setLoggdInUser(loggedInUser);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void search(){

//        tbl_events.sortPolicyProperty().set(t -> {
//            Comparator<ObservableEventTuple> comparator = (r1, r2)
//                    -> r1.isPriority & ! r2.isPriority ? 1 //vacations with priority go first
//                    : r2.isPriority & ! r1.isPriority ? -1 //vacations with priority go first
//                    : t.getComparator() == null ? 0 //no column sorted: don't change order
//                    : t.getComparator().compare(r1, r2); //columns are sorted: sort accordingly
//            FXCollections.sort(tbl_events.getItems(), comparator);
//            return true;
//        });
        List<String> categories = new ArrayList<>();
//        String destination = "";
        List<Event> events = null;
        if (!txtfld_search.getText().equals("")){       //TODO: NEED TO GET CATEGORY INSTEAD OF DESTINATION FROM DROPDOWN MENU
//            destination = txtfld_search.getText();
            events = myController.searchEventByCategories(categories);
        }
        else {
            events = myController.readAllEvents();
        }
//        String destination = txtfld_search.getText();
//        List<Vacation> vacations = myController.searchVacattions(destination);
        List<ObservableEventTuple> eventTuples = new ArrayList<>();
        for (Event event: events) {
            eventTuples.add(new ObservableEventTuple(event.getTitle(), event.getDatePublished(), event.getRep().getName(), event.getStatus(), event.getOrganization().getName()));
        }


        tbl_events.setItems(FXCollections.observableArrayList(eventTuples));

        //TODO search and populate table
    }

    private List<ObservableEventTuple> getEventsTuples(){
        List<Event> events = myController.readAllEvents();
        List<ObservableEventTuple> eventTuples = new ArrayList<>();
        for (Event event: events) {
            eventTuples.add(new ObservableEventTuple(event.getTitle(), event.getDatePublished(), event.getRep().getName(), event.getStatus(), event.getOrganization().getName()));
        }
        return eventTuples;
    }

     /**
     *
     * @return a tuple of the selected row from the displayed table.
     */
    private ObservableEventTuple getSelectedEventFromTable(){
        return tbl_events.getItems().get(tbl_events.getSelectionModel().getSelectedIndex());
    }


    public void message(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Messaging is not supported in this demo.");
        alert.setHeaderText("Not Supported");
        alert.setTitle("Not Supported");
        alert.showAndWait();
    }

    public static class ObservableEventTuple{
        StringProperty title;
        StringProperty published_on;
        StringProperty rep_user;
        StringProperty status;
        StringProperty organization;


        public ObservableEventTuple(String title, String published_on, String rep_user, String status, String organization) {
            this.title = new SimpleStringProperty(title);
            this.published_on = new SimpleStringProperty(published_on);
            this.rep_user = new SimpleStringProperty(rep_user);
            this.status = new SimpleStringProperty(status);
            this.organization = new SimpleStringProperty(organization);
        }


    }

//


//    private UserSearchScene openUserSearchScene() {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            BorderPane pane = loader.load(getClass().getResource("UserSearchScene.fxml").openStream());
//            rootPane.getChildren().setAll(pane);
//            UserSearchScene UserSearchScene = loader.getController();
//            UserSearchScene.setController(myController);
//            return UserSearchScene;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//    public void openUserCreationScene(ActionEvent actionEvent) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
////            BorderPane pane = FXMLLoader.load(getClass().getResource("UserCreationScene.fxml"));
//            BorderPane pane = loader.load(getClass().getResource("UserCreationScene.fxml").openStream());
//            rootPane.getChildren().setAll(pane);
//            UserCreationScene userCreationscene = loader.getController();
//            userCreationscene.setController(myController);
//            userCreationscene.changeDateFormat();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public Boolean checkIfUserExists(String userName) {
        //
        return true;
    }


    @Override
    public void setController(Controller aController) {
        if (aController != null) {
            myController = aController;
        }
    }

//    public void setLoggedInLable(String username) {
//        isConnected = true;
//        login_create.setText(username);
//    }
//
//    public void setLoggedInUser(AUser loggedInUser){
//        this.loggedInUser=loggedInUser;
//    }
//
//    public void openUserSearchWithRead(ActionEvent actionEvent) {
//        openUserSearchScene().setReadEnabled(true);
//    }
//
//    public void openUserSearchWithUpdate(ActionEvent actionEvent) {
//        UserSearchScene userSearchScene = openUserSearchScene();
//        userSearchScene.setReadEnabled(true);
//        userSearchScene.setUpdateEnabled(true);
//    }
//
//    public void openUserSearchWithDelete(ActionEvent actionEvent) {
//        UserSearchScene userSearchScene = openUserSearchScene();
//        userSearchScene.setReadEnabled(true);
//        userSearchScene.setDeleteEnabled(true);
//    }
//
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
//                userReadScene.setTables();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }


}