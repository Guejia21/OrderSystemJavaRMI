/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.controladores;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.DTO.HamburguesaDTO;
import servidor.Repositorios.GeneradorTurnoRepositoryInt;
/**
 *
 * @author David
 */
public class ControladorGeneradorTurnoImpl extends UnicastRemoteObject implements ControladorGeneradorTurnoInt{
    private final GeneradorTurnoRepositoryInt objRepositorio;
    public ControladorGeneradorTurnoImpl(GeneradorTurnoRepositoryInt objRepositorio) throws RemoteException{
        super();
        this.objRepositorio=objRepositorio;
    }
    
    @Override
    public int generarTurno(HamburguesaDTO objHamburguesa) throws RemoteException{
        return this.objRepositorio.generarTurno(objHamburguesa);
    }
}
