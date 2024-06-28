package edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario;

import java.util.ArrayList;

public class PoemonEspecieLegendario {

    private Evolution_chain evolution_chain;
    private boolean is_legendary;
    private boolean is_mythical;
    private ArrayList<Flavor_text_entries> flavor_text_entries;

    public ArrayList<Flavor_text_entries> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(ArrayList<Flavor_text_entries> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public Evolution_chain getEvolution_chain() {
        return evolution_chain;
    }

    public void setEvolution_chain(Evolution_chain evolution_chain) {
        this.evolution_chain = evolution_chain;
    }

    public boolean isIs_legendary() {
        return is_legendary || is_mythical;
    }

    public void setIs_legendary(boolean is_legendary) {
        this.is_legendary = is_legendary;
    }
    public void setIs_mythical(boolean is_mythical) {
        this.is_mythical = is_mythical;
    }
}
