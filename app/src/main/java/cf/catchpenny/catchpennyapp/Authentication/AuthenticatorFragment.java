package cf.catchpenny.catchpennyapp.Authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import cf.catchpenny.catchpennyapp.Models.JWT;
import cf.catchpenny.catchpennyapp.R;
import cf.catchpenny.catchpennyapp.REST.RESTClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AuthenticatorFragment extends Fragment {

    public static final String API_URL = "http://192.168.1.4/api/v1/";
    public static final String TAG = AuthenticatorFragment.class.getSimpleName();

    public AuthenticatorFragment() {
        // Required empty public constructor
    }

    public void attempt(String email, String password) {

        // Create a very simple REST adapter which points to the API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our RESTClient interface.
        RESTClient client = retrofit.create(RESTClient.class);

        Log.d(TAG, "Email: " + email);
        Log.d(TAG, "Password: " + password);

        // Create a call instance for retrieving the JWT token.
        Call<JWT> call = client.getToken(email, password);

        call.enqueue(new Callback<JWT>() {
            @Override
            public void onResponse(Response<JWT> response, Retrofit retrofit1) {
                if (response.isSuccess()) {
                    // request successful (status code h200w200, 201)

                    JWT jwt = response.body();

                    SharedPreferences sharedPref = getActivity().getSharedPreferences(
                            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.jwt), jwt.token);
                    editor.apply();

                    Log.d(TAG, "Token recieved: " + jwt.token);
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.e(TAG, response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    /*public void retrieveToken() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.jwt), "No Token");

        Log.d(TAG, token);
    }*/
}
