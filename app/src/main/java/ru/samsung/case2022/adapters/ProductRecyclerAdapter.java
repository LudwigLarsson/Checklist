package ru.samsung.case2022.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.samsung.case2022.R;
import ru.samsung.case2022.data.DataBaseHandler;
import ru.samsung.case2022.model.Products;
import ru.samsung.case2022.ui.EditProductActivity;
import ru.samsung.case2022.utils.Util;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Products> products;
    private Context context;

    public ProductRecyclerAdapter(Context context, ArrayList<Products> products) {
        this.inflater = LayoutInflater.from(context);
        this.products = products;
        this.context = context;

    }


    private class MyHolder extends RecyclerView.ViewHolder {
        private TextView productName;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);


        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_item, parent, false);

        Log.e("Point_2", "bvefhvb");
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        try {

            Products p = this.products.get(position);
            ((MyHolder) holder).productName.setText(p.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.changeI = p.getId();
                    Intent intent=new Intent(context, EditProductActivity.class);
                    context.startActivity(intent);
                }
            });
        } catch (Exception e) {

        }


    }


    @Override
    public int getItemCount() {

        DataBaseHandler bd = new DataBaseHandler(this.context);
        ArrayList<Products> products = (ArrayList<Products>) bd.getAllProd();
        return products.size();
    }

}
