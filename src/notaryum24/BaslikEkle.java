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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Murat
 */
public class BaslikEkle extends AnchorPane {
    
    public BaslikEkle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BaslikEkle.fxml"));
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
    private TextField baslikismitextalani;

    @FXML
    private Button eklebutonu;
    
    @FXML
    private Button vazgecbutonu;

    @FXML
    void baslikeklebutonunatiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        String onisim="";
        String baslik_ismi = baslikismitextalani.getText();
        String baslik_adi = baslik_ismi.replaceAll(" ", "");
        AnchorPane pane = (AnchorPane)notturupane.getParent();
        AnchorPane ustpane = (AnchorPane)pane.getChildren().get(1);
        HBox hb = (HBox)ustpane.getChildren().get(0);
        int sayi = hb.getChildren().size();
        for(int i = 0; i<sayi;i++){
            Label lab = (Label)hb.getChildren().get(i);
            if(i==0){
                onisim+=lab.getText();//.replaceAll(" ", "");
            }
            else{
                if(!lab.getText().equals(">")){
                onisim+=("_"+lab.getText());//.replaceAll(" ", ""));
                }
            }
        }
        String onisim1 = onisim.replaceAll(" ", "");
        veritabani.BaslikYarat(onisim1, baslik_adi);
        veritabani.NotTuruListesineEkle(onisim1, baslik_ismi);
        pane.getChildren().remove(3);

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
