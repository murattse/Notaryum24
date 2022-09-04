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
import javafx.scene.layout.HBox;

/**
 *
 * @author murat.secilmis
 */
public class NotTuruSilme extends AnchorPane {
    
    public NotTuruSilme() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotTuruSilme.fxml"));
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
        
        AnchorPane pane = (AnchorPane) notturupane.getParent();
        AnchorPane ustpane = (AnchorPane)pane.getChildren().get(1);
        HBox hb = (HBox)ustpane.getChildren().get(0);
        Label lab = (Label)hb.getChildren().get(0);
        String dilismi = lab.getText();
        String dilismi1 = lab.getText().replaceAll(" ", "");
        
        String notturuismi = combom.getValue();
        String notturuismi1 = combom.getValue().replaceAll(" ", "");
        
        int notturusayisi = veritabani.BaslikListele(dilismi1+"_"+notturuismi1).size();
        System.out.println("dfdfd "+notturusayisi);
        if(notturusayisi==0){
            veritabani.NotTuruTablosunuSil(dilismi1, notturuismi1);
            veritabani.NotTurunuListedindenSil(dilismi1, notturuismi);
            pane.getChildren().remove(3);
        }
        else{
            uyarmalabeli.setText("Not Türü Başlığının içi dolu olduğu için silme işlemi gerçekleştirilemiyor.");//Başlığın içi dolu olduğu için ismi değiştirilemiyor.
        }
    }

    @FXML
    void vazgecbutonunatiklandi(ActionEvent event) {
        AnchorPane pane = (AnchorPane)notturupane.getParent();
        pane.getChildren().remove(3);
    }
    
    @FXML
    private void initialize() {
    }
}
