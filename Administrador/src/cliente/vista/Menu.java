package cliente.vista;

import java.rmi.RemoteException;

import cliente.utilidades.UtilidadesConsola;
import servidor.controladores.ControladorRegistroReferenciaAdminInt;

public class Menu {
    private final ControladorRegistroReferenciaAdminInt objRemoto;
    public Menu(ControladorRegistroReferenciaAdminInt objRemoto) {
        this.objRemoto = objRemoto;
    }
    public void mostrarMenu() throws RemoteException {
        int opcion = 0;
        do {
            System.out.println("===============Menu===============");
            System.out.println("Amdministración del sistema");
            System.out.println("1. Activar Sistema");
            System.out.println("2. Desactivar Sistema");            
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero("Digite una opción: ",0,Integer.MAX_VALUE);

            switch (opcion) {
                case 1: {
                    if(objRemoto.activarSistema()){
                        System.out.println("El sistema ha sido activado");
                        break;
                    }
                    System.out.println("El sistema ya está activado");                    
                    break;
                }
                    
                case 2: {
                    if(objRemoto.desactivarSistema()){
                        System.out.println("El sistema ha sido desactivado");
                        break;
                    }
                    System.out.println("El sistema ya está desactivado");                    
                    break;
                }                    
                case 3 :{
                    System.out.println("Saliendo, gracias por usar nuestra app :)...");
                    break;
                }                    
                default:{
                    System.out.println("Opción incorrecta");
                    break;
                }                    
            }
        } while (opcion != 3);
    }
}
