package com.wesuper.allstar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wesuper.allstar.R;
import com.wesuper.allstar.adapter.CardAdapter;
import com.wesuper.allstar.entity.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/31.
 */

public class HomeFragment extends Fragment{

    private String key;

    public HomeFragment() {
    }

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    int[] imgRes = { R.drawable.bg_monkey_king, R.drawable.bg_monkey_king , R.drawable.bg_monkey_king, R.drawable.bg_monkey_king};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            key = getArguments().getString("key");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_home, container, false);
//        TextView textView = view.findViewById(R.id.key);
//        textView.setText(key);

        viewPager = view.findViewById(R.id.view_page);
        viewPager.setPageMargin(60);
        pagerAdapter = new PagerAdapter() {

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                LinearLayout root = new LinearLayout(getContext());
                LinearLayout.LayoutParams root_layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                root.setLayoutParams(root_layoutParams);

                root.setOrientation(LinearLayout.VERTICAL);

                ImageView imageView = new ImageView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(dp2px(15), dp2px(5), dp2px(15), dp2px(5));
                imageView.setLayoutParams(layoutParams);
                Glide.with(imageView)
                        .load(imgRes[position])
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(40)))
                        .into(imageView);

                root.addView(imageView);
                container.addView(root);


                return root;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        };
        viewPager.setAdapter(pagerAdapter);
        Log.i("all_star", "当前位置");

        viewPager.setOffscreenPageLimit(3);

        ListView listView = view.findViewById(R.id.list_card);
        CardAdapter adapter = new CardAdapter(getContext());
        adapter.setData(getCards());

        listView.setAdapter(adapter);
        listView.setDivider(null);

        return view;
    }

    private List<Card> getCards(){
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            Card card = new Card();
            card.setText("文字");
            card.setVersion("v1.0.1");
            card.setTitle("标题");
            cards.add(card);
        }
        return cards;
    }

    private int dp2px(int dp){
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
