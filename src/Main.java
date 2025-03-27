import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Generar Referencias");
            System.out.println("2. Simular Paginación");
            System.out.println("3. Salir");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                System.out.print("Tamaño de página (bytes): ");
                int tamañoPagina = sc.nextInt();
                sc.nextLine();
                System.out.print("Nombre del archivo BMP: ");
                String nombreArchivo = sc.nextLine();
                GeneradorReferencias gen = new GeneradorReferencias(tamañoPagina, nombreArchivo);
                gen.generarReferencias();
                System.out.println("Referencias generadas en referencias.txt");
            } else if (opcion == 2) {
                System.out.print("Número de marcos: ");
                int numMarcos = sc.nextInt();
                sc.nextLine();
                System.out.print("Nombre del archivo de referencias: ");
                String nombreArchivo = sc.nextLine();
                SimuladorPaginacion sim = new SimuladorPaginacion(numMarcos, nombreArchivo);
                sim.simular();
            } else if (opcion == 3) {
                break;
            }
        }
        sc.close();
    }
}