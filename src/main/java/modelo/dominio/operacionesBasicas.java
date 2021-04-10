/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.app;

import Excepcciones.ExcepccionOperacionBasica;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PC1
 */
public class operacionesBasicas {
    private double numA;
    private double numB;

    public operacionesBasicas(double numA, double numB) {
        this.numA = numA;
        this.numB = numB;
    }

    public void setNumA(double numA) throws ExcepccionOperacionBasica {
        if( validaDigitos(numA+"") ){
            this.numA = numA;
        }else{
            throw new ExcepccionOperacionBasica("Error en el tipo de dato, debe ser un numero real");
        }
    }
    
    public void setNumB(double numB) throws ExcepccionOperacionBasica{
        if( validaDigitos(numB+"") ){
            this.numB = numB;
        }else{
            throw new ExcepccionOperacionBasica("Error!!!, el numero maximo de digitos para un double es : 16");
        }
    }
    
    public boolean validaDigitos(String numA){
        if( numA.length() > 1 && numA.length() <=16 ){
            return true;
        }else{
            return false;
        }
    }
//    public boolean validaNumero(String numA){
//        Pattern pat     = Pattern.compile("[0-9.0-9]");
//        String input    = "" + numA;
//        Matcher mat     = pat.matcher(input);
//        if( mat.find() ){
//            return true;
//        }else{
//            return false;
//        }
//    }

    public double getNumA() {
        return numA;
    }
    public double getNumB() {
        return numB;
    }
    public double sumar(){
         return numA+numB;
    }
    public double restar(){
         return numA-numB;
    }
    public double multiplar(){
         return numA*numB;
    }
    public double dividir(){
         return numA/numB;
    }
}
