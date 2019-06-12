package MVC;

//import MVC.Users.VacationsView;
import Entities.Category;
import Entities.Event;
import Entities.Organization;
import Entities.User;
import MVC.Controller.Controller;
import MVC.Views.IView;
import MVC.Views.VacationsView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MVC.Model.Model;
//import MVC.Users.IView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{

        Model model = new Model();
        Category category = new Category("凸(艹皿艹 )");
        Category category1 = new Category("凸(艹皿艹 )凸(艹皿艹 )凸(艹皿艹 )凸(艹皿艹 )");

        model.insertCategory(category);
        model.insertCategory(category1);


        List<String> catList = model.readAllCategories();

        for (String cat : catList){
            System.out.print(cat+",");
        }
//        Organization organization = new Organization("nitzan Police");
//        Organization organization2 = new Organization("nitzan2 Police");
//        User user = new User("Nitzan",0,"nitzan@nitzan.com","123456",organization);
//        User user2 = new User("Nitzan2",0,"nitzan2@nitzan.com","123456",organization);
//        Event event = new Event("NitzanEvent" , user, organization, null);
//        Event event2 = new Event("NitzanEvent" , user2, organization2, null);
//        model.insertEvent(event);
//        model.insertEvent(event2);
//
//        List<Map<String,String>> mapList = model.readAllEvents();
//        for (Map<String,String>  e: mapList ) {
//            for (String key : e.keySet() ) {
//                System.out.print(e.get(key)+",");
//            }
//            System.out.println();
//        }



//        Controller controller = new Controller(model);
//
//        FXMLLoader loader = new FXMLLoader();
//
//        Parent root = loader.load(Main.class.getResourceAsStream("Views/VacationsScene.fxml"));
//        System.out.println(Main.class.getResource("Style.css").toString());
//        root.getStylesheets().add(Main.class.getResource("Style.css").toString());
//        primaryStage.setTitle("Event4U");
//        primaryStage.setScene(new Scene(root, 550, 400));
//
//        primaryStage.setMinHeight(480);
//        primaryStage.setMinWidth(580);
//
//        primaryStage.setMaxHeight(480);
//        primaryStage.setMaxWidth(580);
//
//
//        Locale.setDefault(Locale.ENGLISH);  //also import
//
//        IView crudView = loader.getController();
//        crudView.setController(controller);
////        ((VacationsView) crudView).setAllEvents();  //TODO: change class name VacationsView
//        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
