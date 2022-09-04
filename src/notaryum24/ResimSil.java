/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author murat.secilmis
 */
public class ResimSil extends AnchorPane {
    
    public ResimSil() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResimSil.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    private AnchorPane notturupane;

    @FXML
    private Button silbutonu;

    @FXML
    private Button vazgecbutonu;

    @FXML
    public ChoiceBox<String> combom;

    @FXML
    private Label uyarmalabeli;

    @FXML
    void silbutonunatiklandi(ActionEvent event) {
        NotText not = new NotText();
        String nottext = not.nottext;
        AnchorPane pane = (AnchorPane) notturupane.getParent();
        
        Veritabani veritabani = new Veritabani();
        
        String aciklama = combom.getValue();
        veritabani.ResimleriVeAciklamalariSil(nottext,aciklama);
        pane.getChildren().remove(3);
    }

    @FXML
    void vazgecbutonunatiklandi(ActionEvent event) {
        AnchorPane pane = (AnchorPane) notturupane.getParent();
        pane.getChildren().remove(3);
    }
    
    @FXML
    private void initialize() {
    }
}
