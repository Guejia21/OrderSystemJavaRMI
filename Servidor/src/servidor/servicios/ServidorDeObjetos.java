/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.servicios;

import servidor.Repositorios.GenerarTurnoRepositoryImpl;
import servidor.controladores.ControladorGeneradorTurnoImpl;
import servidor.controladores.ControladorPrepararPedidoImp;
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
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        int numPuertoRMIRegistryDisplay = 0;
        String direccionIpRMIRegistryDisplay = "";
        System.out.println("Ingrese los datos del RMI Registry donde se encuentra el servidor de asignación de cocineros: ");        
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena("Dirección IP: ");        
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero("Puerto: ");
        
        System.out.println("Ingrese los datos del RMI Registry donde se encuentra el servidor display: ");        
        direccionIpRMIRegistryDisplay = UtilidadesConsola.leerCadena("Dirección IP: ");        
        numPuertoRMIRegistryDisplay = UtilidadesConsola.leerEntero("Puerto: ");
        ControladorDisplayInt objRemotoDisplay = (ControladorDisplayInt) UtilidadesRegistroC.obtenerObjRemoto(
            direccionIpRMIRegistryDisplay,
            numPuertoRMIRegistryDisplay,
            "controladorDisplay");
        ControladorRegistroReferenciaCocinerosImpl objRemotoRegistroReferencias = new ControladorRegistroReferenciaCocinerosImpl();
        ControladorRegistroReferenciaAdminImp objRemotoRegistroReferenciaAdmin = new ControladorRegistroReferenciaAdminImp();
        GenerarTurnoRepositoryImpl objRepositorio = new GenerarTurnoRepositoryImpl(objRemotoDisplay, objRemotoRegistroReferencias,objRemotoRegistroReferenciaAdmin);
        ControladorGeneradorTurnoImpl objRemoto = new ControladorGeneradorTurnoImpl(objRepositorio);        
        ControladorPrepararPedidoImp objRemotoPrepararPedido = new ControladorPrepararPedidoImp(objRepositorio);
        try{
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "controladorGeneradorTurno");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoRegistroReferencias, direccionIpRMIRegistry, numPuertoRMIRegistry, "controladorRegistroReferenciaCocineros");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoRegistroReferenciaAdmin, direccionIpRMIRegistry, numPuertoRMIRegistry, "controladorRegistroReferenciaAdmin");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoPrepararPedido, direccionIpRMIRegistry, numPuertoRMIRegistry, "controladorPrepararPedido");
        } catch (Exception e){
            System.out.println("No fue posible arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }
    }
}
