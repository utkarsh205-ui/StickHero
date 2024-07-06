package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Endgame {

    @FXML
    public Label CherryLabel;

    @FXML
    public Label ScoreLabel;

    private int ScoreValue = 0;
    private int CherryValue = 0;

    public void setCherryintScore(int totalCherries) {

        this.CherryValue= totalCherries;
    }

    public void setCherryLabelScore() {

        this.CherryLabel.setText(String.valueOf(this.CherryValue));
    }

    public void setintScore(int score) {

        this.ScoreValue = score;
    }

    public void setLabelScore() {

        this.ScoreLabel.setText(String.valueOf(this.ScoreValue));
    }
}
