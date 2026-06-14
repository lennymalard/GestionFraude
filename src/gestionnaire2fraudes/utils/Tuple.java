package gestionnaire2fraudes.utils;

/**
 * Classe représentant un tuple.
 */
public class Tuple {
    Object element1;
    Object element2;

    /**
     * Constructeur pour initialiser un tuple avec deux éléments.
     *
     * @param element1 Le premier élément du tuple.
     * @param element2 Le second élément du tuple.
     */
    public Tuple(Object element1, Object element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    /**
     * Retourne element1.
     *
     * @return element1.
     */
    public Object getElement1() {
        return element1;
    }

    /**
     * Retourne element2.
     *
     * @return elément2.
     */
    public Object getElement2() {
        return element2;
    }

    /**
     * Modifie element1.
     *
     * @param element1 La nouvelle valeur de l'élément 1.
     */
    public void setElement1(Object element1) {
        this.element1 = element1;
    }

    /**
     * Modifie element2.
     *
     * @param element2 La nouvelle valeur de l'élément 2.
     */
    public void setElement2(Object element2) {
        this.element2 = element2;
    }
}