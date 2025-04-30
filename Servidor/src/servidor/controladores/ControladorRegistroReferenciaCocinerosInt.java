package servidor.controladores;
import cliente.controladores.ControladorCocineroCallBackInt;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorRegistroReferenciaCocinerosInt extends Remote {
    public void registrarReferenciaCocinero(ControladorCocineroCallBackInt referenciaCocineros, int noCocinero) throws RemoteException;
    public boolean estaActivado() throws RemoteException;
}
