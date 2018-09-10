package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.viewpager.CardConfig;
import com.example.dell.baseproject.viewpager.CardItemTouchHelperCallback;
import com.example.dell.baseproject.viewpager.CardLayoutManager;
import com.example.dell.baseproject.viewpager.OnSwipeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewpagerTwoActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_viewpager_two_activity)
    RecyclerView recyclerViewViewpagerTwoActivity;
    @BindView(R.id.activity_viewpager_two)
    RelativeLayout activityViewpagerTwo;

    private List<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_two);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        list.add(R.mipmap.icon_small_one);
        list.add(R.mipmap.icon_small_two);
        list.add(R.mipmap.icon_small_three);
        list.add(R.mipmap.icon_small_four);
        list.add(R.mipmap.icon_small_five);
        list.add(R.mipmap.icon_small_six);
        list.add(R.mipmap.icon_small_seven);
        list.add(R.mipmap.icon_small_eight);
        list.add(R.mipmap.icon_small_nine);
        list.add(R.mipmap.icon_small_ten);
        list.add(R.mipmap.icon_yu_one);
        list.add(R.mipmap.icon_yu_two);
        list.add(R.mipmap.icon_yu_three);
        list.add(R.mipmap.icon_yu_four);
        list.add(R.mipmap.icon_yu_five);
        list.add(R.mipmap.icon_yu_seven);
        list.add(R.mipmap.icon_yu_six);
        list.add(R.mipmap.icon_yu_eight);
        list.add(R.mipmap.icon_yu_nine);
        list.add(R.mipmap.icon_yu_ten);

        recyclerViewViewpagerTwoActivity.setItemAnimator(new DefaultItemAnimator());
        recyclerViewViewpagerTwoActivity.setAdapter(new MyAdapter());
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerViewViewpagerTwoActivity.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<Integer>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer o, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
                Toast.makeText(ViewpagerTwoActivity.this, direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(ViewpagerTwoActivity.this, "data clear", Toast.LENGTH_SHORT).show();
                recyclerViewViewpagerTwoActivity.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        recyclerViewViewpagerTwoActivity.getAdapter().notifyDataSetChanged();
                    }
                }, 3000L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerViewViewpagerTwoActivity, touchHelper);
        recyclerViewViewpagerTwoActivity.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerViewViewpagerTwoActivity);


    }



    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_viewpager_two_actiivty, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
            avatarImageView.setImageResource(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;
            ImageView likeImageView;
            ImageView dislikeImageView;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
            }

        }
    }


}
