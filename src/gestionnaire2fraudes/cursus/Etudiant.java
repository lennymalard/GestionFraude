package gestionnaire2fraudes.cursus;

/**
 * Représente un étudiant identifié par un ID unique, son nom/prénom et son cursus.
 */
public class Etudiant {
    private static int numId = 0;

    private int id;
    private String nom;
    private String prenom;
    private Cursus cursus;

    /**
     * Constructeur d'un étudiant. L'identifiant est généré automatiquement.
     *
     * @param nom Le nom de famille de l'étudiant.
     * @param prenom Le prénom de l'étudiant.
     * @param cursus Le cursus actuel de l'étudiant.
     */
    public Etudiant(String nom, String prenom, Cursus cursus){
        this.id = numId;
        this.nom = nom;
        this.prenom = prenom;
        this.cursus = cursus;
        numId++; // L'identifiant de l'étudiant
    }

    /**
     * Retourne le compteur global d'identifiants.
     *
     * @return Le prochain identifiant.
     */
    public static int getNumId() {
        return numId;
    }

    /**
     * Modifie numId.
     *
     * @param numId La nouvelle valeur de numId.
     */
    public static void setNumId(int numId) {
        Etudiant.numId = numId;
    }

    /**
     * Retourne id.
     *
     * @return L'identifiant de l'étudiant.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Modifie l'identifiant de l'étudiant.
     *
     * @param id Le nouvel ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom de l'étudiant.
     *
     * @return Le nom.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Modifie le nom de l'étudiant.
     *
     * @param nom Le nouveau nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom de l'étudiant.
     *
     * @return Le prénom.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Modifie le prénom de l'étudiant.
     *
     * @param prenom Le nouveau prénom.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne le cursus de l'étudiant.
     *
     * @return Le cursus.
     */
    public Cursus getCursus() {
        return this.cursus;
    }

    /**
     * Modifie le cursus de l'étudiant.
     *
     * @param cursus Le nouveau cursus.
     */
    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }

    /**
     * Retourne la représentation sous forme de chaîne de caractères de l'étudiant.
     *
     * @return L'identité de l'étudiant formattée.
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        //str.append("Nom : ");
        str.append(this.getNom());

        //str.append(" Prenom : ");
        str.append(" ");
        str.append(this.getPrenom());

        str.append("; Cursus : ");
        str.append(this.getCursus());

        str.append("; Id : ");
        str.append(this.getId());
        return str.toString();
    }
}