package cliente.controladores;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorAdminCallBackImp extends UnicastRemoteObject implements ControladorAdminCallBackInt{
    public ControladorAdminCallBackImp() throws RemoteException{
        super();    
    }
    @Override
    public void  notificarCocinerosOcupados(String mensaje) throws RemoteException{
        // Implementación del método para notificar que los cocineros están ocupados
        System.out.println(mensaje);
    }
}