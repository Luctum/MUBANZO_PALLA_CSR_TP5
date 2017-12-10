package mp_partie2.internals;

import java.util.ArrayList;
import java.util.List;

public class Train extends Thread{

    private int idTrain;
    private int vitesse;
    private int capacite;
    private int tmpsArret;
    private Gare gare;

    public enum EtatTrain {
        A,
        B,
        C,
        D,
    }

    public EtatTrain etat;

    private List<Voyageur> voyageurs;

    public Train(int idTrain, int vitesse, int capacite, int tmpsArret, Gare gare) {
        this.gare = gare;
        this.idTrain = idTrain;
        this.vitesse = vitesse;
        this.capacite = capacite;
        this.tmpsArret = tmpsArret;
        this.voyageurs = new ArrayList<Voyageur>();
        this.etat = EtatTrain.A;
    }

    @Override
    public void run() {
        try {
            System.out.println("Le train est entré en gare:" + this.getidTrain());
            this.gare.getEspaceQuai().addTrain(this);
            Thread.sleep(this.getTmpsArret());
            this.gare.getEspaceQuai().removeTrain(this);
            System.out.println("Le train est parti de la gare" + this.getidTrain() + " avec " + this.getVoyageur().size() + " voyageurs");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getidTrain() {
        return idTrain;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getCapacite() {
        return capacite;
    }

    public boolean resteDeLaPlace(){
        return this.capacite > this.voyageurs.size();
    }

    public int getTmpsArret() {
        return tmpsArret;
    }

    public void addVoyageurs(Voyageur v) {
        this.voyageurs.add(v);
    }

    public List getVoyageur() {
        return ((List) ((ArrayList)this.voyageurs).clone());
    }
}
