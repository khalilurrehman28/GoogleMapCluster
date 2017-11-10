
package com.dupleit.vector.googlemap.modal;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

public class Datum implements ClusterItem {

    @SerializedName("USER_COORDINATE")
    @Expose
    private String uSERCOORDINATE;
    @SerializedName("USER_ID")
    @Expose
    private String uSERID;
    @SerializedName("USER_NAME")
    @Expose
    private String uSERNAME;
    @SerializedName("USER_IMAGE")
    @Expose
    private String uSERIMAGE;

    LatLng userLatLang;

    public Datum(String uSERID, String uSERNAME, String uSERIMAGE, LatLng userLatLang) {
        this.uSERID = uSERID;
        this.uSERNAME = uSERNAME;
        this.uSERIMAGE = uSERIMAGE;
        this.userLatLang = userLatLang;
    }

    public String getUSERID() {
        return uSERID;
    }

    public void setUSERID(String uSERID) {
        this.uSERID = uSERID;
    }

    public String getUSERNAME() {
        return uSERNAME;
    }

    public void setUSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    public String getUSERCOORDINATE() {
        return uSERCOORDINATE;
    }

    public void setUSERCOORDINATE(String uSERCOORDINATE) {
        this.uSERCOORDINATE = uSERCOORDINATE;
    }

    public String getUSERIMAGE() {
        return uSERIMAGE;
    }

    public void setUSERIMAGE(String uSERIMAGE) {
        this.uSERIMAGE = uSERIMAGE;
    }

    @Override
    public LatLng getPosition() {
        return userLatLang;
    }

    @Override
    public String getTitle() {
        return uSERNAME;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
