/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;

import java.io.Serializable;

/**
 * Clase hamburguesa mediante el DTO
 */
public class HamburguesaDTO implements Serializable{
    private int noMesa;
    private String nombre;
    private int cantidadIngredientesExtra;
    private int tipoHamburguesa;
   //---------------------Constructor----------------------
    public HamburguesaDTO(int noMesa, String nombre, int cantidadIngredientesExtra, int tipoHamburguesa) {
        this.noMesa = noMesa;
        this.nombre = nombre;
        this.cantidadIngredientesExtra = cantidadIngredientesExtra;
        this.tipoHamburguesa = tipoHamburguesa;
    }

    public HamburguesaDTO() {
    }
    
    //---------------------Getters y setters----------------------
    public int getNoMesa() {
        return noMesa;
    }

    public void setNoMesa(int noMesa) {
        this.noMesa = noMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadIngredientesExtra() {
        return cantidadIngredientesExtra;
    }

    public void setCantidadIngredientesExtra(int cantidadIngredientesExtra) {
        this.cantidadIngredientesExtra = cantidadIngredientesExtra;
    }

    public int getTipoHamburguesa() {
        return tipoHamburguesa;
    }

    public void setTipoHamburguesa(int tipoHamburguesa) {
        this.tipoHamburguesa = tipoHamburguesa;
    }     
}
