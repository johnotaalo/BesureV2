package ke.co.besure.besure.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.adapter.FacilityAdapter;
import ke.co.besure.besure.helper.RetrofitHelper;
import ke.co.besure.besure.model.County;
import ke.co.besure.besure.model.Facility;
import ke.co.besure.besure.server.service.FacilityService;
import retrofit2.Call;
import retrofit2.Retrofit;

public class ReferralsActivity extends AppCompatActivity {
    RecyclerView facilityRecyclerView;
    RelativeLayout noFacilitiesLayout;
    RelativeLayout loadingLayout;
    DB db;
    List<County> countyList;
    int selectedCounty = 0;
    private static String TAG = "Facilities";

    FacilityAdapter adapter;
    List<Facility> facilities = new ArrayList<Facility>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrals);

        db = new DB(this);
        countyList = db.getAllCounties();

        initComponent();
    }

    public void initComponent() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        TextView title = myToolbar.findViewById(R.id.toolbar_title);
        noFacilitiesLayout = findViewById(R.id.no_facilities);
        loadingLayout = findViewById(R.id.loading);

        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        facilityRecyclerView = findViewById(R.id.facilityList);
        facilityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        facilityRecyclerView.setHasFixedSize(true);

        adapter = new FacilityAdapter(this, facilities);
        facilityRecyclerView.setAdapter(adapter);

        ((Button) findViewById(R.id.spn_state)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStateChoiceDialog((Button) v);
            }
        });

        adapter.setOnItemClickListener(new FacilityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Facility obj, int position) {
                Intent intent = new Intent(ReferralsActivity.this, ReferralMapsActivity.class);
                intent.putExtra("facility_name", obj.getFacility_name());
                intent.putExtra("nearest_town", obj.getNearest_town());
                intent.putExtra("longitude", obj.getLongitude());
                intent.putExtra("latitude", obj.getLatitude());
                startActivity(intent);
            }
        });
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
//                List<Pharmacy> filteredPharmacies = getPharmacies(countyList.get(which).getId());
                selectedCounty = which;
                facilities.clear();
                adapter.notifyDataSetChanged();
                loadingLayout.setVisibility(View.VISIBLE);
                facilityRecyclerView.setVisibility(View.GONE);
                noFacilitiesLayout.setVisibility(View.GONE);
                new getFacilities().execute(counties[which]);
                bt.setText(String.format("%s", counties[which]));
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

    private void getFacilties(List<Facility> facs){
        this.facilities.addAll(facs);
        loadingLayout.setVisibility(View.GONE);
        if (this.facilities.size() > 0){
            facilityRecyclerView.setVisibility(View.VISIBLE);
            noFacilitiesLayout.setVisibility(View.GONE);
        }else{
            facilityRecyclerView.setVisibility(View.GONE);
            noFacilitiesLayout.setVisibility(View.VISIBLE);
            nofacilityDialog();
        }

        adapter.notifyDataSetChanged();
    }

    private void nofacilityDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_warning);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    class getFacilities extends AsyncTask<String, String, List<Facility>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Started");
        }

        @Override
        protected List<Facility> doInBackground(String... strings) {
            String county = strings[0];
            Log.d(TAG, "County name: " + strings[0]);
            try {
                Retrofit retrofit = RetrofitHelper.getInstance().createHelper();
                FacilityService service = retrofit.create(FacilityService.class);

                Call<List<Facility>> facilityCall = service.get(county);
                publishProgress("Fetching Facilities");
                List<Facility> facs = facilityCall.execute().body();
                if(facs != null) {
                    Log.d(TAG, "Facilities found: " + facs.size());
                    return facs;
                }else{
                    return new ArrayList<>();
                }
            }catch (Exception e){
                Log.e(TAG, e.getMessage());
                return new ArrayList<Facility>();
            }
        }

        @Override
        protected void onPostExecute(List<Facility> facs) {
            super.onPostExecute(facs);
            Log.d(TAG, "Ended");

            if (facs.size() > 0){
                getFacilties(facs);
                Log.d(TAG, "Success");
            }else{
                loadingLayout.setVisibility(View.GONE);
                noFacilitiesLayout.setVisibility(View.VISIBLE);
                nofacilityDialog();
                Log.d(TAG, "There was an error");
            }
        }
    }
}
