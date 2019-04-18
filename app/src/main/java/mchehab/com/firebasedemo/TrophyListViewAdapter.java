package mchehab.com.firebasedemo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by muhammadchehab on 1/11/18.
 */

public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private List<Trophy> listTrophy;

    public ListViewAdapter(Activity activity, List<Trophy> listTrophy){
        this.activity = activity;
        this.listTrophy = listTrophy;
    }

    @Override
    public int getCount() {
        return listTrophy.size();
    }

    @Override
    public Object getItem(int position) {

        return listTrophy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row_listview, null);

            holder = new ViewHolder();
            holder.editTextSport = convertView.findViewById(R.id.editTextSport);
            holder.textViewYear = convertView.findViewById(R.id.textViewYear);
            holder.textViewDescription = convertView.findViewById(R.id.textViewDescription);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.editTextSport.setText(listTrophy.get(position).getSport());
        holder.textViewYear.setText(listTrophy.get(position).getYear());
        holder.textViewDescription.setText(listTrophy.get(position).getDescription() + "");

        return convertView;
    }

    class ViewHolder{
        TextView editTextSport;
        TextView textViewYear;
        TextView textViewDescription;
    }
}