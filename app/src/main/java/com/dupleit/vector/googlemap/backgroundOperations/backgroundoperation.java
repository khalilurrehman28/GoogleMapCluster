package com.dupleit.vector.googlemap.backgroundOperations;

import android.os.AsyncTask;
import android.util.Log;

import com.dupleit.vector.googlemap.Network.APIService;
import com.dupleit.vector.googlemap.Network.ApiClient;
import com.dupleit.vector.googlemap.modal.Datum;
import com.dupleit.vector.googlemap.modal.TempObject;
import com.dupleit.vector.googlemap.modal.UsersMaps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by android on 10/11/17.
 */

public class backgroundoperation extends AsyncTask<Void, TempObject, String> {

    public ClusterManager<Datum> mClusterManager;
    public GoogleMap mMap;


    public backgroundoperation(ClusterManager<Datum> mClusterManager, GoogleMap mMap) {
        this.mClusterManager = mClusterManager;
        this.mMap = mMap;
    }

    @Override
    protected String doInBackground(Void... params) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<UsersMaps> userCall = service.UserLogin();
        userCall.enqueue(new Callback<UsersMaps>() {
            @Override
            public void onResponse(Call<UsersMaps> call, Response<UsersMaps> response) {
                if (response.isSuccessful()){
                        //Toast.makeText(MainActivity.this, "Hello from api", Toast.LENGTH_SHORT).show();
                        onProgressUpdate(new TempObject(response));

                }
            }

            @Override
            public void onFailure(Call<UsersMaps> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
        return null;
    }

    @Override
    public void onProgressUpdate(TempObject... value) {
        super.onProgressUpdate(value);
        Response<UsersMaps> response = value[0].getResponse();
        List<Datum> objUser = response.body().getData();
        Double lat =  0.0;
        Double lang = 0.0;
        int count = 0;
        for (Datum quizShow : objUser) {
            String[] userCoordinates  = quizShow.getUSERCOORDINATE().split(",");
            LatLng latLng = new LatLng(Double.parseDouble(userCoordinates[0].trim()),Double.parseDouble(userCoordinates[1].trim()));
            lat = lat+Double.parseDouble(userCoordinates[0].trim());
            lang = lang+Double.parseDouble(userCoordinates[1].trim());
            mClusterManager.addItem(new Datum(quizShow.getUSERID(),quizShow.getUSERNAME(),quizShow.getUSERIMAGE(),latLng));
            count++;
        }
        // final LatLngBounds bounds =
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng((lat/count),(lang/count)))    // Sets the center of the map to Mountain View
                .zoom(7)                   // Sets the zoom
                .bearing(16)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mClusterManager.cluster();

    }
}
