public class Main {

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                Model model = new Model(4);
                Fenetre fen = new Fenetre(model);
            }

        });
    }
}
