package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renemm
 */
public class FXMLRegistroController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtPassword;
    private String txtUsuario;

    private BufferedReader buffer;
    private BufferedWriter bufferEscritura;
    private FileReader archivoLectura;
    private FileWriter archivoEscritura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void iniciar() {

    }

    @FXML
    private void resgistrarUsuario(ActionEvent event) throws IOException {
        Exception e;
        try {
            if (txtNombre.getText().equals("") || txtApellidos.getText().equals("") || txtPassword.getText().equals("")) {
                e = new Exception("Casillas vacias");
                desplegarMensaje("Error", "LLena las casillas con tu informacion", e, Alert.AlertType.ERROR);
            } else {
                e = new Exception(" Registro exitoso");
                if (validaUsuario()) {
                    //Hacemos la escritura de los datos en el archivo
                    //Mandamos mensaje , de usuario creado
                    e = new Exception(" Registro Exitoso");
                    insertUsuario();
                    desplegarMensaje("Exito", "Usuario Registrado con exito", e, Alert.AlertType.INFORMATION);
                    Node node = (Node) event.getSource();
                    Stage stageP = (Stage) node.getScene().getWindow();
                    stageP.close();
                }
            }
        } catch (NumberFormatException exeption) {
            desplegarMensaje("¡Cuidado!", "Formato incorrecto.", exeption, Alert.AlertType.INFORMATION);
        }
    }

    /*
    *   Aqui validamos lo que nos regreso la busqueda del usuario
    *   y mandamos mensaje si el usuario ya existe,
    *   en caso contrario mandamos true, es decir usuario valido
     */
    private Boolean validaUsuario() throws IOException {
        Exception e;
        Boolean valida = false;
        if (buscaUsuario()) {
            //Mandamos mensaje de usuario ya existente
            e = new Exception(" No se puede hacer registro");
            desplegarMensaje("ERROR", "Usuario Existente", e, Alert.AlertType.ERROR);
        } else {
            valida = true;
        }
        return valida;
    }

    /*Capa de Datos*/
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
        //Generamos al usuario
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader("/Users/renemm/Desktop/Construccion/WorkSpace/copyCal/src/main/resources/archivos/usuarios.txt"));
            //Leer la primera línea, guardando en un String
            String texto = br.readLine();
            String busqueda = this.txtNombre.getText() + " " + this.txtApellidos.getText() + " " + this.txtPassword.getText();
            //Repetir mientras no se llegue al final del fichero
            while (texto != null) {
                //Hacer lo que sea con la línea leída
                System.out.println(busqueda + " " + texto);
                if (busqueda.equals(texto)) {
                    valida = true;
                }
                texto = br.readLine();
            }
        } catch (FileNotFoundException e) {
            desplegarMensaje("¡Error!", "Fichero no encontrado", e, Alert.AlertType.ERROR);
        } catch (Exception e) {
            desplegarMensaje("¡Error!", "Error de lectura del fichero", e, Alert.AlertType.ERROR);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                desplegarMensaje("¡Error!", "Error al cerrar el fichero", e, Alert.AlertType.ERROR);
            }
        }
        return valida;
    }

    /*
    *   Este metodo, Inserta un usuario nuevo, al final del archivo
    *   la forma de insertarlo es mediante un Buffer de escritura y un Archivo de escritura y colocando el estado en append
    *   este estado append, permite agregar contenido y no sobreescribir, mediante la instruccion true.
    *   Estoy seguro de que hay formas mas  sencillas de hacer la insersion, sin embargo a mi me funciono.
    *   El formato en que se inserta un suario es el siguiente:
    *   nombre[ESPACIO]apellidos[ESPACIO]password
     */
    private void insertUsuario() {
        try {
            File file = new File("/Users/renemm/Desktop/Construccion/WorkSpace/copyCal/src/main/resources/archivos/usuarios.txt");
            // Si el archivo no existe, se crea.
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            this.archivoEscritura = new FileWriter(file.getAbsoluteFile(), true);
            this.bufferEscritura = new BufferedWriter(this.archivoEscritura);
            this.bufferEscritura.write("\n" + this.txtNombre.getText() + " " + this.txtApellidos.getText() + " " + this.txtPassword.getText());
            System.out.println("información agregada!");
        } catch (IOException e) {
            desplegarMensaje("¡Error!", "Error al momento de abrir archivo", e, Alert.AlertType.INFORMATION);
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (this.bufferEscritura != null) {
                    this.bufferEscritura.close();
                }
                if (this.archivoEscritura != null) {
                    this.archivoEscritura.close();
                }
            } catch (IOException ex) {
                desplegarMensaje("¡Error!", "Error al momento de cerrar archivo", ex, Alert.AlertType.INFORMATION);
            }
        }

    }

    private void desplegarMensaje(String title, String header, Exception info, AlertType tipo) {
        Alert alerta = new Alert(tipo); // Alerta de error            
        alerta.setHeaderText(header);//Cabecera                
        alerta.setTitle(title); //Titulo

        //System.out.println(info.getClass()); 
        alerta.setContentText(info.getMessage());//Información
        alerta.show();/*Utilice el método showAndWait () si necesita 
            mantener a la ventana que se invoca hasta que la etapa modal 
            esté oculta (cerrada).*/

    }

}
