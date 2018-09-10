package com.example.dell.baseproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.dell.baseproject.R;

import java.util.Vector;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/9/8.
 * 邮箱：123123@163.com
 */

public class ImageAdapter extends BaseAdapter {


    private Context mContext;                     // 定义Context
    private Vector<Integer> mImageIds = new Vector<Integer>();    // 定义一个向量作为图片源
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();    // 定义一个向量作为选中与否容器

    private int lastPosition = -1;            //记录上一次选中的图片位置，-1表示未选中任何图片
    private boolean multiChoose;                //表示当前适配器是否允许多选

    public ImageAdapter(Context c, boolean isMulti){
        mContext = c;
        multiChoose = isMulti;

        // 装入资源
        mImageIds.add(R.mipmap.ic_launcher);
        mImageIds.add(R.mipmap.ic_launcher);
        mImageIds.add(R.mipmap.ic_launcher);
        mImageIds.add(R.mipmap.ic_launcher);
        mImageIds.add(R.mipmap.ic_launcher);
        for(int i=0; i<5; i++)
            mImage_bs.add(false);
    }


    @Override
    public int getCount() {
        return mImageIds.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(mContext);                // 给ImageView设置资源
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));     // 设置布局图片
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);         // 设置显示比例类型
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageDrawable(makeBmp(mImageIds.elementAt(position),
                mImage_bs.elementAt(position)));

        return imageView;
    }

    private LayerDrawable makeBmp(int id, boolean isChosen){
        Bitmap mainBmp = ((BitmapDrawable)mContext.getResources().getDrawable(id)).getBitmap();

        // 根据isChosen来选取对勾的图片
        Bitmap seletedBmp;
        if(isChosen == true)
            seletedBmp = BitmapFactory.decodeResource(mContext.getResources(),
                    R.mipmap.delete);
        else
            seletedBmp = BitmapFactory.decodeResource(mContext.getResources(),
                    R.mipmap.icon_back);

        // 产生叠加图
        Drawable[] array = new Drawable[2];
        array[0] = new BitmapDrawable(mainBmp);
        array[1] = new BitmapDrawable(seletedBmp);
        LayerDrawable la = new LayerDrawable(array);
        la.setLayerInset(0, 0, 0, 0, 0);
        la.setLayerInset(1, 0, -5, 60, 45 );

        return la;    //返回叠加后的图
    }

    // 修改选中的状态
    public void changeState(int position){
        // 多选时
        if(multiChoose == true){
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position);     //直接取反即可
        }
        // 单选时
        else{
            if(lastPosition != -1)
                mImage_bs.setElementAt(false, lastPosition);        //取消上一次的选中状态
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position);     //直接取反即可
            lastPosition = position;                //记录本次选中的位置
        }
        notifyDataSetChanged();         //通知适配器进行更新
    }
}
