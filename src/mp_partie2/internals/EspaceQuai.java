package mp_partie2.internals;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EspaceQuai {

    private int nbVoie;
    private List<Train> trainEnQuai;

    public EspaceQuai(int nbVoie) {
        this.nbVoie = nbVoie;
        this.trainEnQuai = new ArrayList<>();
    }

    /**
     * @return le nombre de voies libres
     */
    public int nbVoiesLibres(){
        return nbVoie - this.trainEnQuai.size();
    }

    /**
     * Ajout un train à la liste des trains si jamais une voie est disponible
     * @param train
     * @throws InterruptedException
     */
    public synchronized void addTrain(Train train) throws InterruptedException {
        while(nbVoiesLibres() < 1) {
            train.etat = Train.EtatTrain.B;
            wait();
        }
        Thread.sleep(1000/train.getVitesse());
        this.trainEnQuai.add(train);
        train.etat = Train.EtatTrain.C;
        notifyAll();
    }

    /**
     * Supprime un train de la liste des trains (départ)
     * @param train
     */
    public synchronized void removeTrain(Train train) {
        this.trainEnQuai.remove(train);
        train.etat = Train.EtatTrain.D;
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
                    v.etat = Voyageur.EtatVoyageur.C;
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
