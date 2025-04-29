package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;

import servidor.DTO.NotificacionDTO;

public interface ControladorDisplayInt  extends Remote{
    public void mostrarNotificacion(NotificacionDTO objNotificacion) throws RemoteException;
}
