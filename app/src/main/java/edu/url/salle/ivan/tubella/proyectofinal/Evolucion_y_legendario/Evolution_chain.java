package edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario;

public class Evolution_chain {

    private String url;

    public String getUrl() {

        String[] parts = url.split("/");

        return parts[parts.length - 1];
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
