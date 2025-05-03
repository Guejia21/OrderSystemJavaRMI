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
        // Obtener la hora actual
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaActualizacion = ahora.format(formato);
        CocineroDTO vectorCocineros[] = objNotificacion.getVectorCocineros();
        System.out.println("============= Turnos y cocineros asignados=============");
        System.out.printf(
            "%-10s %-12s %-22s %-25s %-10s%n",
            "No mesa","No cocinero","Cant. Ingredientes", "Nombre Hamburguesa","Tipo"
        );
        for(int i = 0; i<3 ; i++){
            if (vectorCocineros[i] != null && vectorCocineros[i].getObjHamburguesa() != null) {
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
        if(objNotificacion.getCantidadPedidosFilaVirtual() > 0){
            System.out.println("Cantidad de pedidos en la fila virtual: " + objNotificacion.getCantidadPedidosFilaVirtual());
        }
        System.out.println("Hora de actualización: " + horaActualizacion);
    }
    
}
