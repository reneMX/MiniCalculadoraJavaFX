    package controlador;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.app.MensajeEmergente;

/**
 * FXML Controller class
 *
 * @author renemm
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPassword;

    private BufferedReader buffer;
    private FileReader file;
    private String usuario;
    private String password;
    
    private MensajeEmergente msjEmergente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /*
    *   Este metodo invoca toda la funcionalidad de ejecucion.
    *   Aqui se contola la ejecucion de alguna excepccion posible
    */
    @FXML
    private void ingresar(ActionEvent evento) {
        try {
            ejecucion();
        } catch (IOException ex) {
            this.msjEmergente = new MensajeEmergente("Exception", "Exception de entrada y salida de informacion", ex, Alert.AlertType.WARNING);
            this.msjEmergente.despliegaMensaje();
        }
    }

    /*
    *   Este metodo basado en eventos, reacciona al presionar enter, para iniciar sesion
    *   asu vez, llama al metodo ejecucion, donde se realiza la vlidacion de usuario, etc..
    */
    @FXML
    private void enter(KeyEvent evntKey) {
        if (evntKey.getCode().toString().equals("ENTER")) {
            try {
                ejecucion();
            } catch (IOException ex) {
                this.msjEmergente = new MensajeEmergente("Exception", "Exception de entrada y salida de informacion", ex, Alert.AlertType.WARNING);
                this.msjEmergente.despliegaMensaje();
            }
        }
    }
    
    
    /*
    *   Desde este metodo, se controla el curso principal de la iteraccion con el usuario
    *
    */
    private void ejecucion() throws IOException {
        Exception e;
        if (txtUsuario.getText().contentEquals(" ") || txtPassword.getText().contentEquals("")) {
            e = new Exception("Casillas vacias");
            this.msjEmergente = new MensajeEmergente("Error de llenado", "Debes llenar todas las casillas con tu informacion", e, Alert.AlertType.ERROR);
            this.msjEmergente.despliegaMensaje();
        } else {
            if (buscaUsuario()) {
                muestraVentanaPrincipal();
            } else {
                e = new Exception("Error de Usuario");
                this.msjEmergente = new MensajeEmergente("User not registered", "Este usuario No  esta registrado", e, Alert.AlertType.ERROR);
                this.msjEmergente.despliegaMensaje();
            }
        }
    }
    
    /*
    *   Este metodo, lanza la ventana donde se ubica la min-calculadora.
    */
    @FXML
    private void muestraVentanaPrincipal(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/FXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Mini-Calculadora");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Ex: " + e.getMessage());
        }
    }
    
    /*
    *   Este metodo, lanza la ventana de registro para un usuario nuevo
    */
    @FXML
    private void muestraResgistro(ActionEvent event){
            Node node       = (Node) event.getSource();
            Stage stageP    = (Stage) node.getScene().getWindow();
//            stageP.setOpacity(0.5);//Opacamos la ventanaLogin
        try {
            FXMLLoader fxmlLoader   = new FXMLLoader(getClass().getResource("/vista/FXMLRegistro.fxml"));
            Parent root1            = (Parent) fxmlLoader.load();
            Stage stage             = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Formulario de Resgistro");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Ex: " + e.getMessage());
        }
    }
    
    /*
    *   Mediante el Objeto Desktop, este metodo, abre el navegador por default
    *   y abre el enlace, en este caso Facebook
    *   Sin embargo, su alcance es solo visual, pues no esta implenentada
    *   una funcionalidad con alguna API Facebbok-Java
    */
    @FXML
    private void sesionFacebook() throws URISyntaxException {
          try {
            Desktop.getDesktop().browse(new URI("https://www.facebook.com/"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    
    /*Capa de Datos*/
    
    /*
    * Se Planteo una clase archivo, que no esta implenetada, esto con el fin
    *   de abstraer los datos, y manejar la infomacion desde una clase
    *   para intentar crear un bajo acoplamiento
    */
    
    /*
    *   Este metodo, hace la busqueda de un usuario, y ya que en el Login
    *   se maneja coomo usuario y password, y aqui tenemos nombre y apellidos
    *   el usuario se unira mediante una cadena de la sig. manera
    *   nombre[ESPACIO]apellidos[ESPACIO]password
    *   lo anterior, se hace asi, ya que la informacion de guardo de esa manera,
    *   separando nombre,apellidos y password, con un espacio, y evitar problemas
    *   con algun caracter en el password
    */
    private Boolean buscaUsuario() throws FileNotFoundException, IOException {
        Boolean valida = false;
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader( "/archivos/usuarios.txt")  );
            
            
            //Leer la primera línea, guardando en un String
            String texto = br.readLine();
            String busqueda = this.txtUsuario.getText() + " " + this.txtPassword.getText();
//            String busqueda = this.txtNombre.getText() + " " + this.txtApellidos.getText() + " " + this.txtPassword.getText();
            //Repetir mientras no se llegue al final del fichero
            while (texto != null) {
                //Hacer lo que sea con la línea leída
                System.out.println(busqueda + " " + texto);
                if (busqueda.equals(texto)) {
                    this.usuario = this.txtUsuario.getText();
                    this.password = this.txtPassword.getText();
                    valida = true;
                }
                texto = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
        return valida;
    }

    
 
    

}//fin clase FXMLLoginController
