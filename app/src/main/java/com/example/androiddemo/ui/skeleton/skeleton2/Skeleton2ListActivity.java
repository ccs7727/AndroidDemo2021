package com.example.androiddemo.ui.skeleton.skeleton2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddemo.R;

import java.util.ArrayList;

public class Skeleton2ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GradientXmlAdapter gradientXmlAdapter;
    private ArrayList<DataObject> dataObjects = new ArrayList<DataObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_xml);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        gradientXmlAdapter = new GradientXmlAdapter(getApplicationContext(), dataObjects, recyclerView, () -> recyclerView.setAdapter(gradientXmlAdapter));
        recyclerView.postDelayed(() -> {
            dataObjects = new GeneratesDataFake().generateDataFake();
            gradientXmlAdapter.addMoreDataAndSkeletonFinish(dataObjects);
        }, 3000);
    }

}
