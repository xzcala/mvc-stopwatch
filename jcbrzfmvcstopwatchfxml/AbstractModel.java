/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcbrzfmvcstopwatchfxml;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 *
 * @author Jon
 */
public class AbstractModel {
    
    protected PropertyChangeSupport propertyChangeSupport;
    
    protected Timeline timeline;
    protected KeyFrame keyFrame;
    
    protected double tickTimeInSeconds;
    protected double secondsElapsed;
    protected double angleDeltaPerSeconds;
    
    
    public AbstractModel(){
        propertyChangeSupport = new PropertyChangeSupport(this);  
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
