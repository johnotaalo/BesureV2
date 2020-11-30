package ke.co.besure.besure.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.adapter.ConductTestAdapter;
import ke.co.besure.besure.config.Constants;
import ke.co.besure.besure.model.ConductTestModel;

import ke.co.besure.besure.R;
import ke.co.besure.besure.utils.Tools;
import ke.co.besure.besure.utils.ViewAnimation;

public class ConductTest extends AppCompatActivity {
    RecyclerView stepsRecyclerView;
    CardView instiVideo, oraquickEnglishVideo, oraquickSwahiliVideo;
    ImageView imgInsti, imgOraquikEnglish, imgOraquickSwahili;

    ImageButton btnExpand;

    LinearLayout lytWhatNext, expandLayout;

    boolean whatnextexpanded = false;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conduct_test);

        type = getIntent().getStringExtra("type");

        initComponent();
    }

    private void initComponent(){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        TextView title = myToolbar.findViewById(R.id.toolbar_title);

        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        instiVideo = findViewById(R.id.instiVideo);
        oraquickEnglishVideo = findViewById(R.id.oraquickEnglish);
        oraquickSwahiliVideo = findViewById(R.id.oraquickSwahili);
        stepsRecyclerView = findViewById(R.id.steps);
        imgInsti = findViewById(R.id.instiImage);
        imgOraquikEnglish = findViewById(R.id.oraquickEnglishImage);
        imgOraquickSwahili = findViewById(R.id.oraquickSwahiliImage);
        lytWhatNext = findViewById(R.id.lyt_what_next);
        btnExpand = findViewById(R.id.bt_expand);
        expandLayout = findViewById(R.id.lyt_expand);

        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stepsRecyclerView.setHasFixedSize(true);

        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayoutExpand(!whatnextexpanded, v, expandLayout);
                whatnextexpanded = show;
                if(show){
                    expandLayout.setVisibility(View.VISIBLE);
                } else {
                    expandLayout.setVisibility(View.GONE);
                }
            }
        });

        ConductTestAdapter adapter = new ConductTestAdapter(this, getInstructions());
        stepsRecyclerView.setAdapter(adapter);

        if (type.equals("insti")){
            instiVideo.setVisibility(View.VISIBLE);
            oraquickEnglishVideo.setVisibility(View.GONE);
            oraquickSwahiliVideo.setVisibility(View.GONE);

            instiVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(String.format("https://www.youtube.com/watch?v=%s", Constants.instiVidoID)));
                    startActivity(intent);
                }
            });

            Glide.with(this)
                    .load(String.format(Constants.youtubeThumbnailTemplate, Constants.instiVidoID))
                    .into(imgInsti);
        }else{
            instiVideo.setVisibility(View.GONE);
            oraquickEnglishVideo.setVisibility(View.VISIBLE);
            oraquickSwahiliVideo.setVisibility(View.VISIBLE);

            oraquickEnglishVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(String.format("https://www.youtube.com/watch?v=%s", Constants.oraquickVideoID)));
                    startActivity(intent);
                }
            });

            oraquickSwahiliVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(String.format("https://www.youtube.com/watch?v=%s", Constants.oraquickSwahiliID)));
                    startActivity(intent);
                }
            });

            Glide.with(this)
                    .load(String.format(Constants.youtubeThumbnailTemplate, Constants.oraquickVideoID))
                    .into(imgOraquikEnglish);

            Glide.with(this)
                    .load(String.format(Constants.youtubeThumbnailTemplate, Constants.oraquickSwahiliID))
                    .into(imgOraquickSwahili);
        }
    }

    private List<ConductTestModel> getInstructions(){
        List<ConductTestModel> instructions = new ArrayList<>();

        if (type.equals("insti")){
            instructions.add(new ConductTestModel("Preparation", "1. open test device pouch\n" +
                    "2.plce test device on flat surface\n" +
                    "3. remove cap of bottle 1 and place on flat surface"));
            instructions.add(new ConductTestModel("Collect Blood", "1. Twist and pull out lancet tip\n" +
                    "2. Rub finger and hand to increase blood flow\n" +
                    "3. Place lancet on the side of fingertip and press hard till you hear a clicking sound\n" +
                    "4. Rub finger to create large drop of blood\n" +
                    "5. Let drop fall into bottle 1 then close cap\n" +
                    "6. Apply band aid"));
            instructions.add(new ConductTestModel("Test", "1. Shake and pour all liquid from bottle 1, wait until liquid disappears\n" +
                    "2. Shake and pour all liquid from bottle 2, wait until liquid disappears\n" +
                    "3. Shake and pour all liquid from bottle 3, wait until liquid disappears"));
            instructions.add(new ConductTestModel("Read Result", "Negative : One dot means your test result is Negative\n" +
                    "Positive : Two dots means your test is positive. You are probably positive. Positive results must be confirmed by doctor\n" +
                    "Invalid: No dot."));
            instructions.add(new ConductTestModel("Disposal", "Put all items back into outer packaging and throw away into waste bin"));
        }else{
            instructions.add(new ConductTestModel("Preparation", "1. Ensure prior to testing you have not had anything to eat,\n" +
                    "drink or has chewed gum for at least 15 minutes.\n" +
                    "2. Please wait for at least 30 minutes prior to testing if you have used any\n" +
                    "oral care products."));
            instructions.add(new ConductTestModel("Collect", "1.Place the Flat Pad above the teeth against the outer gum.\n" +
                    "2.Gently swab completely around the outer gums, both upper and lower, one time around, using the Flat\n" +
                    "Pad\n" +
                    "** DO NOT swab the roof of the mouth, the inside of the cheek or the tongue.\n" +
                    "**Both sides of the Flat Pad may be used during this procedure"));
            instructions.add(new ConductTestModel("Test", "1. Insert the Flat Pad of the Device all the way into the Vial\n" +
                    "2. Make sure that the Flat Pad touches the bottom of the Vial.\n" +
                    "3.The Result Window on the Device should be facing towards you\n" +
                    "4.Start timing the test\n" +
                    "**DO NOT remove the Device from the Vial while the test is running.\n" +
                    "**Pink fluid will appear and travel up the Result Window. The pink fluid will gradually disappear as the test develops"));
            instructions.add(new ConductTestModel("Read Result", "1.Read the results after 20 minutes but not more than 40 minutes in a fully lighted area\n" +
                    "Negative : A reddish-purple line appears next to the triangle labeled “C”, and NO line appears next to the triangle labeled “T”.\n" +
                    "Positive: A reddish-purple line appears next to the triangle labeled “C” and a reddish-purple line appears next to the triangle labeled “T”. One of these lines may be darker than the other.\n" +
                    "Invalid:\n" +
                    "NO reddish-purple line appears next to the triangle labeled “C” or\n" +
                    "• a red background in the Result Window makes it difficult to read the result after 20 minutes or\n" +
                    "• if any of the lines are NOT inside the “C” or “T” triangle areas or\n" +
                    "• any partial line on one side of the “C” or “T” triangle areas (\n" +
                    "** Refer to the Test Result and Interpretation of Test Result section in this package insert" +
                    "A Negative Result\n" +
                    "\n" +
                    "As with many tests, there is a chance for false results. To reduce the chance of false results, be sure to follow the\n" +
                    "instructions and use the test correctly. If you have a negative result but you were involved in an HIV-risk activity\n" +
                    "in the past 3 months, you could be in what is called the “window period” and it is recommended to repeat\n" +
                    "\n" +
                    "testing at a later date.\n" +
                    "A Positive Result\n" +
                    "\n" +
                    "Consult a doctor as soon as possible and inform him/her that you have performed a self test for HIV. All positive\n" +
                    "\n" +
                    "results must be confirmed by a laboratory test.\n" +
                    "What Next After A Positive Result?\n" +
                    "\n" +
                    "Having HIV does not mean you have AIDS. With early diagnosis and treatment, it is unlikely that you will develop\n" +
                    "AIDS."));
            instructions.add(new ConductTestModel("Disposal", "Put all items back into outer packaging and throw away into waste bin"));
        }
        return instructions;
    }

    private boolean toggleLayoutExpand(boolean show, View view, View lyt_expand) {
        Tools.toggleArrow(show, view);
        if (show) {
            ViewAnimation.expand(lyt_expand);
        } else {
            ViewAnimation.collapse(lyt_expand);
        }
        return show;
    }
}
