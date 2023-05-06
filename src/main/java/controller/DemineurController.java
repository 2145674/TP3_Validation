package controller;


import view.Demineur;

import javax.swing.*;


public class DemineurController {

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new Demineur(16,30,99,3);//hop, on lance le jeux en expert
  }
}
