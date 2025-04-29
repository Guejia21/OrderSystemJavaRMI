package cliente.controladores;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorCallBackImp extends UnicastRemoteObject implements ControladorCallBackInt{
    public ControladorCallBackImp() throws RemoteException{
        super();    
    }
    @Override
    public void notificarAsignacionPedido(String mensaje) throws RemoteException{
        System.out.println(mensaje);
    }
}