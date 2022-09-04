/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Murat
 */
public class DilEklePenceresi extends AnchorPane {
    
    public DilEklePenceresi() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DilEklePenceresi.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private AnchorPane dilpane;
    
    @FXML
    private TextField diltextalani;

    @FXML
    private Button eklebutonu;
    
    @FXML
    private Button vazgecbutonu;

    @FXML
    void eklebutonunatiklandi(ActionEvent event) {
        //----------veritabanına kaydetme
        Veritabani veritabani = new Veritabani();
        veritabani.DilListesineDilEkle(diltextalani.getText());
        veritabani.DilTablosuYarat(diltextalani.getText());
        diltextalani.setText("");
        
        AnchorPane pane = (AnchorPane) dilpane.getParent();
        pane.getChildren().remove(2);

    }
    @FXML
    void vazgecbutonunatiklandi(ActionEvent event) {
        AnchorPane pane = (AnchorPane) dilpane.getParent();
        pane.getChildren().remove(2);
    }

    
    @FXML
    private void initialize() {
    }
}
