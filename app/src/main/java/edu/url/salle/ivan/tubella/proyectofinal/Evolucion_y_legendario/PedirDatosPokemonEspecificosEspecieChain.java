package edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PedirDatosPokemonEspecificosEspecieChain {
    @GET("{numero}")
    Call<PokemonEvolutionChain> obteneDatosPokemon(@Path("numero") String url);

}
