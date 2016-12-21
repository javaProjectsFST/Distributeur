package controller.main;

import controller.DistributeurController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application{
 
    private static GeneralController generalController;
    
    static Connection makeConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Connection to localhost:3306
//            String url = "jdbc:mysql://localhost:3306/distributeur";
//            String user = "root";
//            String mdp="";
            
            //Connection to hosted database
            String url = "jdbc:mysql://seif.sytes.net:3306/distributeur";
            String user = "root";
            String mdp="root";
            
            return DriverManager.getConnection(url, user, mdp);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args){
        generalController=new GeneralController(makeConnection());
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene=new Scene(generalController.getGeneralView());
        
        stage.setHeight(997);
        stage.setWidth(1200);
        stage.setTitle("Distributeur");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
