package servidor.controladores;

import cliente.controladores.ControladorAdminCallBackInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorRegistroReferenciaAdminImp extends UnicastRemoteObject implements ControladorRegistroReferenciaAdminInt {
    private ControladorAdminCallBackInt referenciaAdmin;
    static boolean activado;
    public ControladorRegistroReferenciaAdminImp() throws RemoteException {        
        super();
        this.referenciaAdmin = null;                   
    }
    @Override
    public boolean activarSistema() {
        if(activado){
            System.out.println("El sistema ya está activado.");
            return false;
        }
        activado = !activado;
        System.out.println("Sistema activado.");
        return true;
    }
    @Override
    public boolean desactivarSistema() {
        if(!activado){
            System.out.println("El sistema ya está desactivado.");
            return false;
        }
        activado = !activado;
        System.out.println("Sistema desactivado.");
        return true;
    }
    @Override
    public boolean registrarReferenciaAdmin(ControladorAdminCallBackInt administrador) throws RemoteException {
        if(this.referenciaAdmin != null){
            System.out.println("Ya existe una referencia de administrador registrada.");
            return false;
        }
        this.referenciaAdmin = administrador;
        System.out.println("Referencia de administrador registrada.");
        return true;
    }
    public void notificarAdministrador(String mensaje){
        try{
            referenciaAdmin.notificarCocinerosOcupados(mensaje);
        }catch(RemoteException ex){
            System.out.println("Error al notificar al administrador");
        }
    }
    public static boolean getActivated(){
        return activado;
    }


}
