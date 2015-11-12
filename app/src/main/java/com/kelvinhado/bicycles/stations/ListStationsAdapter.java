package com.kelvinhado.bicycles.stations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kelvinhado.bicycles.R;

import java.util.List;

/**
 * Created by kel on 10/13/15.
 */
public class ListStationsAdapter  extends ArrayAdapter {

    List<Station> contractList;
    LayoutInflater mInflater;
    Context context;

    public ListStationsAdapter(Context context, List<Station> list) {
        super(context, 0, list);
        this.contractList = list;
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_station_list,parent,false);
            holder = new ViewHolder();

            //holder.number = (TextView) convertView.findViewById(R.id.tvStationNumber);
            holder.name = (TextView) convertView.findViewById(R.id.tvStationName);
            holder.address = (TextView) convertView.findViewById(R.id.tvStationAddress);

            // we set a tag to our view to re-use it
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Station station = contractList.get(position);
        holder.name.setText(station.name);
        //holder.number.setText(Integer.toString(station.number));
        holder.address.setText(station.address);
        return convertView;

    }

    static class ViewHolder
    {
        //TextView number;
        TextView name;
        TextView address;
    }
}
