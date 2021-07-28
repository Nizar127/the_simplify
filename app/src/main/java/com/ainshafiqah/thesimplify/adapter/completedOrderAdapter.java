package com.ainshafiqah.thesimplify.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.thesimplify.R;
import com.ainshafiqah.thesimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class completedOrderAdapter extends FirebaseRecyclerAdapter<OrderData, completedOrderAdapter.CompleteOrderViewHolder> {

    public completedOrderAdapter(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull completedOrderAdapter.CompleteOrderViewHolder holder, int position, @NonNull OrderData model) {
        holder.completeTrackingNum.setText(model.getTrackingNum());
        holder.completeCustName.setText(model.getName());
        holder.completeAddress.setText(model.getAddress());
        holder.completeStatus.setText(model.getStatus());
    }

    @Override
    public CompleteOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercomplete_ui, parent, false);
        return new completedOrderAdapter.CompleteOrderViewHolder(view);    }

     class CompleteOrderViewHolder extends RecyclerView.ViewHolder {

        TextView completeCustName, completeAddress, completeTrackingNum, completeStatus;
         public CompleteOrderViewHolder(@NonNull View itemView) {

             super(itemView);

             completeAddress = itemView.findViewById(R.id.addressCompleted);
             completeCustName = itemView.findViewById(R.id.custNameCompleted);
             completeTrackingNum = itemView.findViewById(R.id.trackingNumCompleted);
         }
     }
}
