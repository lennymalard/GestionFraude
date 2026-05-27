package gestionnaire2fraudes.cursus;

public class Etudiant {
    private static int numId = 0;

    private int id;
    private String nom;
    private String prenom;
    private Cursus cursus;

    Etudiant(String nom, String prenom, Cursus cursus){
        this.id = numId;
        this.nom = nom;
        this.prenom = prenom;
        this.cursus = cursus;
        numId++;
    }

    public static int getNumId() {
        return numId;
    }

    public static void setNumId(int numId) {
        Etudiant.numId = numId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Cursus getCursus() {
        return cursus;
    }

    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }
}
