/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Murat
 */
public class UstLabel1 extends AnchorPane {
    
    public UstLabel1() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UstLabel1.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private Label label1;

    @FXML
    void ustlabelclicked(MouseEvent event) {
        int labelindex=0;
        String labeltext = label1.getText();
        //tıklanan label'in indexini bulmak için
        HBox hb = (HBox)label1.getParent();
        int labelsayisi = hb.getChildren().size();
        for(int i =0;i<labelsayisi;i++){
            Label lab = (Label) hb.getChildren().get(i);
            String text = lab.getText();
            if(text.equals(labeltext)){
                labelindex=i+1;
            }
        }
        //tıklanan labelin veritabani listesinde ismini bulmak için
        String onisim="";
        for(int i = 0; i<labelindex;i++){
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
        
        if(labelindex==1&&!label1.getText().equals(">")){
            ikincipencereyukle();
        }
        else if(labelindex==3&&!label1.getText().equals(">")){
            ucuncupencereyukle();
        }
        else if (labelindex>3&&!label1.getText().equals(">")){
            ucustupencereyukle();
        }
    }
    
    @FXML
    void ustlabelentered(MouseEvent event) {
        if(!label1.getText().equals(">")){
            label1.setUnderline(true);
        }
    }

    @FXML
    void ustlabelexit(MouseEvent event) {
        if(!label1.getText().equals(">")){
            label1.setUnderline(false);
        }
    }
    
    public void ikincipencereyukle(){
        //ikinci ekranı eklemek için
        ikincipencere ikinci = new ikincipencere();
        HBox hb = (HBox)label1.getParent();
        AnchorPane ustpane = (AnchorPane)hb.getParent();
        AnchorPane pane = (AnchorPane)ustpane.getParent();
        AnchorPane anapane = (AnchorPane) pane.getParent();
        anapane.getChildren().clear();
        anapane.getChildren().add(ikinci.getChildren().get(0));
        //labeli eklemek için 
        UstLabel1 ustlabel = new UstLabel1();
        String dilismi = label1.getText();
        AnchorPane pane2 = (AnchorPane) anapane.getChildren().get(0);
        AnchorPane ustpane2 = (AnchorPane)pane2.getChildren().get(1);
        HBox usthb = (HBox) ustpane2.getChildren().get(0);
        usthb.getChildren().add(ustlabel.getChildren().get(0));
        Label ustlabelim = (Label)usthb.getChildren().get(0);
        ustlabelim.setText(dilismi);
        
        Veritabani veritabani = new Veritabani();
        int notturubutonsayisi = veritabani.NotTurunuListele(dilismi).size();
        int hboxsayisi= (int) Math.ceil(notturubutonsayisi/3f);
        //hbox için
        for(int i =0; i<hboxsayisi;i++){
            Hboxum hbo = new Hboxum();
            pane = (AnchorPane) anapane.getChildren().get(0);
            ScrollPane sc = (ScrollPane)pane.getChildren().get(2);
            VBox vb2 = (VBox)sc.getContent();
            vb2.getChildren().add(hbo.getChildren().get(0));
        }
        //butonlar için
        for(int i =0; i<notturubutonsayisi;i++){
            int sayi = (int) Math.floor(i/3);
            //vbox'ı bulmak için
            pane = (AnchorPane) anapane.getChildren().get(0);
            ScrollPane sc = (ScrollPane)pane.getChildren().get(2);
            VBox vb2 = (VBox)sc.getContent();
            //butonları yerleştirir
            HBox hbm = (HBox)vb2.getChildren().get(sayi);
            Buttonum2 buton2 = new Buttonum2();
            Button but = (Button) buton2.getChildren().get(0);
            but.setText(veritabani.NotTurunuListele(dilismi).get(i));
            hbm.getChildren().add(buton2.getChildren().get(0)); 
        }
    }
    public void ucuncupencereyukle(){
        //üçüncü ekranı eklemek için
        ArrayList<String>labellistesi = new ArrayList<String>();
        ucuncupencere ucuncu = new ucuncupencere();
        HBox hb = (HBox)label1.getParent();
        AnchorPane ustpane = (AnchorPane)hb.getParent();
        AnchorPane pane = (AnchorPane)ustpane.getParent();
        AnchorPane anapane = (AnchorPane) pane.getParent();
        int labelsayisi=hb.getChildren().size();
        for(int i=0; i<labelsayisi;i++){
            Label lab = (Label)hb.getChildren().get(i);
            String text = lab.getText();
            labellistesi.add(text);
        }
        anapane.getChildren().clear();
        anapane.getChildren().add(ucuncu.getChildren().get(0));
        
        //ilk labeli eklemek için 
        UstLabel1 ilklabel = new UstLabel1();
        AnchorPane pane2 = (AnchorPane) anapane.getChildren().get(0);
        AnchorPane ustpane2 = (AnchorPane)pane2.getChildren().get(1);
        HBox usthb = (HBox) ustpane2.getChildren().get(0);
        usthb.getChildren().add(ilklabel.getChildren().get(0));
        Label ilklabelim = (Label)usthb.getChildren().get(0);
        ilklabelim.setText(labellistesi.get(0));
        
        //ara label ">" ekleme
        UstLabel1 aralabel = new UstLabel1();
        usthb.getChildren().add(aralabel.getChildren().get(0));
        Label aralabelim = (Label)usthb.getChildren().get(1);
        aralabelim.setText(">");
        
        //ikinci labeli eklemek için 
        UstLabel1 ikincilabel = new UstLabel1();
        usthb.getChildren().add(ikincilabel.getChildren().get(0));
        Label ikincilabelim = (Label)usthb.getChildren().get(2);
        ikincilabelim.setText(labellistesi.get(2));
        
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
                }
            }
        }
        int baslik_sayisi = veritabani.BaslikListele(onisim).size();
        for(int i=0;i<baslik_sayisi;i++){
            AltLabel altlabel = new AltLabel();
            Label altlabelim = (Label) altlabel.getChildren().get(0);
            altlabelim.setText(veritabani.BaslikListele(onisim).get(i));
            vbm.getChildren().add(altlabelim);
        }
    }
    public void ucustupencereyukle(){
        Veritabani veritabani = new Veritabani();
        ArrayList<String>labellistesi = new ArrayList<String>();
        int labelindex=0;
        String labeltext = label1.getText();
        //tıklanan label'in indexini bulmak için
        HBox hb = (HBox)label1.getParent();
        int labelsayisi = hb.getChildren().size();
        for(int i =0;i<labelsayisi;i++){
            Label lab = (Label) hb.getChildren().get(i);
            String text = lab.getText();
            if(text.equals(labeltext)){
                labelindex=i+1;
            }
        }
        //tıklanan label'a kadar olan label isimlerini toplamak için
        for(int i=0;i<labelindex;i++){
            Label lab = (Label) hb.getChildren().get(i);
            String text = lab.getText();
            labellistesi.add(text);
        }
        //ust label isimlerini toplamak için
        String onisim ="";
        for(int i = 0; i<labelindex;i++){
            Label lab = (Label)hb.getChildren().get(i);
            if(i==0){
                onisim+=lab.getText();
            }
            else{
                if(!lab.getText().equals(">")){
                onisim+=("_"+lab.getText());
                }
            }
        }
        //ust labellerdan bir liste yapmak için
        ArrayList<Label>ustlabellistesi = new ArrayList<Label>();
        for(int i = 0; i<labelindex;i++){
            Label lab = (Label)hb.getChildren().get(i);
            ustlabellistesi.add(lab);
        }
        //hbox içindeki üstlabel'leri düzenlemek için
        hb.getChildren().clear();
        for(int i = 0; i<ustlabellistesi.size();i++){
            hb.getChildren().add(ustlabellistesi.get(i));
        }
        
        String onisim1 = onisim.replaceAll(" ", "");
        int baslik_sayisi = veritabani.BaslikListele(onisim1).size();
        //scrollpane içindeki vbox'ı bulmak ve temizlemek
        AnchorPane ustpane = (AnchorPane) hb.getParent();
        AnchorPane pane2 = (AnchorPane) ustpane.getParent();
        if(!pane2.getChildren().get(2).getId().equals("tab")){
            ScrollPane sc = (ScrollPane)pane2.getChildren().get(2);
            VBox vbm = (VBox) sc.getContent();
            vbm.getChildren().clear();


            //vbox içine label mı yoksa text area mı koyulacak anlamak için
            baslik_sayisi = veritabani.BaslikListele(onisim1).size();
            if(baslik_sayisi>0){
                if(veritabani.BaslikListele(onisim1).get(0)!=null){
                    //vbox içine label eklemek için
                    baslik_sayisi = veritabani.BaslikListele(onisim1).size();
                    for(int i=0;i<baslik_sayisi;i++){
                    AltLabel altlabel = new AltLabel();
                    Label altlabelim = (Label) altlabel.getChildren().get(0);
                    altlabelim.setText(veritabani.BaslikListele(onisim1).get(i));
                    vbm.getChildren().add(altlabelim);
                    }
                }else if(veritabani.NotListele(onisim1).get(0)!=null){
                    //vbox içine text area eklemek için
                    NotText nottext = new NotText();
                    vbm.getChildren().add(nottext.getChildren().get(1));
                    for(int i=0; i<baslik_sayisi;i++){
                        NotText nottext2 = new NotText();
                        vbm.getChildren().add(nottext2.getChildren().get(0));
                        HBox hbm = (HBox)vbm.getChildren().get(i+1);
                        TextArea text1 = (TextArea)hbm.getChildren().get(0);
                        TextArea text2 = (TextArea)hbm.getChildren().get(1);
                        TextArea text3 = (TextArea)hbm.getChildren().get(2);
                        String not=veritabani.NotListele(onisim1).get(i);
                        String metod=veritabani.MetodListele(onisim1).get(i);
                        String aciklama=veritabani.AciklamaListele(onisim1).get(i);
                        text1.setText(not);
                        text2.setText(metod);
                        text3.setText(aciklama);
                    } 
                }
                else{
                    vbm.getChildren().clear();
                } 
            } 
        }
        else{
            //resimpencesini silmek ve üçüncü pencereyi eklemek için
            HBox hbox = (HBox)label1.getParent();
            AnchorPane ustpane4 = (AnchorPane)hbox.getParent();
            AnchorPane anapane = (AnchorPane)ustpane4.getParent();
            anapane.getChildren().clear();
            ucuncupencere ucuncu = new ucuncupencere();
            anapane.getChildren().add(ucuncu.getChildren().get(0));
            
            //üçüncü pencerenin içindeki vbox'ı bulmak için
            AnchorPane pane3 = (AnchorPane)anapane.getChildren().get(0);
            ScrollPane sc = (ScrollPane)pane3.getChildren().get(2);
            VBox vbm = (VBox) sc.getContent();

            //üsthbox içine labelları eklemek için
            AnchorPane ustpane3 = (AnchorPane)pane3.getChildren().get(1);
            HBox hbox3 = (HBox)ustpane3.getChildren().get(0);
            for(int i=0;i<labellistesi.size();i++){
                UstLabel1 ustlabel = new UstLabel1();
                hbox3.getChildren().add(ustlabel.getChildren().get(0));
                Label lab = (Label)hbox3.getChildren().get(i);
                lab.setText(labellistesi.get(i));
            }
            
            //vbox içine label mı yoksa text area mı koyulacak anlamak için
            baslik_sayisi = veritabani.BaslikListele(onisim1).size();
            if(baslik_sayisi>0){
                if(veritabani.BaslikListele(onisim1).get(0)!=null){
                    //vbox içine label eklemek için
                    baslik_sayisi = veritabani.BaslikListele(onisim1).size();
                    for(int i=0;i<baslik_sayisi;i++){
                    AltLabel altlabel = new AltLabel();
                    Label altlabelim = (Label) altlabel.getChildren().get(0);
                    altlabelim.setText(veritabani.BaslikListele(onisim1).get(i));
                    vbm.getChildren().add(altlabelim);
                    }
                }else if(veritabani.NotListele(onisim1).get(0)!=null){
                    //vbox içine text area eklemek için
                    NotText nottext = new NotText();
                    vbm.getChildren().add(nottext.getChildren().get(1));
                    for(int i=0; i<baslik_sayisi;i++){
                        NotText nottext2 = new NotText();
                        vbm.getChildren().add(nottext2.getChildren().get(0));
                        HBox hbm = (HBox)vbm.getChildren().get(i+1);
                        TextArea text1 = (TextArea)hbm.getChildren().get(0);
                        TextArea text2 = (TextArea)hbm.getChildren().get(1);
                        TextArea text3 = (TextArea)hbm.getChildren().get(2);
                        String not=veritabani.NotListele(onisim1).get(i);
                        String metod=veritabani.MetodListele(onisim1).get(i);
                        String aciklama=veritabani.AciklamaListele(onisim1).get(i);
                        text1.setText(not);
                        text2.setText(metod);
                        text3.setText(aciklama);
                    } 
                }
                else{
                    vbm.getChildren().clear();
                } 
            }
            else{
                vbm.getChildren().clear();
            }
        }
    }
    @FXML
    private void initialize() {
    }
}
