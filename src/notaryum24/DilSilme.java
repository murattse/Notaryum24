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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author murat.secilmis
 */
public class DilSilme extends AnchorPane {
    
    public DilSilme() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DilSilme.fxml"));
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
        Veritabani veritabani = new Veritabani();
        
        String dilismi = combom.getValue();
        String dilismi1 = combom.getValue().replaceAll(" ", "");
        
        int liste_sayisi = veritabani.NotTurunuListele(dilismi1).size();
        if(liste_sayisi==0){
            veritabani.DilTablosunuSil(dilismi1);
            veritabani.DilListesindenSil(dilismi);
            
            AnchorPane pane = (AnchorPane)notturupane.getParent();
            pane.getChildren().remove(2);
        }
        else{
            uyarmalabeli.setText("Dil Başlığının içi dolu olduğu için silme işlemi gerçekleştirilemiyor.");//Başlığın içi dolu olduğu için ismi değiştirilemiyor.
        }
    }

    @FXML
    void vazgecbutonunatiklandi(ActionEvent event) {
        AnchorPane pane = (AnchorPane)notturupane.getParent();
        pane.getChildren().remove(2);
    }
    
    @FXML
    private void initialize() {
    }
}
