package gestionnaire2fraudes.cursus;

public enum Modalite {
    EXAMEN_ECRIT ("Examen écrit"),
    ORAL("Oral"),
    QCM("QCM"),
    ORDINATEUR("Ordinateur"),
    PROJET("Projet"),
    TP("TP");

    private String modalite = "";

    Modalite(String modalite) {
        this.modalite = modalite;
    }

    @Override
    public String toString(){
        return this.modalite;
    }
}
