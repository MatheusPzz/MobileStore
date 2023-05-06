package com.example.e_commerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.e_commerce.R;
import com.example.e_commerce.models.NewProductsModel;
import com.example.e_commerce.models.PopularProductsModel;
import com.example.e_commerce.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView popularity,name,description,price, quantity;
    Button addToCart,buyNow;
    ImageView add_item,minus_item;

    Toolbar toolbar;

    int totalQuantity = 0;
    int totalPrice = 0;

        // New products
        NewProductsModel newProductsModel;

        // Show all
    ShowAllModel showAllModel = null;

    FirebaseAuth auth;

        // Popular Products
    PopularProductsModel popularProductsModel = null;
    private FirebaseFirestore firebaseFirestore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detailed");

        if (object instanceof NewProductsModel){
            newProductsModel = (NewProductsModel) object;
        }else if(object instanceof  PopularProductsModel) {
            popularProductsModel = (PopularProductsModel) object;
        }
        else if(object instanceof  ShowAllModel) {
            showAllModel = (ShowAllModel) object;
        }

        detailedImg = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name);
        popularity = findViewById(R.id.rating);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);
        quantity = findViewById(R.id.quantity_item);

        addToCart = findViewById(R.id.add_cart);
        buyNow = findViewById(R.id.buy_now);

        add_item = findViewById(R.id.add_item);
        minus_item = findViewById(R.id.minus_item);

        // New Products

        if (newProductsModel != null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            popularity.setText(newProductsModel.getPopularity());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));
            name.setText(newProductsModel.getName());

            totalPrice = newProductsModel.getPrice() * totalQuantity;
        }
        // Popular Products

        if (popularProductsModel != null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            popularity.setText(popularProductsModel.getPopularity());
            description.setText(popularProductsModel.getDescription());
            price.setText(String.valueOf(popularProductsModel.getPrice()));
            name.setText(popularProductsModel.getName());

            totalPrice = popularProductsModel.getPrice() * totalQuantity;
        }

        //Show all products
        if (showAllModel != null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            popularity.setText(showAllModel.getPopularity());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());

            totalPrice = showAllModel.getPrice() * totalQuantity;
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    if(newProductsModel != null){
                        totalPrice = newProductsModel.getPrice() * totalQuantity;

                    }
                    if(popularProductsModel != null){
                        totalPrice = popularProductsModel.getPrice() * totalQuantity;

                    }
                    if (showAllModel != null){
                        totalPrice = showAllModel.getPrice() * totalQuantity;

                    }
                }

            }
        });
        minus_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }

            }
        });

    }

    private void addToCart() {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firebaseFirestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added To The Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}