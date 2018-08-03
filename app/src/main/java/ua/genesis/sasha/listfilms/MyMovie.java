package ua.genesis.sasha.listfilms;

public class MyMovie {
     String title;
     String releaseDate;
     String overview;
     String posterPath;
     Double popularity;

     MyMovie(String title, String releaseDate, String overview, String posterPath, Double popularity){
          this.title=title;
          this.releaseDate=releaseDate;
          this.overview=overview;
          this.posterPath=posterPath;
          this.popularity=popularity;
     }
}
