package edu.url.salle.ivan.tubella.proyectofinal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PedirDatosPokemonEspecificos {
    @GET("pokemon/{name}")
    Call<PokemonRespuestaEspecifico> obteneDatosPokemon(@Path("name")String pokemon);

}

