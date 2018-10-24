package boat.golden.marketit;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.sql.Timestamp;
import android.net.Uri;


public class SellerMainActivity extends AppCompatActivity {
RecyclerView recyclerView;
EditText a,b,price;
Button c;
FirebaseDatabase database;
ImageView imageView;
String UID;
FloatingActionButton fab;
Uri uri;
Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK&&requestCode==101){
            try {
                uri = data.getData();
                ContentResolver resolver = SellerMainActivity.this.getContentResolver();
                bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);

                imageView.setImageBitmap(bitmap);
                //upload();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main);
        recyclerView=findViewById(R.id.seller_recycler);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });





    }
    public void showdialog() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SellerMainActivity.this);
        View dialogView = View.inflate(SellerMainActivity.this,R.layout.dialog_seller_upload,null);
        dialogBuilder.setView(dialogView);

        a = (EditText) dialogView.findViewById(R.id.projectname);
        b = (EditText) dialogView.findViewById(R.id.projectdesc);
        price = (EditText) dialogView.findViewById(R.id.productprice );
        //d = dialogView.findViewById(R.id.productprice);
/*        objtype = dialogView.findViewById(R.id.typespinner);
        objtype.setOnItemSelectedListener(this);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, objects);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        objtype.setAdapter(aa);*/

        imageView = (ImageView) dialogView.findViewById(R.id.imageview);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i, 101);
            }
        });

        dialogBuilder.setTitle("Upload your Product");
        dialogBuilder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                DatabaseReference livemart;
                SharedPreferences prefs = SellerMainActivity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
                UID = prefs.getString("UID", "Random");
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                database=FirebaseDatabase.getInstance();
                livemart = database.getReference("SHOPS/" + UID +"/PRODUCTS/"+ts);


                if (a.getText().toString().length() > 0 && b.getText().toString().length() > 0  ) {

                    livemart.child("picurl").setValue("NULL");

                    livemart.child("productname").setValue(a.getText().toString());
                    livemart.child("productdesc").setValue(b.getText().toString());
                    livemart.child("price").setValue(price.getText().toString());

                    livemart.child("producttype").setValue("NULL", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                            SharedPreferences prefs = SellerMainActivity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
                            Toast.makeText(SellerMainActivity.this, "Product Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    });

                } else {
                    Toast.makeText(SellerMainActivity.this, "Upload Failed : \nEnter All Fields correctly", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        AlertDialog b = dialogBuilder.create();
        b.show();


    }









    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_shop:     Toast.makeText(SellerMainActivity.this,"Edit",Toast.LENGTH_SHORT).show();       break;
            case R.id.sold_items:    Toast.makeText(SellerMainActivity.this,"Items Sold",Toast.LENGTH_SHORT).show();        break;
            case R.id.tutorial:       Toast.makeText(SellerMainActivity.this,"Tutorial",Toast.LENGTH_SHORT).show();     break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seller_main_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

}
