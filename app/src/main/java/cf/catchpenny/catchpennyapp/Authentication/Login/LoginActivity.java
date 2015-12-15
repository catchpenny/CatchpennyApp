package cf.catchpenny.catchpennyapp.Authentication.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cf.catchpenny.catchpennyapp.HomeActivity;
import cf.catchpenny.catchpennyapp.Models.JWT;
import cf.catchpenny.catchpennyapp.R;
import cf.catchpenny.catchpennyapp.REST.RESTClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

    public static final String API_URL = "http://192.168.1.4/api/v1/";
    public static final String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(getString(R.string.jwt));
        editor.apply();

        Log.d(TAG, "Removed token from preferences");

        if (findViewById(R.id.container) != null) {

            // If we're being restored from a previous state, then
            // we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            LoginFragment fragment = new LoginFragment();

            getFragmentManager().
                    beginTransaction().
                    add(R.id.container, fragment, "Login_fragment").
                    commit();
        }
    }

    public void attemptLogin(View view) {

        mProgress = new ProgressDialog(this);
        mProgress.setIndeterminate(true);
        mProgress.setMessage("Logging in...");
        mProgress.show();

        final Context context = this;

        // TODO Transfer this to a fragment

        String email = ((EditText) findViewById(R.id.email_editText)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_editText)).getText().toString();

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

                    SharedPreferences sharedPref = getSharedPreferences(
                            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.jwt), jwt.token);
                    editor.apply();

                    Log.d(TAG, "Token recieved: " + jwt.token);

                    Intent intent = new Intent(context, HomeActivity.class);
                    startActivity(intent);

                    if (mProgress.isShowing())
                        mProgress.dismiss();

                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.e(TAG, response.raw().toString());
                    if (mProgress.isShowing())
                        mProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (mProgress.isShowing())
                    mProgress.dismiss();
            }
        });
    }

    /*public void retrieveToken(View view) {

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if (sharedPref.contains(getString(R.string.jwt))) {
            String token = sharedPref.getString(getString(R.string.jwt), "No Token");
            Log.d(TAG, "Token retrieved from Preferences: " + token);
        }

    }*/
}
