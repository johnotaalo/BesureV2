package ke.co.besure.besure.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.R;
import ke.co.besure.besure.activity.ReproHealthActivity;
import ke.co.besure.besure.adapter.VideoAdapter;
import ke.co.besure.besure.model.Video;


public class ReproductiveHealthFragment extends Fragment implements View.OnClickListener {

    CardView anatomyPhysiologyBtn, familyPlanningBtn, sexSexualityBtn, tennagePregnancyBtn, sexualConcentBtn, genderViolenceBtn;

    public ReproductiveHealthFragment() {
    }

    public static ReproductiveHealthFragment newInstance() {
        ReproductiveHealthFragment fragment = new ReproductiveHealthFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reproductive_health, container, false);

        anatomyPhysiologyBtn = root.findViewById(R.id.anatomyPhysiologyBtn);
        familyPlanningBtn = root.findViewById(R.id.familyPlanningBtn);
        sexSexualityBtn = root.findViewById(R.id.sexSexualityBtn);
        tennagePregnancyBtn = root.findViewById(R.id.tennagePregnancyBtn);
        sexualConcentBtn = root.findViewById(R.id.sexualConcentBtn);
        genderViolenceBtn = root.findViewById(R.id.genderViolenceBtn);

        anatomyPhysiologyBtn.setOnClickListener(this);
        familyPlanningBtn.setOnClickListener(this);
        sexSexualityBtn.setOnClickListener(this);
        tennagePregnancyBtn.setOnClickListener(this);
        sexualConcentBtn.setOnClickListener(this);
        genderViolenceBtn.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), ReproHealthActivity.class);
        String section = "";
        switch (v.getId()){
            case R.id.anatomyPhysiologyBtn:
                section = "anatomy_and_physiology";
                break;
            case R.id.familyPlanningBtn:
                section = "family_planning";
                break;
            case R.id.sexSexualityBtn:
                section = "sex_and_sexuality";
                break;
            case R.id.tennagePregnancyBtn:
                section = "teenage_pregnancy";
                break;
            case R.id.sexualConcentBtn:
                section = "sexual_concent";
                break;
            case R.id.genderViolenceBtn:
                section = "gender_based_violence";
                break;
        }

        intent.putExtra("section", section);
        startActivity(intent);
    }
}