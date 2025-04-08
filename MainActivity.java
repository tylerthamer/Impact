package com.example.cartview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView totalText;
    private ListView cartListView;
    private Button addItemBtn, clearCartBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalText = findViewById(R.id.cart_total);
        cartListView = findViewById(R.id.cart_list);
        addItemBtn = findViewById(R.id.add_item);
        clearCartBtn = findViewById(R.id.clear_cart);

        updateCartView();

        addItemBtn.setOnClickListener(v -> {

            CartItem item = new CartItem("Product", 19.99);
            Cart.getInstance().addItem(item);
            updateCartView();
            Toast.makeText(this, item.getItemName() + " added to cart.", Toast.LENGTH_SHORT).show();
        });

        clearCartBtn.setOnClickListener(v -> {
            Cart.getInstance().clearCart();
            updateCartView();
            Toast.makeText(this, "Cart cleared.", Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint("DefaultLocale")
    private void updateCartView() {
        Cart cart = Cart.getInstance();
        ArrayAdapter<CartItem> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                cart.getItems()
        );

        cartListView.setAdapter(adapter);
        totalText.setText(String.format("Total: $%.2f", cart.getTotalPrice()));
    }
}