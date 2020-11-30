package ke.co.besure.besure.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.model.Pharmacy;
import ke.co.besure.besure.provider.PharmacyProvider;

public class PharmacyMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    int id = 0;
    Pharmacy pharmacy;

    TextView pharmacyName, pharmacyContactPerson, pharmacyLocation, pharmacyPhone;
    Button getDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_maps);
        id = getIntent().getIntExtra("pharmacy_id", 0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initComponents();
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
//            Log.e("PharmacyMap", "Style parsing failed.");
//        }

        // Add a marker in Sydney and move the camera
        LatLng pharm = new LatLng(Float.parseFloat(pharmacy.getPharmacy_latitude()), Float.parseFloat(pharmacy.getPharmacy_longitude()));
        mMap.addMarker(new MarkerOptions().position(pharm).title(pharmacy.getPharmacy_name()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pharm, 18));
    }


    private void initComponents(){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        TextView title = myToolbar.findViewById(R.id.toolbar_title);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pharmacy = getPharmacy(id);
        pharmacyName = findViewById(R.id.pharmacyName);
        pharmacyContactPerson = findViewById(R.id.pharmacyContactPerson);
        pharmacyLocation = findViewById(R.id.pharmacyLocation);
        pharmacyPhone = findViewById(R.id.pharmacyPhone);
        getDirections = findViewById(R.id.getDirections);

        pharmacyName.setText(pharmacy.getPharmacy_name());
        pharmacyContactPerson.setText((!pharmacy.getPharmacy_contact_person().isEmpty())? pharmacy.getPharmacy_contact_person() : "N/A");
        pharmacyLocation.setText((!pharmacy.getPharmacy_location().isEmpty()) ? pharmacy.getPharmacy_location() : "N/A");
        pharmacyPhone.setText((!pharmacy.getPharmacy_phone().isEmpty()) ? pharmacy.getPharmacy_phone() : "N/A");

        getDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "geo:0,0?q=" + pharmacy.getPharmacy_latitude() + "," + pharmacy.getPharmacy_longitude() + "("+pharmacy.getPharmacy_name()+")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }

    private Pharmacy getPharmacy(int id){
        Pharmacy pharmacy = new Pharmacy();

        Cursor cursor = this.getContentResolver().query(PharmacyProvider.CONTENT_URI, null, "id=?",new String[]{String.valueOf(id)}, null);
        if (cursor.moveToFirst()){
            pharmacy.setId(cursor.getInt(cursor.getColumnIndex(DB.PHARMACY_ID)));
            pharmacy.setPharmacy_name(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_NAME)));
            pharmacy.setPharmacy_location(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_LOCATION)));
            pharmacy.setPharmacy_contact_person(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_CONTACT_PERSON)));
            pharmacy.setPharmacy_phone(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_PHONE)));
            pharmacy.setPharmacy_latitude(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_LATITUDE)));
            pharmacy.setPharmacy_longitude(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_LONGITUDE)));
        }
        return pharmacy;
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}
