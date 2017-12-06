package mp_partie1;


import java.util.ArrayList;
import java.util.List;

public class Gare{

    private EspaceQuai espaceQuai;
    private EspaceVente espaceVente;
    private List<Train> trainsPrévus;
    private List<Voyageur> voyageursEnGare;

    public Gare() {
        this.espaceVente = new EspaceVente(80, 100);
        this.espaceQuai = new EspaceQuai(2);
    }

    public EspaceQuai getEspaceQuai() {
        return espaceQuai;
    }

    public void setEspaceQuai(EspaceQuai espaceQuai) {
        this.espaceQuai = espaceQuai;
    }

    public EspaceVente getEspaceVente() {
        return espaceVente;
    }

    public void setEspaceVente(EspaceVente espaceVente) {
        this.espaceVente = espaceVente;
    }

    public static void main(String[] args){
        Gare g = new Gare();

        g.trainsPrévus = new ArrayList<>();
        g.voyageursEnGare = new ArrayList<>();
        for(int i = 0; i<5; i++){
            g.trainsPrévus.add(new Train(i, i*5+1, 1, 20000, g));
        }
        for(int i = 0; i<100; i++){
            g.voyageursEnGare.add(new Voyageur("Voyageur"+i, g));
        }
        //Les trains essaient d'entrer en quai
        g.trainsPrévus.forEach(Train::start);
        //Les voyageurs essaient d'obtenir un ticket + de monter dans les trains
        g.voyageursEnGare.forEach(Voyageur::start);
    }

}
