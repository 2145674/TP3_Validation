package view;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class DemineurTest {

    @Test
    void etantDonneLUtilisateurJoueAuDemineur_quandLUtilisateurPerdCarEtatDeLaDeminCaseA0ETLaCaseEstUneMine_alorsLeBoutonSourirAfficheLEtatMort() {
        //GIVEN
        Demineur demineur = new Demineur(16, 30, 99, 3);

        //WHEN
        demineur.getDemineur().getJeux()[1][1].getDeminCase().setEtat(0);
        demineur.getDemineur().getJeux()[1][1].getDeminCase().setMine(true);
        demineur.decouvre(1,1);

        //THEN
        //L'état « Mort » est représenté par l'icon oups
        assertEquals(demineur.getOups(),demineur.getBoutonNouveau().getIcon());
    }

    @Test
    void etantDonneLUtilisateurJoueAuDemineur_quandLUtilisateurPerdCarEtatDeLaDeminCaseA3ETLaCaseEstUneMine_alorsLeBoutonSourirAfficheLEtatMort() {
        //GIVEN
        Demineur demineur = new Demineur(16, 30, 99, 3);

        //WHEN
        demineur.getDemineur().getJeux()[8][8].getDeminCase().setEtat(3);
        demineur.getDemineur().getJeux()[8][8].getDeminCase().setMine(true);
        demineur.decouvre(8,8);

        //THEN
        //L'état « Mort » est représenté par l'icon oups
        assertEquals(demineur.getOups(),demineur.getBoutonNouveau().getIcon());
    }
}