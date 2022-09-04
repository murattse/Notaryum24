/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author murat.secilmis
 */
public class Imagenot extends AnchorPane {
    
    public Imagenot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Imagenot.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private ImageView image;
    
    private boolean image_dolu=false;
    private boolean resim_tiklandi=false;

    @FXML
    void handledragdropped(DragEvent event) {
        
        List<File>files = event.getDragboard().getFiles();
        try {
            Image im = new Image (new FileInputStream(files.get(0)));
            image.setImage(im);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Imagenot.class.getName()).log(Level.SEVERE, null, ex);
        }
        Veritabani veritabani = new Veritabani();
        try {
            FileInputStream fis = new FileInputStream(files.get(0));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readNum; (readNum = fis.read(buf))!=-1;){
                bos.write(buf, 0, readNum);
        }
        byte[] imagecik=bos.toByteArray();
        NotText not = new NotText();
        String nottext=not.nottext;
        
        
        StackPane st = (StackPane) image.getParent();
        HBox hb = (HBox) st.getParent();
        int sayi = hb.getChildren().size();
        if(veritabani.ResimleriListele(nottext).size()<sayi){
            int fark = sayi -veritabani.ResimleriListele(nottext).size();

            String index = Integer.toString(1+veritabani.ResimleriListele(nottext).size());
            veritabani.ImageEkle(imagecik, nottext,index);
        }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Imagenot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Imagenot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handledragover(DragEvent event) {
        event.acceptTransferModes(TransferMode.ANY);
    }

    @FXML
    void imagetiklandi(MouseEvent event) {
        StackPane st = (StackPane)image.getParent();
        HBox hb = (HBox)st.getParent();
        AnchorPane scrolicipane = (AnchorPane)hb.getParent();
        HBox hblabel = (HBox)scrolicipane.getChildren().get(0);
        
        if(!resim_tiklandi){
            Image im = image.getImage();
            double width = 800;
            double height = 940;
            image.setFitWidth(width);
            image.setFitHeight(height);
            resim_tiklandi=true;
            hb.setSpacing(width/2);
            hblabel.setSpacing(width/2);
            hb.setTranslateX(200);
            hblabel.setTranslateX(200);
            
            BosImage bosimage =new BosImage();
            hb.getChildren().add(bosimage.getChildren().get(0));
            NotEkle notekle=new NotEkle();
            hblabel.getChildren().add(notekle.notlabel);
            int index = hblabel.getChildren().size()-1;
            Label lab = (Label) hblabel.getChildren().get(index);
            lab.setText("");
            
        }
        else{
            Image im = image.getImage();
            image.setFitWidth(385);
            image.setFitHeight(200);
            resim_tiklandi=false;
            hb.setSpacing(20);
            hblabel.setSpacing(20);
            hb.setTranslateX(0);
            hblabel.setTranslateX(0);
            int index =hb.getChildren().size()-1;
            hb.getChildren().remove(index);
            hblabel.getChildren().remove(index);
        }
        
    }
    @FXML
    private void initialize() {
    }
}
