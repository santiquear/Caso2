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
        return -1;
    }

    public synchronized void reemplazarPagina(Pagina nuevaPagina) {
        List<Pagina> enRAM = tablaPaginas.getPaginasEnRAM();
        Pagina victima = null;
    
        for (Pagina p : enRAM) {
            if (!p.bitReferencia && !p.bitModificacion && victima == null) {
                victima = p;
            }
        }
    
        if (victima == null) {
            for (Pagina p : enRAM) {
                if (!p.bitReferencia && p.bitModificacion && victima == null) {
                    victima = p;
                }
            }
        }
    
        if (victima == null) {
            for (Pagina p : enRAM) {
                if (p.bitReferencia && !p.bitModificacion && victima == null) {
                    victima = p;
                }
            }
        }
    
        if (victima == null && !enRAM.isEmpty()) {
            victima = enRAM.get(0);
        }
    
        if (victima != null) {
            Marco marco = marcos.get(victima.marcoAsignado);
            marco.paginaAsignada = nuevaPagina;
            victima.enRAM = false;
            victima.marcoAsignado = -1;
            nuevaPagina.enRAM = true;
            nuevaPagina.marcoAsignado = marco.numeroMarco;
        }
    }
    
}