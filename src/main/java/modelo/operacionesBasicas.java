/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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

    public double getNumA() {
        return numA;
    }

    public void setNumA(double numA) {
        this.numA = numA;
    }

    public double getNumB() {
        return numB;
    }

    public void setNumB(double numB) {
        this.numB = numB;
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
