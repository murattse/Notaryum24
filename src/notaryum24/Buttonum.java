/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import com.sun.javafx.scene.control.skin.ScrollPaneSkin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Murat
 */
public class Buttonum extends AnchorPane {
    
    public Buttonum() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Buttonum.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private Button buton;
    @FXML
    void dilbutonutikla(ActionEvent event) {
        //ikinci ekranı eklemek için
        ikincipencere ikinci = new ikincipencere();
        HBox hb = (HBox)buton.getParent();
        VBox vb = (VBox)hb.getParent();
        AnchorPane pane = (AnchorPane)vb.getParent().getParent().getParent().getParent();
        AnchorPane anapane = (AnchorPane)pane.getParent();
        anapane.getChildren().clear();
        anapane.getChildren().add(ikinci.getChildren().get(0));
        //labeli eklemek için 
        UstLabel1 ustlabel = new UstLabel1();
        String dilismi = buton.getText();
        AnchorPane pane2 = (AnchorPane) anapane.getChildren().get(0);
        AnchorPane ustpane = (AnchorPane)pane2.getChildren().get(1);
        HBox usthb = (HBox) ustpane.getChildren().get(0);
        usthb.getChildren().add(ustlabel.getChildren().get(0));
        Label ustlabelim = (Label)usthb.getChildren().get(0);
        ustlabelim.setText(dilismi);
        
        Veritabani veritabani = new Veritabani();
        int notturubutonsayisi = veritabani.NotTurunuListele(dilismi).size();
        int hboxsayisi= (int) Math.ceil(notturubutonsayisi/3f);
        //hbox için
        for(int i =0; i<hboxsayisi;i++){
            Hboxum hbo = new Hboxum();
            pane2 = (AnchorPane) anapane.getChildren().get(0);
            ScrollPane sc = (ScrollPane)pane2.getChildren().get(2);
            VBox vb2 = (VBox)sc.getContent();
            vb2.getChildren().add(hbo.getChildren().get(0));
        }
        //butonlar için
        for(int i =0; i<notturubutonsayisi;i++){
            int sayi = (int) Math.floor(i/3);
            //vbox'ı bulmak için
            pane2 = (AnchorPane) anapane.getChildren().get(0);
            ScrollPane sc = (ScrollPane)pane2.getChildren().get(2);
            VBox vb2 = (VBox)sc.getContent();
            //butonları yerleştirir
            HBox hbm = (HBox)vb2.getChildren().get(sayi);
            Buttonum2 buton2 = new Buttonum2();
            Button but = (Button) buton2.getChildren().get(0);
            but.setText(veritabani.NotTurunuListele(dilismi).get(i));
            hbm.getChildren().add(buton2.getChildren().get(0)); 
        }
    }
    @FXML
    private void initialize() {
    }
}
