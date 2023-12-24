import java.util.ArrayList;

public class SujetObserve {
    protected ArrayList<Observateur> observateurs;

    public SujetObserve(){
        observateurs = new ArrayList<>();
    }

    public void ajouterObservateur(Observateur obs){
        observateurs.add(obs);
    }

    public void notifierObservateurs(){
        for(Observateur obs : observateurs){
            obs.reagir();
        }
    }
}
