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

/**
 *
 * @author David
 */
public class GenerarTurnoRepositoryImpl implements GeneradorTurnoRepositoryInt{
    private int numeroTurno;
    private int cantidadPedidosFila = 0;
    private final CocineroDTO vectorCocineros[];
    private final HamburguesaDTO pedidosFilaVirtual[];
    private final ControladorDisplayInt objRemotoDisplay;
    private final ControladorRegistroReferenciaCocinerosImpl objReferenciaControladorReferenciaCocineros;
    
    public GenerarTurnoRepositoryImpl(ControladorDisplayInt objRemotoDisplay, ControladorRegistroReferenciaCocinerosImpl objReferenciaControladorReferenciaCocineros) throws RemoteException{
        System.out.println("Configurando cocineros");
        this.vectorCocineros = new CocineroDTO[3];
        this.pedidosFilaVirtual = new HamburguesaDTO[10];
        this.numeroTurno = 1;
        for(int i = 0; i<3; i++){
            this.vectorCocineros[i] = new CocineroDTO();
            this.vectorCocineros[i].setNoCocinero(i+1);
            this.vectorCocineros[i].setOcupado(false);
        }
        this.objRemotoDisplay = objRemotoDisplay;
        this.objReferenciaControladorReferenciaCocineros = objReferenciaControladorReferenciaCocineros;
    }
    
    private int consultarNumeroCocineroDisponible(){
        int posicion = -1;
        for(int i = 0; i<3; i++){
            if(this.vectorCocineros[i].isOcupado()==false){
                posicion = i;
                break;
            }
        }
        return posicion;
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
            System.out.println("Los cocineros se encuentran ocupados");
            this.pedidosFilaVirtual[this.cantidadPedidosFila]=objHamburguesa;
            this.cantidadPedidosFila++;
            System.out.println("El pedido se agregó a la fila virtual");
        }
        else{
            System.out.println("El cocinero en la posicion " +posicion+" esta libre y se asignara al pedido "+objHamburguesa.getNombre());
            this.vectorCocineros[posicion].setOcupado(true);
            this.vectorCocineros[posicion].setObjHamburguesa(objHamburguesa);
            this.vectorCocineros[posicion].setNoCocinero(posicion+1);
            System.out.println("Notificando al cocinero asignado");
            this.objReferenciaControladorReferenciaCocineros.notificarCocinero("Se ha asignado para cocinar una nueva hamburguesa con los siguientes datos: "
                                                                                + " nombre "+objHamburguesa.getNombre() +" "
                                                                                + " tipo "+objHamburguesa.getTipoHamburguesa() +" "
                                                                                + " cantidad de ingredientes extra "+objHamburguesa.getCantidadIngredientesExtra(), posicion+1);   
        }
        this.numeroTurno++;
        NotificacionDTO objNotificacion = new NotificacionDTO();
        objNotificacion.setVectorCocineros(vectorCocineros);
        objNotificacion.setCantidadPedidosFilaVirtual(cantidadPedidosFila);
        try{
            this.objRemotoDisplay.mostrarNotificacion(objNotificacion);
            System.out.println("Notificando al servidor display");
        }catch (RemoteException ex){
            System.out.println("No fue posible norificar al servidor display "+ex.getMessage());
        }
        return this.cantidadPedidosFila;
    }
}
