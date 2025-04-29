/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cliente.servicios;
import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGeneradorTurnoInt;

/**
 *
 * Clase que ejecuta las funcionalidades del cliente
 */
public class ClienteDeObjetos {
    private static ControladorGeneradorTurnoInt objRemoto;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        System.out.println("Ingrese los siguientes datos del RMI Registry: ");        
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena("Dirección IP: ");        
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero("Puerto(0 a 65535): ",0,65535);
        //Se busca el objeto remotor en el RMI Registry
        objRemoto = (ControladorGeneradorTurnoInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistry,
                numPuertoRMIRegistry, 
                "controladorGeneradorTurno");
        if(objRemoto!=null){
            Menu objMenu = new Menu(objRemoto);
            objMenu.ejecutarMenuPrincipal();        
        }        
    }
    
}
