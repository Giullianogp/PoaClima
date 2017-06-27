package com.example.giull.poaclima;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.giull.poaclima.Models.Leitura;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String Url = "https://metroclimaestacoes.procempa.com.br/metroclima/seam/resource/rest/externalRest/ultimaLeitura";
    private GoogleMap mMap;
    private List<Leitura> mLeitura = new ArrayList<>();
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }


    private void MakeRequest() {

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray array) {

                        for (int i = 0; i < array.length(); i++) {
                            try {
                                JSONObject jsonObject = array.getJSONObject(i);
                                mLeitura.add(ParseRequest(jsonObject));
                            } catch (JSONException e) {
                                Log.e("PoaClima", e.getLocalizedMessage());
                            }
                        }

                        AddItensToMap();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("PoaClima", error.getMessage());
                    }
                });


        queue.add(jsObjRequest);
    }

    public void AddItensToMap() {

        for (Leitura leitura: mLeitura)
        {
            LatLng local = new LatLng(leitura.getLatitude(), leitura.getLongitude());
            MarkerOptions mOpt = new MarkerOptions();
            mOpt.position(local);
            mMap.addMarker(mOpt);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            GetLastLocation();
            MakeRequest();
        }
    }

    private void GetLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng local = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions mOpt = new MarkerOptions();
                            mOpt.position(local);
                            //mOpt.icon(R.mipmap.ic_launcher);
                            mMap.addMarker(mOpt);
                            CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(local, 17);
                            mMap.animateCamera(camera);
                        }
                    }
                });
    }

    private Leitura ParseRequest(JSONObject jsonObject) {
        try {
            Leitura leitura = new Leitura();
            leitura.setId(jsonObject.getInt("id"));
            leitura.setEstacao(jsonObject.getString("estacao"));
            leitura.setEndereco(jsonObject.getString("endereco"));
            leitura.setEstacao(jsonObject.getString("bairro"));
            leitura.setEstacao(jsonObject.getString("latitude"));
            leitura.setEstacao(jsonObject.getString("longitude"));
            leitura.setEstacao(jsonObject.getString("data"));
            leitura.setEstacao(jsonObject.getString("temperaturaInterna"));
            leitura.setEstacao(jsonObject.getString("umidadeInterna"));
            leitura.setEstacao(jsonObject.getString("temperaturaExterna"));
            leitura.setEstacao(jsonObject.getString("umidadeExterna"));

            leitura.setEstacao(jsonObject.getString("chuvaDiaria"));
            leitura.setEstacao(jsonObject.getString("pressao"));
            leitura.setEstacao(jsonObject.getString("velocidadeVento"));
            leitura.setEstacao(jsonObject.getString("direcaoVento"));
            leitura.setEstacao(jsonObject.getString("velocidadeVentoRajada"));
            leitura.setEstacao(jsonObject.getString("direcaoVentoRajada"));
            leitura.setEstacao(jsonObject.getString("quadranteVento"));

            leitura.setEstacao(jsonObject.getString("sensacaoTermica"));
            leitura.setEstacao(jsonObject.getString("pontoOrvalho"));
            leitura.setEstacao(jsonObject.getString("alturaNuvens"));
            leitura.setEstacao(jsonObject.getString("idRessonare"));

            return leitura;

        } catch (JSONException e) {
            Log.e("PoaClima", e.getMessage());
            return null;
        }
    }
}
