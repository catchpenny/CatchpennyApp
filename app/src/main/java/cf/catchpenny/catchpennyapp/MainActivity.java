package cf.catchpenny.catchpennyapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cf.catchpenny.catchpennyapp.Authentication.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container) != null) {

            // If we're being restored from a previous state, then
            // we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            LandingFragment fragment = new LandingFragment();

            getFragmentManager().
                    beginTransaction().
                    add(R.id.container, fragment, "Landing_fragment").
                    commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void startLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
