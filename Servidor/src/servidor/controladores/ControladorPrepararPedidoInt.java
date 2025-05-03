package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorPrepararPedidoInt extends Remote {
    boolean validarIdCocineroDisponible(int idCocinero) throws RemoteException;
    void registrarCocinero(int idCocinero) throws RemoteException;
    int prepararPedido(int idCocinero) throws RemoteException;
    String detallesPedido(int idCocinero) throws RemoteException;        
}
