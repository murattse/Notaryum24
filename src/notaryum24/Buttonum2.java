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
import javafx.scene.layout.VBox;

/**
 *
 * @author murat.secilmis
 */
public class Buttonum2 extends AnchorPane {
    
    public Buttonum2() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Buttonum2.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private Button buton2;

    @FXML
    void notturubutonutikla(ActionEvent event) {
        
        //üçüncü ekranı eklemek için
        ucuncupencere ucuncu = new ucuncupencere();
        HBox hb = (HBox)buton2.getParent();
        VBox vb = (VBox)hb.getParent();
        AnchorPane pane = (AnchorPane)vb.getParent().getParent().getParent().getParent();
        
        //label text'i almak için
        AnchorPane ustpane2 = (AnchorPane)pane.getChildren().get(1);
        HBox hb2 = (HBox)ustpane2.getChildren().get(0);
        Label label1 = (Label)hb2.getChildren().get(0);
        String dilismi = label1.getText();
        
        AnchorPane anapane = (AnchorPane)pane.getParent();
        anapane.getChildren().clear();
        anapane.getChildren().add(ucuncu.getChildren().get(0));
        
        //ilk labeli eklemek için 
        UstLabel1 ilklabel = new UstLabel1();
        AnchorPane pane2 = (AnchorPane) anapane.getChildren().get(0);
        AnchorPane ustpane = (AnchorPane)pane2.getChildren().get(1);
        HBox usthb = (HBox) ustpane.getChildren().get(0);
        usthb.getChildren().add(ilklabel.getChildren().get(0));
        Label ilklabelim = (Label)usthb.getChildren().get(0);
        ilklabelim.setText(dilismi);
        
        //ara label ">" ekleme
        UstLabel1 aralabel = new UstLabel1();
        usthb.getChildren().add(aralabel.getChildren().get(0));
        Label aralabelim = (Label)usthb.getChildren().get(1);
        aralabelim.setText(">");
        
        //ikinci labeli eklemek için 
        UstLabel1 ikincilabel = new UstLabel1();
        String notismi = buton2.getText();
        usthb.getChildren().add(ikincilabel.getChildren().get(0));
        Label ikincilabelim = (Label)usthb.getChildren().get(2);
        ikincilabelim.setText(notismi);
        
        //scrollpane içindeki vbox'ı bulmak
        ScrollPane sc = (ScrollPane)pane2.getChildren().get(2);
        VBox vbm = (VBox) sc.getContent();
        
        //vbox içine label koymak için
        Veritabani veritabani = new Veritabani();
        String onisim ="";
        int sayi =usthb.getChildren().size();
        for(int i = 0; i<sayi;i++){
            Label lab = (Label)usthb.getChildren().get(i);
            if(i==0){
                onisim+=lab.getText();
            }
            else{
                if(!lab.getText().equals(">")){
                onisim+=("_"+lab.getText());
                System.out.println("tel "+onisim);
                }
            }
        }
        /*
        int baslik_sayisi = veritabani.BaslikListele(onisim).size();
        for(int i=0;i<baslik_sayisi;i++){
            AltLabel altlabel = new AltLabel();
            Label altlabelim = (Label) altlabel.getChildren().get(0);
            altlabelim.setText(veritabani.BaslikListele(onisim).get(i));
            vbm.getChildren().add(altlabelim);
        }
        */
        ////////////////////////////////////////////////////////////////////////
        //vbox içine label mı yoksa text area mı koyulacak anlamak için
        int baslik_sayisi;
        AnchorPane pane3 = (AnchorPane) anapane.getChildren().get(0);//xxxxxxxxxxxxxxxxxxxxxxxxxxx
        ScrollPane scm = (ScrollPane)pane3.getChildren().get(2);
        VBox vbcik=(VBox) scm.getContent();//xxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        baslik_sayisi = veritabani.BaslikListele(onisim).size();
        if(baslik_sayisi>0){
            if(veritabani.BaslikListele(onisim).get(0)!=null){
                //vbox içine label eklemek için
                baslik_sayisi = veritabani.BaslikListele(onisim).size();
                for(int i=0;i<baslik_sayisi;i++){
                AltLabel altlabel = new AltLabel();
                Label altlabelim = (Label) altlabel.getChildren().get(0);
                altlabelim.setText(veritabani.BaslikListele(onisim).get(i));
                vbcik.getChildren().add(altlabelim);
                }
            }else if(veritabani.NotListele(onisim).get(0)!=null){
                //vbox içine text area eklemek için
                
                NotText nottext = new NotText();
                vbcik.getChildren().add(nottext.getChildren().get(1));
                for(int i=0; i<baslik_sayisi;i++){
                    NotText nottext2 = new NotText();
                    vbcik.getChildren().add(nottext2.getChildren().get(0));//vb
                    HBox hbm = (HBox)vbcik.getChildren().get(i+1);
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
                vbcik.getChildren().clear();
            } 
        }
        else{
            vbcik.getChildren().clear();
        }
    }
    @FXML
    private void initialize() {
    }
}
