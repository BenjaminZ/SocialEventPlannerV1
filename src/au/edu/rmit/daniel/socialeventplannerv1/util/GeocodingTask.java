package au.edu.rmit.daniel.socialeventplannerv1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;
import au.edu.rmit.daniel.socialeventplannerv1.model.GeoResponse;
import au.edu.rmit.daniel.socialeventplannerv1.model.GeoResult;
import au.edu.rmit.daniel.socialeventplannerv1.model.GeoResult.Location;

import com.google.gson.Gson;


public class GeocodingTask extends AsyncTask<String, Void, GeoResponse> {
    private static final String GEO_API =
            "http://maps.googleapis.com/maps/api/geocode/json?address=";

    @Override
    protected GeoResponse doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        GeoResponse geoResponse = null;
        try {
            HttpResponse response = httpClient.execute(new HttpGet(GEO_API + params[0]));
            HttpEntity entity = response.getEntity();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String responseStr = sb.toString();
            Gson gson = new Gson();
            geoResponse = gson.fromJson(responseStr, GeoResponse.class);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geoResponse;
    }

    @Override
    protected void onPostExecute(GeoResponse result) {
        if (result != null && result.getStatus().equals("OK") && result.getResults().size() > 0) {
            GeoResult geoResult = result.getResults().get(0);
            if (geoResult != null) {
                Location location = geoResult.getGeometry().getLocation();
                double lat = location.getLat();
                double lng = location.getLng();
                Log.i("geo", "lat=" + lat + ", lng=" + lng);
                mListener.onResponse(location);
            }
        } else {
            Log.i("geo", "error");
        }
    }

    private OnResponseListener mListener;

    public interface OnResponseListener {
        public void onResponse(Location location);
    }

    public void setOnResponseListener(OnResponseListener listener) {
        this.mListener = listener;
    }
}
