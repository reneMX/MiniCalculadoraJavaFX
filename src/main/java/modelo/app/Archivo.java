package modelo.app;

//import controlador.AcercaDeController;
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
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC1
 */
public class Archivo {

    private final String ruta;//Una variable final solo se puede inicializar una vez
    private final String directorioBase = "archivos/";
    

    public Archivo(String nombreArchivo) throws URISyntaxException, MalformedURLException {//path --> direccion         
        URL resource = this.getClass().getResource("/"+this.directorioBase+nombreArchivo);
        this.ruta = resource.getPath();
        System.out.println("URL: " + ruta);
        
                
    }

    public void guardarEnArchivoSerializable(Object info) {
        File archivo=new File(this.ruta);      
        System.out.println("URL: " + this.ruta );
//        try {//System.out.println("length: "+archivo.length()); // Write objects to file            
//            if(archivo.length()>0){
//                MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream(this.ruta,true));//append -->adjuntar, añadir
//                moos.writeObject(info);// Write objects to file
//                moos.flush();
//                moos.close();            
//            }else {
//                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.ruta));
//                oos.writeObject(info);// Write objects to file
//                oos.flush();// El método flush () se usa para eliminar cualquier búfer interno que pueda estar en uso. Por ejemplo, al usar BufferedOutputStream, los contenidos se escriben en trozos para mejorar el rendimiento (es más lento escribir cada byte a medida que vienen)
//                oos.close();            
//            }            
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
      
    public ArrayList<Object> leerArchivoSerializable() {
        ArrayList<Object> objectsList = new ArrayList<>();
        boolean bandera = true; // ..../target/classes/archivo.txt
        //System.out.println(Thread.currentThread().getContextClassLoader().getResource(this.directorioBase+"registros").getPath());
        try (ObjectInputStream salida = new ObjectInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(this.directorioBase + "users").getPath()))) {
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
        try (FileReader f = new FileReader(this.ruta);
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
        //String path  = Archivo.class.getResource(ruta).getPath();
        //System.out.println(path);
        //System.out.println(this.ruta);
    */ 
    /*InputStream inputStream = new FileInputStream(
        Thread.currentThread().getContextClassLoader().getResource(
        this.directorioBase+"registros").getPath());*/
        /*Reader reader = new InputStreamReader(inputStream);
        int data = reader.read();
        while(data != -1){
        char theChar = (char) data;
        data = reader.read();
        System.out.println("" +data) ;
        }
        reader.close();
    
        /*Usuario aux2 = (Usuario) aux;
                    System.out.println(aux2.getNombre());
                    System.out.println(aux2.getApellidos());
                    System.out.println(aux2.getConstrseña());
                    System.out.println(" ");
        */
    
}
