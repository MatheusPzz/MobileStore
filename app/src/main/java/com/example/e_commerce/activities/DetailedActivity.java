package com.example.e_commerce.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.R;
import com.example.e_commerce.models.NewProductsModel;
import com.example.e_commerce.models.PopularProductsModel;
import com.example.e_commerce.models.ShowAllModel;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView popularity,name,description,price;
    Button addToCart,buyNow;
    ImageView add_item,minus_item;

        // New products
        NewProductsModel newProductsModel;

        // Show all
    ShowAllModel showAllModel = null;

        // Popular Products
    PopularProductsModel popularProductsModel = null;
    private FirebaseFirestore firebaseFirestore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        firebaseFirestore = FirebaseFirestore.getInstance();

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
        }
        // Popular Products

        if (popularProductsModel != null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            popularity.setText(popularProductsModel.getPopularity());
            description.setText(popularProductsModel.getDescription());
            price.setText(String.valueOf(popularProductsModel.getPrice()));
            name.setText(popularProductsModel.getName());
        }

        //Show all products
        if (showAllModel != null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            popularity.setText(showAllModel.getPopularity());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());
        }

    }
}