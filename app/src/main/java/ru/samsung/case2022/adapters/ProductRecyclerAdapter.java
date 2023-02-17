package ru.samsung.case2022.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.samsung.case2022.R;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;

    private Context context;

    public ProductRecyclerAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);

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
            DataBaseHandler bd = new DataBaseHandler(this.context);
            ArrayList<Products> products = (ArrayList<Products>) bd.getAllProd();
            Products p = products.get(position);
            ((MyHolder) holder).productName.setText(p.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.changeI = p.getId();
                    Intent intent=new Intent(context, ChangeProduct.class);
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
