package cf.catchpenny.catchpennyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cf.catchpenny.catchpennyapp.Models.User;
import cf.catchpenny.catchpennyapp.Preferences.SettingsActivity;
import cf.catchpenny.catchpennyapp.REST.RESTClient;
import cf.catchpenny.catchpennyapp.REST.ServiceGenerator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = HomeActivity.class.getSimpleName();
    private ProgressDialog mProgress;
    private String token;
    private Context context;
    private Menu menu;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        token = sharedPref.getString(getString(R.string.jwt), "No Token");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, token, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menu = navigationView.getMenu();

        loadUser();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(context, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Log.d(TAG, "groupId: " + item.getGroupId());
        Log.d(TAG, "itemId: " + item.getItemId());
        Log.d(TAG, "order: " + item.getOrder());
        Log.d(TAG, "title: " + item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void test(View view) {
        /*mProgress = new ProgressDialog(this);
        mProgress.setIndeterminate(true);
        mProgress.setMessage("Getting username...");
        mProgress.show();

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if (sharedPref.contains(getString(R.string.jwt))) {
            token = sharedPref.getString(getString(R.string.jwt), "No Token");
        }
        Log.d(TAG, "Token retrieved from Preferences: " + token);
        RESTClient client = ServiceGenerator.createService(RESTClient.class, token);
        Call<User> call = client.getResponse();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit1) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    User userBody = response.body();
                    Log.d(TAG, userBody.getFirstName() + " " + userBody.getLastName());
                } else {
                    //request not successful (like 400,401,403 etc)
                    Log.d(TAG, response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Could not even connect to server
                Log.d(TAG, "Failed to get user");
            }
        });*/
        /*Call<Channel> channels = client.getDetails("4", "6", null);
        channels.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Response<Details> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    Details details = response.body();
                    ChannelFragment channelFragment = (ChannelFragment) getFragmentManager().findFragmentById(R.id.fragment);
                    channelFragment.setNextPage(details.getNextPage());
                    channelFragment.setListItems(details.getChannelMessages());
                    for (Channel channel : details.getChannels()) {
                        Log.d(TAG, channel.getName());
                        menu.add(0, channel.getId(), Menu.NONE, channel.getName());
//                        menu.add(int groupId, int itemId, int order, CharSequence title);
                        if (mProgress.isShowing())
                            mProgress.dismiss();
                    }
                } else {
                    //request not successful (like 400,401,403 etc)
                    Log.d(TAG, response.raw().toString());
                    if (mProgress.isShowing())
                        mProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (mProgress.isShowing())
                    mProgress.dismiss();
                Log.d(TAG, "Failed to get channels");
            }
        });*/
    }

    public void loadUser() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if (sharedPref.contains(getString(R.string.jwt))) {
            token = sharedPref.getString(getString(R.string.jwt), "No Token");
        }
        Log.d(TAG, "Token retrieved from Preferences: " + token);

        RESTClient client = ServiceGenerator.createService(RESTClient.class, token);
        Call<User> call = client.getUserInformation();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    User userBody = response.body();

                } else {
                    //request not successful (like 400,401,403 etc)
                    Log.d(TAG, response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Could not even connect to server
                Log.d(TAG, "Failed to get user");
            }
        });
    }
}
