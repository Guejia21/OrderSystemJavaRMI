package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;

import cliente.controladores.ControladorAdminCallBackInt;

public interface ControladorRegistroReferenciaAdminInt extends Remote{
    public boolean activarSistema() throws RemoteException;
    public boolean desactivarSistema() throws RemoteException;
    public boolean registrarReferenciaAdmin(ControladorAdminCallBackInt administrador) throws RemoteException;    
}
