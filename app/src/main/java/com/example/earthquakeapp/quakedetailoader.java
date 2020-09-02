package com.example.earthquakeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class quakedetailoader extends AsyncTaskLoader<List<quakedetails>> {

    private static final String LOG_TAG = quakedetailoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link quakedetailoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public quakedetailoader(Context context, String url) {
        super(context);
        mUrl = url;
        // TODO: Finish implementing this constructor
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"TEST: onStartLoading() called ...");
        forceLoad();
    }

    @Override
    public List<quakedetails> loadInBackground() {
        // TODO: Implement this method
        Log.i(LOG_TAG,"TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        String response = fetchdata.fetchEarthquakeData(mUrl);
        List<quakedetails> earthquakes = simplifying_info.extractEarthquakes(response);
        return earthquakes;
    }

}
