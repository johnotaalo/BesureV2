package ke.co.besure.besure.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.model.Pharmacy;
import ke.co.besure.besure.provider.PharmacyProvider;

public class PharmacyMapActivity extends AppCompatActivity {
    int id = 0;
    Pharmacy pharmacy;

    TextView pharmacyName, pharmacyContactPerson, pharmacyLocation, pharmacyPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_map);

        id = getIntent().getIntExtra("pharmacy_id", 0);

        initComponents();

    }

    private void initComponents(){
        pharmacy = getPharmacy(id);
        pharmacyName = findViewById(R.id.pharmacyName);
        pharmacyContactPerson = findViewById(R.id.pharmacyContactPerson);
        pharmacyLocation = findViewById(R.id.pharmacyLocation);
        pharmacyPhone = findViewById(R.id.pharmacyPhone);

        pharmacyName.setText(pharmacy.getPharmacy_name());
        pharmacyContactPerson.setText((!pharmacy.getPharmacy_contact_person().isEmpty())? pharmacy.getPharmacy_contact_person() : "N/A");
        pharmacyLocation.setText((!pharmacy.getPharmacy_location().isEmpty()) ? pharmacy.getPharmacy_location() : "N/A");
        pharmacyPhone.setText((!pharmacy.getPharmacy_phone().isEmpty()) ? pharmacy.getPharmacy_phone() : "N/A");
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
}
