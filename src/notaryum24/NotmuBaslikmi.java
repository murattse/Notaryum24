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
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Murat
 */
public class NotmuBaslikmi extends AnchorPane {
    
    public NotmuBaslikmi() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NotmuBaslikmi.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    private AnchorPane notmubaslikmipane;

    @FXML
    private Button baslikeklebutonu;

    @FXML
    private Button noteklebutonu;

    @FXML
    private Button vazgecbutonu;

    @FXML
    void baslikeklebutonutiklandi(ActionEvent event) {
        BaslikEkle baslikekle = new BaslikEkle();
        AnchorPane pane3 = (AnchorPane) notmubaslikmipane.getParent();
        pane3.getChildren().remove(3);
        pane3.getChildren().add(baslikekle.getChildren().get(0));
    }

    @FXML
    void noteklebutonutiklandi(ActionEvent event) {
        NotEkle notekle= new NotEkle();
        AnchorPane pane3 = (AnchorPane) notmubaslikmipane.getParent();
        pane3.getChildren().remove(3);
        pane3.getChildren().add(notekle.getChildren().get(0));
    }

    @FXML
    void vazgecbutonutiklandi(ActionEvent event) {
        AnchorPane pane3 = (AnchorPane) notmubaslikmipane.getParent();
        pane3.getChildren().remove(3);
    }
    @FXML
    private void initialize() {
    }
}
