package cliente.controladores;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorCocineroCallBackInt extends Remote{
    public void notificarAsignacionPedido(String mensaje) throws RemoteException;
}
