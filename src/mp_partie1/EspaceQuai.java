package mp_partie1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EspaceQuai {

    private int nbVoie;
    private List<Train> trainEnQuai;

    public EspaceQuai(int nbVoie) {
        this.nbVoie = nbVoie;
        this.trainEnQuai = new ArrayList<>();
    }

    public int getNbVoie() {
        return nbVoie;
    }

    public void setNbVoie(int nbVoie) {
        this.nbVoie = nbVoie;
    }

    public int nbVoiesLibres(){
        return nbVoie - this.trainEnQuai.size();
    }

    public List<Train> getTrainsEnQuai() {
        return this.trainEnQuai;
    }

    public synchronized void addTrain(Train train) throws InterruptedException {
        while(nbVoiesLibres() < 1) {
            wait();
        }
        Thread.sleep(1000/train.getVitesse());
        this.trainEnQuai.add(train);
        notifyAll();
    }

    public void removeTrain(Train train) {
        this.trainEnQuai.remove(train);
        notifyAll();
    }

    /**
     * Le voyageur v essaie de monter à bord, s'il ne trouve pas de train pour monter il attends que de nouveaux trains arrivent.
     * @param v
     * @throws InterruptedException
     */
    public synchronized void monterABord(Voyageur v) throws InterruptedException {
        boolean trainDispo = false;
        while(!trainDispo){
            Iterator<Train> it = this.trainEnQuai.iterator();
            while(!trainDispo && it.hasNext()){
                Train t = it.next();
                if(t.resteDeLaPlace()){
                    t.addVoyageurs(v);
                    System.out.println(v.getNom() + " est entré dans le train " + t.getidTrain());
                    trainDispo = true;
                }
            }
            if(!trainDispo){
                System.out.println(v.getNom() + " attends qu'un train arrive...");
                wait();
            }
        }
    }

}
