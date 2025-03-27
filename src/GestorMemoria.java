import java.util.*;

public class GestorMemoria {
    private List<Marco> marcos;
    private TablaPaginas tablaPaginas;

    public GestorMemoria(int numMarcos, TablaPaginas tabla) {
        marcos = new ArrayList<>();
        for (int i = 0; i < numMarcos; i++) {
            marcos.add(new Marco(i));
        }
        this.tablaPaginas = tabla;
    }

    public synchronized int asignarMarco(Pagina pagina) {
        for (Marco m : marcos) {
            if (m.paginaAsignada == null) {
                m.paginaAsignada = pagina;
                pagina.enRAM = true;
                pagina.marcoAsignado = m.numeroMarco;
                return m.numeroMarco;
            }
        }
        return -1; // No hay marcos libres
    }

    public synchronized void reemplazarPagina(Pagina nuevaPagina) {
        // Algoritmo NRU
        List<Pagina> enRAM = tablaPaginas.getPaginasEnRAM();
        Pagina victima = null;
        // Clase 0: R=0, M=0
        for (Pagina p : enRAM) {
            if (!p.bitReferencia && !p.bitModificacion) {
                victima = p;
                break;
            }
        }
        // Clase 1: R=0, M=1
        if (victima == null) {
            for (Pagina p : enRAM) {
                if (!p.bitReferencia && p.bitModificacion) {
                    victima = p;
                    break;
                }
            }
        }
        // Clase 2: R=1, M=0
        if (victima == null) {
            for (Pagina p : enRAM) {
                if (p.bitReferencia && !p.bitModificacion) {
                    victima = p;
                    break;
                }
            }
        }
        // Clase 3: R=1, M=1
        if (victima == null) {
            victima = enRAM.get(0); // Primera página si todas son R=1, M=1
        }

        Marco marco = marcos.get(victima.marcoAsignado);
        marco.paginaAsignada = nuevaPagina;
        victima.enRAM = false;
        victima.marcoAsignado = -1;
        nuevaPagina.enRAM = true;
        nuevaPagina.marcoAsignado = marco.numeroMarco;
    }
}