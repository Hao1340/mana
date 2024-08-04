package com.example.spendmanager.Model;

import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spendmanager.Controller.AddRevenueActivity;
import com.example.spendmanager.Database.DatabaseHelper;
import com.example.spendmanager.R;

import java.util.List;

public class revenuelist extends BaseAdapter {
    private Context context;
    private Button editButton;
    private List<Revenue> revenueList;
    private DatabaseHelper dbHelper;
    private LayoutInflater inflater;


    public revenuelist(Context context, List<Revenue> revenueList, DatabaseHelper dbHelper) {
        this.context = context;
        this.revenueList = revenueList;
        this.dbHelper = dbHelper;
        this.inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return revenueList.size();
    }

    @Override
    public Object getItem(int position) {
        return revenueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return revenueList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_revenue, parent, false);
        }

        ImageView iconImageView = convertView.findViewById(R.id.iconImageView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);
        TextView dateTextView = convertView.findViewById(R.id.date);
        TextView amountTextView = convertView.findViewById(R.id.amountTextView);
        Button editButton = convertView.findViewById(R.id.editButton);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        Revenue revenue = revenueList.get(position);


        descriptionTextView.setText(revenue.getDescription());
        dateTextView.setText(revenue.getDate());
        amountTextView.setText(String.valueOf(revenue.getAmount()));

        // Handle edit and delete button clicks here

        // Xử lý sự kiện khi người dùng nhấn nút "Edit"
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddRevenueActivity.class);
                intent.putExtra("REVENUE_ID", revenue.getId());

                context.startActivity(intent);
            }
        });
        // Xử lý sự kiện khi người dùng nhấn nút "Delete"
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = dbHelper.deleteRevenue(revenue.getId());
                if (success) {
                    // Xóa mục khỏi danh sách và thông báo cho adapter
                    revenueList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Revenue deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error deleting revenue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }


}

