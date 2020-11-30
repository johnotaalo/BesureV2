package ke.co.besure.besure.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.R;
import ke.co.besure.besure.activity.HIVFactActivity;
import ke.co.besure.besure.adapter.VideoAdapter;
import ke.co.besure.besure.model.HIVFact;
import ke.co.besure.besure.model.Video;
import ke.co.besure.besure.provider.HIVFactProvider;


public class HIVFactsFragment extends Fragment implements View.OnClickListener {

    private DB mDB;

    CardView testingBtn, disclosureBtn, treatmentBtn, preventionBtn, hivTbBtn, hivFamilyBtn, supportGroupsBtn;

    HIVFact fact;

    public HIVFactsFragment() {
    }

    public static HIVFactsFragment newInstance() {
        HIVFactsFragment fragment = new HIVFactsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hivfacts, container, false);
        mDB = new DB(getContext());

        testingBtn = root.findViewById(R.id.testingBtn);
        disclosureBtn = root.findViewById(R.id.disclosureBtn);
        treatmentBtn = root.findViewById(R.id.treatmentBtn);
        preventionBtn = root.findViewById(R.id.preventionBtn);
        hivTbBtn = root.findViewById(R.id.hivTb);
        hivFamilyBtn = root.findViewById(R.id.hivFamilyBtn);
        supportGroupsBtn = root.findViewById(R.id.supportGroupsBtn);

        testingBtn.setOnClickListener(this);
        disclosureBtn.setOnClickListener(this);
        treatmentBtn.setOnClickListener(this);
        preventionBtn.setOnClickListener(this);
        hivTbBtn.setOnClickListener(this);
        hivFamilyBtn.setOnClickListener(this);
        supportGroupsBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), HIVFactActivity.class);
        String section = "testing";
        switch (v.getId()){
            case R.id.testingBtn:
                section = "testing";
                break;
            case R.id.disclosureBtn:
                section = "disclosure";
                break;
            case R.id.treatmentBtn:
                section = "treatment";
                break;
            case R.id.preventionBtn:
                section = "prevention";
                break;
            case R.id.hivTb:
                section = "hiv-tb";
                break;
            case R.id.hivFamilyBtn:
                section = "hiv-family";
                break;
            case R.id.supportGroupsBtn:
                section = "support-group";
                break;

        }

        intent.putExtra("section", section);
        startActivity(intent);
    }
}