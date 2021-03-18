package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author renemm
 */
public class FXMLDocumentController implements Initializable {
   
    
    @FXML
    private TextField txtResultado;
    @FXML
    private  TextField txtOperandoB;
    @FXML
    private  Label operandoB;
    @FXML
    private  RadioButton suma;
    @FXML
    private  RadioButton resta;
    @FXML
    private  RadioButton multi;
    @FXML
    private  RadioButton div;
    
    @FXML
    private ToggleGroup grupo;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        txtResultado.setText("Error");
        
    }
    
    public void RBSeleccionado(ActionEvent evento){
      if(txtOperandoB.getText() == " "){
          System.out.println("SE IMPRIMIO");
      }      
    }
    
    public void cierraVentana(ActionEvent evento){
      
      
    }
    public void SoloNumerosEnteros(KeyEvent keyEvent) {
    try{
        char key = keyEvent.getCharacter().charAt(0);

        if (!Character.isDigit(key)){
            keyEvent.consume();
        }
    } catch (Exception ex){ }
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
