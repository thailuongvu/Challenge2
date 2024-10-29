package com.example.challlenge2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val product1 = Product("Laptop",999.99,5)
        val product2 = Product("Smartphone",499.99,10)
        val product3 = Product("Tablet",299.99,0)
        val product4 = Product("Smartwatch",199.99,3)
        val products : List<Product> = listOf(product1,product2,product3,product4)
        // total price
        val totalPrice = calSum(products)
        Log.i("Total Price",": $totalPrice")
        println("Total Price: $totalPrice")
        //find the most expensive
        val mostExpensiveProduct = findTheMostExpensiveProduct(products)

        if (mostExpensiveProduct != null) {
            println("Most expensive product: ${mostExpensiveProduct.name} at \$${mostExpensiveProduct.price}")
        } else {
            println("No products available.")
        }
        // Check inventory
        println(""+checkProductName(products))
        // sort
        val sortedProducts = sortedAscending(products, "price")
        for (product in sortedProducts) {
            println("${product.name}: Price = \$${product.price}, Quantity = ${product.quantity}")
        }
        // 2.2 Array Manipulation and Missing number
        // using XOR operation
        val input  = intArrayOf(3,7,1,2,6,4)
        println("Missing number : ${findMissingNumber(input)}")
    }
    fun calSum( products: List<Product>):Double{
        val totalPrice = products.sumOf { it.price*it.quantity }
        return totalPrice
    }
    fun findTheMostExpensiveProduct(products: List<Product>):Product?{
        return products.maxByOrNull { it.price }
    }
    fun checkProductName(products: List<Product>): Boolean{
        return products.any{ it.name == "Headphones"}
    }
    fun sortedDescending(products: List<Product>, option :String): List<Product>{
        return when (option) {
            "price" -> products.sortedByDescending { it.price }
            "quantity" -> products.sortedByDescending { it.quantity }
            else -> products // Return original list if an unknown option is provided
        }
    }
    fun sortedAscending(products: List<Product>, option :String): List<Product>{
        return when (option) {
            "price" -> products.sortedBy { it.price }
            "quantity" -> products.sortedBy { it.quantity }
            else -> products // Return original list if an unknown option is provided
        }
    }
    fun findMissingNumber(arr:IntArray):Int{
        //Cal the xor from 1 to n+1
        var res = 0
        for( i in 1 .. arr.size+1){
            res= res xor i
        }
        // cal the xor arr
        for(num in arr){
            res = res xor num
        }
        return res;
    }
}