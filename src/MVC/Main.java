package MVC;

//import MVC.Users.VacationsView;
import MVC.Controller.Controller;
import MVC.Views.IView;
import MVC.Views.VacationsView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Entities.EntityController;
import MVC.Model.Model;
//import MVC.Users.IView;

import java.util.Locale;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{

        Model model = new Model();
        Controller controller = new Controller(model);

        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(Main.class.getResourceAsStream("Users/VacationsScene.fxml"));
        System.out.println(Main.class.getResource("Style.css").toString());
        root.getStylesheets().add(Main.class.getResource("Style.css").toString());
        primaryStage.setTitle("EveryVacation4U");
        primaryStage.setScene(new Scene(root, 550, 400));

        primaryStage.setMinHeight(480);
        primaryStage.setMinWidth(580);

        primaryStage.setMaxHeight(480);
        primaryStage.setMaxWidth(580);


        Locale.setDefault(Locale.ENGLISH);  //also import

        IView crudView = loader.getController();
        crudView.setController(controller);
        ((VacationsView) crudView).setAllEvents();  //TODO: change class name VacationsView
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
