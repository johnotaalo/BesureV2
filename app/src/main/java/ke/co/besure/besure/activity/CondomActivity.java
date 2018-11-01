package ke.co.besure.besure.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ke.co.besure.besure.R;

public class CondomActivity extends AppCompatActivity {
    CardView video, dos, donts;
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
        dos = findViewById(R.id.donts);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=oaLdNErJ-Fk"));
                startActivity(intent);
            }
        });
    }
}
