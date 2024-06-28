package edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario;

import edu.url.salle.ivan.tubella.proyectofinal.PokemonRespuestaEspecifico;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PedirDatosPokemonEspecificosEspecie {
    @GET("pokemon-species/{name}")
    Call<PoemonEspecieLegendario> obteneDatosPokemon(@Path("name") String pokemon);

}

