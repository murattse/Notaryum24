/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Murat
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public AnchorPane anapane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ilkpencere ilk = new ilkpencere();
        anapane.getChildren().add(ilk.getChildren().get(0));
    }
    
}
