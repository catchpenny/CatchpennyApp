package cf.catchpenny.catchpennyapp.Authentication.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import cf.catchpenny.catchpennyapp.HomeActivity;
import cf.catchpenny.catchpennyapp.Models.JWT;
import cf.catchpenny.catchpennyapp.R;
import cf.catchpenny.catchpennyapp.REST.RESTClient;
import cf.catchpenny.catchpennyapp.REST.ServiceGenerator;
import retrofit.Call;
import retrofit.Callback;
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

        getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE).edit().remove(getString(R.string.jwt)).apply();

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

        RESTClient client = ServiceGenerator.createService(RESTClient.class);

        Log.d(TAG, "Email: " + email);
        Log.d(TAG, "Password: " + password);

        // Create a call instance for retrieving the JWT token.
        Call<JWT> call = client.getToken(email, password);

        call.enqueue(new Callback<JWT>() {
            @Override
            public void onResponse(Response<JWT> response, Retrofit retrofit1) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    JWT jwt = response.body();

                    getSharedPreferences(
                            getString(R.string.preference_file_key), Context.MODE_PRIVATE).
                            edit()
                            .putString(getString(R.string.jwt), jwt.getToken())
                            .apply();

                    Calendar rightNow = Calendar.getInstance();
                    rightNow.add(Calendar.DATE, 1);
                    long expiryDate = rightNow.getTimeInMillis();

                    getSharedPreferences(
                            getString(R.string.preference_file_key), Context.MODE_PRIVATE).
                            edit()
                            .putLong("Expiry", expiryDate)
                            .apply();

                    Log.d(TAG, "Token recieved: " + jwt.getToken());

                    startActivity(new Intent(context, HomeActivity.class));

                    if (mProgress.isShowing())
                        mProgress.dismiss();
                } else {
                    //request not successful (like 400,401,403 etc)
                    if (response.code() == 401) {
                        Toast.makeText(LoginActivity.this, "Email/Password incorrect. Please retype.", Toast.LENGTH_SHORT).show();
                    }

                    if (mProgress.isShowing())
                        mProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Could not even connect to server
                if (mProgress.isShowing())
                    mProgress.dismiss();

                Toast.makeText(LoginActivity.this, "Please check your connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
