package wmlove.learnandroid.AsyncLoad;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/12.
 */
public class AsyncUtils {
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    public static String getURL(){
        return URL;
    }

    public static List<NewsBean> getJsonData(String url){
        List<NewsBean> newsBeanList = new ArrayList<>();
        JSONObject jsonObject;
        NewsBean newsBean;
        InputStream is;
        try {
            URL mUrl = new URL(url);
            is= mUrl.openStream();
            Log.i("TAG",is+"");
            String jsonString = readStream(is);
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                newsBean = new NewsBean();
                newsBean.name = jsonObject.getString("name");
                newsBean.picSmall = jsonObject.getString("picSmall");
                newsBean.description = jsonObject.getString("description");
                newsBeanList.add(newsBean);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return newsBeanList;
    }

    public static List<String> getUrls(List<NewsBean> newsBeanList){
        List<String> urls = new ArrayList<>();
        for(NewsBean newsBean : newsBeanList){
            String url = newsBean.picSmall;
            urls.add(url);
        }
        return urls;
    }


    private static String readStream(InputStream is){
        String result = "";
        InputStreamReader isr;
        BufferedReader br;
        try {
            String line = "";
            isr = new InputStreamReader(is,"utf-8");
            br = new BufferedReader(isr);
            while ((line=br.readLine())!=null){
                Log.i("TAG",line+"");
                result+=line;
            }
            br.close();
            isr.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
