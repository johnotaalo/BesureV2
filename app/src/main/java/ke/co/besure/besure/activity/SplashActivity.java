package ke.co.besure.besure.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.helper.RetrofitHelper;
import ke.co.besure.besure.model.County;
import ke.co.besure.besure.model.FAQ;
import ke.co.besure.besure.model.Pharmacy;
import ke.co.besure.besure.provider.FAQProvider;
import ke.co.besure.besure.provider.PharmacyProvider;
import ke.co.besure.besure.server.service.CountyService;
import ke.co.besure.besure.server.service.FAQService;
import ke.co.besure.besure.server.service.FacilityService;
import ke.co.besure.besure.server.service.PharmacyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity {
    DB db;
    ContentResolver mContentResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContentResolver = this.getContentResolver();

        db = new DB(this);
//        db.clearDatabase();

        Log.d("Download", "Counties: " + db.getAllCounties().size());
        if (db.getAllCounties().size() > 0) {
            openNextPage();
        }else{
            downloadData();
        }
    }

    public void openNextPage(){
        startActivity(new Intent(this, OnboardingActivity.class));
        finish();
    }

    public void downloadData(){
        new getContent().execute();
        downloadFAQs();
        Retrofit retrofit = RetrofitHelper.getInstance().createHelper();

        PharmacyService pharmacyService = retrofit.create(PharmacyService.class);
        Call<List<Pharmacy>> pharmacyCall = pharmacyService.get();

        pharmacyCall.enqueue(new Callback<List<Pharmacy>>() {
            @Override
            public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {
                List<Pharmacy> pharmacies = response.body();
                if (pharmacies.size() > 0) {
                    mContentResolver.delete(PharmacyProvider.CONTENT_URI, null, null);
                    for (Pharmacy pharmacy :
                            pharmacies) {
                        ContentValues contentValues = new ContentValues();

                        contentValues.put(DB.PHARMACY_ID, pharmacy.getId());
                        contentValues.put(DB.PHARMACY_NAME, pharmacy.getPharmacy_name());
                        contentValues.put(DB.PHARMACY_LOCATION, pharmacy.getPharmacy_location());
                        contentValues.put(DB.PHARMACY_LATITUDE, pharmacy.getPharmacy_latitude());
                        contentValues.put(DB.PHARMACY_LONGITUDE, pharmacy.getPharmacy_longitude());
                        contentValues.put(DB.PHARMACY_COUNTY_ID, pharmacy.getCounty_id());
                        contentValues.put(DB.PHARMACY_CONTACT_PERSON, pharmacy.getPharmacy_contact_person());
                        contentValues.put(DB.PHARMACY_PHONE, pharmacy.getPharmacy_phone());

                        mContentResolver.insert(PharmacyProvider.CONTENT_URI, contentValues);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pharmacy>> call, Throwable t) {
               Log.e("Download", t.getMessage());
            }
        });
    }

    public void downloadFAQs(){
        Retrofit retrofit = RetrofitHelper.getInstance().createHelper();
        FAQService faqService = retrofit.create(FAQService.class);
        Call<List<FAQ>> faqCall = faqService.get();

        faqCall.enqueue(new Callback<List<FAQ>>() {
            @Override
            public void onResponse(Call<List<FAQ>> call, Response<List<FAQ>> response) {
                List<FAQ> faqs = response.body();
                if (faqs.size() > 0) {
                    mContentResolver.delete(FAQProvider.CONTENT_URI, null, null);
                    for (FAQ faq :
                            faqs) {
                        ContentValues contentValues = new ContentValues();

                        contentValues.put(DB.FAQ_ID, faq.getId());
                        contentValues.put(DB.FAQ_QUESTION, faq.getQuestion());
                        contentValues.put(DB.FAQ_ANSWER, faq.getAnswer());
                        contentValues.put(DB.FAQ_TYPE, faq.getType());

                        mContentResolver.insert(FAQProvider.CONTENT_URI, contentValues);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FAQ>> call, Throwable t) {
                Log.e("Download", t.getMessage());
            }
        });
    }

    class getContent extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Retrofit retrofit = RetrofitHelper.getInstance().createHelper();

                CountyService countyService = retrofit.create(CountyService.class);


                Call<List<County>> countyCall = countyService.get();


                publishProgress("Getting county list");
                List<County> counties = countyCall.execute().body();

                db.addCounties(counties);

                return true;
            }catch (IOException ex){
                System.out.println(ex.getMessage());
                Log.e("Splash", ex.toString());
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            System.out.println("Preparing data...");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
//            progressBar.setVisibility(View.GONE);
//            imgStatus.setVisibility(View.VISIBLE);
            if (aBoolean) {
                openNextPage();
//                txtProgress.setText("Download Complete");
//                imgStatus.setImageResource(R.drawable.ic_checked);
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Do something after 5s = 5000ms
//
//                    }
//                }, 2000);
            }else{
                Log.e("Download", "There was an error downloading data");
                Toast.makeText(SplashActivity.this, "There was an error downloading data", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
//            txtProgress.setText(values[0]);
        }
    }
}
