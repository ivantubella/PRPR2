package edu.url.salle.ivan.tubella.proyectofinal.ListaPokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PedirDatosPokemon {
    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

}
