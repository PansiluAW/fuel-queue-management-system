//import packages and libraries
package com.example.fuelqueueclassversionfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class FuelCenterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelCenterApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1044, 500);
        stage.setTitle("Fuel Center");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        //call launch method
        launch();
    }
}
