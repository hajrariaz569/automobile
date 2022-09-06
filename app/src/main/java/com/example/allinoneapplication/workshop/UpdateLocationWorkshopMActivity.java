package com.example.allinoneapplication.workshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allinoneapplication.R;
import com.example.allinoneapplication.ui.SelectLocationActivity;
import com.example.allinoneapplication.ui.SignUpActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UpdateLocationWorkshopMActivity extends AppCompatActivity {
    GoogleMap googleMap;
    TextView tv_submit_place, tv_show_selected_place;
    ImageView map_marker_pin_img;
    MapView mMapView;
    String SelectedAddress;
    String selectedPlace;
    double selectedLat, selectedLng;
    TextView tv_search_place;
    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_CODE = 10;
    private GoogleApiClient googleApiClient;
    LatLng latLng, markerLatlng;
    Marker currentLocationMarker, newMarker;
    String location;
    PlacesClient placesClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location_workshop_mactivity);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));

        MapsInitializer.initialize(UpdateLocationWorkshopMActivity.this);
        tv_submit_place = (TextView) findViewById(R.id.tv_submit_place);
        tv_show_selected_place = (TextView) findViewById(R.id.tv_show_selected_place);
        map_marker_pin_img = findViewById(R.id.map_marker_pin_img);
        map_marker_pin_img.setVisibility(View.INVISIBLE);
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        // Create a new Places client instance.
        String apiKey = "AIzaSyByVdzDd2TZwqXqFxfoJRPgJJJviizwGdM";
        if (!Places.isInitialized()) {
            Places.initialize(UpdateLocationWorkshopMActivity.this, apiKey);
        }

        placesClient = Places.createClient(this);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_auto_complete_fragment);
        //autocompleteFragment.setTypeFilter(TypeFilter.CITIES);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                final LatLng latLng = place.getLatLng();
                final String name = place.getName();
                mMapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(final GoogleMap googleMap) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(name);
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                        currentLocationMarker = googleMap.addMarker(markerOptions);
                        currentLocationMarker.setVisible(false);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

                        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                            @Override
                            public void onCameraIdle() {
                                if (googleMap != null) {
                                    googleMap.clear();
                                }
                                if (currentLocationMarker != null) {
                                    currentLocationMarker.remove();
                                }
                                map_marker_pin_img.setVisibility(View.VISIBLE);
                                LatLng center = googleMap.getCameraPosition().target;
                                newMarker = googleMap.addMarker(new MarkerOptions().position(center).title("New pos"));
                                newMarker.setVisible(false);
                                markerLatlng = newMarker.getPosition();
                                selectedLat = markerLatlng.latitude;
                                selectedLng = markerLatlng.longitude;
                                SelectedAddress = getStringAddress(markerLatlng.latitude, markerLatlng.longitude);
                                tv_show_selected_place.setText(SelectedAddress);
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(@NonNull Status status) {
            }
        });

        tv_submit_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WorkshopMechanicProfileActivity.class);
                intent.putExtra("ADDRESS", SelectedAddress);
                intent.putExtra("LATITUDE", selectedLat);
                intent.putExtra("LONGITUDE", selectedLng);
                startActivity(intent);
                finish();
            }
        });

    }

    public String getStringAddress(Double lat, Double lng) {
        String add = "";
        String city = "";
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(UpdateLocationWorkshopMActivity.this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            add = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return add;

    }
}