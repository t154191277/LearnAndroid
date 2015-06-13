package wmlove.learnandroid.AsyncLoad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wmlove.learnandroid.R;

/**
 * Created by Administrator on 2015/6/12.
 */
public class NewsAdapter extends BaseAdapter{
    private List<NewsBean> mNewsBeanList;
    private LayoutInflater mLayoutInflater;
    private List<String> urls;
    private ImageLoader mImageLoader;

    public NewsAdapter(List<NewsBean> mNewsBeanList,Context context) {
        this.mNewsBeanList = mNewsBeanList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.urls = AsyncUtils.getUrls(mNewsBeanList);
        this.mImageLoader = new ImageLoader();
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
        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon, AsyncUtils.getURL());
        return convertview;
    }

    class ViewHolder{
        public TextView tvTitle,tvContent;
        public ImageView ivIcon;
    }
}
