package ke.co.besure.besure.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ke.co.besure.besure.R;

public class ConductTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conduct_test);

        initComponent();
    }

    private void initComponent(){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        TextView title = myToolbar.findViewById(R.id.toolbar_title);

        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
