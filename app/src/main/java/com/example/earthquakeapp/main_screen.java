package com.example.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class main_screen extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<quakedetails>> {

    private quakedetailsadapter mAdapter;

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private static final String LOG_TAG = main_screen.class.getName();

    /**
     * URL for earthquake data from the USGS dataset
     */
    private static final String USGS_REQUEST_URL =
            //"http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";
    // "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6";
    "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7";
//    "http://127.0.0.1:5500/trial.html"
//        "./touch.json";

    private TextView mEmptyStateTextView ;

    private boolean isNetworkConnected ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ListView earthquakeListView = (ListView) findViewById(R.id.list_view);

        mAdapter = new quakedetailsadapter(this, new ArrayList<quakedetails>());

        earthquakeListView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(main_screen.this,"Intenting to Website",Toast.LENGTH_SHORT);
                quakedetails uurl = mAdapter.getItem(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(uurl.getUrl()));
                startActivity(i);
            }
        });

//        EarthQuakeAsync task = new EarthQuakeAsync();
//        task.execute(USGS_REQUEST_URL);

//        LoaderManager loaderManager = getLoaderManager();
//        loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null, this);
        LoaderManager.getInstance(this).initLoader(EARTHQUAKE_LOADER_ID,null,this);
    }

//    private class EarthQuakeAsync extends AsyncTask<String, Void, List<quakedetails>> {
//
//        @Override
//        protected List<quakedetails> doInBackground(String... strings) {
//
//            if (strings[0] == null || strings.length < 1) {
//                return null;
//            }
//            String response = fetchdata.fetchEarthquakeData(strings[0]);
//            ArrayList<quakedetails> toBeREturned = simplifying_info.extractEarthquakes(response);
//            return toBeREturned;
//        }
//
//        @Override
//        protected void onPostExecute(List<quakedetails> quakedetails) {
//            mAdapter.clear();
//            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
//            // data set. This will trigger the ListView to update.
//            if (quakedetails != null && !quakedetails.isEmpty()) {
//                mAdapter.addAll(quakedetails);
//
//            }
//        }
//    }

    @Override
    public Loader<List<quakedetails>> onCreateLoader(int i, Bundle bundle) {
        // TODO: Create a new loader for the given URL
        Log.i(LOG_TAG,"TEST: onCreateLoader() called ...");
        return new quakedetailoader(this,USGS_REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<quakedetails>> loader, List<quakedetails> earthquakes) {
        // TODO: Update the UI with the result
        Log.i(LOG_TAG,"TEST: onLoadFinished() called ...");
        mAdapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar_circular);
        progressBar.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_earthquakes);

//        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkRequest.Builder builder = new NetworkRequest.Builder();


    }

    @Override
    public void onLoaderReset(Loader<List<quakedetails>> loader) {
        // TODO: Loader reset, so we can clear out our existing data.
        Log.i(LOG_TAG,"TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }
}