package edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario;

import java.util.ArrayList;
import java.util.List;

public class PokemonEvolutionChain {

    public Chain chain;

    public static class Chain{
        public Species species;
        public List<EvolvesTo> evolves_to;
    }
    public static class EvolvesTo{
        public Species species;
        public List<EvolvesTo> evolves_to;

    }
    public static class Species{
        public String name;
    }

    public List<String> return_names(){
    List<String> names = new ArrayList<>();
    addnames(chain, names);
    return names;
    }

    private void addnames(Chain chain, List<String> names){
        if(chain!=null){
            names.add((chain.species.name));
            if (chain.evolves_to!=null){
                for (EvolvesTo evolvesTo : chain.evolves_to){
                    addnames2(evolvesTo, names);
                }
            }
        }
    }

    private void addnames2(EvolvesTo evolvesTo, List<String> names){
        if(evolvesTo!=null){
            names.add((evolvesTo.species.name));
            if (evolvesTo.evolves_to!=null){
                for (EvolvesTo evolvesTo2 : evolvesTo.evolves_to){
                    addnames2(evolvesTo2, names);
                }
            }
        }
    }

}
