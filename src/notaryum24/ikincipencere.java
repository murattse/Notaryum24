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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Murat
 */
public class ikincipencere extends AnchorPane {
    
    public ikincipencere() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ikincipencere.fxml"));
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
    private Button geri1butonu;

    @FXML
    private AnchorPane minipane2;

    @FXML
    private Button notturueklebutonu;
    
    @FXML
    private Button notturusilmebutonu;
    
    @FXML
    public ScrollPane scroll;

    @FXML
    private Button refreshbutonu;
    
    @FXML
    private HBox usthbox;

    @FXML
    void geri1butonutiklandi(ActionEvent event) {
        ilkpencere ilk = new ilkpencere();
        AnchorPane pane = (AnchorPane) geri1butonu.getParent();
        AnchorPane anapane = (AnchorPane)pane.getParent().getParent();
        anapane.getChildren().clear();
        anapane.getChildren().add(ilk.getChildren().get(0));

    }

    @FXML
    void notturueklebutonutiklandi(ActionEvent event) {
        NotTuruEkle notturuekle = new NotTuruEkle();
        pane.getChildren().add(notturuekle.getChildren().get(0));
    }
    
    @FXML
    void notturusilmebutonutiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        NotTuruSilme notturusilme = new NotTuruSilme();
        pane.getChildren().add(notturusilme.getChildren().get(0));
        
        Label lab = (Label) usthbox.getChildren().get(0);
        String dilismi = lab.getText();
        String dilismi1 = lab.getText().replaceAll(" ", "");
        int notturusayisi = veritabani.NotTurunuListele(dilismi1).size();
        
        for(int i=0; i<notturusayisi;i++){
            notturusilme.combom.getItems().add(veritabani.NotTurunuListele(dilismi1).get(i));
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
        
        //label'in textini bulur
        Label label = (Label)usthbox.getChildren().get(0);
        String dilismi = label.getText();
        
        Veritabani veritabani = new Veritabani();
        int butonsayisi =veritabani.NotTurunuListele(dilismi).size();
        System.out.println("buton sayisi "+butonsayisi);
        int hboxsayisi= (int) Math.ceil(butonsayisi/3f);
        for(int i =0; i<hboxsayisi;i++){
            Hboxum hb = new Hboxum();
            vbm1.getChildren().add(hb.getChildren().get(0));
        }
        for(int i =0; i<butonsayisi;i++){
            int sayi = (int) Math.floor(i/3);
            HBox hbm = (HBox)vbm1.getChildren().get(sayi);
            Buttonum2 buton = new Buttonum2();
            Button but = (Button) buton.getChildren().get(0);
            but.setText(veritabani.NotTurunuListele(dilismi).get(i));
            hbm.getChildren().add(buton.getChildren().get(0));
            
        }
    }
    @FXML
    private void initialize() {
    }
}
