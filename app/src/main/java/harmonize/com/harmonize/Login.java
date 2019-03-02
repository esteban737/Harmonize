package harmonize.com.harmonize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.Artist;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class Login extends AppCompatActivity {
    TextView temp;


    private static final int REQUEST_CODE = 1337;
    private static final String CLIENT_ID = "960b9fef24394d88ac8280307294ffa0";
    //private static final String CLIENT_ID = "91a9588ff6134f01af5870fc49e8e39d";
    private static final String REDIRECT_URI = "harmonize://callback";

    String ACCESS_CODE;
     static String TOKENS;

    SpotifyApi api;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                .setScopes(new String[]{"user-read-private", "playlist-read", "playlist-read-private", "streaming"})
                .build();


        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }


    protected void getArtists() {

        SpotifyService spotify = api.getService();

        spotify.getArtist("0UWZUmn7sybxMCqrw9tGa7", new Callback<Artist>() {

            @Override
            public void success(Artist artist, Response response) {
                Log.d("Artist success", artist.name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Artist failure", error.toString());
            }
        });



        }





    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Log.i("TAG", "onActivityResult");

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            Log.i("TAG2", String.valueOf(requestCode));
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            Log.i("TAG3", "pass response");
            switch (response.getType()) {

                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    //TOKENS = response.getAccessToken();
                    Log.d("TESTTT",response.getAccessToken());
                    api = new SpotifyApi();
                    api.setAccessToken(response.getAccessToken().trim());

                    //Log.d("Testttt", )

                    getArtists();
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    Log.e("ERROR", response.getError());
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
                    Log.i("DEFAULT", "failed");
            }

        }
    }

    public void saveDocument(){


    }

}
