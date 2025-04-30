package cliente.controladores;

import java.rmi.RemoteException;
import java.rmi.Remote;
//Interface 6
public interface ControladorAdminCallBackInt extends Remote {
    public void  notificarCocinerosOcupados(String mensaje) throws RemoteException;
}
