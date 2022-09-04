/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
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
public class ResimPenceresi extends AnchorPane {
    
    public ResimPenceresi() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResimPenceresi.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    private AnchorPane minipane3;

    @FXML
    private Button resimeklebutonu;
    
    @FXML
    private Button resimsilbutonu;

    @FXML
    private Button refreshbutonu;

    @FXML
    private AnchorPane ustpane3;

    @FXML
    private HBox usthbox;

    @FXML
    private Button geri1butonu;
    
    @FXML
    private TabPane tab;

    @FXML
    private Tab tab1;

    @FXML
    private ScrollPane scrollresim;

    @FXML
    public HBox hboxlabel;

    @FXML
    public HBox hbox;

    @FXML
    public VBox textareavbox;

    @FXML
    private Tab tab2;
    
    @FXML
    void geri1butonutiklandi(ActionEvent event) {
        textareadegisikliklerikaydet();
        AnchorPane pane = (AnchorPane)minipane3.getParent();

        Veritabani veritabani = new Veritabani();
        
        //ust labellardan veritabanı ismi bulunur
        String onisim ="";
        ArrayList<String>labelisimlistesi = new ArrayList<String>();
        int sayi =usthbox.getChildren().size();
        for(int i = 0; i<sayi;i++){
            Label label = (Label)usthbox.getChildren().get(i);
            String labisim = label.getText();
            labelisimlistesi.add(labisim);
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
        
        pane.getChildren().clear();
        ucuncupencere ucuncu = new ucuncupencere();
        //ücüncüpencere içindeki vbox ve ustbhox ları bulmak için
        pane.getChildren().add(ucuncu.getChildren().get(0));
        AnchorPane pane3 = (AnchorPane)pane.getChildren().get(0);
        ScrollPane sc = (ScrollPane)pane3.getChildren().get(2);
        VBox vb = (VBox)sc.getContent();
        
        //üsthbox içine labelları eklemek için
        AnchorPane ustpane = (AnchorPane)pane3.getChildren().get(1);
        HBox hbox = (HBox)ustpane.getChildren().get(0);
        for(int i=0;i<sayi;i++){
            UstLabel1 ustlabel = new UstLabel1();
            hbox.getChildren().add(ustlabel.getChildren().get(0));
            Label lab = (Label)hbox.getChildren().get(i);
            lab.setText(labelisimlistesi.get(i));
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
    void refreshbutonutikla4(ActionEvent event) {
        textareadegisikliklerikaydet();
    }

    @FXML
    void resimeklebutonutiklandi(ActionEvent event) {
        Imagenot imagenot = new Imagenot();
        NotEkle not2ekle = new NotEkle();
        TextAreanot textarea = new TextAreanot();
        hbox.getChildren().add(imagenot.getChildren().get(0));
        hboxlabel.getChildren().add(not2ekle.notlabel);
        int eklenen_son_labelin_indexi= hboxlabel.getChildren().size();
        Label son_eklenen_lab = (Label)hboxlabel.getChildren().get(eklenen_son_labelin_indexi-1);
        son_eklenen_lab.setText(Integer.toString(eklenen_son_labelin_indexi)+". Resim");
        textareavbox.getChildren().add(textarea.getChildren().get(0));
        TextArea textareacik = (TextArea)textareavbox.getChildren().get(eklenen_son_labelin_indexi);
        textareacik.setText(eklenen_son_labelin_indexi+".Resim Açıklaması");
        textareadegisikliklerikaydet();
    }
    
    @FXML
    void resimsilbutonutiklandi(ActionEvent event) {
        textareadegisikliklerikaydet();
        ResimSil resimsil = new ResimSil();
        AnchorPane pane = (AnchorPane) minipane3.getParent();
        pane.getChildren().add(resimsil.getChildren().get(0));
        
        int resim_sayisi = textareavbox.getChildren().size()-1;//resim ile text area sayilari aynı olduğu için ve ayrıca vbox içinde fazladan bir label olduğundan
        
        //text areaların metinlerini liste haline getirmek için
        ArrayList<String> textlist = new ArrayList<String>();
        for(int i = 1; i<resim_sayisi+1;i++){
            TextArea textareacik = (TextArea) textareavbox.getChildren().get(i);
            textlist.add(textareacik.getText());
        }
        //resim isimlerini listelemek için
        ArrayList<String> resimisimlist = new ArrayList<String>();
        for(int i = 0; i<resim_sayisi;i++){
            TextArea texta = (TextArea) textareavbox.getChildren().get(i+1);//xxxxxxxxxxxxxxxxxxxxxxx
            
            //resimisimlist.add((i+1)+".Resim");
            resimisimlist.add(texta.getText());//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        }
        
        for(int i=0; i<resim_sayisi;i++){
            resimsil.combom.getItems().add(resimisimlist.get(i));
        }
    }
    
    @FXML
    void usthboxtiklandi(MouseEvent event) {
        textareadegisikliklerikaydet();
    }
    
    @FXML
    private void initialize() {
    }
    
    public void textareadegisikliklerikaydet(){
        Veritabani veritabani = new Veritabani();
        NotText nottext = new NotText();
        ArrayList<String>textarealistesi = new ArrayList<String>();
        int sayi =textareavbox.getChildren().size()-1;
        for(int i=0;i<sayi;i++){
            TextArea textarea = (TextArea)textareavbox.getChildren().get(i+1);
            String text = textarea.getText();
            textarealistesi.add(text);
            System.out.println(textarealistesi);
        }
        if(veritabani.ResimAciklamalariniListele(nottext.nottext).size()>0&&textarealistesi.size()==veritabani.ResimAciklamalariniListele(nottext.nottext).size()){
            for(int i=0;i<sayi;i++){
                if(!veritabani.ResimAciklamalariniListele(nottext.nottext).get(i).equals(textarealistesi.get(i))){
                    veritabani.ResimAciklamalariniEkle(nottext.nottext, veritabani.ResimAciklamalariniListele(nottext.nottext).get(i), textarealistesi.get(i));
                }
            }
        }
    }
}
