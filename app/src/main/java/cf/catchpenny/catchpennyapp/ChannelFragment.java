package cf.catchpenny.catchpennyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cf.catchpenny.catchpennyapp.Models.ChannelMessage;
import cf.catchpenny.catchpennyapp.REST.RESTClient;
import cf.catchpenny.catchpennyapp.REST.ServiceGenerator;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {

    public static final String TAG = ChannelFragment.class.getSimpleName();
    private ArrayAdapter<String> mMessagesAdapter;
    private List<String> messagesArray;
    private boolean flag_loading;
    private String token;
    private Integer nextPage;

    public ChannelFragment() {
        // Required empty public constructor
        messagesArray = new ArrayList<String>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_channel, container, false);

        String[] messagesArray = {
                "dummy",
        };

        List<String> channelMessages = new ArrayList<>(Arrays.asList(messagesArray));

        mMessagesAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.message_list_item,
                R.id.message_list_item_textview,
                channelMessages);

        ListView listView = (ListView) rootView.findViewById(R.id.channel_messages);
        listView.setAdapter(mMessagesAdapter);

        /*listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0) {
                    if(flag_loading == false)
                    {
                        flag_loading = true;
                        addItems();
                    }
                }
            }
        });*/

        /*listView.setOnScrollChangeListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                customLoadMoreDataFromApi(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }

            // Append more data into the adapter
            public void customLoadMoreDataFromApi(int offset) {
                // This method probably sends out a network request and appends new data items to your adapter.
                // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
                // Deserialize API response and then construct new objects to append to the adapter
            }
        });*/

        return rootView;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public void setListItems(List<ChannelMessage> channelMessages) {
        messagesArray.clear();
        for (ChannelMessage cm : channelMessages)
        {
            messagesArray.add(cm.getBody());
            Log.d("ChannelFragment", cm.getBody());
        }

        String[] result = new String[ messagesArray.size() ];
        messagesArray.toArray(result);

        for(String message : result) {
            mMessagesAdapter.add(message);
        }
    }

    private void addItems() {
        flag_loading = false;
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if (sharedPref.contains(getString(R.string.jwt))) {
            token = sharedPref.getString(getString(R.string.jwt), "No Token");
        }
        RESTClient client = ServiceGenerator.createService(RESTClient.class, token);
        /*Call<Details> channels = client.getDetails("1", "1", nextPage);
        channels.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Response<Details> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    Log.d(TAG, response.raw().toString());
                    Details details = response.body();
                    nextPage = details.getNextPage();
                    setListItems(details.getChannelMessages());
                    mMessagesAdapter.notifyDataSetChanged();
                } else {
                    //request not successful (like 400,401,403 etc)
                    Log.d(TAG, response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "Failed to get channels");
            }
        });*/
    }
}
