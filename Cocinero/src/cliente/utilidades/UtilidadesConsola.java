package cliente.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UtilidadesConsola {

    public static int leerEntero(String mensaje, int minimo, int maximo) {
        int opcion = 0;
        boolean valido = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        do {
            try {
                System.out.print(mensaje);
                String linea = br.readLine();
                opcion = Integer.parseInt(linea);
    
                if (opcion >= minimo && opcion <= maximo) {
                    valido = true;
                } else {
                    System.out.println("El número debe estar entre " + minimo + " y " + maximo + ". Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error, ingrese un número válido.");
            }
        } while (!valido);
    
        return opcion;
    }

    public static String leerCadena(String mensaje) {
        String linea = "";
        boolean valido = false;
        do {
            try {
                System.out.print(mensaje);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                linea = br.readLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Error intente nuevamente...");
                valido = false;
            }
        } while (!valido);

        return linea;

    }
}
