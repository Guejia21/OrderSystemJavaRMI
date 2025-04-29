package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import servidor.DTO.CocineroDTO;
import servidor.DTO.NotificacionDTO;

public class ControladorDisplayImp extends UnicastRemoteObject implements ControladorDisplayInt{
    public ControladorDisplayImp() throws RemoteException{
        super();
    }
    @Override
    public void mostrarNotificacion(NotificacionDTO objNotificacion) throws RemoteException {
        CocineroDTO vectorCocineros[] = objNotificacion.getVectorCocineros();
        System.out.println("============= Turnos y cocineros asignados=============");
        System.out.printf(
            "%-10s %-12s %-22s %-25s %-10s%n",
            "No mesa","No cocinero","Cant. Ingredientes", "Nombre Hamburguesa","Tipo"
        );
        for(int i = 0; i<3 ; i++){
            if (vectorCocineros[i].isOcupado()) {
                System.out.printf(
                    "%-10s %-12s %-22s %-25s %-10s%n",
                    vectorCocineros[i].getObjHamburguesa().getNoMesa(),
                    vectorCocineros[i].getNoCocinero(),
                    vectorCocineros[i].getObjHamburguesa().getCantidadIngredientesExtra(),
                    vectorCocineros[i].getObjHamburguesa().getNombre(),
                    vectorCocineros[i].getObjHamburguesa().getTipoHamburguesa()
                );
            }
        }
        System.out.println("Cantidad de pedidos en la fila virtual: " + objNotificacion.getCantidadPedidosFilaVirtual());
    }
    
}
