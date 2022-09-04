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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Murat
 */
public class ilkpencere extends AnchorPane {
    
    public ilkpencere() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ilkpencere.fxml"));
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
    public VBox scvbox;

    @FXML
    private AnchorPane minipane;

    @FXML
    private Button dileklebutonu;
    
    @FXML
    private Button dilsilmebutonu;

    @FXML
    private Button refreshbutonu;

    @FXML
    void dileklebutonutiklandi(ActionEvent event) {
        DilEklePenceresi dilekle = new DilEklePenceresi();
        pane.getChildren().add(dilekle.getChildren().get(0));
    }
    
    @FXML
    void dilsilmebutonutiklandi(ActionEvent event) {
        Veritabani veritabani = new Veritabani();
        DilSilme dilsilme = new DilSilme();
        pane.getChildren().add(dilsilme.getChildren().get(0));
        
        int dilsayisi = veritabani.DilListesiniListele().size();
        for(int i = 0;i<dilsayisi;i++){
            dilsilme.combom.getItems().add(veritabani.DilListesiniListele().get(i));
        }
    }

    @FXML
    void refreshbutonutikla(ActionEvent event) {
        refreshfonksiyonu();
    }
    
    @FXML
    private void initialize() {
        baslamafonksiyonu();
    }
    
    public void baslamafonksiyonu(){
        ScrollPane scm = (ScrollPane) pane.getChildren().get(0);
        VBox vbm = (VBox)scm.getContent();
        Veritabani veritabani = new Veritabani();
        int butonsayisi =veritabani.DilListesiniListele().size();
        int hboxsayisi= (int) Math.ceil(butonsayisi/3f);
        for(int i =0; i<hboxsayisi;i++){
            Hboxum hb = new Hboxum();
            vbm.getChildren().add(hb.getChildren().get(0));
        }
        for(int i =0; i<butonsayisi;i++){
            int sayi = (int) Math.floor(i/3);
            HBox hbm = (HBox)vbm.getChildren().get(sayi);
            Buttonum buton = new Buttonum();
            Button but = (Button) buton.getChildren().get(0);
            but.setText(veritabani.DilListesiniListele().get(i));
            hbm.getChildren().add(buton.getChildren().get(0));
            
        }
    }
    public void refreshfonksiyonu(){
        //vbox'ı temizler
        AnchorPane anapane = (AnchorPane)pane.getParent();
        ScrollPane scm1 = (ScrollPane) pane.getChildren().get(0);
        VBox vbm1 = (VBox)scm1.getContent();
        vbm1.getChildren().clear();
        
        Veritabani veritabani = new Veritabani();
        int butonsayisi =veritabani.DilListesiniListele().size();
        int hboxsayisi= (int) Math.ceil(butonsayisi/3f);
        for(int i =0; i<hboxsayisi;i++){
            Hboxum hb = new Hboxum();
            vbm1.getChildren().add(hb.getChildren().get(0));
        }
        for(int i =0; i<butonsayisi;i++){
            int sayi = (int) Math.floor(i/3);
            HBox hbm = (HBox)vbm1.getChildren().get(sayi);
            Buttonum buton = new Buttonum();
            Button but = (Button) buton.getChildren().get(0);
            but.setText(veritabani.DilListesiniListele().get(i));
            hbm.getChildren().add(buton.getChildren().get(0));
            
        }
    }
}
