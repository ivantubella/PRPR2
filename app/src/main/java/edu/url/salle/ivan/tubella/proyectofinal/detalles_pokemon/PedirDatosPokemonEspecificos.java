package edu.url.salle.ivan.tubella.proyectofinal.detalles_pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PedirDatosPokemonEspecificos {
    @GET("pokemon/{name}")
    Call<PokemonRespuestaEspecifico> obteneDatosPokemon(@Path("name")String pokemon);

}

