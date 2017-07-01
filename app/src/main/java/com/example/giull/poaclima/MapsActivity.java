package com.example.giull.poaclima;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.giull.poaclima.Models.Leitura;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
            if (leitura == null) continue;
            LatLng local = new LatLng(leitura.getLatitude(), leitura.getLongitude());
            MarkerOptions mOpt = new MarkerOptions();

            if (leitura.getChuvaDiaria() > 0)
            {
                    if (leitura.getChuvaDiaria() < 5)
                    {
                        mOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.cloud));
                    }
                    else
                    {
                        mOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.rain));
                    }
            }
            else
            {
                mOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.sun));

            }

            mOpt.title(leitura.getEstacao());
            mOpt.snippet("Chuva Diária: " + leitura.getChuvaDiaria());

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
            leitura.setBairro(jsonObject.getString("bairro"));
            leitura.setLatitude(jsonObject.getDouble("latitude"));
            leitura.setLongitude(jsonObject.getDouble("longitude"));
            leitura.setData(jsonObject.getString("data"));
            leitura.setTemperaturaInterna(jsonObject.isNull("temperaturaInterna") ? 0 : jsonObject.getDouble("temperaturaInterna"));
            leitura.setUmidadeInterna(jsonObject.isNull("umidadeInterna") ? 0 :jsonObject.getDouble("umidadeInterna"));
            leitura.setTemperaturaExterna(jsonObject.isNull("temperaturaExterna") ? 0 :jsonObject.getDouble("temperaturaExterna"));
            leitura.setUmidadeExterna(jsonObject.isNull("umidadeExterna") ? 0 :jsonObject.getDouble("umidadeExterna"));
            leitura.setChuvaDiaria(jsonObject.isNull("chuvaDiaria") ? 0 :jsonObject.getDouble("chuvaDiaria"));
            leitura.setPressao(jsonObject.isNull("pressao") ? 0 :jsonObject.getDouble("pressao"));
            leitura.setVelocidadeVento(jsonObject.isNull("velocidadeVento") ? 0 :jsonObject.getDouble("velocidadeVento"));
            leitura.setDirecaoVento(jsonObject.isNull("direcaoVento") ? 0 :jsonObject.getDouble("direcaoVento"));
            leitura.setDirecaoVentoRajada(jsonObject.isNull("velocidadeVentoRajada") ? 0 :jsonObject.getDouble("velocidadeVentoRajada"));
            leitura.setDirecaoVentoRajada(jsonObject.isNull("direcaoVentoRajada") ? 0 :jsonObject.getDouble("direcaoVentoRajada"));
            leitura.setQuadranteVento(jsonObject.getString("quadranteVento"));
            leitura.setSensacaoTermica(jsonObject.isNull("sensacaoTermica") ? 0 :jsonObject.getDouble("sensacaoTermica"));
            leitura.setPontoOrvalho(jsonObject.isNull("pontoOrvalho") ? 0 :jsonObject.getDouble("pontoOrvalho"));
            leitura.setAlturaNuvens(jsonObject.isNull("alturaNuvens") ? 0 :jsonObject.getDouble("alturaNuvens"));
            leitura.setIdRessonare(jsonObject.getInt("idRessonare"));

            return leitura;

        } catch (JSONException e) {
            Log.e("PoaClima", e.getMessage());
            return null;
        }
    }
}
