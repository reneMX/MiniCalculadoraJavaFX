
package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author renemm
 */
public class Archivo {
    
    //Atributos
    private BufferedReader buffer;
    private FileReader file;
    
    //Constructores
    public Archivo(){
        this.buffer = null;
        this.file   = null;
    }
    
    
    //Metodos
    
    /*
    * Este metodo busca un usuario, mediante la contrasenia y el nombre+apellidos
    * La informacion esta guardada como:
    * nombre[espacio]apellido[espacio]password
    * Por lo tanto la busqueda se hace uniendo en una cadena el nombre,apellidos y password
    */
    private Boolean buscaUsuario(String txtUsuario, String txtPassword) throws FileNotFoundException, IOException {
        //Variable Boleana para validar al usuario
        Boolean valida = false;
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            // Se crea un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader("/Users/renemm/Desktop/Construccion/WorkSpace/copyCal/src/main/resources/archivos/usuarios.txt"));
            //Leer la primera línea, guardando en un String
            String texto = br.readLine();
            String busqueda = txtUsuario + " " + txtPassword;
            //Repetir mientras no se llegue al final del fichero
            while (texto != null) {
                //Hacer lo que sea con la línea leída
                System.out.println(busqueda + " " + texto);
                if (busqueda.equals(texto)) {
//                    usuario  = txtUsuario;
//                    password = txtPassword;
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
    
}
