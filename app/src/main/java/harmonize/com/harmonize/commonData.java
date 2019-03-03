/*package harmonize.com.harmonize;

import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.UserPublic;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class commonData extends AppCompatActivity {

    public ArrayList<artists> artistList;
    public ArrayList<Strings> alreadyProcessedListArt;
    public ArrayList<genres> genreList;
    public ArrayList<Strings> alreadyProcessedGenreList;
    public ArrayList<songs> songList;
    public ArrayList<Strings> alreadyProcessedList;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_data);

        SpotifyApi api = new SpotifyApi();
        SharedPreferences access = this.getSharedPreferences("Login", MODE_PRIVATE);
        String Token = access.getString("token", "");

        api.setAccessToken(Token);

        SpotifyService spotify = api.getService();

        /*for( i people; i < n; i++)
        {
            spotify.getUser("id", new Callback<User>(){

                @Override
                public void success(Album album, Response response) {
                    Log.d("Album success", album.name);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("Album failure", error.toString());
                }
            });



        }*/




    }

    public void groupData(){




    }


    public void commonSongs(people){

        for (each person i = 0; i < n; i++){


            for ( all favorite songs j = 0; j < m; j++){
                int sort = alreadyProcessedList.indexOf(favoriteSongs[j].name;

                if(sort == -1) {
                    int artistSort = alreadyProcessedListArt.indexof(favoriteSongs[j].artist);
                    if (artistSort != -1)
                        songList.add(new song(name, artistList.get(artistSort), song.ranking));

                    else {
                        artists newArtist = new artists(name);
                        songList.add(new song(name, newArtist, song.ranking));
                    }

                    alreadyProcessedList.add(favoriteSongs[j].name);
                }

                else{
                    songList.get(sort).numofPeople++;
                    songList.get(sort).overallPopularity += song.ranking;
                }

            }

        }

    }


    public void commonArtists({people}){

        for (each person i = 0; i < n; i++){

            for ( all favorite artists j = 0; j < m; j++){
                int sort = alreadyProcessedListArt.indexOf(favoriteArtists[j].name);
                int genreSort = alreadyProcessedGenreList.indexof(favoriteArtists[j].genre);

                if(sort == -1) {

                    if (genreSort != -1) {
                        artistList.add(new artists(name, genreList.get(genreSort)));
                        genreList.get(genreSort).popularity += artist.ranking;
                        genreList.get(genreSort).numofPeople ++;
                    }
                    else{
                        genres Genre = new genres(name);
                        artistList.add(new artists(name, genre, ranking));
                    }

                    alreadyProcessedListArt.add(favoriteArtists[j].name);
                }

                else{
                    int alterArtist = artistList.get(j);

                    alterArtist.numofPeople++;
                    alterArtist.overallPopularity += artist.ranking;
                    genreList.get(genreSort).numOfPeople++;
                    genreList.get(genreSort).popularity += artist.ranking;
                }

            }

        }


    }


    public class artists{

        String names = "";
        int numofPeople = 0;
        int overallPopularity = 0;
        genres artistGenre;

        artists(String name, genres genre, int ranking){
        names = name;
        numofPeople++;
        overallPopularity+=ranking;
        artistGenre = genre;
        genre.popularity+=ranking;
        }

    }


    public class songs{
        String names = "";
        int numofPeople = 0;
        int overallPopularity = 0;
        artists songArtist;

        songs(String name, artists artist, int ranking){
            names = name;
            numofPeople++;
            overallPopularity+=ranking;
            songArtist = artist;

        }

    }
    


    public class genres{

        String title;
        int numofPeople = 0;
        int popularity = 0;

        genres(String name, int ranking){
            title = name;
            numofPeople++;
            popularity += ranking;
        }
    }


    public class user{

        favoriteSongs
        favoriteArtists



    }



}*/


