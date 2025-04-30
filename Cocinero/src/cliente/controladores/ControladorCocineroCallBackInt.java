package cliente.controladores;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ControladorCocineroCallBackInt extends Remote {
    public void  notificarAsignacionPedido(String mensaje) throws RemoteException;
}
