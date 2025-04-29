package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import cliente.controladores.ControladorCallBackInt;

public class ControladorRegistroReferenciaCocinerosImpl extends UnicastRemoteObject implements ControladorRegistroReferenciaCocinerosInt{
    private final HashMap<Integer, ControladorCallBackInt> referencias;
    
    public ControladorRegistroReferenciaCocinerosImpl() throws RemoteException {
        super();
        this.referencias = new HashMap<>();
    }

    @Override
    public void registrarReferenciaCocinero(ControladorCallBackInt referenciaCocinero, int noCocinero) throws RemoteException{
        this.referencias.put(noCocinero, referenciaCocinero);
    }

    public void notificarCocinero(String mensaje, int noCocinero){
        var referencia = this.referencias.get(noCocinero);
        try{
            referencia.notificarAsignacionPedido(mensaje);
        }catch(RemoteException ex){
            System.out.println("Error al notificar al cocinero");
        }
    }
}
