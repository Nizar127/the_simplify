package com.ainshafiqah.thesimplify.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ainshafiqah.thesimplify.R;
import com.ainshafiqah.thesimplify.UpdateStatus;
import com.ainshafiqah.thesimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class statusAdapter extends FirebaseRecyclerAdapter<OrderData, statusAdapter.statusViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public statusAdapter(@NonNull FirebaseRecyclerOptions<OrderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull statusAdapter.statusViewHolder holder, int position, @NonNull OrderData model) {
        holder.statusTrackingNum.setText(model.getTrackingNum());
        holder.statusCustName.setText(model.getName());
        holder.statusAdress.setText(model.getAddress());
        holder.statusDetail.setText(model.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateStatus.class);
                intent.putExtra("orderID",model.getOrderID());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public statusAdapter.statusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_order, parent, false);
        return new statusAdapter.statusViewHolder(view);
    }

    class statusViewHolder extends RecyclerView.ViewHolder{

        TextView statusCustName, statusAdress, statusTrackingNum, statusDetail;

        public statusViewHolder(@NonNull View itemView) {
            super(itemView);

            statusCustName     = itemView.findViewById(R.id.custNamestatusorder);
            statusAdress       = itemView.findViewById(R.id.addressstatusorder);
            statusTrackingNum  = itemView.findViewById(R.id.trackingNumstatusorder);
            statusDetail       = itemView.findViewById(R.id.statusOrderDetail);


        }
    }
}
