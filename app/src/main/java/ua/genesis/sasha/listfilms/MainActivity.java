package ua.genesis.sasha.listfilms;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static MainActivity content;
    public static ArrayList<MyMovie> myMovie;
    private static ApiMovie apiMovie;
    RecyclerView.Adapter mAdapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    Movie mov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content=this;
        myMovie=new ArrayList<MyMovie>();

        apiMovie=Controller.getApi();
        apiMovie.getMovie().enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                mov=(Movie)response.body();
                for(int i=0;i<mov.results.size();i++) {
                    Movie.Result d=mov.results.get(i);
                    Log.i("Script", "TITLE=" + mov.results.get(i).title);
                    Log.i("Script", "DATE=" + mov.results.get(i).releaseDate);
                    Log.i("Script", "POSTER_PATH=" + mov.results.get(i).posterPath);
                    Log.i("Script", "POPULARITY=" + mov.results.get(i).popularity);
                    Log.i("Script", "OVERVIEW=" + mov.results.get(i).overview);
                    myMovie.add(new MyMovie(d.title,d.releaseDate,d.overview,d.posterPath,d.popularity));
                }
                adapter();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MainActivity.this,"An error occured during networking",Toast.LENGTH_SHORT).show();
                Log.i("Script","ERROR="+t.getMessage());

            }
        });
    }

    public void adapter(){
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new MyAdapter(this,myMovie);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
