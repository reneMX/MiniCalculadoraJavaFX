
package modelo.app;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renemm
     */
public class Archivo {
    
    //Atributos
    private final String url;
    private final String directorioBase = "archivos/";
    
    //Constructores
    public Archivo(String nombreArchivo){
        URL recurso     = this.getClass().getResource( "/" + this.directorioBase + nombreArchivo );
        this.url        = recurso.getPath();
    }
    
    
    //Metodos
    
    
    public void guardarEnArchivoSerializable(Object info){
        File archivo =  new File(this.url);
        try{
            if( archivo.length() > 0 ){
                ObjetoFlujoDeSalida flujoSalida = new ObjetoFlujoDeSalida(new FileOutputStream( this.url, true )  );
                flujoSalida.writeObject(info);
                flujoSalida.flush();
                flujoSalida.close();
            }else{
                ObjectOutputStream objtFlujo = new ObjectOutputStream(new FileOutputStream(this.url));
                objtFlujo.writeObject(info);
                objtFlujo.flush();
                objtFlujo.close();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    public ArrayList<Object> leerArchivoSerializable() {
        ArrayList<Object> objectsList = new ArrayList<>();
        boolean bandera = true;
        try (ObjectInputStream salida = new ObjectInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(this.directorioBase + "registros").getPath()))) {
            do {
                try {
                    Object aux = salida.readObject();                    
                    objectsList.add(aux);
                } catch (EOFException exc) {
                    bandera = false;//break;
                }
            } while (bandera);
            salida.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objectsList;
    }
    
    
    /*
    Texto plano
    */
    public String leerArchivo() {
        String texto = "", cadena = null;
        //System.out.println(this.ruta);
        try (FileReader f = new FileReader(this.url);
                BufferedReader b = new BufferedReader(f)) {
            while ((cadena = b.readLine()) != null) {
                texto += cadena;   //concateno lineas             
            }
            f.close();
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AcercaDeController.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();

        } catch (IOException ex) {
//            Logger.getLogger(AcercaDeController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
        }
        //System.out.println(texto);
        return texto;
    }
    /*
    * Este metodo busca un usuario, mediante la contrasenia y el nombre+apellidos
    * La informacion esta guardada como:
    * nombre[espacio]apellido[espacio]password
    * Por lo tanto la busqueda se hace uniendo en una cadena el nombre,apellidos y password
    */
//    private Boolean buscaUsuario(String txtUsuario, String txtPassword) throws FileNotFoundException, IOException {
//        //Variable Boleana para validar al usuario
//        Boolean valida = false;
//        //Declarar una variable BufferedReader
//        BufferedReader br = null;
//        try {
//            // Se crea un objeto BufferedReader al que se le pasa 
//            //   un objeto FileReader con el nombre del fichero
//            br = new BufferedReader(new FileReader("/Users/renemm/Desktop/Construccion/WorkSpace/copyCal/src/main/resources/archivos/usuarios.txt"));
//            //Leer la primera línea, guardando en un String
//            String texto = br.readLine();
//            String busqueda = txtUsuario + " " + txtPassword;
//            //Repetir mientras no se llegue al final del fichero
//            while (texto != null) {
//                //Hacer lo que sea con la línea leída
//                System.out.println(busqueda + " " + texto);
//                if (busqueda.equals(texto)) {
////                    usuario  = txtUsuario;
////                    password = txtPassword;
//                    valida = true;
//                }
//                texto = br.readLine();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Error: Fichero no encontrado");
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error de lectura del fichero");
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch (Exception e) {
//                System.out.println("Error al cerrar el fichero");
//                System.out.println(e.getMessage());
//            }
//        }
//        return valida;
//    }
//  
    
    
    
}//fin clase Aarchivo
