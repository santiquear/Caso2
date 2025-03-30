package estructurasdedatos;
public class Pagina {
    private int numeroPagina;
    private boolean enRAM;
    private int marcoAsignado = -1;
    private boolean bitReferencia;
    private boolean bitModificacion;

    public Pagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
        this.enRAM = false;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public boolean isEnRAM() {
        return enRAM;
    }

    public void setEnRAM(boolean enRAM) {
        this.enRAM = enRAM;
    }

    public int getMarcoAsignado() {
        return marcoAsignado;
    }

    public void setMarcoAsignado(int marcoAsignado) {
        this.marcoAsignado = marcoAsignado;
    }

    public boolean isBitReferencia() {
        return bitReferencia;
    }

    public void setBitReferencia(boolean bitReferencia) {
        this.bitReferencia = bitReferencia;
    }

    public boolean isBitModificacion() {
        return bitModificacion;
    }

    public void setBitModificacion(boolean bitModificacion) {
        this.bitModificacion = bitModificacion;
    }
}