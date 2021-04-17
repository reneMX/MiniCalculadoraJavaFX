package controlador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.app.Archivo;
import modelo.app.Usuario;
import modelo.app.operacionesBasicas;

/**
 *
 * @author PC1
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {	
            //Cargo la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(NewFXMain.class.getResource("/vista/FXMLLogin.fxml"));
        
            // Cargo la ventana
            Pane ventana = (Pane) loader.load();
            
            // Cargo el scene
            Scene scene = new Scene(ventana);
            
            // Se agrega estilo css
            scene.getStylesheets().add("../styles/estilo.css");
            
            // Seteo la scene y la muestro  
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Ex: "+e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        launch(args);
//        Archivo archivo = new Archivo("users");
//        Usuario user    = new Usuario("Rene","Martinez","iasb38367w!#$%#$%^");
//        archivo.guardarEnArchivoSerializable(user);
//        archivo.leerArchivoSerializable();
        
//        for(Object usuario : usuarios){
//            Usuario usuarioAuxiliar = (Usuario)usuario;
//            System.out.println("" + usuarioAuxiliar.getNombre() + "  " + usuarioAuxiliar.getConstrseña());        
//        }
//        
        
    }
    
}
