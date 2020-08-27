package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Model.Cart;
import com.example.ecommerce.Prevalest.Prevalest;
import com.example.ecommerce.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Button NextProcessBtn;
    private TextView textTotalAmount, textMsg1;

    private int overTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NextProcessBtn = (Button) findViewById(R.id.next_process_btn);
        textTotalAmount = (TextView) findViewById(R.id.total_price);
        textMsg1 = (TextView) findViewById(R.id.msg1);



        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                textTotalAmount.setText("Total Price = " + String.valueOf(overTotalPrice) + " $");

                Intent intent = new Intent(CartActivity.this,ConfirmFinalOrderActivity.class);
                intent.putExtra("Total Price",String.valueOf(overTotalPrice));
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        CheckOrderStat();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart list");

        final FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View").child(Prevalest.CurrentOnlineUser.getPhone())
                .child("Products"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int i, @NonNull final Cart cart)
            {
                holder.textProductQuantity.setText("Quantity = "+cart.getQuantity());
                holder.textProductPrice.setText("Price "+cart.getPrice()+" $");
                holder.textProductName.setText(cart.getPname());

                int oneTypeProductTPrice = (Integer.valueOf(cart.getPrice())) * Integer.valueOf(cart.getQuantity());
                overTotalPrice = overTotalPrice + oneTypeProductTPrice ;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Remove"
                                };

                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Option");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i==0)
                                {
                                    Intent intent = new Intent(CartActivity.this,ProductDatailsActivity.class);
                                    intent.putExtra("pid",cart.getPid());
                                    startActivity(intent);
                                }
                                if (i==1)
                                {
                                    cartListRef.child("User View")
                                            .child(Prevalest.CurrentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(cart.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {
                                                    if (task.isSuccessful())
                                                    {
                                                        Toast.makeText(CartActivity.this,"Item removed Successfully.",Toast.LENGTH_LONG).show();

                                                        Intent intent = new Intent(CartActivity.this,HomeActivity.class);
                                                        startActivity(intent);
                                                    }

                                                }
                                            });
                                }

                            }
                        });
                        builder.show();
                    }
                });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void CheckOrderStat()
    {
        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalest.CurrentOnlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.exists())
                {
                    String shippingState = snapshot.child("state").getValue().toString();
                    String userName = snapshot.child("name").getValue().toString();

                    if (shippingState.equals("shipped"))
                    {
                        textTotalAmount.setText("Dear" + userName + "\n Order is shipped successfully.");
                        recyclerView.setVisibility(View.GONE);

                        textMsg1.setVisibility(View.VISIBLE);
                        textMsg1.setText("Congratulations, your final order has been Shipped successfully. Soon will received your order at door step.");
                        NextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(CartActivity.this,"you can purchase more product, once you received your first final order.",Toast.LENGTH_LONG).show();

                    }
                    else if (shippingState.equals("not shipped"))
                    {
                        textTotalAmount.setText("Shipping State = Not Shipped");
                        recyclerView.setVisibility(View.GONE);

                        textMsg1.setVisibility(View.VISIBLE);
                        NextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(CartActivity.this,"you can purchase more product, once you received your first final order.",Toast.LENGTH_LONG).show();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}