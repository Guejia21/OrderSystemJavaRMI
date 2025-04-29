package servidor.servicios;

import java.rmi.RemoteException;

import servidor.controladores.ControladorDisplayImp;
import servidor.utilidades.UtilidadesConsola;
import servidor.utilidades.UtilidadesRegistroS;

public class ServidorDeObjetos {
    public static void main(String[] args) throws RemoteException {
        int numeroPuertoRMIRegistryServidorDisplay = 0;
        String direccionIpRMIRegistryServidorDisplay = "";
        System.out.println("Ingrese los siguientes datos del RMI Registry: ");  
        direccionIpRMIRegistryServidorDisplay = UtilidadesConsola.leerCadena("Dirección IP: ");
        numeroPuertoRMIRegistryServidorDisplay = UtilidadesConsola.leerEntero("Puerto: ");
        ControladorDisplayImp objRemoto = new ControladorDisplayImp();
        try{
            UtilidadesRegistroS.arrancarNS(numeroPuertoRMIRegistryServidorDisplay);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistryServidorDisplay, numeroPuertoRMIRegistryServidorDisplay, "controladorDisplay");
        }catch(Exception e){
            System.out.println("No fue posible arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }

    }    
}
