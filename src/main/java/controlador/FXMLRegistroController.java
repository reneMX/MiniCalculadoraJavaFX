package controlador;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renemm
 */
public class FXMLRegistroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void iniciar(){
//        try {	
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/FXMLRegistro.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.initModality(Modality.NONE);
//            stage.setTitle("Password Manager");
//            Scene scena = new Scene(root1);
//            stage.setScene(scena);
//            // Se agrega estilo css
//            scena.getStylesheets().add("../styles/estilo.css");
//            stage.show();    
//        } catch (IOException e) {
//            System.out.println("Ex: "+e.getMessage());
//        }
    }
    
    @FXML
    private void resgistrarUsuario(ActionEvent event){
        Node node       = (Node) event.getSource();
        Stage stageP    = (Stage) node.getScene().getWindow();
        stageP.close();
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/FXMLLogin.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
////            stage.initModality(Modality.NONE); 
//            stage.setTitle("Login");
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch (IOException e) {
//            System.out.println("Ex: " + e.getMessage());
//        }
        
            System.out.println("entro a resgistro");
    }
    
    
    
    
    
}
