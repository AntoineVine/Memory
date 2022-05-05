import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlBouton implements ActionListener {

    Model model;
    Fenetre fenetre;
    private String nomCarte1, nomCarte2;
    private JButton carte1, carte2;
    private int depart;
    public static int victoire = 0;

    public ControlBouton(Fenetre fenetre, Model model){this.fenetre = fenetre;
        this.model = model;}

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.fenetre.fermer){
            System.exit(0);
        }else if(e.getSource() == this.fenetre.relancer){
            fenetre.dispose();
            fenetre.reset();
            fenetre.popUp.dispose();
        }

        if(depart == 0){
            model.chrono.start();
            depart++;
        }

        if(carte1 != null && carte2 != null){
            if(victoire == 0 ) {
                (carte1).setEnabled(true);
                (carte2).setEnabled(true);
                carte1 = null;
                carte2 = null;
                nomCarte1 = "";
                nomCarte2 = "";
            }else if (victoire == 1){
                carte1 = null;
                carte2 = null;
                nomCarte1 ="";
                nomCarte2 ="";
                victoire--;
            }
        }

        if(carte1 == null){

            carte1 = (JButton) e.getSource();
            nomCarte1 = (carte1).getName();
            (carte1).setEnabled(false);
            if(nomCarte1=="piege"){
                model.setEssais(model.getEssais() - 1);
                fenetre.labEssais.setText("Essais restant : " + model.getEssais() + "/" + model.getEssaisInit() );
                model.verifEssais(fenetre);
                carte1 = null;
                nomCarte1 = "";
            }

        }else if (carte2 == null){
            carte2 = (JButton) e.getSource();
            nomCarte2 = (carte2).getName();
            (carte2).setEnabled(false);
            if(nomCarte2=="piege"){
                model.setEssais(model.getEssais() - 1);
                fenetre.labEssais.setText("Essais restant : " + model.getEssais() + "/" + model.getEssaisInit() );
                model.verifEssais(fenetre);
                (carte1).setEnabled(true);
                carte1 = null;
                carte2=null;
                nomCarte1 = "";
                nomCarte2 ="";
            }

            if(carte1 != null && carte2 != null){
                try {
                    model.verif(carte1, carte2, nomCarte1, nomCarte2, fenetre);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }
    }

    public static void setVictoire(int i){
        victoire = i;
    }
}
