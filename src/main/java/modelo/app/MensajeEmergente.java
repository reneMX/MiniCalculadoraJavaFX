package modelo.app;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author renemm
 */
public class MensajeEmergente {
    

//Atributos
    private String titulo;
    private String cabecera;
    private Exception tipoExcepccion;
    private Alert alerta;


//Constructores       
    
    
    public MensajeEmergente(String titulo, String cabecera, Exception tipoExcepccion, AlertType tipoAlerta ){
        
        if(validaObjetoNulo(tipoAlerta, tipoExcepccion)){
            this.alerta         = new Alert(tipoAlerta); 
            this.tipoExcepccion = tipoExcepccion;
            this.titulo         = titulo;
            this.cabecera       = cabecera;
        }
    }
    
    
/**
 * @param tipoAlerta != null
 * @param tipoExcepccion != null
 * @return valor boleano para validar objetos nulos
 * @throws NullPointerException Excepccion nula
 */
    public boolean validaObjetoNulo(AlertType tipoAlerta, Exception tipoExcepccion) throws NullPointerException{
       boolean valida = false;
       if(tipoAlerta != null && tipoExcepccion != null){
           valida = true;
       }else{
           throw new NullPointerException("Error, Tipo de Alerta o Excepccion NULA");
       }
       return valida;
    }
    
    
//Metodos    
    
    /**
     * Este metodo es invocado para mostrar un mensaje
     */
    public void despliegaMensaje(){
        this.alerta.setHeaderText(this.cabecera);
        this.alerta.setTitle(this.titulo); 
        this.alerta.setContentText(this.tipoExcepccion.getMessage());
        this.alerta.show();
    }
    
    
}
