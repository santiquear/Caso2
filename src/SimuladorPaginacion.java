import java.io.*;
import java.util.*;

public class SimuladorPaginacion {
    private int numMarcos;
    private String nombreArchivoReferencias;

    public SimuladorPaginacion(int numMarcos, String nombreArchivoReferencias) {
        this.numMarcos = numMarcos;
        this.nombreArchivoReferencias = nombreArchivoReferencias;
    }

    public void simular() throws IOException {
        // Leer archivo de referencias
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

        // Inicializar estructuras
        TablaPaginas tablaPaginas = new TablaPaginas(np);
        GestorMemoria gestorMemoria = new GestorMemoria(numMarcos, tablaPaginas);

        // Iniciar hilos
        ProcesadorReferencias procesador = new ProcesadorReferencias(referencias, tablaPaginas, gestorMemoria);
        ActualizadorEstado actualizador = new ActualizadorEstado(tablaPaginas);
        procesador.start();
        actualizador.start();

        // Esperar a que termine el procesador
        try {
            procesador.join();
            actualizador.detener();
            actualizador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Resultados
        int hits = procesador.getHits();
        int misses = procesador.getMisses();
        double porcentajeHits = (hits / (double) nr) * 100;
        long tiempoReal = hits * 50L + misses * 10_000_000L; // ns
        long tiempoTodosHits = nr * 50L; // ns
        long tiempoTodosMisses = nr * 10_000_000L; // ns

        System.out.printf("Hits: %d, Misses: %d, Porcentaje Hits: %.2f%%\n", hits, misses, porcentajeHits);
        System.out.printf("Tiempo Real: %d ns, Todos Hits: %d ns, Todos Misses: %d ns\n",
                tiempoReal, tiempoTodosHits, tiempoTodosMisses);
    }
}