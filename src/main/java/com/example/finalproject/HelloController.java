package com.example.finalproject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.event.Event;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.animation.RotateTransition;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point3D;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;

import java.security.Key;
import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {

    private Stage primaryStage;
    private boolean isGrowing = false;
    private boolean gamerun=true;
    private Rectangle stick;
    private Rectangle rectangle1;
    private boolean gamecontinue=true;
    private ImageView flipper;
    private ImageView characterImageView;
    private ImageView cherry;
    private int score = 0;
    private int total_cherries = 0;
    private int giver;
    private boolean upside=true;
    private boolean cherrybool;
    private AnchorPane anchorPane;
    private boolean check;
    private Rectangle rectangle2;

/*
    private boolean flag = false;
*/

    private Circle cir1;
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onPlayButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameplay.fxml"));
            Parent root1 = loader.load();
            anchorPane = (AnchorPane) root1;
            Image bg1 = new Image("file:///C:/Users/Utkarsh/Desktop/finalproject/src/main/java/com/example/finalproject/wallpaper.png");
            ImageView background = new ImageView(bg1);
            background.setFitWidth(800);
            background.setFitHeight(530);
            anchorPane.getChildren().add(background);
            Stage newStage = new Stage();
            newStage.setTitle("New Screen");
            newStage.setScene(new Scene(root1, 750, 525));
            newStage.show();
            rectangle1 = new Rectangle(50, 229, Color.BLACK);
            rectangle1.setLayoutX(0);
            rectangle1.setLayoutY(300);
            characterImageView = new ImageView(new Image("file:///C:/Users/Utkarsh/Desktop/finalproject/src/main/java/com/example/finalproject/char.png"));
            flipper= new ImageView(new Image("file:///C:/Users/Utkarsh/Desktop/finalproject/src/main/java/com/example/finalproject/char2.jpg"));
            cherry=new ImageView(new Image("file:///C:/Users/Utkarsh/Desktop/finalproject/src/main/java/com/example/finalproject/Cherry Fruit Clipart Vector, Two Cherries Pair Elegant Cherry Fruit Design, Cherry, Fruit, Cherries PNG Image For Free Download.png"));
            cherry.setFitHeight(30);
            cherry.setFitWidth(30);
            characterImageView.setFitWidth(30);
            characterImageView.setFitHeight(30);
            flipper.setFitWidth(30);
            flipper.setFitHeight(30);
            flipper.setLayoutX(rectangle1.getLayoutX()+flipper.getFitWidth());
            flipper.setLayoutY(rectangle1.getLayoutY());
            characterImageView.setLayoutX(rectangle1.getLayoutX()+rectangle1.getWidth() - characterImageView.getFitWidth());
            characterImageView.setLayoutY(rectangle1.getLayoutY()-characterImageView.getFitHeight());
            cherry.setLayoutX(200);
            cherry.setLayoutY(300);
            flipper.setVisible(false);
            rectangle2 = new Rectangle(50, 229, Color.BLACK);
            rectangle2.setLayoutX(300);
            rectangle2.setLayoutY(300);
            anchorPane.getChildren().addAll(rectangle1, rectangle2,cherry,flipper,characterImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(10), e -> handleStickGrowing())
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            System.out.println("came back");
            anchorPane.setOnMousePressed(event -> handleMousePressed(event, rectangle1, rectangle2));
            anchorPane.setOnMouseReleased(event->handleMouseReleased(event));
            anchorPane.setOnMouseClicked(event->handlemouseclicked(event));
            if(!gamecontinue)
            {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleMousePressed(MouseEvent event, Rectangle rec1, Rectangle rec2) {
        if (event.getButton() == MouseButton.SECONDARY) {
            isGrowing = true;
            double stickStartX = rec1.getLayoutX() + rec1.getWidth() ;
            double stickStartY = rec1.getLayoutY();
            stick = new Rectangle(4, 0, Color.BLACK);
            stick.setLayoutX(stickStartX);
            stick.setLayoutY(stickStartY);
            anchorPane.getChildren().add(stick);
        }
    }

    private void handleMouseReleased(MouseEvent event) {
//        System.out.println("aaya hai upar toh");
        if (event.getButton() == MouseButton.SECONDARY) {
            isGrowing = false;
            Rectangle rec3=new Rectangle(stick.getHeight(),stick.getWidth());
            Rotate rotate= new Rotate(0,stick.getX()+stick.getWidth()/2,stick.getY()+stick.getHeight());
            stick.getTransforms().clear();
            stick.getTransforms().add(rotate);
            Timeline timee= new Timeline(new KeyFrame(Duration.seconds(0.5),new KeyValue(rotate.angleProperty(),90)));
            timee.play();
            timee.setOnFinished(e->animateHeroMovement(rectangle1.getLayoutX()));
        }


    }

    private void handleStickGrowing() {
        if (isGrowing && stick != null) {
            stick.setLayoutY(stick.getLayoutY() - 2);
            stick.setHeight(stick.getHeight() + 2);
        }
    }

    private void animateHeroMovement(double valueimp)
    {
        if (stick != null && characterImageView != null) {
            double stickRightX = rectangle2.getLayoutX()+rectangle2.getWidth();
            double val=stick.getLayoutX() + stick.getHeight();
            if (((stick.getLayoutX() + stick.getHeight()) < rectangle2.getLayoutX()) || ((rectangle2.getLayoutX() + rectangle2.getWidth()) < (stick.getLayoutX() + stick.getHeight()))) {
                Timeline t1 = new Timeline();
                if(!cherrybool)
                {
                    cherry_pillar_move();
                }
                else if(cherrybool && cherry.getLayoutX()>(rectangle1.getWidth()+rectangle1.getLayoutX()+stick.getHeight()))
                {
                    cherry_pillar_move();
                }
                else {
                    double firstmove = cherry.getLayoutX() - (characterImageView.getLayoutX() + characterImageView.getFitWidth());
                    KeyFrame keyframe1 = new KeyFrame(Duration.seconds(2), new KeyValue(characterImageView.layoutXProperty(), characterImageView.getLayoutX() + firstmove));
                    KeyFrame keyframe2 = new KeyFrame(Duration.seconds(2), new KeyValue(flipper.layoutXProperty(), flipper.getLayoutX() + firstmove));
                    t1.getKeyFrames().addAll(keyframe1, keyframe2);
                    t1.play();
                    t1.setOnFinished(e -> {
                        cherry_pillar_move();
                        checker();
                    });
                }
            }
            else {
                gamecontinue=false;
                if(!cherrybool)
                {
                    cherry_to_piller();
                }
                else {
                    System.out.println("vskdvefwefewm,,cxvds");
                    Timeline t1 = new Timeline();
                    double firstmove = cherry.getLayoutX() + cherry.getFitWidth() - (characterImageView.getLayoutX() + characterImageView.getFitWidth());
                    KeyFrame keyframe1 = new KeyFrame(Duration.seconds(2), new KeyValue(characterImageView.layoutXProperty(), characterImageView.getLayoutX() + firstmove));
                    KeyFrame keyframe2 = new KeyFrame(Duration.seconds(2), new KeyValue(flipper.layoutXProperty(), flipper.getLayoutX() + firstmove));
                    t1.getKeyFrames().addAll(keyframe1, keyframe2);
                    t1.play();
                    t1.setOnFinished(e -> {
                        cherry_to_piller();
                        checker();
                    });
                    System.out.println("idhar se bhi nikla hun");
                }
            }
        }
    }

    private void checker()
    {
        if(!upside)
        {
            total_cherries+=1;
            cherry.setVisible(false);
        }
    }

    private void cherry_pillar_move()
    {
        Timeline t2= new Timeline();
        double secondmove= rectangle1.getLayoutX()+stick.getHeight()-characterImageView.getLayoutX()+characterImageView.getFitWidth();
        KeyFrame keyframe1 = new KeyFrame(Duration.seconds((2/stick.getHeight())*secondmove), new KeyValue(characterImageView.layoutXProperty(), characterImageView.getLayoutX()+secondmove));
        KeyFrame keyframe2=new KeyFrame(Duration.seconds((2/stick.getHeight())*secondmove),new KeyValue(flipper.layoutXProperty(),flipper.getLayoutX()+secondmove));
        t2.getKeyFrames().addAll(keyframe1,keyframe2);
        t2.play();
        t2.setOnFinished(e -> {checker3(); });
    }
    private void cherry_to_piller()
    {
        Timeline t2= new Timeline();
        double secondmove= rectangle2.getLayoutX()-characterImageView.getLayoutX()-characterImageView.getFitWidth();
        KeyFrame keyframe1 = new KeyFrame(Duration.seconds((2/stick.getHeight())*secondmove), new KeyValue(characterImageView.layoutXProperty(), characterImageView.getLayoutX()+secondmove));
        KeyFrame keyframe2=new KeyFrame(Duration.seconds((2/stick.getHeight())*secondmove),new KeyValue(flipper.layoutXProperty(),flipper.getLayoutX()+secondmove));
        t2.getKeyFrames().addAll(keyframe1,keyframe2);
        t2.play();
        t2.setOnFinished(e -> {checker2(); });
    }
    private void movetolast()
    {
        Timeline t3= new Timeline();
        double secondmove= rectangle2.getWidth();
        KeyFrame keyframe1 = new KeyFrame(Duration.seconds((2/stick.getHeight())*secondmove), new KeyValue(characterImageView.layoutXProperty(), characterImageView.getLayoutX()+secondmove));
        KeyFrame keyframe2=new KeyFrame(Duration.seconds((2/stick.getHeight())*secondmove),new KeyValue(flipper.layoutXProperty(),flipper.getLayoutX()+secondmove));
        t3.getKeyFrames().addAll(keyframe1,keyframe2);
        t3.play();
        t3.setOnFinished(e -> moverec1back());
    }
    private void checker2()
    {
        if(!upside)
        {
            initiateFallTimeline();
        }
        else
        {
            score+=1;
            movetolast();
        }
    }
    private void checker3()
    {
        initiateFallTimeline();
    }
    private void moverec1back()
    {
        Timeline rectangle_move = new Timeline();
        KeyFrame keyframe1 = new KeyFrame(Duration.seconds(2), new KeyValue(rectangle1.layoutXProperty(), -500));
        KeyFrame keyframe2 = new KeyFrame(Duration.seconds(2), new KeyValue(rectangle2.layoutXProperty(), 0));
        KeyFrame keyframe3 = new KeyFrame(Duration.seconds(2), new KeyValue(characterImageView.layoutXProperty(), 0+rectangle2.getWidth()-characterImageView.getFitWidth()));
        KeyFrame keyframe4 = new KeyFrame(Duration.seconds(2), new KeyValue(stick.layoutXProperty(), -1000));
        KeyFrame keyframe5= new KeyFrame(Duration.seconds(2), new KeyValue(flipper.layoutXProperty(),0+ rectangle2.getWidth()-characterImageView.getFitHeight()));
        KeyFrame keyframe6= new KeyFrame(Duration.seconds(2),new KeyValue(cherry.layoutXProperty(),-200));
        rectangle_move.getKeyFrames().addAll(keyframe1,keyframe2,keyframe3,keyframe4,keyframe5,keyframe6);
        rectangle_move.play();
        rectangle_move.setOnFinished(e->resetgame(characterImageView,rectangle_move));
    }

    private void handlemouseclicked(MouseEvent event)
    {
        System.out.println("aaya hai udhar toh");

        if(event.getButton()== MouseButton.PRIMARY)
        {
            if((characterImageView.getLayoutX()+characterImageView.getFitWidth())!=(rectangle1.getLayoutX()+rectangle1.getWidth())){
                if (upside) {
                    System.out.println("odefgklfmge");
                    upside = false;
                    flipper.setVisible(true);
                    characterImageView.setVisible(false);
                } else {
                    upside = true;
                    characterImageView.setVisible(true);
                    flipper.setVisible(false);
                }
            }
        }
        else
        {
            System.out.println("mai toh nahi aaya udhar");
        }
    }

    private void resetgame(ImageView characterImageView,Timeline moveTimeline)
    {
        stick.setVisible(false);
        System.out.println("resetgame me bhi aaya");
        rectangle1=rectangle2;
        double breadth=randomizer2();
        Rectangle rectangle3=new Rectangle(randomizer2(),229);
        double second=randomizer1(rectangle1.getLayoutX());
        rectangle3.setLayoutX(second);
        rectangle3.setLayoutY(300);
        rectangle2=rectangle3;
        anchorPane.getChildren().add(rectangle3);
        System.out.println("the rectangle 1 is at "+rectangle1.getLayoutX());
        if(randbool()) {
            double cherryx = cherryrandom(rectangle1.getLayoutX() + rectangle1.getWidth(), rectangle2.getLayoutX() - cherry.getFitWidth() - 10);
            cherry.setLayoutX(cherryx);
            cherry.setVisible(true);
            cherrybool=true;
            System.out.println("upar aaya");
        }
        else
        {
            cherrybool=false;
            System.out.println("niche aaya");
        }
        moveTimeline.stop();
        moveTimeline.getKeyFrames().clear();
    }
    private boolean randbool()
    {
        Random random= new Random();
        return random.nextBoolean();
    }
    private double cherryrandom(double x1,double x2)
    {
        Random random=new Random();
        double number= random.nextDouble(x2-x1)+x1;
        return number;
    }

    private double randomizer1(double val)
    {
        double maxval=600;
        double minval=val+200;
        Random random = new Random();
        double randomNumber = random.nextDouble(maxval - minval-20) + minval;
        return randomNumber;
    }

    private double randomizer2()
    {
        double maxval=150;
        double minval=40;
        Random random = new Random();
        double randomNumber = random.nextDouble(maxval - minval + 1) + minval;
        return randomNumber;
    }



    private void initiateFallTimeline()  {
        Timeline hh1=new Timeline();
        KeyFrame keyframe2=new KeyFrame(Duration.seconds(2), new KeyValue(characterImageView.layoutYProperty(),1000));
        KeyFrame keyframe3= new KeyFrame(Duration.seconds(2), new KeyValue(flipper.layoutYProperty(),1000));
        hh1.getKeyFrames().addAll(keyframe2,keyframe3);
        hh1.play();
        double pivotX = stick.getX() + stick.getWidth() / 2;
        double pivotY = stick.getY() + stick.getHeight();

        // Create a Rotate object
        Rotate rotate = new Rotate(0, pivotX, pivotY);
        stick.getTransforms().add(rotate);
        final Timeline[] tt = new Timeline[1];
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.01), e -> {
            if (rotate.getAngle() < 90) {
                rotate.setAngle(rotate.getAngle() + 2);
            } else {
                tt[0].stop();
            }
        });
        tt[0] = new Timeline(keyFrame);
        tt[0].setCycleCount(Timeline.INDEFINITE);
        tt[0].play();

        func(score, total_cherries);
    }

    public void func(int score, int total_cherries) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("endgame.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            // Get the controller of the endgame.fxml
            Endgame endGameController = fxmlLoader.getController();

            endGameController.setCherryintScore(total_cherries);
            endGameController.setCherryLabelScore();

            endGameController.setintScore(score);
            endGameController.setLabelScore();
            // Pass any data or perform actions on the EndGameController if needed
            // endGameController.someMethod();

            Scene scene = new Scene(root);
            stage.setTitle("StickHero!");
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
    }
}