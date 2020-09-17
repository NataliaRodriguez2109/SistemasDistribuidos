/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.elements;

import java.io.Serializable;

/**
 *
 * @author Jesus David Otero
 */
public class Paquete implements Serializable {

    public String arreglo[];

    public Paquete(String arreglo[]) {
        this.arreglo = arreglo;
    }

    public String[] getArreglo() {
        return arreglo;
    }

}
