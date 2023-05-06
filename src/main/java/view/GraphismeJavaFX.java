package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.*;

public class GraphismeJavaFX {

    public static WritableImage drapeau, question, questionSel, mine, boum,
            erreur;
    public static WritableImage[] chiffre = new WritableImage[9];
    private Color[] couleurs = new Color[8];
    public static Color dessus = new Color(214, 208, 200, 0.1); //couleur du "dessus" des cases

    public GraphismeJavaFX(GraphicsConfiguration gr) {
        //Les couleurs des chiffres
        couleurs[0] = new Color(0, 0, 255, 0.1);
        couleurs[1] = new Color(0, 128, 0, 0.1);
        couleurs[2] = new Color(255, 0, 0, 0.1);
        couleurs[3] = new Color(0, 0, 128, 0.1);
        couleurs[4] = new Color(128, 0, 0, 0.1);
        couleurs[5] = new Color(0, 128, 128, 0.1);
        couleurs[6] = new Color(128, 0, 128, 0.1);
        couleurs[7] = new Color(0, 0, 0, 0.1);

        GraphicsContext g;

        //chiffres
        for (int i = 0; i <= 8; i++) {
            chiffre[i] = new WritableImage(16, 16);
            PixelWriter pw = chiffre[i].getPixelWriter();
            pw.setColor(0, 0, Color.LIGHTGRAY);
            for (int x = 1; x <= 14; x++) {
                pw.setColor(x, 0, Color.LIGHTGRAY);
                pw.setColor(0, x, Color.LIGHTGRAY);
            }
            pw.setColor(15, 0, Color.LIGHTGRAY);
            pw.setColor(0, 15, Color.LIGHTGRAY);
            if (i != 0) {
                pw.setColor(1, 12, couleurs[i - 1]);
                pw.setColor(2, 11, couleurs[i - 1]);
                pw.setColor(3, 10, couleurs[i - 1]);
                pw.setColor(4, 9, couleurs[i - 1]);
                pw.setColor(5, 8, couleurs[i - 1]);
                pw.setColor(6, 7, couleurs[i - 1]);
                pw.setColor(7, 6, couleurs[i - 1]);
                pw.setColor(8, 5, couleurs[i - 1]);
                pw.setColor(9, 4, couleurs[i - 1]);
                pw.setColor(10, 3, couleurs[i - 1]);
                pw.setColor(11, 2, couleurs[i - 1]);
                pw.setColor(12, 1, couleurs[i - 1]);
            }
        }

//        for (int i = 0; i <= 8; i++) {
//            chiffre[i] = gr.createCompatibleVolatileImage(16, 16);
//            g = (Graphics2D) chiffre[i].getGraphics();
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                    RenderingHints.VALUE_ANTIALIAS_ON);
//            g.setStroke(new BasicStroke(1.2f));
//            g.setFont(new java.awt.Font("Monospaced", 1, 15));
//            g.setColor(new Color(249, 249, 247));
//            g.fillRect(0, 0, 16, 16);
//            if (i != 0) g.setColor(couleurs[i - 1]);
//            if (i != 0) g.drawString("" + i, 4, 12);
//            g.setColor(Color.lightGray);
//            g.drawLine(0, 0, 0, 15);
//            g.drawLine(0, 0, 15, 0);
//            g.dispose();
//        }
//
//        //drapeau
//        drapeau = gr.createCompatibleVolatileImage(16, 16);
//        g = (Graphics2D) drapeau.getGraphics();
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setStroke(new BasicStroke(1.2f));
//        g.setColor(dessus);
//        g.fillRect(0, 0, 16, 16);
//        g.setColor(Color.white);
//        g.drawLine(0, 0, 0, 15);
//        g.drawLine(0, 0, 15, 0);
//        g.setColor(Color.black);
//        g.drawLine(9, 12, 9, 4);
//        g.drawLine(10, 12, 10, 4);
//        g.drawLine(8, 12, 11, 12);
//        g.drawLine(7, 13, 12, 13);
//        g.setColor(Color.red);
//        int[] x = {
//                9, 4, 4, 9};
//        int[] y = {
//                5, 5, 6, 8};
//        g.fillPolygon(x, y, 4);
//        g.dispose();
//
//        //erreur
//        erreur = gr.createCompatibleVolatileImage(16, 16);
//        g = (Graphics2D) erreur.getGraphics();
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setStroke(new BasicStroke(1.2f));
//        g.setColor(dessus);
//        g.fillRect(0, 0, 16, 16);
//        g.setColor(Color.white);
//        g.drawLine(0, 0, 0, 15);
//        g.drawLine(0, 0, 15, 0);
//        g.setColor(Color.black);
//        g.drawLine(9, 12, 9, 4);
//        g.drawLine(10, 12, 10, 4);
//        g.drawLine(8, 12, 11, 12);
//        g.setColor(Color.red);
//        g.fillPolygon(x, y, 4);
//        g.setColor(Color.red);
//        g.drawLine(3, 3, 12, 12);
//        g.drawLine(3, 12, 12, 3);
//        g.dispose();
//
//        //question
//        question = gr.createCompatibleVolatileImage(16, 16);
//        g = (Graphics2D) question.getGraphics();
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setStroke(new BasicStroke(1.2f));
//        g.setColor(dessus);
//        g.fillRect(0, 0, 16, 16);
//        g.setColor(Color.white);
//        g.drawLine(0, 0, 0, 15);
//        g.drawLine(0, 0, 15, 0);
//        g.setFont(new java.awt.Font("Dialog", 1, 13));
//        g.setColor(Color.blue);
//        g.drawString("?", 4, 13);
//        g.dispose();
//
//        //question selectionnée
//        questionSel = gr.createCompatibleVolatileImage(16, 16);
//        g = (Graphics2D) questionSel.getGraphics();
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setStroke(new BasicStroke(1.2f));
//        g.setColor(dessus);
//        g.fillRect(0, 0, 16, 16);
//        g.setColor(Color.gray);
//        g.drawLine(0, 0, 0, 15);
//        g.drawLine(0, 0, 15, 0);
//        g.setFont(new java.awt.Font("Dialog", 1, 12));
//        g.setColor(Color.blue);
//        g.drawString("?", 5, 12);
//        g.dispose();
//
//        //mine
//        mine = gr.createCompatibleVolatileImage(16, 16);
//        g = (Graphics2D) mine.getGraphics();
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setStroke(new BasicStroke(1.2f));
//        g.setColor(Color.lightGray);
//        g.drawLine(0, 0, 0, 15);
//        g.drawLine(0, 0, 15, 0);
//        g.setColor(Color.black);
//        g.fillRect(5, 5, 5, 5);
//        g.drawLine(3, 7, 11, 7);
//        g.drawLine(7, 3, 7, 11);
//        g.setColor(new Color(128, 128, 128));
//        g.drawLine(2, 7, 2, 7);
//        g.drawLine(4, 6, 4, 6);
//        g.drawLine(4, 8, 4, 8);
//        g.drawLine(4, 4, 4, 4);
//        g.drawLine(4, 10, 4, 10);
//        g.drawLine(6, 4, 6, 4);
//        g.drawLine(6, 10, 6, 10);
//        g.drawLine(7, 2, 7, 2);
//        g.drawLine(7, 12, 7, 12);
//        g.drawLine(8, 4, 8, 4);
//        g.drawLine(8, 10, 8, 10);
//        g.drawLine(10, 4, 10, 4);
//        g.drawLine(10, 10, 10, 10);
//        g.drawLine(10, 6, 10, 6);
//        g.drawLine(10, 8, 10, 8);
//        g.drawLine(12, 7, 12, 7);
//        g.setColor(Color.white);
//        g.drawLine(6, 6, 6, 6);
//        g.dispose();
//
//        //boum
//        boum = gr.createCompatibleVolatileImage(16, 16);
//        g = (Graphics2D) boum.getGraphics();
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g.setStroke(new BasicStroke(1.2f));
//        g.setColor(Color.red);
//        g.fillRect(0, 0, 16, 16);
//        g.setColor(new Color(128, 64, 64));
//        g.drawLine(0, 0, 0, 15);
//        g.drawLine(0, 0, 15, 0);
//
//        g.setColor(Color.black);
//        g.fillRect(5, 5, 5, 5);
//        g.drawLine(3, 7, 11, 7);
//        g.drawLine(7, 3, 7, 11);
//        g.setColor(new Color(128, 0, 0));
//        g.drawLine(2, 7, 2, 7);
//        g.drawLine(4, 6, 4, 6);
//        g.drawLine(4, 8, 4, 8);
//        g.drawLine(4, 4, 4, 4);
//        g.drawLine(4, 10, 4, 10);
//        g.drawLine(6, 4, 6, 4);
//        g.drawLine(6, 10, 6, 10);
//        g.drawLine(7, 2, 7, 2);
//        g.drawLine(7, 12, 7, 12);
//        g.drawLine(8, 4, 8, 4);
//        g.drawLine(8, 10, 8, 10);
//        g.drawLine(10, 4, 10, 4);
//        g.drawLine(10, 10, 10, 10);
//        g.drawLine(10, 6, 10, 6);
//        g.drawLine(10, 8, 10, 8);
//        g.drawLine(12, 7, 12, 7);
//        g.setColor(Color.white);
//        g.drawLine(6, 6, 6, 6);
//        g.dispose();
//    }
    }
// Canvas canvas = new Canvas(350,270);
//         GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//         graphicsContext.lineTo(0,100);
//         graphicsContext.lineTo(1,100);
//         graphicsContext.lineTo(2,100);
//         graphicsContext.lineTo(3,100);
//
//         Canvas canvas1 = new Canvas(350,270);
//         GraphicsContext graphicsContext1 = canvas1.getGraphicsContext2D();
//         graphicsContext1.lineTo(4,100);
//         graphicsContext1.lineTo(5,100);
//         graphicsContext1.lineTo(6,100);
//         graphicsContext1.lineTo(7,100);
//         graphicsContext1.setLineWidth(3);
//
//         GridPane gridPane = new GridPane();
//         gridPane.add(canvas, 3, 0);
//         gridPane.add(canvas1,4,0);
//         root.getChildren().add(gridPane);
//

}