package com.example.exp_7;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    ImageButton btnNext, btnPrevious;

    int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        TabLayout tabLayout = findViewById(R.id.tabIndicator);

        ImageSliderAdapter adapter = new ImageSliderAdapter(images);

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {}).attach();

        btnNext.setOnClickListener(v ->
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1));

        btnPrevious.setOnClickListener(v ->
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1));
    }
}