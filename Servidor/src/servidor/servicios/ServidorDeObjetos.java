/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.servicios;

import servidor.Repositorios.GenerarTurnoRepositoryImpl;
import servidor.controladores.ControladorGeneradorTurnoImpl;
import servidor.controladores.ControladorRegistroReferenciaAdminImp;
import servidor.controladores.ControladorRegistroReferenciaCocinerosImpl;
import servidor.utilidades.UtilidadesConsola;
import servidor.utilidades.UtilidadesRegistroS;
import java.rmi.RemoteException;
import servidor.controladores.ControladorDisplayInt;
import servidor.utilidades.UtilidadesRegistroC;


/**
 *
 * @author David
 */
public class ServidorDeObjetos {
    public static void main(String args[]) throws RemoteException{
        int numPuertoRMIRegistryCocineros = 0;
        String direccionIpRMIRegistryCocineros = "";
        int numPuertoRMIRegistryDisplay = 0;
        String direccionIpRMIRegistryDisplay = "";
        System.out.println("Ingrese los datos del RMI Registry donde se encuentra el servidor de asignación de cocineros: ");        
        direccionIpRMIRegistryCocineros = UtilidadesConsola.leerCadena("Dirección IP: ");        
        numPuertoRMIRegistryCocineros = UtilidadesConsola.leerEntero("Puerto: ");
        
        System.out.println("Ingrese los datos del RMI Registry donde se encuentra el servidor display: ");        
        direccionIpRMIRegistryDisplay = UtilidadesConsola.leerCadena("Dirección IP: ");        
        numPuertoRMIRegistryDisplay = UtilidadesConsola.leerEntero("Puerto: ");
        ControladorDisplayInt objRemotoDisplay = (ControladorDisplayInt) UtilidadesRegistroC.obtenerObjRemoto(
            direccionIpRMIRegistryDisplay,
            numPuertoRMIRegistryDisplay,
            "controladorDisplay");
        ControladorRegistroReferenciaCocinerosImpl objRemotoRegistroReferencias = new ControladorRegistroReferenciaCocinerosImpl();
        GenerarTurnoRepositoryImpl objRepositorio = new GenerarTurnoRepositoryImpl(objRemotoDisplay, objRemotoRegistroReferencias);
        ControladorGeneradorTurnoImpl objRemoto = new ControladorGeneradorTurnoImpl(objRepositorio);
        ControladorRegistroReferenciaAdminImp objRemotoRegistroReferenciaAdmin = new ControladorRegistroReferenciaAdminImp();
        try{
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistryCocineros);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistryCocineros, numPuertoRMIRegistryCocineros, "controladorGeneradorTurno");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoRegistroReferencias, direccionIpRMIRegistryCocineros, numPuertoRMIRegistryCocineros, "controladorRegistroReferenciaCocineros");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoRegistroReferenciaAdmin, direccionIpRMIRegistryCocineros, numPuertoRMIRegistryCocineros, "controladorRegistroReferenciaAdmin");
        } catch (Exception e){
            System.out.println("No fue posible arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }
    }
}
