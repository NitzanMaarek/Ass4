package MVC.Views;

import Entities.User;
import MVC.Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;;


public class EventsCreateScene implements Initializable {


    public TextField txtfld_title;
    public TextArea txtfld_update;
    public MenuButton menubttn_associatedForces = new MenuButton();
    public MenuButton menubttn_categories = new MenuButton();

    @FXML
    public BorderPane rootPane;

    private Controller myController;
    User loggedInUser ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menubttn_associatedForces.getItems().addAll(new CheckMenuItem("Police"), new CheckMenuItem("Military"), new CheckMenuItem("Fire Dept."));
    }

    public void setLoggedInUser(User usr){
        loggedInUser = usr;
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

    public void setController(Controller controller){
        this.myController=controller;
    }

//    public void setLoggdInUser(AUser loggdInUser){
//        this.loggdInUser=loggdInUser;
//    }

    private String convertDateToStorageFormat(String birthDate) {
        String toReturn = "";
        String [] str = birthDate.split("/");
        for (String st : str ){
            toReturn=st+toReturn;
        }
        return toReturn;
    }


    private boolean isWord(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c) && c != ' ' && c != '-') {
                return false;
            }
        }

        return true;
    }

    private static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    private void showEventAlert(String alertString) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success LogIN!");
        alert.setHeaderText(null);
        alert.setContentText(alertString);
        alert.showAndWait();

    }


    private List<String> getChosenForces(){
        List<String> chosenForces = new ArrayList<>();
        for(MenuItem menuItem: menubttn_associatedForces.getItems()){
            CheckMenuItem checkMenuItem = (CheckMenuItem)menuItem;
            if(checkMenuItem.isSelected()){
                chosenForces.add(checkMenuItem.getText());
            }
        }
        return chosenForces;
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
    public void onCreateButtonClicked(){

        List<String> alerts = new ArrayList<>();
//        List<String> events = new ArrayList<>();
        Map<String, String> event = new HashMap<>();

        String event_title = txtfld_title.getText();
        if (event_title.isEmpty()){
            alerts.add("Enter title");
        }
        else if (!isWord(event_title)){
            alerts.add("Title contains invalid characters");
        }

        String event_update = txtfld_update.getText();
        if(event_update.isEmpty()) {
            alerts.add("Enter update");
        }

        List<String> chosenForces = getChosenForces();
        if (chosenForces.size() == 0 ) {
            alerts.add("Choose associated forces");
        }
        List<String> chosenCategories = getChosenCategories();
        if (chosenCategories.size() == 0) {
            alerts.add("Select categories");
        }
        if (alerts.isEmpty()){
            event.put("title", event_title);
            event.put("update", event_update);
            String categories = stringSetToStringWithComma(chosenCategories);
            event.put("categories",categories);
            String forces = stringSetToStringWithComma(chosenForces);
            event.put("forces", forces);
            event.put("username",loggedInUser.getName());
            event.put("organization", loggedInUser.getOrganization().getName());
            showEventAlert("Event created successfully!");
            myController.addEvent(event);
            openEventsView();
        }
        else {
            showEventAlert(alerts.get(0));
        }
    }

    private String stringSetToStringWithComma(List<String> stringSet){
        String ans = "";
        for(int i=0; i<stringSet.size(); i++){
            ans += stringSet.get(i) + ",";
        }
        if(ans.length() > 0){
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans;
    }

    public void openEventsView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane pane = loader.load(getClass().getResource("MainEventsView.fxml").openStream());
            rootPane.getChildren().setAll(pane);
            MainEventsView mainEventsView = loader.getController();
            mainEventsView.setController(myController);
            mainEventsView.setLoggedInUser(loggedInUser);
            mainEventsView.setAllEvents();
            mainEventsView.setAllCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
