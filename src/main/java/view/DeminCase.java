package view;

//C'est une case de jeux en partie autonome
//Elle g�re elle m�me certain ev�nements de la souris

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeminCase
    extends JPanel
    implements MouseListener {

  model.DeminCase deminCase = new model.DeminCase();

  public DeminCase() {
    try {
      //construction de la case
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public model.DeminCase getDeminCase() {
    return deminCase;
  }

  public void setDeminCase(model.DeminCase deminCase) {
    this.deminCase = deminCase;
  }

  private void jbInit() throws Exception {
    this.setBackground(Graphisme.dessus);
    this.setMaximumSize(new Dimension(16, 16)); //On impose la taille
    this.setMinimumSize(new Dimension(16, 16));
    this.addMouseListener(this);
    this.setPreferredSize(new Dimension(16, 16));
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
    //Selectionne la case si on clique dessus
    if (e.getModifiers() == 16 && deminCase.getEtat() != 1 && deminCase.getEtat() != 2 && !deminCase.isBlocked()) {
      deminCase.setSelected(true);
      this.paintComponent(this.getGraphics());
      repaint();
    }
  }

  public void mouseReleased(MouseEvent e) {
    //Déselctionne la cases
    deminCase.setSelected(false);
    this.paintComponent(this.getGraphics());
    repaint();
  }

  public void mouseEntered(MouseEvent e) {
    //Si la case est relev�e est que la souris passe dessus avec le clic gauche, on s�l�ctionne
    if (e.getModifiers() == 16 && deminCase.getEtat() != 1 && deminCase.getEtat() != 2 && !deminCase.isBlocked()) {
      deminCase.setSelected(true);
      this.paintComponent(this.getGraphics());
      repaint();
    }
  }

  public void mouseExited(MouseEvent e) {
    //pas fin mais efficace
    deminCase.setSelected(false);
    this.paintComponent(this.getGraphics());
    repaint();
  }

  public void paintComponent(Graphics g/*ra*/) {
    super.paintComponent(g/*ra*/);
    /*Graphics2D g = (Graphics2D) gra;
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                       RenderingHints.VALUE_ANTIALIAS_ON);
    g.setStroke(new BasicStroke(1.5f));*/
    if (getDeminCase().getGr() != null) {
      if (!getDeminCase().isSelected()) { //case non enfonc�e
        if (getDeminCase().getEtat() == 0) { //normal
          g.setColor(Color.white); //bordure haut et gauche blanche
          g.drawLine(0, 0, 0, 15);
          g.drawLine(0, 0, 15, 0);
        }
        else if (getDeminCase().getEtat() == 1) g.drawImage(getDeminCase().getGr().chiffre[getDeminCase().getChiffre()], 0, 0, null); //chiffre ou blanc
        else if (getDeminCase().getEtat() == 2) g.drawImage(getDeminCase().getGr().drapeau, 0, 0, null); //drapeau
        else if (getDeminCase().getEtat() == 6) g.drawImage(getDeminCase().getGr().erreur, 0, 0, null); //erreur de drapeau
        else if (getDeminCase().getEtat() == 3) g.drawImage(getDeminCase().getGr().question, 0, 0, null); //?
        else if (getDeminCase().getEtat() == 4) g.drawImage(getDeminCase().getGr().boum, 0, 0, null); //mine sur fond rouge
        else if (getDeminCase().getEtat() == 5) g.drawImage(getDeminCase().getGr().mine, 0, 0, null); //mine
      }
      else { //case enfonc�e
        if (getDeminCase().getEtat() == 3) g.drawImage(getDeminCase().getGr().questionSel, 0, 0, null); //?
        else if (getDeminCase().getEtat() != 1) { //autre cas de case relev�e normalement, seul le cas etat==0 en raison des conditions du reste du programme
          g.setColor(Color.gray); //bordure haut et gauche grise
          g.drawLine(0, 0, 0, 15);
          g.drawLine(0, 0, 15, 0);
        }
      }
    }
    //g.setStroke(new BasicStroke(1.5f));
    g.setColor(Color.darkGray); //bordure bas et droite
    g.drawLine(0, 15, 15, 15);
    g.drawLine(15, 0, 15, 15);
    g.dispose();
  }


  public void reset() { //remise � zero des principaux param�tres
    getDeminCase().setEtat(0);
    getDeminCase().setSelected(false);
    getDeminCase().setMine(false);
    getDeminCase().setBlocked(false);
    //repaint();
  }
}
