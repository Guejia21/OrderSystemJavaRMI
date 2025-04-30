package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.DTO.HamburguesaDTO;
import servidor.controladores.ControladorGeneradorTurnoInt;

public class Menu {

    private final ControladorGeneradorTurnoInt objRemoto;

    public Menu(ControladorGeneradorTurnoInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("===============Menu===============");
            System.out.println("Sistema para generar pedidos de hamburguesas");
            System.out.println("1. Enviar pedido");
            System.out.println("2. Salir");

            opcion = UtilidadesConsola.leerEntero("Digite una opción: ",0,Integer.MAX_VALUE);

            switch (opcion) {
                case 1 ->
                    Opcion1();
                case 2 ->
                    System.out.println("Saliendo, gracias por usar nuestra app :)...");
                default ->
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 2);
    }

    private void Opcion1()  {        
        try {
            if(!objRemoto.estaActivado()){
                System.out.println("El sistema se encuentra desactivado, intente más tarde");
                return;
            }
            System.out.println("===============Registro de Pedido de Hamburguesa===============");            
            int mesa = UtilidadesConsola.leerEntero("Número de mesa: ",0,100);            
            String nombre = UtilidadesConsola.leerCadena("Nombre: ");           
             int tipo;
            do{
                tipo = UtilidadesConsola.leerEntero("Tipo de hamburguesa (1->Pequeña, 2->Mediana,3->Grande):",0,3);                            
            }while(tipo < 0 && tipo>3);            
            int cantIngredientesExtra = UtilidadesConsola.leerEntero("Cantidad de ingredientes extra: ",0,100);
            HamburguesaDTO objHamburguesa = new HamburguesaDTO(mesa, nombre, cantIngredientesExtra, tipo);

            int valor = objRemoto.generarTurno(objHamburguesa);
            System.out.println("Pedido recibido exitosamente!");
            System.out.println("Cantidad de pedidos en la fila: " + valor);
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente..." + e);
        }
    }
}
