package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity
{
    private ImageView tShirt,sportTShirt,femaleDresses, sweaters;
    private ImageView glasses,hatsCap,walletsBagsPurses, shoes;
    private ImageView headphoneHandFree,laptops,watches, mobilePhone;

    private Button LogoutBtn, CheckOrdersBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });

        tShirt =(ImageView)findViewById(R.id.t_shirt);
        sportTShirt =(ImageView)findViewById(R.id.sports_t_shirt);
        femaleDresses =(ImageView)findViewById(R.id.female_dresses);
        sweaters =(ImageView)findViewById(R.id.sweathers);

        glasses =(ImageView)findViewById(R.id.glasses);
        hatsCap =(ImageView)findViewById(R.id.hats_cap);
        walletsBagsPurses =(ImageView)findViewById(R.id.purses_bags_wallets);
        shoes =(ImageView)findViewById(R.id.shoes);

        headphoneHandFree =(ImageView)findViewById(R.id.headphones_handfree);
        laptops =(ImageView)findViewById(R.id.laptops_pc);
        watches =(ImageView)findViewById(R.id.watches);
        mobilePhone =(ImageView)findViewById(R.id.mobilephones);


        tShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","tShirt");
                startActivity(intent);
            }
        });

        sportTShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Sports tShirts");
                startActivity(intent);

            }
        });
        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Female Dresses");
                startActivity(intent);

            }
        });

        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Sweaters");
                startActivity(intent);

            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Glasses");
                startActivity(intent);

            }
        });

        hatsCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Hats Caps");
                startActivity(intent);

            }
        });

        walletsBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Wallets Bags Purses");
                startActivity(intent);

            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Shoes");
                startActivity(intent);

            }
        });
        headphoneHandFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Headphones HandFree");
                startActivity(intent);

            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Laptops");
                startActivity(intent);

            }
        });

        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Watches");
                startActivity(intent);

            }
        });

        mobilePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminNewProudectActivity.class);
                intent.putExtra("category","Mobile Phone");
                startActivity(intent);

            }
        });
    }
}