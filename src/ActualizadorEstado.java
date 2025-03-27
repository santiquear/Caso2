public class ActualizadorEstado extends Thread {
    private TablaPaginas tablaPaginas;
    private volatile boolean running = true;

    public ActualizadorEstado(TablaPaginas tabla) {
        this.tablaPaginas = tabla;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1);
                synchronized (tablaPaginas) {
                    for (Pagina p : tablaPaginas.getPaginasEnRAM()) {
                        p.bitReferencia = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void detener() { running = false; }
}