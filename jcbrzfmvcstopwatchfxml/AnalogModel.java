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
public class AnalogModel extends AbstractModel{
    
    public AnalogModel() {
        tickTimeInSeconds = 0.1;
        secondsElapsed = 0.0;
    }
    
    public void setupTimer() {
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            updateAnalog();
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public void updateAnalog() {
        double old = secondsElapsed;
        secondsElapsed += tickTimeInSeconds;
        firePropertyChange("Analog",old,secondsElapsed);
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
    
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        }
        return false;
    }
}
