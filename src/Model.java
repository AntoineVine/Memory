import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;

public class Model {

    private int taille, essais, nbrDuoTrouve, nbrDuoATrouve, essaisInit;
    JLabel temps;
    Chrono chrono;
    Icon set;
    String[] bestScores = new String[20];
    String fichierRecord;
    Font f = new Font("Arial", Font.PLAIN, 45);

    public Model(int initTaille){
        taille = initTaille;
        System.out.println(taille);
        if(taille == 3) {
            essais = 3;
            nbrDuoATrouve = 4;
        }else if(taille == 4) {
            essais = 6;
            nbrDuoATrouve = 8;
        }else if(taille == 5){
            essais = 8;
            nbrDuoATrouve = 12;
        }
        setEssaisInit();
        nbrDuoTrouve = 0;
        temps = new JLabel();
        temps.setFont(f);
        chrono = new Chrono(temps);
    }

    //----- Les setters ------
    public void setTaille(int taille){
        this.taille = taille;
    }

    public void setNbrDuoATrouve(int Duo){
        this.nbrDuoATrouve = Duo;
    }

    public void setFichierRecord(String fichierRecord) {
        this.fichierRecord = fichierRecord;
    }

    public void setEssais(int essais){
        this.essais = essais;
    }

    public void setEssaisInit(){
        this.essaisInit = essais;
    }

    //----- Les getters ------
    public int getTaille(){
        return taille;
    }

    public int getEssais(){
        return essais;
    }

    public JLabel getTemps(){
        return temps;
    }

    public int getEssaisInit(){
        return this.essaisInit;
    }

    public String getFichierRecord() {
        return fichierRecord;
    }

    public Icon repertoire(String image){
        switch (image){
            case "virtualriot":
                set = new ImageIcon(new ImageIcon("Images/virtualriot.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "modestep":
                set = new ImageIcon(new ImageIcon("Images/modestep.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "phaseone":
                set = new ImageIcon(new ImageIcon("Images/phaseone.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "barelyalive":
                set = new ImageIcon(new ImageIcon("Images/barelyalive.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "d&f":
                set = new ImageIcon(new ImageIcon("Images/d&f.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "murda":
                set = new ImageIcon(new ImageIcon("Images/murda.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "dirtyphonics":
                set = new ImageIcon(new ImageIcon("Images/dirtyphonics.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "infekt":
                set = new ImageIcon(new ImageIcon("Images/infekt.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "bandlez":
                set = new ImageIcon(new ImageIcon("Images/bandlez.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "12planet":
                set = new ImageIcon(new ImageIcon("Images/12planet.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "myro":
                set = new ImageIcon(new ImageIcon("Images/myro.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "eliminate":
                set = new ImageIcon(new ImageIcon("Images/eliminate.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "piege":
                set = new ImageIcon(new ImageIcon("Images/piege.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
            case "victoire":
                set = new ImageIcon(new ImageIcon("Images/couronne.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
                return set;
        }
        return null;
    }

    public void verif(JButton carte1, JButton carte2, String nomCarte1, String nomCarte2, Fenetre fen) throws IOException {

        if(nomCarte1.equals(nomCarte2)){
            nbrDuoTrouve++;
            ControlBouton.setVictoire(1);
//            (carte1).setDisabledIcon(repertoire("victoire"));
//            (carte2).setDisabledIcon(repertoire("victoire"));
        }else{
            essais--;
            fen.labEssais.setText("Essais restant : " + getEssais() + "/" + essaisInit );
        }

        verifEssais(fen);

        if (nbrDuoTrouve == nbrDuoATrouve){
//            (carte1).setDisabledIcon(repertoire("victoire"));
//            (carte2).setDisabledIcon(repertoire("victoire"));
            chrono.terminate();
            fen.popUp("Bravo vous avez finit le memory !");
            fin();
        }
    }

    public void verifEssais(Fenetre fenetre){
        if(essais == 0){
            chrono.terminate();
            fenetre.popUp("Désolé vous avez raté, mais retentez votre chance :)");
        }
    }

    public void fin() throws IOException {
        getRecords(fichierRecord);

        for(int i=0; i< bestScores.length; i++){
            if(chrono.t < Float.parseFloat(bestScores[i].replace(",", "."))){
                DecimalFormat df = new DecimalFormat("#######0.0");
                String tmp = df.format(chrono.t);
                bestScores[i+2]= bestScores[i+1];
                bestScores[i+1] = bestScores[i];
                bestScores[i]= tmp;
                break;
            }
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichierRecord));
            bw.write(""+bestScores[0]);
            bw.newLine();
            bw.write(""+bestScores[1]);
            bw.newLine();
            bw.write(""+bestScores[2]);
            bw.newLine();
            bw.close();
        }catch(Exception e){ e.printStackTrace(); }
    }

    public void getRecords(String fichier) throws IOException {
        BufferedReader buff=new BufferedReader(new FileReader(fichier));
        String ligne;
        int next=0;
        while((ligne=buff.readLine()) != null){
            bestScores[next] = ligne;
            next++;
        }
    }

}
