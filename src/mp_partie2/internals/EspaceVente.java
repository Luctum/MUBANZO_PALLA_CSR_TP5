package mp_partie2.internals;


public class EspaceVente {

    private int billetsDisponibles;
    private int stockBilletMax;

    public EspaceVente(int billetsDisponibles, int stockstockBilletMax) {
        this.billetsDisponibles = billetsDisponibles;
        this.stockBilletMax = stockBilletMax;
    }

    public int getBilletsDisponibles() {
        return billetsDisponibles;
    }

    public void setBilletsDisponibles(int billetsDisponibles) {
        this.billetsDisponibles = billetsDisponibles;
    }

    public int getstockBilletMax() {
        return stockBilletMax;
    }

    public void setstockBilletMax(int stockBilletMax) {
        this.stockBilletMax = stockBilletMax;
    }

    /**
     * Achete un billet pour le voyageur v
     * @param v
     * @return
     * @throws InterruptedException
     */
    public synchronized boolean achatBillet(Voyageur v) throws InterruptedException {
        if(this.billetsDisponibles > 0 ){
            this.billetsDisponibles--;
            Thread.sleep(100);
            v.etat = Voyageur.EtatVoyageur.B;
            return true;
        }
        return false;
    }

}
