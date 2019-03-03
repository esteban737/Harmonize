package harmonize.com.harmonize;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import kaaes.spotify.webapi.android.models.UserPrivate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class myPlaylist extends AppCompatActivity {

    SpotifyApi api;
    SharedPreferences access;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);
        access = this.getSharedPreferences("Login", MODE_PRIVATE);
        api = new SpotifyApi();
        api.setAccessToken(access.getString("token", ""));
        SpotifyService spotify = api.getService();

        spotify.getMyPlaylists(new Callback <Pager<PlaylistSimple>>() {

            @Override
            public void success(Pager<PlaylistSimple> playlistSimplePager, Response response) {
                String s= playlistSimplePager.items.get(0).tracks.href;
                        int o =playlistSimplePager.items.get(0).tracks.total;
                        Log.i("TOTAL", String.valueOf(o));
                TextView num = findViewById(R.id.numPlaylist);
                num.setText(String.valueOf(o));
                TextView herf= findViewById(R.id.Playher);
                herf.setText(s);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
