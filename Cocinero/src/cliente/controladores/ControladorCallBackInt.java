package cliente.controladores;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ControladorCallBackInt extends Remote {
    public void  notificarAsignacionPedido(String mensaje) throws RemoteException;
}
