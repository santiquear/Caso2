public class Pagina {
    int numeroPagina;
    boolean enRAM;
    int marcoAsignado = -1;
    boolean bitReferencia;
    boolean bitModificacion;

    public Pagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
        this.enRAM = false;
    }
}