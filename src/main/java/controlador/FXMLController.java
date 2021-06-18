package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import modelo.app.MensajeEmergente;
import modelo.app.operacionesBasicas;

/**
 * FXML Controller class
 *
 * @author PC1
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField numA;
    @FXML
    private TextField numB;
    @FXML
    private TextField tFResult;
    @FXML
    private Button btnResuelve;
    @FXML
    private RadioButton rBtnSuma;
    @FXML
    private RadioButton rBtnResta   ;
    @FXML
    private RadioButton rBtnMultiplicacion;
    @FXML
    private RadioButton rBtnDivision;
    @FXML
    private MenuBar mNBarCalcu;
    @FXML
    private ToggleGroup operaciones;
    
    @FXML
    private VBox vBoxLeft;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    /*
    * Este metodo hace que se cierre la Ventana,
    *   mediante la accion de un evento
    */
    @FXML
    private void cerrarVentana(ActionEvent eventoCerrar){
        /* Utilizamos el metodo exit() del objeto Plataforma de aplicaciones */
        Platform.exit();
    }
    
    /*
    *   Borra la informacion de los TextField
    *   Y reinicia la eleccion de los RadioButton
    */
     @FXML
    private void borrarInformacion(ActionEvent event) {
        
        /* colcoamos un string vacio para reiniciar cada TextField */
        numA.setText(" ");
        numB.setText(" ");
        tFResult.setText(" ");
        /* Regresamos la seleccion de cada RadioBtton a falso */
        rBtnSuma.setSelected(false);
        rBtnResta.setSelected(false);
        rBtnMultiplicacion.setSelected(false);
        rBtnDivision.setSelected(false);
        
    }

    
    /*
    * Este metodo, muestra mediante otra venta
    *   creada desde cero, el nombre del autor.
    */
    @FXML
    private void presentaNombre(ActionEvent event) {
        /* Invocamos al metodo, que llama una ventana nueva */    
            Exception e = new Exception("Nombre. ");
            MensajeEmergente msjEmergente = new MensajeEmergente("Acerca de..", "René Martínez M.",e, Alert.AlertType.ERROR);
            msjEmergente.despliegaMensaje();
            
    }
    
    /*
    * Valida las casillas TextField de entrada de operandos
    *   donde se valida la entrada de solo numeros.
    */
    @FXML
    private void SoloNumerosEnteros(KeyEvent keyEvent) {
        try{
            /* Obtenemos el valor de la tecla presionada
                mediante KeyEvent
            */
            char key = keyEvent.getCharacter().charAt(0);
            /* Validamos el caracter , y cuando sea una letra consumimos al evento */
            if (!Character.isDigit(key)){
                keyEvent.consume();
            }         
        } catch (Exception ex){ }
    }

    
    /*
    * Ejecuta la funcionalidad de cada Operacion
    *   mediante un evento, que reacciona cuando
    *   se presiona el boton de resolver.
    */
    @FXML
    private void ejecutar(ActionEvent event) {
        Exception exDenominador         =  new Exception("Denominador Indefinido");
        Exception exFormatoIncorrecto   =  new Exception("Formato incorrecto.");
        Exception exErrorDeOpccion      =  new Exception("Error de Llenado");
        double result = 0;
        MensajeEmergente msjEmergenteDivision = new MensajeEmergente("Error", "El denominador no puede ser 0", exDenominador, Alert.AlertType.ERROR);
        MensajeEmergente msjEmergenteFromatoIncorrecto = new MensajeEmergente("Error", "Debes llenar la las casillas", exFormatoIncorrecto, Alert.AlertType.ERROR);
        MensajeEmergente msjEmergenteErrorDeOpccion = new MensajeEmergente("Error", "Error, Debes seleccionar almenos 1 opccion de operacion", exErrorDeOpccion, Alert.AlertType.ERROR);
        
        if(rBtnSuma.isSelected() || rBtnResta.isSelected() || rBtnMultiplicacion.isSelected() || rBtnDivision.isSelected())
        {
                try{    
                        //recupero los datos del textFlied
                        double numberA = Double.parseDouble(this.numA.getText());
                        double numberB = Double.parseDouble(this.numB.getText());
                         // Creo una instancia del modelo
                        operacionesBasicas objOB = new operacionesBasicas(numberA, numberB);

                        // Realizo la operacion correspondiente,segun el radio seleccionado
                        if (this.rBtnSuma.isSelected()) {
                            result= objOB.sumar();
                        } else if (this.rBtnResta.isSelected()) {
                            result= objOB.restar();
                        } else if (this.rBtnMultiplicacion.isSelected()) {
                            result= objOB.multiplar();
                        } else if (this.rBtnDivision.isSelected()) {               
                            if (numberB == 0) { // Si el denominador es 0, emitir error
                                msjEmergenteDivision.despliegaMensaje();

                            } else {
                                result= objOB.dividir();
                            }
                        }
                        // Muestro el resultado
                        this.tFResult.setText(String.valueOf(result));

                    }catch(NumberFormatException exeption){                        
                          msjEmergenteFromatoIncorrecto.despliegaMensaje();
                    }
        }else{
               msjEmergenteErrorDeOpccion.despliegaMensaje();
        }      
    }
    
    

}
