package com.ainshafiqah.thesimplify.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.thesimplify.R;
import com.ainshafiqah.thesimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class OrderAdapter extends FirebaseRecyclerAdapter<OrderData, OrderAdapter.OrderViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdapter(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position, OrderData model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.statusDetail.setText(model.getStatus());
        holder.trackingNum.setText(model.getTrackingNum());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence theoptions[] = new CharSequence[]{
                        "Yes",
                        "No",
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Are you sure you want to delete this item?");
                builder.setItems(theoptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if(which == 0){
                            //delete item
                            notifyItemRemoved(position);
                        }if(which == 1){
                            Toast.makeText(v.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return false;
            }
        });
    }



    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_ui, parent, false);
        return new OrderViewHolder(view);
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView name, status, address, statusDetail, trackingNum;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.custName);
            address = itemView.findViewById(R.id.address);
            status = itemView.findViewById(R.id.status);
            statusDetail = itemView.findViewById(R.id.statusDetail);
            trackingNum = itemView.findViewById(R.id.trackingNum);
        }
    }
}
