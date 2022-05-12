package com.example.jestjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import javax.xml.transform.sax.SAXResult;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * for increment value
     *
     * @method decrement
     */
    public void increment(View view) {

        if(quantity==100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        display(quantity);
    }

    /**
     * for decrement value
     */
    public void decrement(View view) {
        if(quantity == 0){
            return;
        }
        if(quantity == 1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * @param view
     */
    public void submitOrder(View view) {
        //
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_id);
        boolean hasWhippedCream = checkBox.isChecked();
        //
        CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = Chocolate.isChecked();
        //
        EditText NameField = (EditText)findViewById(R.id.name_field);
        String value = NameField.getText().toString();
        
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String all = orderSummery(price,quantity, hasWhippedCream,hasChocolate,value);
          {
              String email = "bjangir@gmail.com";
              Intent intent = new Intent(Intent.ACTION_SENDTO);
              intent.setData(Uri.parse("mailto:")); // only email apps should handle this
              intent.putExtra(Intent.EXTRA_EMAIL, email);
              intent.putExtra(Intent.EXTRA_SUBJECT,value+"'s order items");
              intent.putExtra(Intent.EXTRA_TEXT, all);
              if (intent.resolveActivity(getPackageManager()) != null) {
                  startActivity(intent);
              }
        }
        //displayPrice(quantity* 5);
    }

    /**
     *
     * @param hasWhippedCream
     * @param hasChocolate
     * @return
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        int basePrice = 5;
        if(hasWhippedCream){
            basePrice += 1;
        }
        if(hasChocolate){
            basePrice += 2;
        }

        return basePrice * quantity;
    }
    /**
     * @param quantity        quantity of product
     * @param addWhippedCream Is whipped cream add or Not
     * @param Name Customer name
     * @param addChocolate Is Chocolate add or not
     * @return order of summery
     */
    public String orderSummery(int price,int quantity, boolean addWhippedCream,boolean addChocolate,String Name) {
        String cream,chocolate;
        if(addWhippedCream==true){
            cream = "Yes";
        }else{
            cream = "No";
        }
        if(addChocolate==true){
            chocolate = "Yes";
        }else{
            chocolate = "No";
        }
        String priceMessage = "Name : "+ Name + "\nQuantity : " + quantity;
        priceMessage += "\nIs add Whipped Cream ? : " + cream;
        priceMessage += "\nIs add Chocolate ? : " + chocolate;
        priceMessage += "\nprice : $" + price + "\nThank You";
        return priceMessage;
    }

    @SuppressLint("SetTextI18n")
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }


}