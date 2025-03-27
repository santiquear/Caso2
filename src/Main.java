import java.io.*;
import java.util.*;

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
                String nombreArchivoReferencias = sc.nextLine();

                
                BufferedReader br = new BufferedReader(new FileReader(nombreArchivoReferencias));
                int tp = Integer.parseInt(br.readLine().split("=")[1]);
                int nf = Integer.parseInt(br.readLine().split("=")[1]);
                int nc = Integer.parseInt(br.readLine().split("=")[1]);
                int nr = Integer.parseInt(br.readLine().split("=")[1]);
                int np = Integer.parseInt(br.readLine().split("=")[1]);
                List<String> referencias = new ArrayList<>();
                String linea;
                while ((linea = br.readLine()) != null) {
                    referencias.add(linea);
                }
                br.close();

                
                TablaPaginas tablaPaginas = new TablaPaginas(np);
                GestorMemoria gestorMemoria = new GestorMemoria(numMarcos, tablaPaginas);

                
                ProcesadorReferencias procesador = new ProcesadorReferencias(referencias, tablaPaginas, gestorMemoria);
                ActualizadorEstado actualizador = new ActualizadorEstado(tablaPaginas);
                procesador.start();
                actualizador.start();

                
                try {
                    procesador.join();
                    actualizador.detener();
                    actualizador.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        
                int hits = procesador.getHits();
                int misses = procesador.getMisses();
                double porcentajeHits = (hits / (double) nr) * 100;
                long tiempoReal = hits * 50L + misses * 10_000_000L; 
                long tiempoTodosHits = nr * 50L; 
                long tiempoTodosMisses = nr * 10_000_000L; 
                System.out.printf("Hits: %d, Misses: %d, Porcentaje Hits: %.2f%%\n", hits, misses, porcentajeHits);
                System.out.printf("Tiempo Real: %d ns, Todos Hits: %d ns, Todos Misses: %d ns\n",
                        tiempoReal, tiempoTodosHits, tiempoTodosMisses);
            } else if (opcion == 3) {
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        sc.close();
    }
}