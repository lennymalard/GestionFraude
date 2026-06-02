package gestionnaire2fraudes.fraude;

import gestionnaire2fraudes.utils.Tuple;

import java.time.LocalDateTime;

public class FraudePapier extends Fraude{
    private Tuple dimensions;
    private boolean plie;

    public FraudePapier(LocalDateTime dateReleve, String contenu, String description, Tuple dimensions, boolean plie) {
        super(dateReleve, contenu, description);
        this.dimensions = dimensions;
        this.plie = plie;
    }

    public Tuple getDimensions() {
        return dimensions;
    }

    public void setDimensions(Tuple dimensions) {
        this.dimensions = dimensions;
    }

    public boolean isPlie() {
        return plie;
    }

    public void setPlie(boolean plie) {
        this.plie = plie;
    }
}
