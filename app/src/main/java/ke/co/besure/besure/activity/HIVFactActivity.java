package ke.co.besure.besure.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.model.HIVFact;
import ke.co.besure.besure.provider.HIVFactProvider;

public class HIVFactActivity extends AppCompatActivity {
    WebView htmlContent;
    String section = "";
    DB mDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hivfact);



        mDB = new DB(this);
        section = getIntent().getStringExtra("section");
        htmlContent = findViewById(R.id.htmlContent);
        initToolbar();
        HIVFact fact = getFact();

        htmlContent.loadData(fact.getContent(), "text/html; charset=utf-8", "utf-8");
    }

    private void initToolbar(){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        TextView title = myToolbar.findViewById(R.id.toolbar_title);
//        title.setText("");
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private HIVFact getFact(){
        HIVFact fact = new HIVFact();

        Cursor cursor = this.getContentResolver().query(HIVFactProvider.CONTENT_URI, null, "section=?",new String[]{section}, null);

        if (cursor.moveToFirst()){
            fact = new HIVFact(cursor.getInt(cursor.getColumnIndex(DB.ID)), cursor.getString(cursor.getColumnIndex(mDB.SECTION_COLUMN)), cursor.getString(cursor.getColumnIndex(mDB.CONTENT_COLUMN)));
        }
        return fact;
    }
}
