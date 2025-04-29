package servidor.DTO;

import java.io.Serializable;

public class NotificacionDTO implements Serializable{
    private CocineroDTO vectorCocineros[];
    private int cantidadPedidosFilaVirtual;
    public NotificacionDTO(){

    }
    public CocineroDTO[] getVectorCocineros(){
        return this.vectorCocineros;
    }
    public int getCantidadPedidosFilaVirtual(){
        return this.cantidadPedidosFilaVirtual;
    }
    public void setVectorCocineros(CocineroDTO[] cocineros){
        this.vectorCocineros = cocineros;
    }
    public void setCantidadPedidosFilaVirtual(int pedidosFila){
        this.cantidadPedidosFilaVirtual = pedidosFila;
    }
}
