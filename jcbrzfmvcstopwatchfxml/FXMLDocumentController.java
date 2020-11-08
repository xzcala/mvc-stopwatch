/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcbrzfmvcstopwatchfxml;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author Jon
 *      a lot of this code came from last semester's, but i fixed what was wrong with it such as the record board displaying the correct values
 */
public class FXMLDocumentController implements Initializable, PropertyChangeListener {
    
    DecimalFormat df = new DecimalFormat("00.0##");
    public int loop,flag;
    public double currentTime,lastTime;
    public double resultTime=0;
    
    @FXML
    private Text clockLabel;
    @FXML
    private Text display1;
    @FXML
    private Text display2;
    @FXML
    private Text display3;
    @FXML
    private ImageView hand;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;

    AnalogModel analogModel;
    DigitalModel digitalModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        analogModel = new AnalogModel();
        digitalModel = new DigitalModel();
        analogModel.setupTimer();
        digitalModel.setupTimer();
        analogModel.addPropertyChangeListener(this);
        digitalModel.addPropertyChangeListener(this);
    }    

    @FXML
    public void startStopTimer(ActionEvent event) {
        if (!(analogModel.isRunning())) {
            analogModel.start();
            digitalModel.start();
            startStopButton.setText("Stop");
            recordResetButton.setText("Record");
        } else {
            analogModel.stop();
            digitalModel.stop();
            startStopButton.setText("Start");
            recordResetButton.setText("Reset");
        }
    }

    @FXML
    public void recordResetTimer(ActionEvent event) {
        if (!(analogModel.isRunning())) {
            hand.setRotate(0);
            clockLabel.setText("00:00:000");
            display1.setText("--:--.ss");
            display2.setText("--:--.ss");
            display3.setText("--:--.ss");
            loop=0;
            recordResetButton.setText("Record");
            analogModel.reset();
            digitalModel.reset();
        } else {
            flag=loop%3;
            if(loop==0){
                resultTime=currentTime;
            } else {
                resultTime=currentTime-lastTime;
            }
            lastTime=currentTime;
            String newTime = df.format(resultTime%60);
            String newTime2=String.format("%02d:%s",(long)resultTime/60,newTime);
            switch(flag){
                case 0: display1.setText("Lap "+(loop+1)+": "+newTime2);
                        loop++;
                        break;
                case 1: display2.setText("Lap "+(loop+1)+": "+newTime2);
                        loop++;
                        break;
                case 2: display3.setText("Lap "+(loop+1)+": "+newTime2);
                        loop++;
                        break;
            }    
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Analog")) {
            hand.setRotate((double) evt.getNewValue()*6);
        }
        else if(evt.getPropertyName().equals("Digital")){
            currentTime= (double) evt.getNewValue();
            String newTime = df.format(currentTime%60);
            String newTime2=String.format("%02d:%s",(long)currentTime/60,newTime);
            clockLabel.setText(newTime2);
        }
    }
}
