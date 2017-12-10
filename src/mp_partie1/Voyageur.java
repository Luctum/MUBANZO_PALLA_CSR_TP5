package mp_partie1;

public class Voyageur extends Thread{

    private String nom;
    private Gare gare;

    /**
     *
     * @param nom
     * @param gare
     */
    public Voyageur(String nom, Gare gare) {
        this.gare = gare;
        this.nom = nom;
    }

    /**
     * Le voyageur essaie d'acheter un ticket, s'il en achete un il peut monter à bord sinon il est triste et s'en va
     */
    @Override
    public void run() {
        EspaceVente ev = this.gare.getEspaceVente();
        EspaceQuai eq = this.gare.getEspaceQuai();

        try {
            if (ev.achatBillet()) {
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
