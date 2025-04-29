package servidor.controladores;
import cliente.controladores.ControladorCallBackInt;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorRegistroReferenciaCocinerosInt extends Remote {
    public void registrarReferenciaCocinero(ControladorCallBackInt referenciaCocineros, int noCocinero) throws RemoteException;
}
