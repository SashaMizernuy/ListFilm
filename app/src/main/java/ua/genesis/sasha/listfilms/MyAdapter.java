package ua.genesis.sasha.listfilms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<MyMovie> mMovie;
    private LayoutInflater mInflater;
    private Context context;

    //предоставляем ссылку на view для каждого елемента данных(Сложным елементам может потребоватся более одного view)
    //мы должны предоставить доступ для всех view елементов данных в view holder

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView title;
        public TextView popular;
        public TextView date;

        public ViewHolder(View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.titleFilm);
            title =itemView.findViewById(R.id.txtTitle);
            popular=itemView.findViewById(R.id.popular);
            date=itemView.findViewById(R.id.release);

        }
    }
    //предоставляем походящий конструктор (зависит от типа набора данных)
    public MyAdapter(Context context, ArrayList<MyMovie> myMovie){
        mInflater = LayoutInflater.from(context);
        mMovie=myMovie;
        this.context=context;
    }
    //создаем новые view(вызывается менеджером макетов)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        //создаем новое view
        View view = mInflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    //заменить содержимое view(вызванное менеджером макета)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){


        MyMovie movies=mMovie.get(position);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+movies.posterPath).fit().into(holder.imageView);
        Log.i("Script","PICASSO = "+holder.imageView.toString());

        holder.title.setText(movies.title);
        holder.popular.setText(String.valueOf(movies.popularity));
        holder.date.setText(String.valueOf(movies.releaseDate));
    }

    //возвращает размер нашего набора данных(вызывается менеджером макета)
    @Override
    public int getItemCount() {
        return mMovie.size();

    }

}