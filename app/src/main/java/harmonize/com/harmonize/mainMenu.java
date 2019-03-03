package harmonize.com.harmonize;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.UserPrivate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class mainMenu extends AppCompatActivity {

    SpotifyApi api;
    SharedPreferences access;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        access = this.getSharedPreferences("Login", MODE_PRIVATE);

        api = new SpotifyApi();

        Log.i("TOKEN", access.getString("token", ""));
        api.setAccessToken(access.getString("token", "").trim());
        SpotifyService spotify = api.getService();

        spotify.getMe(new Callback<UserPrivate>() {
            @Override
            public void success(UserPrivate userPrivate, Response response) {
                Log.i("NAME", userPrivate.display_name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("NAME", error.getMessage());

            }
        });

        spotify.getMe(new Callback<UserPrivate>() {
            @Override
            public void success(UserPrivate userPrivate, Response response) {
                String image = userPrivate.images.get(0).url;
                Log.i("Image", image);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Image", error.getMessage());

            }
        });



    }
}


