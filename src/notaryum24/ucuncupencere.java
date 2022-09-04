/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ucuncupencere extends AnchorPane {
    
    public ucuncupencere() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ucuncupencere.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    int baslik_sayisi;
    int not_sayisi;
    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane minipane3;

    @FXML
    private Button baslikeklebutonu;
    
    @FXML
    private Button basliksilbutonu;
    
    @FXML
    private Button isimdegistirbutonu;
    
    @FXML
    private Button refreshbutonu;
    
    @FXML
    private AnchorPane ustpane3;

    @FXML
    private HBox usthbox;

    @FXML
    private Button geri1butonu;

    @FXML
    private ScrollPane scroll;

    @FXML
    void geri1butonutiklandi(ActionEvent event) {
        
        HBox usthb = (HBox) ustpane3.getChildren().get(0);
        Label ilklabel = (Label)usthb.getChildren().get(0);
        String dilismi = ilklabel.getText();
        //usthbox içinde bulunan label sayısı için
        int label_sayisi=usthb.getChildren().size();
        
        if(label_sayisi==3){
            //ikinci ekranı eklemek için
            ikincipencere ikinci = new ikincipencere();
            AnchorPane anapane = (AnchorPane)pane.getParent();
            anapane.getChildren().clear();
            anapane.getChildren().add(ikinci.getChildren().get(0));
            //labeli eklemek için 
            UstLabel1 ustlabel = new UstLabel1();
            AnchorPane pane2 = (AnchorPane) anapane.getChildren().get(0);
            AnchorPane ustpane = (AnchorPane)pane2.getChildren().get(1);
            HBox hboxim = (HBox) ustpane.getChildren().get(0);
            hboxim.getChildren().add(ustlabel.getChildren().get(0));
            Label ustlabelim = (Label)hboxim.getChildren().get(0);
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
        else if(label_sayisi>3){

            //üst hbox'ı güncellemek için
            usthbox.getChildren().remove(label_sayisi-1);
            usthbox.getChildren().remove(label_sayisi-2);
            //scroll pane içindeki vbox'ı güncellemek için
            VBox vb = (VBox) scroll.getContent();
            vb.getChildren().clear();
            
            //vbox içine label koymak için
            Veritabani veritabani = new Veritabani();
            String onisim ="";
            int sayi =usthb.getChildren().size();
            for(int i = 0; i<sayi;i++){
                Label lab = (Label)usthb.getChildren().get(i);
                if(i==0){
                    onisim+=lab.getText().replaceAll(" ", "");
                }
                else{
                    if(!lab.getText().equals(">")){
                    onisim+=("_"+lab.getText().replaceAll(" ", ""));
                    }
                }
            }
            int baslik_sayisi = veritabani.BaslikListele(onisim).size();
            for(int i=0;i<baslik_sayisi;i++){
                AltLabel altlabel = new AltLabel();
                Label altlabelim = (Label) altlabel.getChildren().get(0);
                altlabelim.setText(veritabani.BaslikListele(onisim).get(i));
                vb.getChildren().add(altlabelim);
            }
        }
    }

    @FXML
    void baslikeklebutonutiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        String isim = ustlabelisimlerinibirlestirme();
        baslik_sayisi = veritabani.BaslikListele(isim).size();
        System.out.println(baslik_sayisi);
        if(baslik_sayisi>0){
            if(veritabani.BaslikListele(isim).get(0)!=null){
                BaslikEkle baslikekle = new BaslikEkle();
                pane.getChildren().add(baslikekle.getChildren().get(0));
            }else if(veritabani.NotListele(isim).get(0)!=null){
                NotEkle notekle = new NotEkle();
                pane.getChildren().add(notekle.getChildren().get(0));
            }
            else{
                NotmuBaslikmi notmubaslikmi = new NotmuBaslikmi();
                pane.getChildren().add(notmubaslikmi.getChildren().get(0));
            } 
        }
        else{
            NotmuBaslikmi notmubaslikmi = new NotmuBaslikmi();
            pane.getChildren().add(notmubaslikmi.getChildren().get(0));
        }
        
    }
    
    @FXML
    void basliksilbutonutiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        BaslikSil basliksil = new BaslikSil();
        pane.getChildren().add(basliksil.getChildren().get(0));
        
        HBox usthb = (HBox) ustpane3.getChildren().get(0);
        String onisim ="";
        int sayi =usthb.getChildren().size();
        for(int i = 0; i<sayi;i++){
            Label lab = (Label)usthb.getChildren().get(i);
            if(i==0){
                onisim+=lab.getText().replaceAll(" ", "");
            }
            else{
                if(!lab.getText().equals(">")){
                onisim+=("_"+lab.getText().replaceAll(" ", ""));
                }
            }
        }
        int baslik_sayisi = veritabani.BaslikListele(onisim).size();
        for(int i=0; i<baslik_sayisi;i++){
            basliksil.combom.getItems().add(veritabani.BaslikListele(onisim).get(i));
        }
    }
    
    @FXML
    void isimdegistirbutonutiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        BaslikIsminiDegistir baslikdegistir =new BaslikIsminiDegistir();
        pane.getChildren().add(baslikdegistir.getChildren().get(0));
        
        HBox usthb = (HBox) ustpane3.getChildren().get(0);
        String onisim ="";
        int sayi =usthb.getChildren().size();
        for(int i = 0; i<sayi;i++){
            Label lab = (Label)usthb.getChildren().get(i);
            if(i==0){
                onisim+=lab.getText().replaceAll(" ", "");
            }
            else{
                if(!lab.getText().equals(">")){
                onisim+=("_"+lab.getText().replaceAll(" ", ""));
                }
            }
        }
        int baslik_sayisi = veritabani.BaslikListele(onisim).size();
        for(int i=0; i<baslik_sayisi;i++){
            baslikdegistir.combom.getItems().add(veritabani.BaslikListele(onisim).get(i));
        }
    }
    
    @FXML
    void refreshbutonutikla2(ActionEvent event) {
        refreshfonksiyonu();
    }
    public void refreshfonksiyonu(){
        //vbox'ı temizler
        AnchorPane anapane = (AnchorPane)pane.getParent();
        ScrollPane scm1 = (ScrollPane) pane.getChildren().get(2);
        VBox vbm1 = (VBox)scm1.getContent();
        vbm1.getChildren().clear();
        
        //-------------------------------------------------------------------
        //ust label isimlerini toplamak için
        String onisim ="";
        int labelindex=usthbox.getChildren().size();
        for(int i = 0; i<labelindex;i++){
            Label lab = (Label)usthbox.getChildren().get(i);
            if(i==0){
                String labtext=lab.getText().replaceAll(" ", "");
                onisim+=labtext;
            }
            else{
                if(!lab.getText().equals(">")){
                    String labtext=lab.getText().replaceAll(" ", "");
                    onisim+=("_"+labtext);
                }
            }
        }
        //----------------------------------------------------------------------
        //vbox içine label mı yoksa text area mı koyulacak anlamak için
        Veritabani veritabani = new Veritabani();
        baslik_sayisi = veritabani.BaslikListele(onisim).size();
        if(baslik_sayisi>0){
            if(veritabani.BaslikListele(onisim).get(0)!=null){
                //vbox içine label eklemek için
                baslik_sayisi = veritabani.BaslikListele(onisim).size();
                for(int i=0;i<baslik_sayisi;i++){
                AltLabel altlabel = new AltLabel();
                Label altlabelim = (Label) altlabel.getChildren().get(0);
                altlabelim.setText(veritabani.BaslikListele(onisim).get(i));
                vbm1.getChildren().add(altlabelim);
                }
            }else if(veritabani.NotListele(onisim).get(0)!=null){
                //vbox içine text area eklemek için
                NotText nottext = new NotText();
                vbm1.getChildren().add(nottext.getChildren().get(1));
                for(int i=0; i<baslik_sayisi;i++){
                    NotText nottext2 = new NotText();
                    vbm1.getChildren().add(nottext2.getChildren().get(0));
                    HBox hbm = (HBox)vbm1.getChildren().get(i+1);
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
                vbm1.getChildren().clear();
            } 
        }
        else{
            vbm1.getChildren().clear();
        }   
    }
    public String ustlabelisimlerinibirlestirme(){
        //vbox içine label koymak için
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
