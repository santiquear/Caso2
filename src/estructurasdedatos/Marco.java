package estructurasdedatos;
public class Marco {
    private int numeroMarco;
    private Pagina paginaAsignada;

    public int getNumeroMarco() {
        return numeroMarco;
    }

    public void setNumeroMarco(int numeroMarco) {
        this.numeroMarco = numeroMarco;
    }

    public Pagina getPaginaAsignada() {
        return paginaAsignada;
    }

    public void setPaginaAsignada(Pagina paginaAsignada) {
        this.paginaAsignada = paginaAsignada;
    }

    public Marco(int numeroMarco) {
        this.numeroMarco = numeroMarco;
    }
}