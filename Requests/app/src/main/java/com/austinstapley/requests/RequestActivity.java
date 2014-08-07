package com.austinstapley.requests;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


public class RequestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public View rootview;

        public PlaceholderFragment() {
        }

        private Button requestButton;
        private EditText responseText;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootview = inflater.inflate(R.layout.fragment_request, container, false);

            this.responseText = (EditText)rootview.findViewById(R.id.editText);
            this.responseText.setKeyListener(null);

            this.requestButton = (Button)rootview.findViewById(R.id.requestButton);
            this.requestButton.setOnClickListener(
                    new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {

                              sendRequest();

                          }
                      }

            );

            return rootview;
        }

        public void sendRequest(){

            AsyncHttpClient client = new AsyncHttpClient();

            RequestParams params = new RequestParams();
            params.put("api_key", "e64b61e85e6c206a");
            params.put("username", "mitchell");
            params.put("password", "qwerty");

            client.post("https://www.organicparking.com/api/1.0/login", params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    responseText.setText(response.toString(), TextView.BufferType.EDITABLE);

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray JSON) {

                    responseText.setText(JSON.toString(), TextView.BufferType.EDITABLE);

                }

            });

        }

    }

}
