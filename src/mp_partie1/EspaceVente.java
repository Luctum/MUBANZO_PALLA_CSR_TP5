package mp_partie1;


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

    public synchronized boolean achatBillet() throws InterruptedException {
        if(this.billetsDisponibles > 0 ){
            this.billetsDisponibles--;
            return true;
        }
        return false;
    }

    public synchronized void remplirStock(){
            this.billetsDisponibles += (stockBilletMax - billetsDisponibles);
    }



}
