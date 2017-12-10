package mp_partie1;


public class EspaceVente {

    private int billetsDisponibles;
    private int stockBilletMax;

    public EspaceVente(int billetsDisponibles, int stockstockBilletMax) {
        this.billetsDisponibles = billetsDisponibles;
        this.stockBilletMax = stockBilletMax;
    }

    /**
     * Achete un billet
     * @return
     * @throws InterruptedException
     */
    public synchronized boolean achatBillet() throws InterruptedException {
        if(this.billetsDisponibles > 0 ){
            this.billetsDisponibles--;
            return true;
        }
        return false;
    }
}
