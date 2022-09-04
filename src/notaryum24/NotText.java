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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Murat
 */
public class NotText extends AnchorPane {
    
    public NotText() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotText.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private TextArea notarea;

    @FXML
    private TextArea metodarea;

    @FXML
    private TextArea aciklamaarea;
    
    @FXML
    private Button detayliincelebutonu;

    @FXML
    private Button degisikliklerikaydetbutonu;
    
    @FXML
    private Button silbutonu;
    
    public static String nottext;
    
    @FXML
    void degisikliklerikaydetbutonutiklandi(ActionEvent event) {
        HBox hb = (HBox)notarea.getParent();
        VBox vb = (VBox)hb.getParent();
        ScrollPane sc = (ScrollPane) vb.getParent().getParent().getParent();
        AnchorPane pane3 = (AnchorPane)sc.getParent();
        AnchorPane ustpane = (AnchorPane)pane3.getChildren().get(1);
        HBox usthb = (HBox) ustpane.getChildren().get(0);
        
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
        
        Veritabani veritabani=new Veritabani();
        veritabani.AciklamaGuncelle(onisim,notarea.getText() , aciklamaarea.getText());
        veritabani.MetodGuncelle(onisim,notarea.getText(),metodarea.getText());
    }

    @FXML
    void detayliincelebutonutiklandi(ActionEvent event) {
        nottext=notarea.getText().replaceAll(" ", "");
        VBox vb = (VBox)detayliincelebutonu.getParent();
        HBox hb = (HBox)vb.getParent();
        VBox vb2 = (VBox)hb.getParent();
        ScrollPane sc = (ScrollPane)vb.getParent().getParent().getParent().getParent().getParent();
        AnchorPane pane3 = (AnchorPane)sc.getParent();
        
        //üst hbox içindeki label isimerini toplamak için
        AnchorPane ustpane = (AnchorPane)pane3.getChildren().get(1);
        HBox usthb = (HBox)ustpane.getChildren().get(0);
        ArrayList<String>labelisimlistesi=new ArrayList<String>();
        int labelsayisi=usthb.getChildren().size();
        for(int i=0;i<labelsayisi;i++){
            Label lab = (Label)usthb.getChildren().get(i);
            String labismi= lab.getText();
            labelisimlistesi.add(labismi);
        }
        
        pane3.getChildren().clear();
        
        Veritabani veritabani = new Veritabani();
        
        String nottext = notarea.getText().replaceAll(" ","");
        int textarea_sayisi= veritabani.ResimAciklamalariniListele(nottext).size();//ne kadar resim olduğunu bulmak için
        
        //veritabaninda ki resim sayisini bulduktan sonra text areaların ve resimlerin içini doldurmak için
        NotEkle notekle = new NotEkle();
        ResimPenceresi resimpenceresi = new ResimPenceresi();
        resimpenceresi.textareavbox.getChildren().add(notekle.notlabel);
        Label aciklamalabeli = (Label)resimpenceresi.textareavbox.getChildren().get(0);
        aciklamalabeli.setText("Açıklamalar");
        
        for(int i = 0;i<textarea_sayisi;i++){
            NotEkle not2ekle = new NotEkle();
            resimpenceresi.hboxlabel.getChildren().add(not2ekle.notlabel);
            Label resimlabeli = (Label)resimpenceresi.hboxlabel.getChildren().get(i);
            resimlabeli.setText((i+1)+" .Resim");
            Imagenot imagenot = new Imagenot();
            TextAreanot textarea = new TextAreanot();
            resimpenceresi.textareavbox.getChildren().add(textarea.getChildren().get(0));
            resimpenceresi.hbox.getChildren().add(imagenot.getChildren().get(0));
            TextArea ta =(TextArea)resimpenceresi.textareavbox.getChildren().get(i+1);
            ta.setText(veritabani.ResimAciklamalariniListele(nottext).get(i));
            StackPane stack = (StackPane)resimpenceresi.hbox.getChildren().get(i);
            ImageView ima = (ImageView)stack.getChildren().get(0);
            ima.setImage(veritabani.ResimleriListele(nottext).get(i));
        }
        pane3.getChildren().add(resimpenceresi.getChildren().get(0));//son olarak resimpenceresini ücüncü pencereye eklemek için
        pane3.getChildren().add(resimpenceresi.getChildren().get(0));
        pane3.getChildren().add(resimpenceresi.getChildren().get(0));
        
        //resim penceresi yüklendikten sonra usthbox içine label eklemek için
        AnchorPane resimustpane = (AnchorPane) pane3.getChildren().get(1);
        HBox resimusthbox= (HBox)resimustpane.getChildren().get(0);
        for(int i = 0; i<labelsayisi;i++){
            UstLabel1 ustlabel = new UstLabel1();
            resimusthbox.getChildren().add(ustlabel.getChildren().get(0));
            Label lab = (Label)resimusthbox.getChildren().get(i);
            lab.setText(labelisimlistesi.get(i));
        }
    }
    
    @FXML
    void silbutonutiklandi(ActionEvent event) {
        HBox hb = (HBox)notarea.getParent();
        VBox vb = (VBox)hb.getParent();
        ScrollPane sc = (ScrollPane) vb.getParent().getParent().getParent();
        AnchorPane pane3 = (AnchorPane)sc.getParent();
        AnchorPane ustpane = (AnchorPane)pane3.getChildren().get(1);
        HBox usthb = (HBox) ustpane.getChildren().get(0);
        
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
        
        Veritabani veritabani = new Veritabani();
        String nottext = notarea.getText().replaceAll(" ","");
        String nottext1 = notarea.getText();
        veritabani.NotTablosunuSil(nottext);
        System.out.println(onisim);
        veritabani.NotSil(onisim,nottext1);
        
    }
    
    @FXML
    private void initialize() {
    }
}
