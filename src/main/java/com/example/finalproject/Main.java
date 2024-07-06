package com.example.finalproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        ImageView backgroundImage= new ImageView(new Image("file:///C:/Users/Utkarsh/Desktop/finalproject/src/main/java/com/example/finalproject/back.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage.getImage(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                null,
                new BackgroundSize(
                        1.0, 1.0, true, true, false, false
                )
        );
        VBox vBox = (VBox) root;
        vBox.setBackground(new Background(background));
        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root, 400, 700);
        stage.setTitle("StickHero!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        launch();
    }
}
