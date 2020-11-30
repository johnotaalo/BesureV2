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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.R;
import ke.co.besure.besure.adapter.DosandDontsAdapter;
import ke.co.besure.besure.model.DosandDonts;

public class CondomActivity extends AppCompatActivity {
    CardView video, dos, donts;
    ImageView videoImage;
    RecyclerView dosanddontsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condom);

        initComponent();
    }

    private void initComponent(){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        TextView title = myToolbar.findViewById(R.id.toolbar_title);

        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        video = findViewById(R.id.video1);
        dos = findViewById(R.id.dos);
        donts = findViewById(R.id.donts);
        videoImage = findViewById(R.id.image_1);
        dosanddontsRecyclerView = findViewById(R.id.dosanddonts);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=oaLdNErJ-Fk"));
                startActivity(intent);
            }
        });

        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        donts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dosanddontsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dosanddontsRecyclerView.setHasFixedSize(true);

        DosandDontsAdapter adapter = new DosandDontsAdapter(this, generateDosandDontsList());
        dosanddontsRecyclerView.setAdapter(adapter);

        Glide.with(this)
                .load(String.format("https://img.youtube.com/vi/%s/0.jpg", "oaLdNErJ-Fk"))
                .into(videoImage);
    }

    private List<DosandDonts> generateDosandDontsList(){
        List<DosandDonts> dosandDontsList = new ArrayList<>();

        dosandDontsList.add(new DosandDonts("DOs", "- Always check the expiry date - yes, condoms have them too - as they tend to become dry, brittle and weakened with age.\n" +
                "-  Before unrolling a condom onto your erect penis, carefully squeeze the tip between your thumb and index fingers (watch those sharp fingernails though) to allow for enough space to catch the semen and prevent air from getting trapped.\n" +
                "-Be careful with body piercings and make sure they don’t puncture or damage the condom.\n" +
                " - Put the condom on your erect penis before any sexual contact with your partner.\n" +
                " - Find a condom that fits you. If it’s too small, it’s probably constantly near breaking point and if it is too big, chances are it will slip off in mid-action. Try on different makes and models to find out which works best for you.\n" +
                "-   Use a new condom every time. This is one instance where recycling is not advisable.\n" +
                "  - Only ever use  water based lubricants, such as KY Jelly, both on the inside and the outside of condoms. Extra lubricant is a good idea since it helps to stop condoms from getting too dry and tearing.\n" +
                " - To prevent spillage after sex, hold the condom in place at the base of the penis as you withdraw.\n" +
                "-    Wrap used condoms in a tissue and throw them into a trash bin. Nobody enjoys stepping onto a gooey, used condom.\n" +
                "-Store your condoms in a dry, cool place, but not in your fridge – that’s too cold.\n" +
                "-Do use condoms together with other contraceptive methods such as the pill or a diaphragm. "));
        dosandDontsList.add(new DosandDonts("DONTs", "- Never use condoms that are past their expiration date, look damaged, feel sticky or just don’t seem quite right.\n" +
                "-Don’t use animal-skin condoms. They don’t protect against STDs. If you are allergic to conventional latex condoms, use ones made out of polyurethane, a type of plastic.\n" +
                "-Never expose condoms to direct heat (e.g. your back pocket or your car’s glove box) or light (e.g. a hot lamp or direct sunlight) and don’t keep them near hard objects (e.g. coins in your wallet, keys in your pocket, or nail clippers in your toiletries bag).\n" +
                "-Don’t use your teeth, fingernails, scissors, or any other sharp objects to open condom wrappers and make sure you do it in sufficient light to be able to see that the condom isn’t damaged while you remove it from the wrapper and put it on.\n" +
                "- Never use lubricants that contain oils, fat or grease such as petroleum jelly (Vaseline®), baby oil, hand or body lotions and creams, cooking oil, massaging lotion or sun cream. They can damage condoms by creating tiny holes and tears.\n" +
                "-Don’t unroll the condom or blow air into it before putting it on – it will no longer be reliable.\n" +
                "- Don’t use more than one condom on your penis at the same time.\n" +
                "-Don’t use male and female condoms at the same time – they may stick to each other and slip off.\n" +
                "- Don’t flush condoms down the toilet. They have a nasty habit of clogging up pipes and ending up on our beaches."));

        return dosandDontsList;
    }
}
