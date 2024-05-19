package edu.url.salle.ivan.tubella.proyectofinal.Api;



import java.util.ArrayList;

import edu.url.salle.ivan.tubella.proyectofinal.Api.models.LlistarPokemons;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIListarPokemons {
    @GET("pokemon")
    Call<LlistarPokemons> getinfo();

}