/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcbrzfmvcstopwatchfxml;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author Jon
 */
public class DigitalModel extends AbstractModel{
    
    public DigitalModel() {
        tickTimeInSeconds = 0.1;
        secondsElapsed = 0.0;
    }
    
    public void setupTimer() {
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            updateDigital();
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public void updateDigital() {
        double old = secondsElapsed;
        secondsElapsed += tickTimeInSeconds;
        firePropertyChange("Digital",old,secondsElapsed);
    }
    
    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public void reset() {
        timeline.stop();
        secondsElapsed=0;
    }

}
