package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import cliente.controladores.ControladorCocineroCallBackInt;


public class ControladorRegistroReferenciaCocinerosImpl extends UnicastRemoteObject implements ControladorRegistroReferenciaCocinerosInt{
    private final HashMap<Integer, ControladorCocineroCallBackInt> referencias;
    
    public ControladorRegistroReferenciaCocinerosImpl() throws RemoteException {
        super();
        this.referencias = new HashMap<>();
    }

    @Override
    public void registrarReferenciaCocinero(ControladorCocineroCallBackInt referenciaCocinero, int noCocinero) throws RemoteException{
        this.referencias.put(noCocinero, referenciaCocinero);
    }
    @Override
    public boolean estaActivado(){
        return ControladorRegistroReferenciaAdminImp.getActivated();
    }
    public void notificarCocinero(String mensaje, int noCocinero) throws RemoteException{        
        if(!ControladorRegistroReferenciaAdminImp.getActivated()){
            System.out.println("El servidor no esta activo, no se puede notificar al cocinero");
            return;
        }
        var referencia = this.referencias.get(noCocinero);
        if (referencia != null) {
            try {
                referencia.notificarAsignacionPedido(mensaje);
            } catch (RemoteException ex) {
                System.out.println("Error al notificar al cocinero");
            }
        } else {
            System.out.println("No hay cocinero registrado con número: " + noCocinero);
        }
    }
}
