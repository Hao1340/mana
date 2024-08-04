/*
package com.example.spendmanager.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spendmanager.Model.DatabaseHelper;
import com.example.spendmanager.Model.Revenue;
import com.example.spendmanager.R;

import java.util.List;

public class RevenueAdapter extends BaseAdapter {
    List<Revenue> revenues;
    private LayoutInflater layoutInflater;
    private Context context;
    private DatabaseHelper dbHelper;
    public View deleteButton;
    public RevenueAdapter(Context context, List<Revenue> list){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.revenues = list;
    }
    @Override
    public int getCount() {
        return revenues.size();
    }

    @Override
    public Object getItem(int i) {
        return revenues.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_revenue, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView =(ImageView) view.findViewById(R.id.iconImageView);
            viewHolder.amountextview = (TextView) view.findViewById(R.id.amountTextView);
            viewHolder.descriptionEditText = (TextView) view.findViewById(R.id.descriptionTextView);
            viewHolder.date = (TextView) view.findViewById(R.id.date);
            viewHolder.editButton = (Button) view.findViewById(R.id.editButton);
            viewHolder.delectButton = (Button) view.findViewById(R.id.deleteButton);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Revenue revenue = this.revenues.get(position);
        viewHolder.imageView.setImageResource(revenue.getImageView());
        viewHolder.date.setText(revenue.getDate());
        viewHolder.amountextview.setText(String.valueOf(revenue.getAmount()));
        viewHolder.descriptionEditText.setText(revenue.getDescription());

        viewHolder.editButton.setOnClickListener(v -> {
            // Handle edit functionality
            Intent intent = new Intent(context, AddRevenueActivity.class);
            intent.putExtra("REVENUE_ID", revenue.getId());
            context.startActivity(intent);
        });

        viewHolder.delectButton.setOnClickListener(v -> {
            // Handle delete functionality
            boolean result = dbHelper.deleteRevenue(revenue.getId());
            if (result) {
                revenues.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deletion failed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
    public class ViewHolder{

        ImageView imageView;
        TextView amountextview;
        TextView descriptionEditText;
        TextView date;
        Button editButton;
        Button delectButton;
    }
}
*/
