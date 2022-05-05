import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlMenu implements ActionListener {

    Model model;
    Fenetre fenetre;
    String Newligne;

    public ControlMenu(Model model ,Fenetre fen){
        this.model = model;
        this.fenetre = fen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.fenetre.taille3){
            model.setTaille(3);
            model.setNbrDuoATrouve(4);
            model.setEssais(3);
            model.setEssaisInit();
            this.fenetre.selectTaille(model);
        }else if(e.getSource() == this.fenetre.taille4){
            model.setTaille(4);
            model.setNbrDuoATrouve(8);
            model.setEssais(6);
            model.setEssaisInit();
            this.fenetre.selectTaille(model);
        }else if(e.getSource() == this.fenetre.taille5){
            model.setTaille(5);
            model.setNbrDuoATrouve(12);
            model.setEssais(8);
            model.setEssaisInit();
            this.fenetre.selectTaille(model);
        }else if(e.getSource() == this.fenetre.newGame){
            fenetre.dispose();
            fenetre.reset();
        }else if(e.getSource() == this.fenetre.bestScore){
            Newligne=System.getProperty("line.separator");
            try {
                model.getRecords(model.getFichierRecord());
                fenetre.fenRecords("Records : " + Newligne + "1) "+model.bestScores[0]+Newligne+"2) "+ model.bestScores[1]+Newligne+"3) "+ model.bestScores[2] );
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
