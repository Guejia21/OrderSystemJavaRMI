/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidor.controladores;
import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.HamburguesaDTO;

/**
 *
 * @author David
 */

public interface ControladorGeneradorTurnoInt extends Remote{
    public int generarTurno(HamburguesaDTO objHamburguesaDTO) throws RemoteException;
    public boolean estaActivado() throws RemoteException;
}
