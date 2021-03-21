package controlador;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
    private FileReader file;
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
    private void resgistrarUsuario(ActionEvent event) throws IOException{
        Exception e;
        try{
            if( txtNombre.getText().equals("")  || txtApellidos.getText().equals("")  || txtPassword.getText().equals("") ){
                 e =  new Exception("Casillas vacias");
                desplegarMensaje("Error", "LLena las casillas con tu informacion", e, Alert.AlertType.ERROR);   
            }else{
                e =  new Exception(" Registro exitoso");
                if( validaUsuario() ){
                    //Hacemos la escritura de los datos en el archivo
                    //Mandamos mensaje , de usuario creado
                    e =  new Exception(" Registro Exitoso");

                    insertUsuario();
                    desplegarMensaje("Exito", "Usuario Registrado con exito", e, Alert.AlertType.INFORMATION );   
                    Node node       = (Node) event.getSource();
                    Stage stageP    = (Stage) node.getScene().getWindow();
                    stageP.close();
                }
                
                
    
            }
        
          }catch(NumberFormatException exeption){                              
                desplegarMensaje("¡Cuidado!", "Formato incorrecto.", exeption, Alert.AlertType.INFORMATION);
            }  
        
        

        
            System.out.println("entro a resgistro");
    }
    
    private Boolean validaUsuario() throws IOException{
        Exception e;
        Boolean valida = false;
        if( buscaUsuario() ){
            //Mandamos mensaje de usuario ya existente
            e =  new Exception(" No se puede hacer registro");
            desplegarMensaje("ERROR", "Usuario Existemte", e, Alert.AlertType.ERROR );   
        }
        else{   valida = true;  }
        return valida;
    }
    
    
    private Boolean buscaUsuario() throws FileNotFoundException, IOException {
        Boolean valida = false;
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        
        //Generamos al usuario
        this.txtUsuario = this.txtNombre.getText() + this.txtApellidos.getText();
        
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader("/Users/renemm/Desktop/Construccion/WorkSpace/copyCal/src/main/resources/archivos/usuarios.txt"));
            //Leer la primera línea, guardando en un String
            String texto = br.readLine();
            String busqueda = this.txtUsuario + " " + this.txtPassword.getText();
            //Repetir mientras no se llegue al final del fichero
            while (texto != null) {
                //Hacer lo que sea con la línea leída
                System.out.println(busqueda + " " + texto);
                if (busqueda.equals(texto)) {
//                    this.usuario = this.txtUsuario.getText();
//                    this.password = this.txtPassword.getText();
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
                pw.println(this.txtNombre.getText()+ " " + this.txtApellidos.getText() + " " + this.txtPassword.getText());
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

    
    
     
    
     private void desplegarMensaje(String title, String header,  Exception info, AlertType tipo){
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
