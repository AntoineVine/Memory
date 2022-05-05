import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Fenetre extends JFrame {

    Model model;

    // Attributs Menu
    JMenuBar barMenu = new JMenuBar();
    JMenu menu, menuTaille;
    JMenuItem taille3, taille4, taille5, newGame, bestScore;

    // Attributs Vue
    JPanel general = new JPanel(), centre, panEssais, panTemps, grille;
    JLabel enceinte1, enceinte2;
    Icon imgEnceinte = new ImageIcon( new ImageIcon("Images/enceinte.png").getImage().getScaledInstance(390, 800, Image.SCALE_DEFAULT));

    // Attributs Grille
    int nbrCarte;
    String nom;

    // Attributs Info
    int taille;
    JLabel labTemps, labEssais;

    // Controlleur
    ControlGroup cg;

    // Police
    Font f1 = new Font("Arial", Font.PLAIN, 45);
    Font f2 = new Font("Arial", Font.PLAIN, 20);

    // Pop-up
    JFrame popUp;
    JPanel principal, panTexte, panTempsPop, panBouton, boutons;
    JLabel texte, labTempsPop;
    JButton fermer, relancer;


    // Image de base
    Icon image;


    public Fenetre(Model model){
        cg = new ControlGroup(model , this);
        this.setJMenuBar(barMenu);
        this.model = model;
        this.creeMenu();
        this.initAttribut(model);
        this.setContentPane(general);

        //----- Paramètre de la fenetre-----
        this.setTitle("Memory");
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void creeMenu(){
        //----- Menu -----
        menu = new JMenu("Options");
        newGame = new JMenuItem("Nouvelle partie");
        bestScore = new JMenuItem("Meilleurs scores");
        newGame.addActionListener(cg.cm);
        bestScore.addActionListener(cg.cm);
        menu.add(newGame);
        menu.add(bestScore);

        //----- Menu Taille -----
        menuTaille = new JMenu("Taille");
        taille3 = new JMenuItem("3x3");
        taille4 = new JMenuItem("4x4");
        taille5 = new JMenuItem("5x5");
        this.taille3.addActionListener(cg.cm);
        this.taille4.addActionListener(cg.cm);
        this.taille5.addActionListener(cg.cm);
        menuTaille.add(taille3);
        menuTaille.add(taille4);
        menuTaille.add(taille5);

        menu.add(menuTaille);
        barMenu.add(menu);
    }

    public void initAttribut(Model model){

        centre = new JPanel();

        // ----- Variables -----
        this.model = model;
        this.taille = model.getTaille();
        nbrCarte = taille*taille;

        if(taille==3){
            model.setFichierRecord("Records3.txt");
        }else if(taille==4){
            model.setFichierRecord("Records4.txt");
        }else if(taille==5){
            model.setFichierRecord("Records5.txt");
        }

        // ----- Deco -----
        enceinte1 = new JLabel();
        enceinte1.setIcon(imgEnceinte);
        enceinte2 = new JLabel();
        enceinte2.setIcon(imgEnceinte);


        // ----- Temps -----
        panTemps = new JPanel();
        labTemps = new JLabel("Temps : ");
        labTemps.setFont(f1);
        panTemps.add(labTemps);
        panTemps.add(model.getTemps());
        centre.add(panTemps);

        // ----- Grille -----
        creeGrille(taille, model);

        // ----- Essais -----
        panEssais = new JPanel();
        labEssais = new JLabel("Essais restant : " + model.getEssais() + "/"+model.getEssaisInit());
        labEssais.setFont(f1);
        panEssais.add(labEssais);
        centre.add(panEssais);

        // Parametrage des panels
        general.setLayout(new BoxLayout(general, BoxLayout.LINE_AXIS));
        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));

        general.add(enceinte1);
        general.add(centre);
        general.add(enceinte2);
    }

    public void creeGrille(int taille, Model model){
        grille = new JPanel(new GridLayout(taille,taille));
        image = new ImageIcon(new ImageIcon("Images/disciple.png").getImage().getScaledInstance(480 / taille, 480 / taille, Image.SCALE_DEFAULT));
        ArrayList<Integer> carteDispo = new ArrayList<>();
        if(taille==3){
            for(int j = 1; j<=4; j++){
                carteDispo.add(j);
                carteDispo.add(j);
            }
            carteDispo.add(5);
        }else if(taille == 4){
            for(int j = 1; j<=taille*2; j++){
                carteDispo.add(j);
                carteDispo.add(j);
            }
        }
        else if(taille == 5){
            for(int j = 1; j<=12; j++){
                carteDispo.add(j);
                carteDispo.add(j);
            }
            carteDispo.add(13);
        }
        JButton[] cases = new JButton[taille*taille];
        for (int i = 0; i < (taille * taille); i++) {
            cases[i] = new JButton();
            String nom = selectCarte(taille, carteDispo);
            cases[i].setName(nom);
            cases[i].setDisabledIcon(model.repertoire(nom));
            cases[i].addActionListener(cg.cb);
            cases[i].setIcon(image);
            grille.add(cases[i]);
        }
        centre.add(grille);
    }

    public String selectCarte(int taille, ArrayList carteDispo) {
        if (taille == 3) {
            System.out.println("nombre de cartes restantes : "+ nbrCarte);
            int numCarte = (int) (Math.random() * nbrCarte);
            System.out.println("num : "+ numCarte);
            int carteChoisis = (int) carteDispo.get(numCarte);
            System.out.println("carte dispo"+carteDispo);
            System.out.println("carte choisis : "+ carteChoisis);
            switch (carteChoisis) {
                case 1:
                    nom = ("virtualriot");
                    carteDispo.remove(numCarte);
                    nbrCarte -= 1;
                    return nom;
                case 2:
                    nom = ("modestep");
                    carteDispo.remove(numCarte);
                    nbrCarte -= 1;
                    return nom;
                case 3:
                    nom = ("phaseone");
                    carteDispo.remove(numCarte);
                    nbrCarte -= 1;
                    return nom;
                case 4:
                    nom = ("barelyalive");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 5:
                    nom = ("piege");
                    carteDispo.remove(numCarte);
                    nbrCarte -= 1;
                    return nom;
            }

        } else if (taille == 4) {
            System.out.println("nombre de cartes restantes : "+ nbrCarte);
            int numCarte = (int) (Math.random() * nbrCarte);
            System.out.println("num : "+ numCarte);
            int carteChoisis = (int) carteDispo.get(numCarte);
            System.out.println("carte dispo"+carteDispo);
            System.out.println("carte choisis : "+ carteChoisis);
            switch (carteChoisis){
                case 1:
                    nom = ("virtualriot");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 2:
                    nom = ("modestep");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 3:
                    nom =("phaseone");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 4:
                    nom = ("barelyalive");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 5:
                    nom = ("d&f");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 6:
                    nom = ("murda");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 7:
                    nom = ("dirtyphonics");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 8:
                    nom = ("infekt");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
            }
        } else if (taille == 5) {
            System.out.println("nombre de cartes restantes : "+ nbrCarte);
            int numCarte = (int) (Math.random() * nbrCarte);
            System.out.println("num : "+ numCarte);
            int carteChoisis = (int) carteDispo.get(numCarte);
            System.out.println("carte dispo"+carteDispo);
            System.out.println("carte choisis : "+ carteChoisis);
            switch (carteChoisis){
                case 1:
                    nom = ("virtualriot");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 2:
                    nom = ("modestep");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 3:
                    nom =("phaseone");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 4:
                    nom = ("barelyalive");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 5:
                    nom = ("d&f");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 6:
                    nom = ("murda");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 7:
                    nom = ("dirtyphonics");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 8:
                    nom = ("infekt");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 9:
                    nom = ("bandlez");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 10:
                    nom = ("12planet");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 11:
                    nom = ("myro");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 12:
                    nom = ("eliminate");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
                case 13:
                    nom = ("piege");
                    carteDispo.remove(numCarte);
                    nbrCarte -=1;
                    return nom;
            }
        }
        return null;
    }

    public void popUp(String text){
        popUp = new JFrame();
        principal = new JPanel();
        principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

        panTexte = new JPanel();
        texte = new JLabel(text);
        texte.setFont(f2);
        panTexte.add(texte);

        panTemps = new JPanel();
        labTemps = new JLabel("Temps : ");
        texte.setFont(f2);
        panTemps.add(labTemps);
        model.getTemps().setFont(f2);
        panTemps.add(model.getTemps());

        panBouton = new JPanel();
        boutons = new JPanel();
        boutons.setLayout(new BoxLayout(boutons, BoxLayout.X_AXIS));
        fermer = new JButton("Fermer le jeu");
        relancer = new JButton("Relancer une partie");
        fermer.addActionListener(cg.cb);
        relancer.addActionListener(cg.cb);
        boutons.add(fermer);
        boutons.add(relancer);
        panBouton.add(boutons);

        principal.add(panTexte);
        principal.add(panTemps);
        principal.add(panBouton);

        popUp.setContentPane(principal);

        popUp.setSize(500, 150);
        popUp.setLocationRelativeTo(null);
        popUp.setResizable(false);
        popUp.setVisible(true);
        popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void selectTaille(Model model) {
        this.getContentPane().removeAll();
        this.initAttribut(model);
        labEssais.setText("Essais restant : " + model.getEssais() + "/"+model.getEssaisInit());
        this.setVisible(true);
    }

    public void reset(){
        Model modelReset= new Model(model.getTaille());
        Fenetre fen = new Fenetre(modelReset);
        ControlGroup control = new ControlGroup(model, fen);
    }

    public void fenRecords(String messageErr) throws IOException {
        model.getRecords(model.getFichierRecord());
        JOptionPane.showMessageDialog(null, messageErr, "Record", JOptionPane.PLAIN_MESSAGE);
    }

}
