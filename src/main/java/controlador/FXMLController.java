/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JOptionPane;
import modelo.operacionesBasicas;

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
    private RadioButton rBtnResta;
    @FXML
    private RadioButton rBtnMultiplicacion;
    @FXML
    private RadioButton rBtnDivision;
    @FXML
    private MenuBar mNBarCalcu;
    @FXML
    private ToggleGroup operaciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void cerrarVentana(ActionEvent eventoCerrar){
        Platform.exit();
    }
     @FXML
    private void borrarInformacion(ActionEvent event) {
        numA.setText(" ");
        numB.setText(" ");
        rBtnSuma.setSelected(false);
        rBtnResta.setSelected(false);
        rBtnMultiplicacion.setSelected(false);
        rBtnDivision.setSelected(false);
        tFResult.setText(" ");
    }

    @FXML
    private void presentaNombre(ActionEvent event) {
            muestraNombre();
    }
    
    
    @FXML
    private void SoloNumerosEnteros(KeyEvent keyEvent) {
        try{
            char key = keyEvent.getCharacter().charAt(0);

            if (!Character.isDigit(key)){
                keyEvent.consume();
            }         
        } catch (Exception ex){ }
    }

    @FXML
    private void ejecutar(ActionEvent event) {
        double result = 0;
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
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText(null);
                                alert.setTitle("Error");
                                alert.setContentText("El operando 2 no puede ser 0");
                                alert.showAndWait();
                            } else {
                                result= objOB.dividir();
                            }
                        }
                        // Muestro el resultado

                        this.tFResult.setText(String.valueOf(result));

                    }catch(NumberFormatException exeption){                        

                        Alert alerta = new Alert(Alert.AlertType.ERROR); // Alerta de error            
                        alerta.setHeaderText("Cuidado Old-Man!");//Cabecera
                        alerta.setTitle("Error"); //Titulo
                        alerta.setContentText("Formato incorrecto.");//Información
                        alerta.showAndWait();/*Utilice el método showAndWait () si necesita 
                        mantener a la ventana que se invoca hasta que la etapa modal 
                        esté oculta (cerrada).*/
                    }
        }else{
            muestraErrorButtonResolve();
//                    Alert alerta = new Alert(Alert.AlertType.ERROR);
//                    alerta.setHeaderText("Error de Llenado");
//                    alerta.setTitle("Error");
//                    alerta.setContentText("Error, Debes seleccionar almenos 1 opccion de operacion");
//                    alerta.showAndWait();
        }      
    }
    
    @FXML
    private void muestraErrorButtonResolve(){
        Alert rbAlerta = new Alert(Alert.AlertType.ERROR);
        rbAlerta.setHeaderText("Error de Llenado");
        rbAlerta.setTitle("Error");
        rbAlerta.setContentText("Error, Debes seleccionar almenos 1 opccion de operacion");
        rbAlerta.showAndWait();
    }
    
    @FXML
    private void muestraNombre(){
        Alert dialogoNombre = new Alert(AlertType.INFORMATION);
        dialogoNombre.setTitle("Acerca de..");
        dialogoNombre.setHeaderText(null);
        dialogoNombre.setContentText("René Martínez M.");
        dialogoNombre.showAndWait();
    }
    
   
    
}
