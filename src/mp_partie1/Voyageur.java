package mp_partie1;

import java.util.Iterator;
import java.util.Optional;

public class Voyageur extends Thread{

    private String nom;
    private Gare gare;

    public Voyageur(String nom, Gare gare) {
        this.gare = gare;
        this.nom = nom;
    }

    @Override
    public void run() {
        EspaceVente ev = this.gare.getEspaceVente();
        EspaceQuai eq = this.gare.getEspaceQuai();

        //Le voyageur essaie d'acheter un ticket, s'il en achete un il peut monter à bord sinon il est triste et s'en va
        try {
            if (ev.achatBillet()) {
                System.out.println(this.getNom() + " A acheté un ticket !");
                try {
                    eq.monterABord(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println(this.getNom() + " N'a pas pu acheter un ticket et est parti...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Gare getGare() {
        return gare;
    }

    public void setGare(Gare gare) {
        this.gare = gare;
    }
}
