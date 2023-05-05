package model;

public class Temps {
    boolean marche=true;
    boolean threadSuspended=false;

    public Temps() {
    }

    public boolean isMarche() {
        return marche;
    }

    public void setMarche(boolean marche) {
        this.marche = marche;
    }

    public boolean isThreadSuspended() {
        return threadSuspended;
    }

    public void setThreadSuspended(boolean threadSuspended) {
        this.threadSuspended = threadSuspended;
    }
}
