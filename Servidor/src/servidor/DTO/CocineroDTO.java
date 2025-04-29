/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class CocineroDTO implements Serializable{
    private int noCocinero;
    private boolean ocupado;
    private HamburguesaDTO objHamburguesa;

    public CocineroDTO() {
    }

    public int getNoCocinero() {
        return noCocinero;
    }

    public void setNoCocinero(int noCocinero) {
        this.noCocinero = noCocinero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public HamburguesaDTO getObjHamburguesa() {
        return objHamburguesa;
    }

    public void setObjHamburguesa(HamburguesaDTO objHamburguesa) {
        this.objHamburguesa = objHamburguesa;
    }
}


