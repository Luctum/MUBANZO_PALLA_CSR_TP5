package mp_partie2.internals;


import org.restlet.Application;

import java.util.ArrayList;
import java.util.List;

public class Gare {

    private EspaceQuai espaceQuai;
    private EspaceVente espaceVente;
    private List<Train> trainsPrévus;
    private List<Voyageur> voyageursEnGare;

    public Gare() {
        this.espaceVente = new EspaceVente(80, 100);
        this.espaceQuai = new EspaceQuai(2);
        this.trainsPrévus = new ArrayList<>();
        this.voyageursEnGare = new ArrayList<>();
    }

    public EspaceQuai getEspaceQuai() {
        return espaceQuai;
    }

    public EspaceVente getEspaceVente() {
        return espaceVente;
    }

    public List<Train> getTrainsPrévus() {
        return trainsPrévus;
    }

    public void addTrain(Train t) {
        this.trainsPrévus.add(t);
        t.start();
    }

    public List<Voyageur> getVoyageursEnGare() {
        return voyageursEnGare;
    }

    public void addVoyageur(Voyageur v) {
        this.voyageursEnGare.add(v);
        v.start();
    }

    public static void main(String[] args){
        Gare g = new Gare();
        for(int i = 0; i<3; i++){
            g.trainsPrévus.add(new Train(i, i*5+1, 10, 5000, g));
        }
        for(int i = 0; i<30; i++){
            g.voyageursEnGare.add(new Voyageur("Voyageur"+i, g));
        }
        //Les voyageurs essaient d'obtenir un ticket + de monter dans les trains
        g.voyageursEnGare.forEach(Voyageur::start);
        //Les trains essaient d'entrer en quai
        g.trainsPrévus.forEach(Train::start);
    }

}
