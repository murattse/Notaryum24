/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Murat
 */
public class AltLabel extends AnchorPane {
    
    public AltLabel() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AltLabel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private Label altlabel;

    @FXML
    void altlabelclicked(MouseEvent event) {
        String text =altlabel.getText();
        //ustpane bulmak için
        VBox vb = (VBox)altlabel.getParent();
        AnchorPane pane = (AnchorPane)vb.getParent().getParent().getParent().getParent();
        AnchorPane ustpane2;
        ustpane2=(AnchorPane)pane.getChildren().get(1);
        
        HBox hb2 = (HBox)ustpane2.getChildren().get(0);
        //hb2 yani ustpane içindeki hbox'a label eklemek için
        UstLabel1 araustlabel = new UstLabel1();
        hb2.getChildren().add(araustlabel.getChildren().get(0));
        Label aralab = (Label) hb2.getChildren().get(hb2.getChildren().size()-1);
        aralab.setText(">");
        
        UstLabel1 yeniustlabel = new UstLabel1();
        hb2.getChildren().add(yeniustlabel.getChildren().get(0));
        Label lab = (Label) hb2.getChildren().get(hb2.getChildren().size()-1);
        lab.setText(text);
        //vbox'ı güncelle
        vb.getChildren().clear();
        
        //ust labellardan veritabanı ismi bulunur
        Veritabani veritabani = new Veritabani();
        String onisim ="";
        int sayi =hb2.getChildren().size();
        for(int i = 0; i<sayi;i++){
            Label label = (Label)hb2.getChildren().get(i);
            if(i==0){
                String labtext = label.getText().replaceAll(" ", "");
                onisim+=labtext;
            }
            else{
                if(!label.getText().equals(">")){
                    String labtext = label.getText().replaceAll(" ", "");
                    onisim+=("_"+labtext);
                }
            }
        }
        
        //vbox içine label mı yoksa text area mı koyulacak anlamak için
        int baslik_sayisi;
        baslik_sayisi = veritabani.BaslikListele(onisim).size();
        if(baslik_sayisi>0){
            if(veritabani.BaslikListele(onisim).get(0)!=null){
                //vbox içine label eklemek için
                baslik_sayisi = veritabani.BaslikListele(onisim).size();
                for(int i=0;i<baslik_sayisi;i++){
                AltLabel altlabel = new AltLabel();
                Label altlabelim = (Label) altlabel.getChildren().get(0);
                altlabelim.setText(veritabani.BaslikListele(onisim).get(i));
                vb.getChildren().add(altlabelim);
                }
            }else if(veritabani.NotListele(onisim).get(0)!=null){
                //vbox içine text area eklemek için
                NotText nottext = new NotText();
                vb.getChildren().add(nottext.getChildren().get(1));
                for(int i=0; i<baslik_sayisi;i++){
                    NotText nottext2 = new NotText();
                    vb.getChildren().add(nottext2.getChildren().get(0));
                    HBox hbm = (HBox)vb.getChildren().get(i+1);
                    TextArea text1 = (TextArea)hbm.getChildren().get(0);
                    TextArea text2 = (TextArea)hbm.getChildren().get(1);
                    TextArea text3 = (TextArea)hbm.getChildren().get(2);
                    String not=veritabani.NotListele(onisim).get(i);
                    String metod=veritabani.MetodListele(onisim).get(i);
                    String aciklama=veritabani.AciklamaListele(onisim).get(i);
                    text1.setText(not);
                    text2.setText(metod);
                    text3.setText(aciklama);
                } 
            }
            else{
                vb.getChildren().clear();
            } 
        }
        else{
            vb.getChildren().clear();
        }
    }

    @FXML
    void altlabelentered(MouseEvent event) {
        altlabel.setUnderline(true);
    }

    @FXML
    void altlabelexit(MouseEvent event) {
        altlabel.setUnderline(false);
    }
    
    public String ustlabelisimlerinibirlestirme(){
        //vbox içine label koymak için
        VBox vb = (VBox)altlabel.getParent();
        AnchorPane pane = (AnchorPane)vb.getParent().getParent().getParent().getParent();
        AnchorPane ustpane2 = (AnchorPane)pane.getChildren().get(1);
        HBox hb2 = (HBox)ustpane2.getChildren().get(0);
        int sayi = hb2.getChildren().size();
        String isim="";
        for(int i = 0; i<sayi;i++){
            Label lab = (Label)hb2.getChildren().get(i);
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
