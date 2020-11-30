package ke.co.besure.besure.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ke.co.besure.besure.R;
import ke.co.besure.besure.config.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView pharmacyButton;
    CardView conductingTestButton, referralButton, callCounsellorBtn, prepBtn, faqsBtn, abstainBtn, useCondomBtn, otherResourcesBtn, healthyLivingBtn;
    LinearLayout hivSelfTestingBtn, reportBtn, hivBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        conductingTestButton = findViewById(R.id.conductingTestBtn);
        pharmacyButton = findViewById(R.id.pharmacyBtn);
        referralButton = findViewById(R.id.referralBtn);
        hivSelfTestingBtn = findViewById(R.id.hivSelfTestingBtn);
        callCounsellorBtn = findViewById(R.id.callCounsellorBtn);
        prepBtn = findViewById(R.id.prepBtn);
        faqsBtn = findViewById(R.id.faqsBtn);
//        abstainBtn = findViewById(R.id.abstainBtn);
        useCondomBtn = findViewById(R.id.useCondomBtn);
        otherResourcesBtn = findViewById(R.id.otherResourcesBtn);
//        reportBtn = findViewById(R.id.report);
        hivBtn = findViewById(R.id.hivBtn);
        healthyLivingBtn = findViewById(R.id.healthyLivingBtn);

        pharmacyButton.setOnClickListener(this);
        referralButton.setOnClickListener(this);
        hivSelfTestingBtn.setOnClickListener(this);
        callCounsellorBtn.setOnClickListener(this);
        prepBtn.setOnClickListener(this);
        faqsBtn.setOnClickListener(this);
        conductingTestButton.setOnClickListener(this);
//        abstainBtn.setOnClickListener(this);
        useCondomBtn.setOnClickListener(this);
        otherResourcesBtn.setOnClickListener(this);
//        reportBtn.setOnClickListener(this);
        hivBtn.setOnClickListener(this);
        healthyLivingBtn.setOnClickListener(this);

        TextView title = myToolbar.findViewById(R.id.toolbar_title);

        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pharmacyBtn:
                startActivity(new Intent(MainActivity.this, PharmacyActivity.class));
                break;

            case R.id.referralBtn:
                startActivity(new Intent(MainActivity.this, ReferralsActivity.class));
                break;

            case R.id.hivSelfTestingBtn:
                selftestDialog();
                break;

            case R.id.callCounsellorBtn:
                counsellorDialog();
                break;

            case R.id.faqsBtn:
                Intent faqIntent = new Intent(MainActivity.this, FAQActivity.class);
                faqIntent.putExtra("type", "other");
                startActivity(faqIntent);
                break;

            case R.id.prepBtn:
                Intent prepIntent = new Intent(MainActivity.this, FAQActivity.class);
                prepIntent.putExtra("type", "prep");
                startActivity(prepIntent);
                break;

            case R.id.conductingTestBtn:
//                startActivity(new Intent(MainActivity.this, ConductTest.class));
                openConductTestDialog();
                break;

//            case R.id.abstainBtn:
//                abstainDialog();
//                break;

            case R.id.useCondomBtn:
                condomDialog();
                break;

            case R.id.otherResourcesBtn:
                startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
                break;

//            case R.id.report:
//                startActivity(new Intent(this, ReportProblemActivity.class));
//                break;

            case R.id.hivBtn:
                hivDialog();
                break;

            case R.id.healthyLivingBtn:
                startActivity(new Intent(MainActivity.this, HealthLivingActivity.class));
                break;
        }
    }

    private void selftestDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_info);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faqIntent = new Intent(MainActivity.this, FAQActivity.class);
                faqIntent.putExtra("type", "other");
                startActivity(faqIntent);
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void commentDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_comment);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


//        ((AppCompatImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void abstainDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_abstain);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void counsellorDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_light);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.bt_call)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCaller();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.bt_whatsapp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsapp();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void openConductTestDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.conduct_test_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final Intent intent = new Intent(this, ConductTest.class);

        ((AppCompatImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.readmoreInsti)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type", "insti");
                startActivity(intent);
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.readmoreOraquick)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type", "oraquick");
                startActivity(intent);
            }
        });

        ImageView instiImageView = dialog.findViewById(R.id.insti);
        ImageView oraquickImageView = dialog.findViewById(R.id.oraquick);

        Glide.with(this)
                .load(Constants.instiImageUrl)
                .into(instiImageView);

        Glide.with(this)
                .load(Constants.oraquickImageUrl)
                .into(oraquickImageView);

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void hivDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.hiv_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.getMore)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void condomDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.condom_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.getMore)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CondomActivity.class));
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void openShare(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hey there, check out the BeSure app at: https://play.google.com/store/apps/details?id=ke.co.besure.besure");
        intent.setType("text/plain");
        startActivity(intent);
    }

    private void openCaller(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "1190"));
        startActivity(intent);
    }

    private void openWhatsapp(){
        boolean isAppInstalled = appInstalledOrNot("com.whatsapp");
        if(isAppInstalled) {
            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=254700121121");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Looks like you do not have WhatsApp installed.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_chat:
                commentDialog();
                break;
            case R.id.action_report:
                startActivity(new Intent(this, ReportProblemActivity.class));
                break;
            case R.id.action_share:
                openShare();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
