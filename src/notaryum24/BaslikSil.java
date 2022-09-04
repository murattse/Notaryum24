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
public class BaslikSil extends AnchorPane {
    
    public BaslikSil() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BaslikSil.fxml"));
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
        String onisim="";
        AnchorPane pane = (AnchorPane) notturupane.getParent();
        AnchorPane ustpane = (AnchorPane)pane.getChildren().get(1);
        HBox hb = (HBox)ustpane.getChildren().get(0);
        int sayi = hb.getChildren().size();
        for(int i = 0; i<sayi;i++){
            Label lab = (Label)hb.getChildren().get(i);
            if(i==0){
                onisim+=lab.getText().replaceAll(" ", "");
            }
            else{
                if(!lab.getText().equals(">")){
                onisim+=("_"+lab.getText().replaceAll(" ", ""));
                }
            }
        }
        String baslik_ismi = combom.getValue().replaceAll(" ", "");
        String baslik_ismi1=combom.getValue();
        String onisim1=onisim+"_"+combom.getValue().replaceAll(" ", "");
        
        Veritabani veritabani = new Veritabani();
        
        int listesayisi = veritabani.BaslikListele(onisim1).size();
        
        if(listesayisi==0){
            veritabani.BaslikSil(onisim, baslik_ismi);
            veritabani.BaslikListesindenSil(onisim, baslik_ismi1);
            pane.getChildren().remove(3);
        }
        else if(listesayisi>0){
            uyarmalabeli.setText("Başlığın içi dolu olduğu için silme işlemi gerçekleştirilemiyor.");//Başlığın içi dolu olduğu için ismi değiştirilemiyor.
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
