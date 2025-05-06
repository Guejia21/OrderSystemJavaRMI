package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import servidor.Repositorios.GenerarTurnoRepositoryImpl;


public class ControladorPrepararPedidoImp extends UnicastRemoteObject implements ControladorPrepararPedidoInt{
    private GenerarTurnoRepositoryImpl objRepositorio;

    public ControladorPrepararPedidoImp(GenerarTurnoRepositoryImpl objRepositorio) throws RemoteException {
        super();
        this.objRepositorio = objRepositorio;
    }

    @Override
    public int prepararPedido(int idCocinero) throws RemoteException{
        if(objRepositorio.liberarCocinero(idCocinero) == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public boolean validarIdCocineroDisponible(int idCocinero) throws RemoteException{
        return objRepositorio.estaCocineroNoInicializado(idCocinero);
    }

    @Override
    public void registrarCocinero(int idCocinero) throws RemoteException{
        objRepositorio.inicializarCocinero(idCocinero);
        objRepositorio.ponerCocineroDisponible(idCocinero);
    }

    @Override
    public String detallesPedido(int idCocinero) throws RemoteException{
        return objRepositorio.detallesPedido(idCocinero);
    }
    @Override
    public boolean estaActivado() throws RemoteException{
        return ControladorRegistroReferenciaAdminImp.getActivated();
    }

    @Override
    public void deshabilitarCocinero(int idCocinero) throws RemoteException{
        objRepositorio.deshabilitarCocinero(idCocinero);
    }
}
