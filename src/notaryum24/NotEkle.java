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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author murat.secilmis
 */
public class NotEkle extends AnchorPane {
    
    public NotEkle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotEkle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane minipane3;

    @FXML
    private Button eklenotbutonu;

    @FXML
    private Button vazgecbutonu;
    
    @FXML
    public Label notlabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextArea notarea;

    @FXML
    private TextArea aciklamaarea;
    
    @FXML
    private TextArea metodarea;
    
    @FXML
    private Label uyarilabeli;

    @FXML
    void eklenotbutonutiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        String notismi=notarea.getText();//.replaceAll(" ", "");
        String aciklama_ismi= aciklamaarea.getText();//.replaceAll(" ", "");
        String metodismi=metodarea.getText();//.replaceAll(" ", "");
        String onisim=ustlabelisimlerinibirlestirme();
        //
        System.out.println(onisim);
        int sayi = veritabani.NotListele(onisim).size();
        int notismivar=0;
        for(int i =0; i<sayi;i++){
            if(veritabani.NotListele(onisim).get(i).equals(notismi)){
                notismivar =+ 1;
            }
        }
        if(notismivar>0){
            uyarilabeli.setText("Bu isimde bir tablo var");
        }else{
            veritabani.NotEkle(onisim, notismi, metodismi, aciklama_ismi);
            String notismi2=notismi.replaceAll(" ", "");
            veritabani.NotYarat(notismi2);
            //not ekle'yi kapatmak için
            AnchorPane pane3 = (AnchorPane)pane.getParent();
            pane3.getChildren().remove(3);
        }
    }

    @FXML
    void vazgecbutonutiklandi(ActionEvent event) {
        AnchorPane pane3 = (AnchorPane)pane.getParent();
        pane3.getChildren().remove(3);
    }
    public String ustlabelisimlerinibirlestirme(){
        //veritabanı ismini bulmak için
        AnchorPane pane3 = (AnchorPane)pane.getParent();
        AnchorPane ustpane = (AnchorPane)pane3.getChildren().get(1);
        HBox usthbox = (HBox)ustpane.getChildren().get(0);
        int sayi = usthbox.getChildren().size();
        String isim="";
        for(int i = 0; i<sayi;i++){
            Label lab = (Label)usthbox.getChildren().get(i);
            if(i==0){
                String labtext=lab.getText().replaceAll(" ", "");
                isim+=labtext;
            }
            else{
                if(!lab.getText().equals(">")){
                    String labtext=lab.getText().replaceAll(" ", "");
                    isim+=("_"+labtext);
                }
            }
        }
        return isim;
    }
    @FXML
    private void initialize() {
    }
}
