package ke.co.besure.besure.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.R;
import ke.co.besure.besure.adapter.DocumentAdapter;
import ke.co.besure.besure.model.Document;

public class DocumentResourceFragment extends Fragment {

    public DocumentResourceFragment() {
    }

    public static DocumentResourceFragment newInstance() {
        DocumentResourceFragment fragment = new DocumentResourceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_document_resource, container, false);

        List<Document> documentList = new ArrayList<>();

        documentList.add(new Document("OraQuick Instructions Manual", "https://trello-attachments.s3.amazonaws.com/5aed80731d22b3f274caf5a6/5baefac11076a239044ecba9/305c62d6cba87493a6bacbeafdb7ede8/Oraquick_instructions.pdf"));
        documentList.add(new Document("Facts about HIV Self Test", "https://trello-attachments.s3.amazonaws.com/5aed80731d22b3f274caf5a6/5baefac11076a239044ecba9/2b58814b229347c53883d6c47605d452/Facts_about_HIVST.pptx"));
        documentList.add(new Document("Insti Kit Instructions", "https://trello-attachments.s3.amazonaws.com/5aed80731d22b3f274caf5a6/5baefac11076a239044ecba9/fdebc6c26a16930080c5dba535baeef3/insti_kits_instructions.pdf"));
        documentList.add(new Document("PrEP Booklet 2017", "https://trello-attachments.s3.amazonaws.com/5aed80731d22b3f274caf5a6/5baefac11076a239044ecba9/290b1e126f164e4ce42ab58f81a45ac6/PrEP_BOOKLET_2017_REVISED.pdf"));
        documentList.add(new Document("HTS Guideline 2015", "https://drive.google.com/open?id=1_Dr7o8Lkt8Lw5z2oJZR9yKXUEQUx2RwQ"));
        documentList.add(new Document("HTS Testing Algorithm", "https://drive.google.com/open?id=1G-9SVuIrrStPfsHgodLCqj1xOjTNRfhe"));
        documentList.add(new Document("Kenya ARV Guideline", "https://drive.google.com/open?id=1rmaMVOosAYsqHeqkt2JtP6mxyehvWllq"));
        documentList.add(new Document("Kenya HIV Self Testing and Assisted Partner Notification Services", "https://drive.google.com/open?id=1javRP7cLZ1S4PWtDpun8nSQ75OB2SWm1"));
        documentList.add(new Document("NASCOP Self Test Bronchure June 2018", "https://drive.google.com/open?id=1qQw5XWJaFUJMnEzqzrfyExsflbHhPHZj"));

        RecyclerView documentRecyclerView = root.findViewById(R.id.documentList);

        documentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        documentRecyclerView.setHasFixedSize(true);

        DocumentAdapter adapter = new DocumentAdapter(getContext(), documentList);
        documentRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DocumentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Document obj, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(obj.getLink()));
                startActivity(intent);
            }
        });

        return root;
    }
}
