package controlador;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;

import javafx.stage.Modality;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void muestraResgistro(){
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/FXMLRegistro.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Formulario de Resgistro");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Ex: " + e.getMessage());
        }
    }
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

    @FXML
    private void ingresar(ActionEvent evento) {
        Hyperlink hyperlink = new Hyperlink("Go to Facebook");
        try {
            ejecucion();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void enter(KeyEvent evntKey) {
        if (evntKey.getCode().toString().equals("ENTER")) {
            try {
                ejecucion();
            } catch (IOException ex) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void ejecucion() throws IOException {
        if (txtUsuario.getText().contentEquals(" ") || txtPassword.getText().contentEquals("")) {
            muestraVentanaEmergente(0);

        } else {
            if (buscaUsuario()) {
                muestraVentanaPrincipal();
            } else {
                muestraVentanaEmergente(0);
            }

        }
    }

    @FXML
    private void muestraVentanaEmergente(int tipo) {
        Alert alertaRbN = new Alert(Alert.AlertType.ERROR);
        alertaRbN.setHeaderText("Error de Llenado");
        alertaRbN.setTitle("Error");
        alertaRbN.setContentText("Error, Debes seleccionar almenos 1 opccion de operacion");
        alertaRbN.showAndWait();
    }

    @FXML
    private void muestraVentanaPrincipal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/FXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Password Manager");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Ex: " + e.getMessage());
        }
    }

    /*Capa de Datos*/
    private Boolean buscaUsuario() throws FileNotFoundException, IOException {
        Boolean valida = false;
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader("/Users/renemm/Desktop/Construccion/WorkSpace/copyCal/src/main/resources/archivos/usuarios.txt"));
            //Leer la primera línea, guardando en un String
            String texto = br.readLine();
            String busqueda = this.txtUsuario.getText() + " " + this.txtPassword.getText();
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

    private void insertUsuario() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("/Users/renemm/Desktop/Construccion/WorkSpace/copyCal/src/main/resources/archivos/usuarios.txt");
            pw = new PrintWriter(fichero);
            for (int i = 0; i < 10; i++) {
                pw.println(this.txtUsuario.getText() + " " + this.txtPassword.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // El finally se utiliza como sentencia final, para
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void updateUsuario() {

    }

    private void deleteUsuario() {

    }

}//fin clase FXMLLoginController
