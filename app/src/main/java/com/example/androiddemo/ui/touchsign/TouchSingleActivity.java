package com.example.androiddemo.ui.touchsign;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.androiddemo.R;

import java.io.File;

public class TouchSingleActivity extends AppCompatActivity {
    private GestureSignatureView gSignature;
    private ImageView singImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_single);
        try {
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onClick(View view) {
        try {
            int i = view.getId();
            if (i == R.id.signSave_tv_cancel) {
                finish();
            } else if (i == R.id.signSave_tv_clear) {
                gSignature.clear();
                singImg.setImageDrawable(null);
            } else if (i == R.id.signSave_tv_save) {
                if (!gSignature.getTouched()) {
                    Toast.makeText(this, "您尚未签字", Toast.LENGTH_SHORT).show();
                    return;
                }
                File file = new File(ConstantsUtil.IMG_FOLDER_PATH);
                file.mkdirs();
                String fillPath = ConstantsUtil.IMG_FOLDER_PATH + "signImg" + System.currentTimeMillis() + ".jpg";
                Log.i("w--", fillPath);
                gSignature.save(fillPath);

                Glide.with(this)
                        .load(fillPath)
                        .into(singImg);

                Toast.makeText(this, "签字成功", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        gSignature = findViewById(R.id.signSave_gsv_signature);
        singImg = findViewById(R.id.singImg);
        ConstantsUtil.FilePath.getInstance(this);
    }


}