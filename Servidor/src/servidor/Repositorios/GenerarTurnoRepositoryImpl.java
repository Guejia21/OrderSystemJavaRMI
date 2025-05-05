/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.Repositorios;

import servidor.DTO.CocineroDTO;
import servidor.DTO.HamburguesaDTO;
import servidor.DTO.NotificacionDTO;
import java.rmi.RemoteException;
import servidor.controladores.ControladorDisplayInt;
import servidor.controladores.ControladorRegistroReferenciaCocinerosImpl;
import servidor.controladores.ControladorRegistroReferenciaAdminImp;

/**
 *
 * @author David
 */
public class GenerarTurnoRepositoryImpl implements GeneradorTurnoRepositoryInt{
    //private int numeroTurno;
    private int cantidadPedidosFila = 0;
    private final CocineroDTO vectorCocineros[];
    private final HamburguesaDTO pedidosFilaVirtual[];
    private final ControladorDisplayInt objRemotoDisplay;
    private final ControladorRegistroReferenciaCocinerosImpl objReferenciaControladorReferenciaCocineros;
    private final ControladorRegistroReferenciaAdminImp objReferenciaControladorReferenciaAdmin;
    
    public GenerarTurnoRepositoryImpl(ControladorDisplayInt objRemotoDisplay, ControladorRegistroReferenciaCocinerosImpl objReferenciaControladorReferenciaCocineros,ControladorRegistroReferenciaAdminImp objReferenciaControladorReferenciaAdmin) throws RemoteException{
        System.out.println("Configurando cocineros");
        this.vectorCocineros = new CocineroDTO[3];
        this.pedidosFilaVirtual = new HamburguesaDTO[10];
        //this.numeroTurno = 1;
        /* 
        for(int i = 0; i<3; i++){
            this.vectorCocineros[i] = new CocineroDTO();
            this.vectorCocineros[i].setNoCocinero(i+1);
            this.vectorCocineros[i].setOcupado(false);
        }*/

        this.objRemotoDisplay = objRemotoDisplay;
        this.objReferenciaControladorReferenciaCocineros = objReferenciaControladorReferenciaCocineros;
        this.objReferenciaControladorReferenciaAdmin = objReferenciaControladorReferenciaAdmin;
    }
    
    public void imprimirHamburguesa(HamburguesaDTO objHamburguesa){
        System.out.println("Numero de mesa: " + objHamburguesa.getNoMesa());
        System.out.println("Nombre de hamburguesa: " + objHamburguesa.getNombre());
        System.out.println("Cantidad de ingredientes extra: " + objHamburguesa.getCantidadIngredientesExtra());
        System.out.println("Tipo de hamburguesa: " + objHamburguesa.getTipoHamburguesa());
    }
    @Override
    public int generarTurno(HamburguesaDTO objHamburguesa){
        int posicion = this.consultarNumeroCocineroDisponible();
        imprimirHamburguesa(objHamburguesa);
        if(posicion == -1){
            System.out.println("No hay cocineros disponibles");
            this.pedidosFilaVirtual[this.cantidadPedidosFila]=objHamburguesa;
            this.cantidadPedidosFila++;
            System.out.println("El pedido se agregó a la fila virtual");            
        }else{
            System.out.println("El cocinero en la posicion " +posicion+" esta libre y se asignara al pedido "+objHamburguesa.getNombre());
            this.vectorCocineros[posicion].setOcupado(true);
            this.vectorCocineros[posicion].setObjHamburguesa(objHamburguesa);
            this.vectorCocineros[posicion].setNoCocinero(posicion + 1);
            System.out.println("Notificando al cocinero asignado");
            try {
                this.objReferenciaControladorReferenciaCocineros.notificarCocinero("\n==================Nueva Notificacion Cocinero=================\nSe ha asignado para cocinar una nueva hamburguesa con los siguientes datos: "
                                                                                    + "No. Mesa " + objHamburguesa.getNoMesa() +" "
                                                                                    + " nombre "+objHamburguesa.getNombre() +" "
                                                                                    + " tipo "+objHamburguesa.getTipoHamburguesa() +" "
                                                                                    + " cantidad de ingredientes extra "+objHamburguesa.getCantidadIngredientesExtra() + "\n================================================================\nSiga con el menu anterior. Digite una opcion: ", posicion + 1);
            } catch (RemoteException e) {
                 System.out.println("Error al notificar al cocinero "+e.getMessage());
            }
        }
        
        // Validar si hay cocineros inicializados antes de enviar la notificación
        if (!hayCocinerosInicializados()) {
            System.out.println("No hay cocineros inicializados. No se enviará la notificación al servidor display.");
            return this.cantidadPedidosFila;
        }
            
        //this.numeroTurno++;
        NotificacionDTO objNotificacion = new NotificacionDTO();
        objNotificacion.setVectorCocineros(vectorCocineros);
        objNotificacion.setCantidadPedidosFilaVirtual(cantidadPedidosFila);
        //Notificaciones
        try{
            this.objRemotoDisplay.mostrarNotificacion(objNotificacion);
            System.out.println("Notificando al servidor display");
            if(this.consultarNumeroCocineroDisponible()==-1){
                System.out.println("Notificando al administrador ");
                this.objReferenciaControladorReferenciaAdmin.notificarAdministrador("Todos los cocineros se encuentran ocupados");
            }
        }catch (RemoteException ex){
            System.out.println("No fue posible enviar las notificaciones"+ex.getMessage());
        }

        //actualizarTablaPedidos();
        return this.cantidadPedidosFila;
    }

    // Metodos para el controlador de cocinero
    
    public void inicializarCocinero(int idCocinero) {
        this.vectorCocineros[idCocinero-1] = new CocineroDTO();
        this.vectorCocineros[idCocinero-1].setNoCocinero(idCocinero);
        this.vectorCocineros[idCocinero-1].setOcupado(false);
        System.out.println("El cocinero con ID " + idCocinero + " ha sido inicializado.");
        reasignarPedidosDeFilaVirtual();
    }

    public void ponerCocineroDisponible(int idCocinero) {
        this.vectorCocineros[idCocinero-1].setOcupado(false);
    }

    public int liberarCocinero(int idCocinero) {
        if(this.vectorCocineros[idCocinero-1].isOcupado() == false || this.vectorCocineros[idCocinero-1].getObjHamburguesa() == null){
            System.out.println("El cocinero no tiene asignado ningun pedido");
            return 0;
        }else{
            this.vectorCocineros[idCocinero-1].setOcupado(false);
            this.vectorCocineros[idCocinero-1].setObjHamburguesa(null);
            System.out.println("El cocinero con ID " + idCocinero + " ha liberado el pedido.");
            reasignarPedidosDeFilaVirtual();
            return 1;
        }
    }

    public int consultarNumeroCocineroDisponible(){
        int posicion = -1;
        for(int i = 0; i<3; i++){
            if(this.vectorCocineros[i] != null && this.vectorCocineros[i].isOcupado()==false){
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    public String detallesPedido(int idCocinero) {
        HamburguesaDTO pedido = this.vectorCocineros[idCocinero-1].getObjHamburguesa();
        if(pedido != null){
            return "Detalles del pedido: \n" +
                   "Numero de mesa: " + pedido.getNoMesa() + "\n" +
                   "Nombre de hamburguesa: " + pedido.getNombre() + "\n" +
                   "Cantidad de ingredientes extra: " + pedido.getCantidadIngredientesExtra() + "\n" +
                   "Tipo de hamburguesa: " + pedido.getTipoHamburguesa() + "\n";
        } else {
            return "No hay pedido asignado al cocinero con ID " + idCocinero;
        }
    }

    public boolean hayCocinerosInicializados(){
        for (CocineroDTO cocinero : this.vectorCocineros) {
            if (cocinero != null) {
                return true; // Hay al menos un cocinero inicializado
            }
        }
        return false; // No hay cocineros inicializados
    }

    public void actualizarTablaPedidos() {
        
        NotificacionDTO objNotificacion = new NotificacionDTO();
        objNotificacion.setVectorCocineros(this.vectorCocineros);
        objNotificacion.setCantidadPedidosFilaVirtual(this.cantidadPedidosFila);
        try{
            this.objRemotoDisplay.mostrarNotificacion(objNotificacion);
            System.out.println("Notificando al servidor display");
        }catch (RemoteException ex){
            System.out.println("No fue posible norificar al servidor display "+ex.getMessage());
        }
    }

    public void reasignarPedidosDeFilaVirtual() {        
        for (int i = 0; i < cantidadPedidosFila; i++) {
            int posicion = consultarNumeroCocineroDisponible();
            if (posicion != -1) { // Si hay un cocinero disponible
                HamburguesaDTO pedido = pedidosFilaVirtual[i];
                vectorCocineros[posicion].setOcupado(true);
                vectorCocineros[posicion].setObjHamburguesa(pedido);
                System.out.println("Asignando pedido de la fila virtual al cocinero en la posición " + (posicion + 1));
        
                // Notificar al cocinero
                try {
                    objReferenciaControladorReferenciaCocineros.notificarCocinero(
                        "\n==================Nueva Notificacion Cocinero=================\nSe ha asignado para cocinar una nueva hamburguesa con los siguientes datos: "
                                                                                    + "No. Mesa " + pedido.getNoMesa() +" "
                                                                                    + " nombre "+pedido.getNombre() +" "
                                                                                    + " tipo "+pedido.getTipoHamburguesa() +" "
                                                                                    + " cantidad de ingredientes extra "+pedido.getCantidadIngredientesExtra() + "\n================================================================\nSiga con el menu anterior. Digite una opcion: ",
                        posicion + 1);
                } catch (RemoteException e) {
                    System.out.println("Error al notificar al cocinero: " + e.getMessage());
                }
        
                // Eliminar el pedido de la fila virtual
                for (int j = i; j < cantidadPedidosFila - 1; j++) {
                    pedidosFilaVirtual[j] = pedidosFilaVirtual[j + 1];
                }
                pedidosFilaVirtual[cantidadPedidosFila - 1] = null;
                cantidadPedidosFila--;
        
                // Actualizar el servidor display
                actualizarTablaPedidos();
        
                // Reducir el índice para revisar el siguiente pedido en la fila
                i--;
            } else {
                break; // Si no hay más cocineros disponibles, salir del bucle
            }
        }
    }
}
