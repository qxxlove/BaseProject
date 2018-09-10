
package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.FilerAdapter;
import com.example.dell.baseproject.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchViewActivity extends AppCompatActivity {


    ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

    ArrayList<String> mListTitle = new ArrayList<String>();
    ArrayList<String> mListText = new ArrayList<String>();
    SimpleAdapter setAdapter ;
    private EditText edittext;
    private ImageView image_delete;
    private ListView listview;

    private  FilerAdapter adapter;


    Handler myhandler = new Handler(Looper.getMainLooper());

    Runnable eChanged = new Runnable() {

        @Override
        public void run() {

            String data = edittext.getText().toString();

            mData.clear();

            getmDataSub(mData, data);

            setAdapter.notifyDataSetChanged();

        }
    };

    Runnable oldData = new Runnable() {

        @Override
        public void run() {

            mData.clear();
            getmData(mData);
            setAdapter.notifyDataSetChanged();

        }
    };
    private TextView text_notice;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        initView();
        initData();
        initClick();
    }


    private void initClick() {
        edittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                //这个应该是在改变的时候会做的动作吧，具体还没用到过。
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
                //这是文本框改变之前会执行的动作
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                /**这是文本框改变之后 会执行的动作
                 * 因为我们要做的就是，在文本框改变的同时，我们的listview的数据也进行相应的变动，并且如一的显示在界面上。
                 * 所以这里我们就需要加上数据的修改的动作了。
                 */
                if(s.length() == 0){
                    image_delete.setVisibility(View.GONE);//当文本框为空时，则叉叉消失

                    List<User> list = new ArrayList<>();

                    list.add(new User("啊","发"));
                    list.add(new User("吧","的"));
                    list.add(new User("此","地方"));
                    list.add(new User("的","个"));
                    list.add(new User("犯","吧"));

                    adapter = new FilerAdapter(SearchViewActivity.this,list);
                    adapter.notifyDataSetChanged();
                    listview.setAdapter(adapter);
                    text_notice.setVisibility(View.GONE);
                    // myhandler.post(oldData);
                }
                else {
                    image_delete.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
                    //myhandler.post(eChanged);
                    adapter.getFilter().filter(s);


                }
            }
        });
        image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //      edittext.setText("");
               /* if (mData.size() == 0) {
                    myhandler.post(oldData);
                }*/
            }
        });

    }

    private void initData() {
      //  getmData(mData);
       // setAdapter = new SimpleAdapter(this,mData,android.R.layout.simple_list_item_2,
        //        new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
        List<User> list = new ArrayList<>();

            list.add(new User("啊","发"));
            list.add(new User("吧","的"));
            list.add(new User("此","地方"));
            list.add(new User("的","个"));
            list.add(new User("犯","吧"));


         adapter = new FilerAdapter(this,list);
        listview.setAdapter(adapter);

    }

    private void initView() {
        edittext = ((EditText) findViewById(R.id.etSearch));
        image_delete = ((ImageView) findViewById(R.id.ivDeleteText));
        listview = ((ListView) findViewById(R.id.mListView));
        text_notice = ((TextView) findViewById(R.id.text_notice));
    }


    private void getmData(ArrayList<Map<String, Object>> mDatas)
    {
        Map<String, Object> item = new HashMap<String, Object>();
        mListTitle.add("This is a title!");
        mListText.add("this is a text.\n2014.09.18.16.33");

        item.put("title", mListTitle.get(0));
        item.put("text", mListText.get(0));
        mDatas.add(item);
        mListTitle.add("This is an another title!");
        mListText.add("this is an another text.\n2014.09.18.16.33");

        item = new HashMap<String, Object>();
        item.put("title", mListTitle.get(1));
        item.put("text", mListText.get(1));
        mDatas.add(item);
    }

    private void getmDataSub(ArrayList<Map<String, Object>> mDataSubs, String data)
    {
        int length = mListTitle.size();
        for(int i = 0; i < length; ++i){
            if(mListTitle.get(i).contains(data) || mListText.get(i).contains(data)){
                Map<String,Object> item = new HashMap<String,Object>();
                item.put("title", mListTitle.get(i));
                item.put("text",  mListText.get(i));
                mDataSubs.add(item);
            }
        }
    }


}
