package cliente.vista;

import servidor.controladores.ControladorPrepararPedidoInt;

import java.rmi.RemoteException;

import cliente.utilidades.UtilidadesConsola;

public class MenuCocinero {
    private final ControladorPrepararPedidoInt objRemoto;
    private int idCocinero;

    public MenuCocinero(ControladorPrepararPedidoInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuCocinero(){
        int opc = 0;
        //pedirIDCocinero();
        do{
            System.out.println("===============Menu Cocinero===============");
            System.out.println("1. Ver detalles del pedido");
            System.out.println("2. Terminar de preparat pedido");
            System.out.println("3. Desconectarse");
            opc = UtilidadesConsola.leerEntero("Digite una opción: ",1,3);
            switch (opc) {
                case 1:
                    try {
                        System.out.println(objRemoto.detallesPedido(idCocinero));
                    } catch (RemoteException e) {
                        System.out.println("Error al obtener los detalles del pedido: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        if(objRemoto.prepararPedido(idCocinero) == 1){
                            System.out.println("Pedido preparado exitosamente!");
                        }else{
                            System.out.println("No tienes asignado ningun pedido por ahora.");
                        }
                    } catch (RemoteException e) {
                        System.out.println("Error al preparar el pedido: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Desconectando...");
                    break;
                default:
                System.out.println("Opción incorrecta");
                    break;
            }
        }while(opc != 3);
    }

    public int pedirIDCocinero(){
        boolean asignado = false;
        while (!asignado) {
            System.out.println("===============Registro Cocinero===============");
            idCocinero = UtilidadesConsola.leerEntero("Ingrese su ID de cocinero (1-3): ", 1, 3);
            try {
                if(!objRemoto.estaActivado()){
                    System.out.println("El sistema se encuentra desactivado, intente más tarde");
                    return -1;
                }                
                if (!objRemoto.validarIdCocineroDisponible(idCocinero)) {
                    objRemoto.registrarCocinero(idCocinero);
                    asignado = true;
                    System.out.println("ID asignado correctamente.");
                } else {
                    System.out.println("ID en uso. Intente con otro.");
                }
            } catch (RemoteException e) {
                System.out.println("Error al validar el ID del cocinero: " + e.getMessage());
            }
        }
        return idCocinero;
    }
}
