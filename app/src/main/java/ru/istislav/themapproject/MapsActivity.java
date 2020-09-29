package ru.istislav.themapproject;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng MINSK = new LatLng(53.898295, 27.553700);
    private static final LatLng SYDNEY = new LatLng(-33.826815, 150.948107);
    private static final LatLng DURHAM = new LatLng(36.009111, -78.911864);

    private Marker mMinsk;
    private Marker mSydney;
    private Marker mDurham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        List<Marker> markerList = new ArrayList<>();

        mMinsk = mMap.addMarker(new MarkerOptions().position(MINSK).title("Minsk"));
        mMinsk.setTag(0);
        markerList.add(mMinsk);

        mSydney = mMap.addMarker(new MarkerOptions().position(SYDNEY).title("Sydney")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mSydney.setTag(0);
        markerList.add(mSydney);

        mDurham = mMap.addMarker(new MarkerOptions().position(DURHAM).title("Durham")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        mDurham.setTag(0);
        markerList.add(mDurham);

        for (Marker m : markerList) {
            Log.d("Marker title", m.getTitle());
            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 2));
        }

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        LatLng moscow = new LatLng(55.5815244,36.8251149);
//
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        // the marker with color
//        mMap.addMarker(new MarkerOptions().position(moscow).title("Marker in Moscow")
//        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(moscow)); // only new location
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moscow, 15)); // location with zoom
    }
}