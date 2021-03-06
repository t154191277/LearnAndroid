package wmlove.learnandroid.AsyncLoad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wmlove.learnandroid.R;

/**
 * Created by Administrator on 2015/6/12.
 */
public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener{
    private List<NewsBean> mNewsBeanList;
    private LayoutInflater mLayoutInflater;
    public static List<String> urls;
    private ImageLoader mImageLoader;
    private int mStart,mEnd;
    private boolean isFirst;

    public NewsAdapter(List<NewsBean> mNewsBeanList,Context context,ListView listView) {
        this.mNewsBeanList = mNewsBeanList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.urls = AsyncUtils.getUrls(mNewsBeanList);
        this.mImageLoader = new ImageLoader(listView);
        isFirst = true;
        listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return mNewsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(convertview==null){
            convertview = mLayoutInflater.inflate(R.layout.item_news,null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertview.findViewById(R.id.tvTitle);
            viewHolder.tvContent = (TextView) convertview.findViewById(R.id.tvContent);
            viewHolder.ivIcon = (ImageView) convertview.findViewById(R.id.ivIcon);
            convertview.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertview.getTag();
        }
        viewHolder.tvTitle.setText(mNewsBeanList.get(position).name);
        viewHolder.tvContent.setText(mNewsBeanList.get(position).description);
        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        viewHolder.ivIcon.setTag(urls.get(position));
        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon, urls.get(position));
        return convertview;
    }



    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        if(scrollState == SCROLL_STATE_IDLE){
            mImageLoader.loadImage(mStart,mEnd);
        }else{
            mImageLoader.cacheAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int start, int count, int totalcount) {
        mStart = start;
        mEnd = start + count;
        if(isFirst && count>0){
            mImageLoader.loadImage(mStart,mEnd);
            isFirst = false;
        }
    }

    class ViewHolder{
        public TextView tvTitle,tvContent;
        public ImageView ivIcon;
    }
}
