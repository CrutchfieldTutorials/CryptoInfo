package com.example.ioutd.cryptoinfo;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ioutd on 1/14/2018.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String CRYPTO_CURRENCY_BASE_URL = "https://api.cryptonator.com";
    private static final String API_PATH = "api";
    private static final String TICKER_PATH = "ticker";

    /**
     * Builds the URL that will be used to fetch JSON data from the Cryptonator api
     * @param baseCode the base currency code
     * @param targetCode the target currency code
     * @return
     */
    // TODO (2): Construct a method to build the URL that will be used to fetch the JSON data
    public static URL buildCryptoCurrencyUrl(String baseCode, String targetCode) {
        // Create the string that contains the base and target currencies
        // Ex: btc-usd
        String currencyPath = baseCode + "-" + targetCode;

        // Build a URI with the base URL and the path parameters
        Uri builtUri = Uri.parse(CRYPTO_CURRENCY_BASE_URL).buildUpon()
                .appendPath(API_PATH)
                .appendPath(TICKER_PATH)
                .appendPath(currencyPath)
                .build();


        URL url = null;
        // Potential MalformedURLException when constructing a new URL
        try {
            // Construct a new URL using a string representation of the built URI
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "buildCryptoCurrencyUrl() returned: " + url);
        return url;
    }

    /**
     * Retrieves the JSON data from the internet using a GET request from the url parameter.
     * @param url the url to fetch the JSON data from
     * @return String representation of the JSON data
     */
    // TODO (3): Construct a method to use the URL that was created to retrieve the JSON data with a GET request
    public static String requestJSONResponse(URL url) {
        // Construct an OkHttpClient to handle the requests
        final OkHttpClient client = new OkHttpClient();

        // Construct a Request object and set the url
        final Request request = new Request.Builder()
                .url(url)
                .build();

        // Create a response object to hold the response from the request
        Response response = null;
        String jsonResponse = "";
        // Potential IOException from client.newCall and response.body()
        try {
            // Execute the request to retrieve the response
            response = client.newCall(request).execute();
            // Retrieve a string represnetation of the response
            jsonResponse = response.body().string();
            // Close the response object
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "getJSONResponse() returned: " + jsonResponse);
        return jsonResponse;
    }
}
