package MVC.Views;
import Entities.Organization;
import Entities.User;
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

public class MainEventsView implements IView {

    public Label login_create ;



    @FXML
    Button btn_home;
    @FXML
    Button btn_newEvent;
    @FXML
    Button btn_addCategoryToEvent;
    @FXML
    Button btn_search;

    @FXML
//    TextField txtfld_search;
//    ObservableList<String> categories = FXCollections.observableArrayList("Urgent", "Critical", "Mild", "Regular");
//    ListView<String> lstview_categories = new ListView<>();
    MenuButton menubttn_categories = new MenuButton();

    @FXML
    TableView<ObservableEventTuple> tbl_events;
    @FXML
    TableColumn<ObservableEventTuple, String> clmn_id;
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
    public ChoiceBox user_chooser;
    public Button login_btn;
    public Organization organization;
    public User loggedInUser ;


    public void setAllEvents(){
        List<ObservableEventTuple> eventsTuples = getEventsTuples();
        tbl_events.setItems(FXCollections.observableArrayList(eventsTuples));
    }

    public void setAllCategories(){
        List<String> categories = myController.getAllCategories();
        //TODO Remove bottom line and use the one above - test it.
//        List<String> categories = new ArrayList<>(Arrays.asList("Urgent", "Critical", "Normal"));
        for(String category : categories){
            CheckMenuItem itemToAdd = new CheckMenuItem(category);
            menubttn_categories.getItems().add(itemToAdd);
        }
    }

    private Set<String> getChosenCategories(){
        Set<String> chosenCategories = new HashSet<>();
        for(MenuItem menuItem: menubttn_categories.getItems()){
            CheckMenuItem checkMenuItem = (CheckMenuItem)menuItem;
            if(checkMenuItem.isSelected()){
                chosenCategories.add(checkMenuItem.getText());
            }
        }
        return chosenCategories;
    }



    public Controller myController;

        /**
     * initialize data columns in the table.
     */
    @FXML
    private void initialize(){
        clmn_id.setCellValueFactory(cellData -> cellData.getValue().eventID);
        clmn_title.setCellValueFactory(cellData -> cellData.getValue().title);
        clmn_published_on.setCellValueFactory(cellData -> cellData.getValue().published_on);
        clmn_rep_user.setCellValueFactory(cellData -> cellData.getValue().rep_user);
        clmn_status.setCellValueFactory(cellData -> cellData.getValue().status);
        clmn_organization.setCellValueFactory(cellData -> cellData.getValue().organization);

        user_chooser.getItems().add("Fire Department user");
        user_chooser.getItems().add("Police user");
        user_chooser.getItems().add("Army user");



    }

    public void setLoggedInUser(Organization org) {

        organization = new Organization(org.getName());
        loggedInUser = new User(org.getName()+" user",organization);

        setAllEvents();
    }

    public void setLoggedInUser(User usr){
        loggedInUser = usr;
        login_create.textProperty().bind(new SimpleStringProperty(loggedInUser.getName()));
        setAllEvents();
    }


    public void onLoginClicked(){
        String st =(String)user_chooser.getValue();

        if (st!=null) {
            st = st.replace(" user","");
            organization = new Organization(st.replace(" user", ""));
            loggedInUser = new User(st + " user", organization);
            login_create.textProperty().bind(new SimpleStringProperty(loggedInUser.getName()));
            setAllEvents();
        }
    }

    //    public void home(ActionEvent actionEvent) {
//        Alert alert = new Alert(Alert.AlertType.WARNING, "The home screen is not supported in this demo.");
//        alert.setHeaderText("Not Supported");
//        alert.setTitle("Not Supported");
//        alert.showAndWait();
//    }

    public void addCategoryToEventClicked(ActionEvent actionEvent) {
        if(tbl_events.getSelectionModel().getSelectedIndex() <= -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select an Event to add a category.");
            alert.setHeaderText("Event not selected.");
            alert.setTitle("Event not selected");
            alert.showAndWait();
        }
        else{
            int eventID = Integer.parseInt(getSelectedEventFromTable().eventID.getValue());
            //TODO Finish adding category to event once eventID is given.
        }
//            try {
//                FXMLLoader loader = new FXMLLoader();
////                BorderPane pane = loader.load(getClass().getResource("AddCategoryToEventScene.fxml").openStream());
////                rootPane.getChildren().setAll(pane);
////                AddCategoryToEventScene vacationBuyScene = loader.getController();
////                vacationBuyScene.setController(myController);
////                //give the selected user's info to the scene
////                vacationBuyScene.setLoggedInLabel(loggedInUser.getUsername());
////                vacationBuyScene.setLoggedInUser(loggedInUser);
////                vacationBuyScene.setTable();
////                String value = getSelectedVacationFromTable().vacationID.toString();
////                vacationBuyScene.setVacationIDBought(Integer.parseInt(getSelectedVacationFromTable().vacationID.getValue()));
////                vacationBuyScene.setLbl_vacationInfo("Price: " + getSelectedVacationFromTable().price.get() + " on " + getSelectedVacationFromTable().departure.get() + " to " + getSelectedVacationFromTable().destination.get());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


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


    public void newEventClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane pane = loader.load(getClass().getResource("EventsCreateScene.fxml").openStream());
            rootPane.getChildren().setAll(pane);
            EventsCreateScene vacationCreateScene = loader.getController();
            vacationCreateScene.setController(myController);
            vacationCreateScene.setLoggedInUser(loggedInUser);
            vacationCreateScene.setAllCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
//                BorderPane pane = loader.load(getClass().getResource("EventsCreateScene.fxml").openStream());
//                rootPane.getChildren().setAll(pane);
//                EventsCreateScene vacationCreateScene = loader.getController();
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
        Set<String> chosenCategories = getChosenCategories();
        List<Map<String,String>> allEventsMap;
        if(chosenCategories.size() == 0){
            setAllEvents();
        }
        else{
            allEventsMap = myController.readAllEvents();
            List<Map<String,String>> eventsMap= new ArrayList<>();
            for (Map<String,String> map : allEventsMap ) {
                boolean hasCaterogy = false;
                List<String> eventsCategoriy = myController.getCategoriesByEventID(Integer.parseInt(map.get("id")));
                for(String cat : eventsCategoriy){
                    if(chosenCategories.contains(cat) && !hasCaterogy){
                        hasCaterogy=true;
                        eventsMap.add(map);
                    }
                }
            }
            List<ObservableEventTuple> eventTuples = new ArrayList<>();
            for (Map<String,String> event: eventsMap) {
                eventTuples.add(new ObservableEventTuple(event.get("title"), event.get("datePublished"), event.get("repName"), event.get("status"), event.get("organization"), event.get("id")));
            }
            tbl_events.setItems(FXCollections.observableArrayList(eventTuples));
        }
    }

//    public void search(){
//        List<String> categories = new ArrayList<>();
//        List<Map<String,String>> events = null;
////        if (!txtfld_search.getText().equals("")){       //TODO: NEED TO GET CATEGORY INSTEAD OF DESTINATION FROM DROPDOWN MENU
//////            destination = txtfld_search.getText();
////            events = myController.searchEventByCategories(categories);
////        }
////        else {
////            events = myController.readAllEvents();
////        }
////        String destination = txtfld_search.getText();
////        List<Vacation> vacations = myController.searchVacattions(destination);
//        List<ObservableEventTuple> eventTuples = new ArrayList<>();
//        for (Map<String,String> event: events) {
//            eventTuples.add(new ObservableEventTuple(event.get("title"), event.get("datePublished"), event.get("repName"), event.get("repName"), event.get("organization")));
//        }
//        tbl_events.setItems(FXCollections.observableArrayList(eventTuples));
//        //TODO search and populate table
//    }



    private List<ObservableEventTuple> getEventsTuples(){
        List<Map<String,String>> allEvents = myController.readAllEvents();
        List<Map<String,String>> events = new ArrayList<>();
        for(Map<String,String> map : allEvents){
            String st = map.get("organization");
            if(st.equals(this.loggedInUser.getOrganization().getName())){
                events.add(map);
            }
        }

        List<ObservableEventTuple> eventTuples = new ArrayList<>();
        for (Map<String,String> event: events) {
            eventTuples.add(new ObservableEventTuple(event.get("title"), event.get("datePublished"), event.get("repName"), event.get("status"), event.get("organization"), event.get("id")));
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


    public void newCategory(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Creating new categories is not supported in this demo.");
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
        StringProperty eventID;


        public ObservableEventTuple(String title, String published_on, String rep_user, String status, String organization, String eventID) {
            this.title = new SimpleStringProperty(title);
            this.published_on = new SimpleStringProperty(published_on);
            this.rep_user = new SimpleStringProperty(rep_user);
            this.status = new SimpleStringProperty(status);
            this.organization = new SimpleStringProperty(organization);
            this.eventID = new SimpleStringProperty(eventID);
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

    public void buyVacation(ActionEvent actionEvent) {

        // If user clicked "Buy" without selecting a vacation
        if(tbl_events.getSelectionModel().getSelectedIndex() <= -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please select a vacation in order to buy one.");
            alert.setHeaderText("Vacation not selected.");
            alert.setTitle("Vacation not selected");
            alert.showAndWait();
        }
        else {
            // Send transaction request (pending transaction is added to DB)
            int eventID = Integer.parseInt(getSelectedEventFromTable().eventID.getValue());
//            myaddTransactionToDatabase(vacationID);

            try {
                FXMLLoader loader = new FXMLLoader();
                BorderPane pane = loader.load(getClass().getResource("AddCategoryToEventScene.fxml").openStream());
                rootPane.getChildren().setAll(pane);
                AddCategoryToEventScene addCategoryToEvent = loader.getController();
                addCategoryToEvent.setController(myController);
                addCategoryToEvent.setAllCategories();
                addCategoryToEvent.setLoggedInUser(loggedInUser);
                addCategoryToEvent.selectedEvent(eventID);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
