package com.example.ioutd.cryptoinfo;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.net.URL;

/**
 * Created by ioutd on 1/14/2018.
 */

// TODO (4): Create a custom Loader class that extends AsyncTaskLoader
public class CryptoCurrencyLoader extends AsyncTaskLoader {

    public static final String TAG = CryptoCurrencyLoader.class.getSimpleName();

    private URL cryptoRequestUrl;
    private static final String REQUEST_URL_KEY = "request_url_key";

    // TODO (5): Create a constructor that calls the super method, accepts a Bundle as a parameter, and retrieves the REQUEST_URL from the Bundle
    public CryptoCurrencyLoader(Context context, Bundle args) {
        super(context);
        this.cryptoRequestUrl =  (URL) args.getSerializable(REQUEST_URL_KEY);
    }

    // TODO (6): Override loadInBackground. Request the JSON response
    @Override
    public String loadInBackground() {

        if (cryptoRequestUrl == null || cryptoRequestUrl.equals(""))
            return null;

        // Load the JSON data from the URL
        String jsonResponse = NetworkUtils.requestJSONResponse(cryptoRequestUrl);

        Log.d(TAG, "loadInBackground() returned: " + jsonResponse);
        return jsonResponse;

    }
}
