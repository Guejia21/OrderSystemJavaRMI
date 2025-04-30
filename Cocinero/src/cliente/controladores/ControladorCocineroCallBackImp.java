package cliente.controladores;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorCocineroCallBackImp extends UnicastRemoteObject implements ControladorCocineroCallBackInt{
    public ControladorCocineroCallBackImp() throws RemoteException{
        super();    
    }
    @Override
    public void notificarAsignacionPedido(String mensaje) throws RemoteException{
        System.out.println(mensaje);
    }
}