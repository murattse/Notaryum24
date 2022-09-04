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
public class NotTuruEkle extends AnchorPane {
    
    public NotTuruEkle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotTuruEkle.fxml"));
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
    private TextField noturutextalani;

    @FXML
    private Button notturueklebutonu;
    
    @FXML
    private Button vazgecbutonu;
    
    @FXML
    void notturueklebutonunatiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        //Label'ın textini bulmak için yazıldı aşağıdaki kodlar
        AnchorPane pane = (AnchorPane)notturupane.getParent();
        AnchorPane ustpane = (AnchorPane)pane.getChildren().get(1);
        HBox hb = (HBox)ustpane.getChildren().get(0);
        Label label = (Label)hb.getChildren().get(0);
        String dil = label.getText().replaceAll(" ","");
        
        String notturutext=noturutextalani.getText().replaceAll(" ", "");
        //veritabani.NotTuruYarat(notturutext, dil);
        veritabani.BaslikYarat(dil, notturutext);
        veritabani.DilTablosunaEkle(dil, noturutextalani.getText());
        pane.getChildren().remove(3);//Not turu ekle bölümünü siler

    }
    @FXML
    void vazgecbutonunatiklandi(ActionEvent event) {
        AnchorPane pane = (AnchorPane)notturupane.getParent();
        pane.getChildren().remove(3);//Not turu ekle bölümünü siler
    }
    @FXML
    private void initialize() {
    }
}
