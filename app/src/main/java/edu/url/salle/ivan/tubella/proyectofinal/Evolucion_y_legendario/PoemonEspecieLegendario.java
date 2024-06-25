package edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario;

public class PoemonEspecieLegendario {

    private Evolution_chain evolution_chain;
    private boolean is_legendary;
    private boolean is_mythical;

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
