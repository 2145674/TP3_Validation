package view;

public class Temps implements Runnable {
  Thread thread;
  Segment aff;

  model.Temps temps = new model.Temps();

  public Temps(Segment compteur) {
    aff=compteur;
  }
  public void run() {
    while (temps.isMarche()) {
      try {
        thread.sleep(1000);
        if (temps.isThreadSuspended()) {
          synchronized(this) {
            while (temps.isThreadSuspended())
              wait();
          }
        }
      }
      catch(java.lang.InterruptedException e) {}
      int time = aff.getSegment().getValeur();
      if (temps.isMarche() && time<999) {//faire plus de 999s, c'est quand même beaucoup...
        aff.setValeur(time+1);
      }

    }
  }
  public void start() {
    if (thread==null) thread = new Thread(this);
    thread.setPriority(thread.MAX_PRIORITY);
    thread.start();
  }
  public void stop() {
    if (thread!=null) thread = null;
  }
  public void cancel() {
    temps.setMarche(false);
  }
  public void suspend() {
    temps.setMarche(true);
  }
  public synchronized void resume() {
    temps.setThreadSuspended(false);
    notify();
  }
}
