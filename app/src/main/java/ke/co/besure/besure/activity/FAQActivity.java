package ke.co.besure.besure.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.adapter.FAQAdapter;
import ke.co.besure.besure.model.FAQ;
import ke.co.besure.besure.provider.FAQProvider;

public class FAQActivity extends AppCompatActivity {
    RecyclerView faqRecycler;
    FAQAdapter adapter;

    String type;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
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

        faqRecycler = findViewById(R.id.faqs);
        faqRecycler.setLayoutManager(new LinearLayoutManager(this));
        faqRecycler.setHasFixedSize(true);

        List<FAQ> faqs = getFaqList(type);
        adapter = new FAQAdapter(this, faqs);
        faqRecycler.setAdapter(adapter);
    }

    private List<FAQ> getFaqList(String type){
        List<FAQ> faqList = new ArrayList<>();
        Cursor cursor = this.getContentResolver().query(FAQProvider.CONTENT_URI, null, "type=?",new String[]{type}, null);
        if (cursor.moveToFirst()){
            do{
                FAQ faq = new FAQ();

                faq.setQuestion(cursor.getString(cursor.getColumnIndex(DB.FAQ_QUESTION)));
                faq.setAnswer(cursor.getString(cursor.getColumnIndex(DB.FAQ_ANSWER)));

                faqList.add(faq);
            }while(cursor.moveToNext());
        }
        return faqList;
    }
}
