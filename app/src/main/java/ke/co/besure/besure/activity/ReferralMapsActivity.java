package ke.co.besure.besure.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import ke.co.besure.besure.R;

public class ReferralMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String facility_name, nearest_town, longitude, latitude;

    TextView facilityName, facilityNearestTown;

    Button getDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initComponents();
    }

    private void initComponents(){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        TextView title = myToolbar.findViewById(R.id.toolbar_title);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        facilityName = findViewById(R.id.facilityName);
        facilityNearestTown =findViewById(R.id.facilityNearestTown);
        getDirections = findViewById(R.id.getDirections);

        facility_name = getIntent().getStringExtra("facility_name");
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        nearest_town = getIntent().getStringExtra("nearest_town");

        facilityName.setText(facility_name);
        facilityNearestTown.setText(nearest_town);

        getDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "geo:0,0?q=" + latitude + "," + longitude + "("+facility_name+")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
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

//        boolean success = mMap.setMapStyle(
//                MapStyleOptions.loadRawResourceStyle(
//                        this, R.raw.style_json));
//
//        if (!success) {
//            Log.e("FacilityMap", "Style parsing failed.");
//        }

        // Add a marker in Sydney and move the camera
        LatLng facility = new LatLng(Float.parseFloat(latitude), Float.parseFloat(longitude));
        mMap.addMarker(new MarkerOptions().position(facility).title(facility_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(facility, 18));
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}
