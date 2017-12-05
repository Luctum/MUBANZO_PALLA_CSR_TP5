package mp_partie1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Train extends Thread{


    private int idTrain;
    private int vitesse;
    private int capacite;
    private int tmpsArret;
    private Gare gare;

    private List<Voyageur> voyageurs;

    public Train(int idTrain, int vitesse, int capacite, int tmpsArret, Gare gare) {
        this.gare = gare;
        this.idTrain = idTrain;
        this.vitesse = vitesse;
        this.capacite = capacite;
        this.tmpsArret = tmpsArret;
        this.voyageurs = new ArrayList<Voyageur>();
    }

    @Override
    public void run() {
        try {
            this.gare.getEspaceQuai().processTrain(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getidTrain() {
        return idTrain;
    }

    public void setidTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public boolean resteDeLaPlace(){
        return this.capacite > this.voyageurs.size();
    }

    public int getTmpsArret() {
        return tmpsArret;
    }

    public void setTmpsArret(int tmpsArret) {
        this.tmpsArret = tmpsArret;
    }

    public void addVoyageurs(Voyageur v) {
        this.voyageurs.add(v);
    }

    public List getVoyageur() {
        return ((List) ((ArrayList)this.voyageurs).clone());
    }
}
