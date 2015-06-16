package wmlove.learnandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

import wmlove.learnandroid.AsyncLoad.AsyncUtils;
import wmlove.learnandroid.AsyncLoad.ImageLoader;
import wmlove.learnandroid.AsyncLoad.NewsAdapter;
import wmlove.learnandroid.AsyncLoad.NewsBean;


public class MainActivity extends Activity {
    private NewsAdapter mNewsAdapter;
    private ListView mListView;
    private String URL = AsyncUtils.getURL();
    private NewsAsyncTask mNewsAsyncTask;
    private ImageLoader mImageLoader;
    private String [] urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNewsAsyncTask = new NewsAsyncTask();
        mNewsAsyncTask.execute(URL);
        mImageLoader = new ImageLoader();
        mListView = (ListView) findViewById(R.id.mListView);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int state) {
                if (state == SCROLL_STATE_IDLE) {
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int first, int count, int totalcount) {
            }
        });
    }

    class NewsAsyncTask extends AsyncTask<String,Void,List<NewsBean>>{

        @Override
        protected List<NewsBean> doInBackground(String... strings) {
            return AsyncUtils.getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeans) {
            super.onPostExecute(newsBeans);
            mNewsAdapter = new NewsAdapter(newsBeans,getApplicationContext());
            mListView.setAdapter(mNewsAdapter);
        }
    }
}
