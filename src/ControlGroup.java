public class ControlGroup {

    private Model model;
    public ControlMenu cm;
    public ControlBouton cb;

    public ControlGroup(Model model, Fenetre fen){
        this.model = model;
        cb = new ControlBouton(fen, model);
        cm = new ControlMenu(model, fen);
    }
}
