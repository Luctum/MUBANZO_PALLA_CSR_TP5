package mp_partie2.internals;

public class Voyageur extends Thread{

    private String nom;
    private Gare gare;

    public enum EtatVoyageur {
        A,
        B,
        C,
        D,
    }

    public EtatVoyageur etat;

    /**
     *
     * @param nom
     * @param gare
     */
    public Voyageur(String nom, Gare gare) {
        this.gare = gare;
        this.nom = nom;
        this.etat = EtatVoyageur.A;
    }

    /**
     * Lancement du processus d'action des voyageurs.
     * Un voyageur achete un ticket et s'il n'y en a plus il est triste
     * Il monte dans un train s'il y en a un de disponible et sinon, il attends
     */
    @Override
    public void run() {
        EspaceVente ev = this.gare.getEspaceVente();
        EspaceQuai eq = this.gare.getEspaceQuai();

        try {
            if (ev.achatBillet(this)) {
                System.out.println(this.getNom() + " a acheté un ticket !");
                try {
                    eq.monterABord(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println(this.getNom() + " est submergé par la tristesse car il n'a pas pu acheter un ticket et est parti...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }
}
