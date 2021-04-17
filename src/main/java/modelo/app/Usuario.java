/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.app;

import java.io.Serializable;



/**
 *
 * @author Albert StringMX
 */
//Esta clase solo recibe los parametros del usuario en el login
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    //Aqui se muestran los atributos de la clase y que son privados
    private String nombre;
    private String apellidos;
    private String password;
    
    //Constructor de la clase que recibe los parametros nombre, apellidos y contraseña
    public Usuario(String nombre, String apellidos, String contraseña) 
    {
        this.password = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    //Aqui se ponen los set y get de los atributos de la clase
    public String getConstrseña() 
    {
        return password;
    }

    public void setConstrseña(String constrseña) 
    {
        this.password = constrseña;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getApellidos() 
    {
        return apellidos;
    }

    public void setApellidos(String apellidos) 
    {
        this.apellidos = apellidos;
    }
    
    //Este metodo lo que hace es imprimir los datos del usuario
    @Override
    public String toString() 
    {
        return "\nNombre: " + nombre + "\nApellidos: " + apellidos + "\nConstrase\u00f1a: " + password;
    }
    
    //Este metodo es similar al toString e imprime los datos del usuario
    public void Imprimir()
    {
        System.out.println(toString());
    }
    
    public void listarUsuarios(){
        
    }
}
