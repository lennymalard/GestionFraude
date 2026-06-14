package gestionnaire2fraudes.cursus;

/**
 * Énumération représentant les modalités de passage d'une épreuve.
 */
public enum Modalite {
    EXAMEN_ECRIT ("Examen écrit"),
    ORAL("Oral"),
    QCM("QCM"),
    ORDINATEUR("Ordinateur"),
    PROJET("Projet"),
    TP("TP");

    private String modalite = "";

    /**
     * Constructeur de la modalité.
     *
     * @param modalite Le nom descriptif de la modalité.
     */
    Modalite(String modalite) {
        this.modalite = modalite;
    }

    /**
     * Retourne la représentation sous forme de chaîne de caractères de la modalité.
     *
     * @return La modalité sous format String.
     */
    @Override
    public String toString(){
        return this.modalite;
    }
}