import java.util.*;

public class TablaPaginas {
    private List<Pagina> paginas;

    public TablaPaginas(int numPaginas) {
        paginas = new ArrayList<>();
        for (int i = 0; i < numPaginas; i++) {
            paginas.add(new Pagina(i));
        }
    }

    public synchronized Pagina getPagina(int numeroPagina) {
        return paginas.get(numeroPagina);
    }

    public synchronized void actualizarBits(int numeroPagina, boolean ref, boolean mod) {
        Pagina p = paginas.get(numeroPagina);
        p.bitReferencia = ref;
        p.bitModificacion = mod;
    }

    public synchronized List<Pagina> getPaginasEnRAM() {
        List<Pagina> enRAM = new ArrayList<>();
        for (Pagina p : paginas) {
            if (p.enRAM) enRAM.add(p);
        }
        return enRAM;
    }
}