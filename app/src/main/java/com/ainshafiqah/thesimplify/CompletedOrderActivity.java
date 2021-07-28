package com.ainshafiqah.thesimplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ainshafiqah.thesimplify.adapter.OrderAdapter;
import com.ainshafiqah.thesimplify.adapter.completedOrderAdapter;
import com.ainshafiqah.thesimplify.adapter.statusAdapter;
import com.ainshafiqah.thesimplify.model.OrderData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompletedOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    completedOrderAdapter mCompleteAdapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order);

        mbase = FirebaseDatabase.getInstance().getReference("Order");

        recyclerView = findViewById(R.id.recyclerviewCompleteOrder);
        //recyclerView.setLayoutManager(new GLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrderData> options = new FirebaseRecyclerOptions.Builder<OrderData>()
                .setQuery(mbase, new SnapshotParser<OrderData>() {
                    @Override
                    public OrderData parseSnapshot(@NonNull DataSnapshot snapshot) {
                        OrderData order = new OrderData();
                        order.setName(snapshot.child("Cust_name").getValue().toString());
                        order.setAddress(snapshot.child("Cust_address").getValue().toString());
                        order.setStatus(snapshot.child("Status").getValue().toString());
                        return order;
                    }
                })
                .build();
        mCompleteAdapter = new completedOrderAdapter(options);
        recyclerView.setAdapter(mCompleteAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCompleteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompleteAdapter.stopListening();
    }
}