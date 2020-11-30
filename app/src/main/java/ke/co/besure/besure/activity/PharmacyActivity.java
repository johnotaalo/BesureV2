package ke.co.besure.besure.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.adapter.PharmacyAdapter;
import ke.co.besure.besure.model.County;
import ke.co.besure.besure.model.Pharmacy;
import ke.co.besure.besure.provider.PharmacyProvider;

public class PharmacyActivity extends AppCompatActivity {
    RecyclerView pharmacyRecyclerView;
    DB db;
    PharmacyAdapter adapter;
    List<Pharmacy> pharmacies;
    List<County> countyList;
    int selectedCounty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);

        db = new DB(this);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        pharmacyRecyclerView = findViewById(R.id.pharmacyList);
        pharmacyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pharmacyRecyclerView.setHasFixedSize(true);

        TextView title = myToolbar.findViewById(R.id.toolbar_title);

        countyList = db.getAllCounties();
        pharmacies = getPharmacies(0);

        adapter = new PharmacyAdapter(this, pharmacies);
        pharmacyRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(pharmacyRecyclerView.getContext(),
                layoutManager.getOrientation());
        pharmacyRecyclerView.addItemDecoration(dividerItemDecoration);

        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        initComponent();
    }

    public void initComponent(){
        ((Button) findViewById(R.id.spn_state)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStateChoiceDialog((Button) v);
            }
        });

        adapter.setOnItemClickListener(new PharmacyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Pharmacy obj, int position) {
//                Intent intent = new Intent(PharmacyActivity.this, PharmacyMapsActivity.class);
//                intent.putExtra("pharmacy_id", obj.getId());
//                startActivity(intent);
                showPharmacyDialog(obj);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void showPharmacyDialog(final Pharmacy pharmacy){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.pharmacy_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.pharmacyName)).setText(pharmacy.getPharmacy_name());
        ((TextView) dialog.findViewById(R.id.pharmacyLocation)).setText((!pharmacy.getPharmacy_location().isEmpty()) ? pharmacy.getPharmacy_location() : "N/A");
        ((TextView) dialog.findViewById(R.id.pharmacyContactPerson)).setText((!pharmacy.getPharmacy_contact_person().isEmpty())? pharmacy.getPharmacy_contact_person() : "N/A");
        ((TextView) dialog.findViewById(R.id.pharmacyPhone)).setText((!pharmacy.getPharmacy_phone().isEmpty()) ? pharmacy.getPharmacy_phone() : "N/A");


        ((AppCompatImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.getDirections)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "geo:0,0?q=" + pharmacy.getPharmacy_latitude() + "," + pharmacy.getPharmacy_longitude() + "("+pharmacy.getPharmacy_name()+")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void showStateChoiceDialog(final Button bt) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a county");
        builder.setCancelable(true);
        final String[] counties = getCounties();
        builder.setSingleChoiceItems(counties, selectedCounty, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                bt.setTextColor(Color.BLACK);
                pharmacies.clear();
                List<Pharmacy> filteredPharmacies = getPharmacies(countyList.get(which).getId());
                bt.setText(String.format("%s (%d Pharmacies)", counties[which], filteredPharmacies.size()));

                selectedCounty = which;
                pharmacies.addAll(filteredPharmacies);
                adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }

    private String[] getCounties(){
        String[] counties = new String[countyList.size()];
        for (int i = 0; i < countyList.size(); i++) {
            counties[i] = countyList.get(i).getCounty_name();
        }

        return counties;
    }

    private List<Pharmacy> getPharmacies(int id){
        String selection = null;
        String[] selectionArgs = null;
        if (id != 0){
            selection = "county_id=?";
            selectionArgs = new String[]{String.valueOf(id)};
        }
        Cursor cursor = this.getContentResolver().query(PharmacyProvider.CONTENT_URI, null, selection,selectionArgs, null);
        List<Pharmacy> pharmacies = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                Pharmacy pharmacy = new Pharmacy();

                pharmacy.setId(cursor.getInt(cursor.getColumnIndex(DB.PHARMACY_ID)));
                pharmacy.setPharmacy_name(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_NAME)));
                pharmacy.setPharmacy_location(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_LOCATION)));
                pharmacy.setPharmacy_contact_person(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_CONTACT_PERSON)));
                pharmacy.setPharmacy_phone(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_PHONE)));
                pharmacy.setPharmacy_latitude(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_LATITUDE)));
                pharmacy.setPharmacy_longitude(cursor.getString(cursor.getColumnIndex(DB.PHARMACY_LONGITUDE)));

                pharmacies.add(pharmacy);
            }while(cursor.moveToNext());
        }
        return pharmacies;
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}
