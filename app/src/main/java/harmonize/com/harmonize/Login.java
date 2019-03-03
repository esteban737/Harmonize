package harmonize.com.harmonize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.content.SharedPreferences;

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



    private static final int REQUEST_CODE = 1337;
    private static final String CLIENT_ID = "960b9fef24394d88ac8280307294ffa0";
    //private static final String CLIENT_ID = "91a9588ff6134f01af5870fc49e8e39d";
    private static final String REDIRECT_URI = "harmonize://callback";
    private SharedPreferences spotify_access;
    private SharedPreferences.Editor editor;


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
                    saveToken(response.getAccessToken().trim());
                    Intent gotoMenu = new Intent(this, MainMenu.class);
                    startActivity(gotoMenu);

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

    public void saveToken(String token){
        spotify_access = getSharedPreferences("Login", MODE_PRIVATE);
        editor = spotify_access.edit();
        editor.putString("token", token);
        editor.commit();

    }

}
