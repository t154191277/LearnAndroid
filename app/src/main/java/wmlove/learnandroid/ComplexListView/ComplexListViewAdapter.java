package wmlove.learnandroid.ComplexListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import wmlove.learnandroid.R;

/**
 * Created by Administrator on 2015/6/12.
 */
public class ComplexListViewAdapter extends BaseAdapter{
    private String [] data1 = {"Hello","World","What the Fuck?"};
    private String [] data2 = {"Good Morning","Hello Android!"};
    private View mConvertView;
    private LayoutInflater mLayoutInflater;
    public ComplexListViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return mConvertView.getTag();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (position%2==0) ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ViewHolder1 viewHolder1 =null;
        ViewHolder2 viewHolder2 =null;
        int type = getItemViewType(position);
        if(convertview==null){
            if(type==1){
                viewHolder2 = new ViewHolder2();
                convertview = mLayoutInflater.inflate(R.layout.testlayout1,viewGroup,false);
                viewHolder2.tv1 = (TextView) convertview.findViewById(R.id.tv1);
                viewHolder2.tv2 = (TextView) convertview.findViewById(R.id.tv2);
            }else{
                viewHolder1 = new ViewHolder1();
                convertview = mLayoutInflater.inflate(R.layout.testlayout2,viewGroup,false);
                viewHolder1.tv1 = (TextView) convertview.findViewById(R.id.tv1);
                viewHolder1.tv2 = (TextView) convertview.findViewById(R.id.tv2);
                viewHolder1.tv3 = (TextView) convertview.findViewById(R.id.tv3);
            }
            convertview.setTag(viewHolder1 == null ? viewHolder2 : viewHolder1);
        }else {
            if(type==1){
                viewHolder2 = (ViewHolder2) convertview.getTag();
            }else{
                viewHolder1 = (ViewHolder1) convertview.getTag();
            }
        }
        if(type==1){
            viewHolder2.tv1.setText(data2[0]);
            viewHolder2.tv2.setText(data2[1]);
        }else{
            viewHolder1.tv1.setText(data1[0]);
            viewHolder1.tv2.setText(data1[1]);
            viewHolder1.tv3.setText(data1[2]);
        }
        mConvertView  = convertview;
        return convertview;
    }


    private class ViewHolder1{
        private TextView tv1,tv2,tv3;
    }

    private class ViewHolder2{
        private  TextView tv1,tv2;
    }
}
