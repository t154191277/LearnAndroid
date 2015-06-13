package wmlove.learnandroid.AsyncLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2015/6/13.
 */
public class ImageLoader {
    private ImageView mImageView;
    private String mUrl;
    private LruCache<String,Bitmap> mCache;


    public ImageLoader() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/4;
        mCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void addBitmapToCache(String url,Bitmap bitmap){
        if(mCache.get(url)!=null){
            mCache.put(url,bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String url){
        return mCache.get(url);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mImageView.getTag().equals(mUrl)){
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
    public void showImageByThread(final ImageView imageView, final String url){
        mImageView = imageView;
        mUrl = url;
        new Thread(){
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromURL(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }.start();
    }
    public Bitmap getBitmapFromURL(String urlString){
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void showImageByAsyncTask(ImageView imageView,String url){
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap==null){
            new NewsAsyncTask(imageView,url).execute(url);
        }else{
            imageView.setImageBitmap(bitmap);
        }

    }

    private class NewsAsyncTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView imageView;
        private String url;
        public NewsAsyncTask(ImageView imageView,String url) {
            this.imageView = imageView;
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = getBitmapFromCache(strings[0]);
            if(bitmap!=null){
                addBitmapToCache(url,bitmap);
            }else{
                bitmap = getBitmapFromURL(strings[0]);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.i("TAG","bitmap"+bitmap);
            if (imageView.getTag().equals(url)){
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
