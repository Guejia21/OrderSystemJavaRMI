package cliente.servicios;

import cliente.controladores.ControladorCocineroCallBackImp;
import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import servidor.controladores.ControladorRegistroReferenciaCocinerosInt;

public class ClienteDeObjetos {
    private static ControladorRegistroReferenciaCocinerosInt objRemoto;
    public static void main(String[] args) throws RemoteException {
        System.out.println("Ingrese la siguiente información del RMI Registry del servidor de turnos: ");
        String direcionIpRMIRegistry = UtilidadesConsola.leerCadena("Direccion IP: ");
        int numPuertoRMIRegistry = UtilidadesConsola.leerEntero("Puerto(0 a 65535): ", 0, 65535);
        int noModulo = UtilidadesConsola.leerEntero("Número cocinero asignado: ", 0, 3);
        objRemoto = (ControladorRegistroReferenciaCocinerosInt) UtilidadesRegistroC.obtenerObjRemoto(
                direcionIpRMIRegistry,
                numPuertoRMIRegistry,
                "controladorRegistroReferenciaCocineros");
        ControladorCocineroCallBackImp objRemotoLadoCliente;    
        try{
            objRemotoLadoCliente = new ControladorCocineroCallBackImp();
            objRemoto.registrarReferenciaCocinero(objRemotoLadoCliente, noModulo);
            System.out.println("Esperando Notificaciones: ");
        }catch(RemoteException ex){
            System.out.println("Error al registrar el modulo en el servidor");
        }
    }
    
}
