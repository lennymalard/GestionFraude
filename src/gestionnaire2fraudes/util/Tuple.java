package gestionnaire2fraudes.util;

public class Tuple {
    Object element1;
    Object element2;
    public Tuple(Object element1, Object element2) {
        this.element1 = element1;
        this.element2 = element2;
    }
    public Object getElement1() {
        return element1;
    }
    public Object getElement2() {
        return element2;
    }
    public void setElement1(Object element1) {
        this.element1 = element1;
    }
    public void setElement2(Object element2) {
        this.element2 = element2;
    }
}
