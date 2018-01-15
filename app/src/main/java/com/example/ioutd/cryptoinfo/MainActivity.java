package com.example.ioutd.cryptoinfo;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tvBase)
    TextView tvBase;

    @BindView(R.id.tvTarget)
    TextView tvTarget;

    @BindView(R.id.tvPrice)
    TextView tvPrice;

    @BindView(R.id.tvVolume)
    TextView tvVolume;

    private static final int CRYPTO_CURRENCY_LOADER = 1;
    private static final String REQUEST_URL_KEY = "request_url_key";
    private static final String BASE_CODE = "btc";
    private static final String TARGET_CODE = "usd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getCryptoCurrencyInfo();
    }
    // TODO (7): Use the custom Loader to retrieve the JSON data on a background thread
    private void getCryptoCurrencyInfo() {

        // This URL could be constructed in the Loader class. Wanted to demonstrate passing an object
        // through a bundle
        URL cryptoRequestUrl = NetworkUtils.buildCryptoCurrencyUrl(BASE_CODE, TARGET_CODE);

        // Put the URL into a bundle to pass to the loader
        Bundle urlBundle = new Bundle();
        urlBundle.putSerializable(REQUEST_URL_KEY, cryptoRequestUrl);

        // Create the CRYPTO_CURRENCY_LOADER
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> cryptoCurrencyLoader = loaderManager.getLoader(CRYPTO_CURRENCY_LOADER);

        // Initialize/Restoart the CRYPTO_CURRENCY_LOADER
        if (cryptoCurrencyLoader == null) {
            loaderManager.initLoader(CRYPTO_CURRENCY_LOADER, urlBundle, this).forceLoad();
        } else {
            loaderManager.restartLoader(CRYPTO_CURRENCY_LOADER, urlBundle, this).forceLoad();
        }
    }

    // TODO (8): Override onCreateLoader. Return the custom Loader
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CRYPTO_CURRENCY_LOADER:
                return new CryptoCurrencyLoader(this, args);
            default:
                throw new UnsupportedOperationException("Invalide Loader");
        }
    }

    // TODO (9): Override onLoadFinished. Retrieve the data from the JSON. Then set the data in the TextViews
    @Override
    public void onLoadFinished(Loader<String> loader, String jsonResponse) {
        // Set the data on the views
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject jsonTickerObject = jsonObject.getJSONObject("ticker");

            String base = jsonTickerObject.getString("base");
            String target = jsonTickerObject.getString("target");
            String price = jsonTickerObject.getString("price");
            String volume = jsonTickerObject.getString("volume");

            Log.d(TAG, "onLoadFinished() returned: " + base);
            tvBase.setText(base);
            tvTarget.setText(target);
            tvPrice.setText(price);
            tvVolume.setText(volume);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        // Not implemented
    }
}
