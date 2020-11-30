package ke.co.besure.besure.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import ke.co.besure.besure.R;
import ke.co.besure.besure.config.Constants;

public class OnboardingActivity extends AppCompatActivity {
    LinearLayout startButton, progressLayout;

    ImageView posterImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        startButton = findViewById(R.id.start);
        posterImage = findViewById(R.id.posterImage);
        progressLayout = findViewById(R.id.progressLayout);

        Glide.with(this)
                .load(Constants.firstGirl)
                .into(posterImage);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
            }
        });
    }
}
