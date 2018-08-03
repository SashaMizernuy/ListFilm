package ua.genesis.sasha.listfilms;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMovie {

    @GET("/3/movie/now_playing?api_key=330be44bd0d082bcdb4f061a051757cb")
    Call<Movie> getMovie();
}
