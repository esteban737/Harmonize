package harmonize.com.harmonize;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;


import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.UserPrivate;
//import okhttp3.OkHttpClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class mainMenu extends AppCompatActivity {

    SpotifyApi api;
    SharedPreferences access;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        access = this.getSharedPreferences("Login", MODE_PRIVATE);
        //OkHttpClient client = new OkHttpClient();

        api = new SpotifyApi();

        //Log.i("TOKEN", access.getString("token", ""));
        api.setAccessToken(access.getString("token", "").trim());
        SpotifyService spotify = api.getService();



        spotify.getMe(new Callback<UserPrivate>() {
            @Override
            public void success(UserPrivate userPrivate, Response response) {

               editor = access.edit();
               editor.putString("name", userPrivate.display_name.trim());
                editor.putString("id", userPrivate.id.trim());
                editor.putString("image", userPrivate.images.get(0).url.trim());
                editor.commit();
                Picasso.get().load(access.getString("image", "")).into((ImageView)findViewById(R.id.cUser_image));
                TextView cUser_name = findViewById(R.id.cUser_name);
                cUser_name.setText(access.getString("name",""));
                Log.i("ID", access.getString("id", ""));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("C_User", error.getMessage());

            }
        });


/*String myURL = "https://api.spotify.com/v1/me/top/artists";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                myURL,
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("TEST", response.toString());

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ERROR", error.getMessage());

                    }
                }

        );

requestQueue.add(objectRequest);*/







    }

    public void playListClick(View view){
        Intent i = new Intent(this, myPlaylist.class);
        startActivity(i);
    }
}


