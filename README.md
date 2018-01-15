
# Crypto-Info

### TODOs
##### AndroidManifest.xml
- [ ] Add the Internet permission to the AndroidManifest

##### NetworkUtils.java
- [ ] Construct a method to build the URL that will fetch the JSON data
- [ ] Construct a method to use the URL to retrieve the JSON data with a GET request

##### CryptoCurrencyLoader.java
- [ ] Create a custom Loader class that extends AsyncTaskLoader
- [ ] Create a constructor that calls teh super method, accepts a Bundle as a parameter, and retrieves the REQUEST_URL from the Bundle
- [ ] Override loadInBackground(). Inside, request the JSON response

##### MainActivity.java
- [ ] Use the custom Loader to retrieve the JSON data on a background thread
- [ ] Override onCreateLoader(). Inside, return the custom Loader
- [ ] Override onLoadFinished(). Inside, retrieve the data from the JSON then set the data in the TextViews

