package com.example.e_commerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce.R;
import com.example.e_commerce.adapters.ShowAllAdapter;
import com.example.e_commerce.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;

    List<ShowAllModel> showAllModelList;

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_items);

        firebaseFirestore = FirebaseFirestore.getInstance();

       recyclerView = findViewById(R.id.show_all_rec);
       recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
       showAllModelList = new ArrayList<>();
       showAllAdapter = new ShowAllAdapter(this, showAllModelList);
       recyclerView.setAdapter(showAllAdapter);

       firebaseFirestore.collection("ShowAll")
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful()){
                           for(DocumentSnapshot doc :task.getResult().getDocuments()){

                               ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                               showAllModelList.add(showAllModel);
                               showAllAdapter.notifyDataSetChanged();
                           }
                       }
                   }
               });
    }
}