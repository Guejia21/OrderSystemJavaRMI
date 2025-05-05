package cliente.servicios;

import cliente.controladores.ControladorCocineroCallBackImp;
import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.MenuCocinero;

import java.rmi.RemoteException;

import servidor.controladores.ControladorPrepararPedidoInt;
import servidor.controladores.ControladorRegistroReferenciaCocinerosInt;

public class ClienteDeObjetos {
    private static ControladorRegistroReferenciaCocinerosInt objRemotoRef;
    private static ControladorPrepararPedidoInt objPrepararPedido;
    public static void main(String[] args) throws RemoteException {
        System.out.println("Ingrese la siguiente información del RMI Registry del servidor de turnos: ");
        String direccionIpRMIRegistry = UtilidadesConsola.leerCadena("Direccion IP: ");
        int numPuertoRMIRegistry = UtilidadesConsola.leerEntero("Puerto(0 a 65535): ", 0, 65535);
        //int noModulo = UtilidadesConsola.leerEntero("Número cocinero asignado: ", 0, 3);

        // Obtener ferencia remota del objeto ControladorPrepararPedidoInt
        objRemotoRef = (ControladorRegistroReferenciaCocinerosInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistry,
                numPuertoRMIRegistry,
                "controladorRegistroReferenciaCocineros");

        // Obtener referencia remota al objeto ControladorPrepararPedidoInt
        objPrepararPedido = (ControladorPrepararPedidoInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistry,
                numPuertoRMIRegistry,
                "controladorPrepararPedido"
        );

        // crea el obj callback para recibir notificaciones del servidor
        ControladorCocineroCallBackImp objRemotoLadoCliente;   
    
        // Registrar el cocinero en el servidor
        try{
             // Pedir id cocinero
            MenuCocinero objMenu = new MenuCocinero(objPrepararPedido);
            int idCocinero;
            do{
                idCocinero = objMenu.pedirIDCocinero();
            }while(idCocinero==-1);            
            objRemotoLadoCliente = new ControladorCocineroCallBackImp();
            objRemotoRef.registrarReferenciaCocinero(objRemotoLadoCliente, idCocinero);
            // Ejecutar el menu del cocinero
            objMenu.ejecutarMenuCocinero();
            System.out.println("Esperando Notificaciones...");
        }catch(RemoteException ex){
            System.out.println("Error al registrar el modulo en el servidor");
        }
    }
}
