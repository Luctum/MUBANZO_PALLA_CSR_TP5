package mp_partie1;


import java.util.ArrayList;
import java.util.List;

public class Gare{

    private EspaceQuai espaceQuai;
    private EspaceVente espaceVente;

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

        List<Train> trains = new ArrayList<>();
        List<Voyageur> voyageurs = new ArrayList<>();
        for(int i = 0; i<5; i++){
            trains.add(new Train(i, i*15+1, 10, 10000, g));
        }
        for(int i = 0; i<100; i++){
            voyageurs.add(new Voyageur("Voyageur"+i, g));
        }
        //Les trains essaient d'entrer en quai
        trains.forEach(Train::start);
        //Les voyageurs essaient d'obtenir un ticket + de monter dans les trains
        voyageurs.forEach(Voyageur::start);
    }

}
