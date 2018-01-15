
# Crypto-Info

### TODOs
##### AndroidManifest.xml
1. Add the Internet permission to the AndroidManifest

##### NetworkUtils.java
2. Construct a method to build the URL that will fetch the JSON data
3. Construct a method to use the URL to retrieve the JSON data with a GET request

##### CryptoCurrencyLoader.java
4. Create a custom Loader class that extends AsyncTaskLoader
5. Create a constructor that calls teh super method, accepts a Bundle as a parameter, and retrieves the REQUEST_URL from the Bundle
6. Override loadInBackground(). Inside, request the JSON response

##### MainActivity.java
7. Use the custom Loader to retrieve the JSON data on a background thread
8. Override onCreateLoader(). Inside, return the custom Loader
9. Override onLoadFinished(). Inside, retrieve the data from the JSON then set the data in the TextViews

