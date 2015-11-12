package com.kelvinhado.bicycles.contracts;

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
public class ListContractsAdapter extends ArrayAdapter<Contract> {
    LayoutInflater mInflater;

    public ListContractsAdapter(Context context, List<Contract> list) {
        super(context, 0, list);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_contract_list,parent,false);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.tvContractName);
            holder.commercial_name = (TextView) convertView.findViewById(R.id.tvContractComName);
            holder.country_code = (TextView) convertView.findViewById(R.id.tvContractCountryCode);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Contract contract = getItem(position);
        holder.name.setText(contract.name);
        holder.commercial_name.setText(contract.commercial_name);
        holder.country_code.setText(contract.country_code);
        return convertView;

    }

    static class ViewHolder
    {
        TextView name;
        TextView commercial_name;
        TextView country_code;
    }
}
