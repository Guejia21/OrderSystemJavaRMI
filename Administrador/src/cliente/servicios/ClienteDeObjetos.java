package cliente.servicios;

import cliente.controladores.ControladorAdminCallBackImp;
import cliente.controladores.ControladorAdminCallBackInt;
import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;

import java.rmi.RemoteException;

import servidor.controladores.ControladorRegistroReferenciaAdminInt;


public class ClienteDeObjetos {
    private static ControladorRegistroReferenciaAdminInt objRemoto;
    public static void main(String[] args) throws RemoteException {
        System.out.println("Ingrese la siguiente información del RMI Registry del servidor de turnos: ");
        String direcionIpRMIRegistry = UtilidadesConsola.leerCadena("Direccion IP: ");
        int numPuertoRMIRegistry = UtilidadesConsola.leerEntero("Puerto(0 a 65535): ", 0, 65535);
        objRemoto = (ControladorRegistroReferenciaAdminInt) UtilidadesRegistroC.obtenerObjRemoto(
            direcionIpRMIRegistry, 
            numPuertoRMIRegistry, 
            "controladorRegistroReferenciaAdmin");
        ControladorAdminCallBackInt objRemotoLadoCliente;    
        try{
            objRemotoLadoCliente = new ControladorAdminCallBackImp();
            if(!objRemoto.registrarReferenciaAdmin(objRemotoLadoCliente)){
                System.out.println("Ya existe un administrador conectado al servidor");
                return;
            }
            System.out.println("Admininstrador conectado al servidor");
        }catch(RemoteException ex){
            System.out.println("Error al registrar el modulo en el servidor");
        }
        if(objRemoto!=null){
            Menu objMenu = new Menu(objRemoto);
            objMenu.mostrarMenu();        
        }   
        
    }
    
}
