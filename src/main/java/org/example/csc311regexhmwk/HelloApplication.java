package org.example.csc311regexhmwk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/csc311regexhmwk/hello-view.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root, 550, 400);
            primaryStage.setTitle("Registration Form");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to execute the whole program
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}