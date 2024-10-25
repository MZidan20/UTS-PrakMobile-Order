package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    private lateinit var foodNameTextView: TextView
    private lateinit var servingsEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var notesEditText: EditText
    private lateinit var orderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Initialize views
        foodNameTextView = findViewById(R.id.etFoodName)
        servingsEditText = findViewById(R.id.etServings)
        nameEditText = findViewById(R.id.etName)
        notesEditText = findViewById(R.id.etNotes)
        orderButton = findViewById(R.id.btnOrder)

        // Set up click listener to open ListFoodActivity
        foodNameTextView.setOnClickListener {
            val intent = Intent(this, ListFoodActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_FOOD)
        }

        // Set up click listener for the "Place Order" button
        orderButton.setOnClickListener {
            if (isValidOrder()) {
                // Start ConfirmationActivity with order details
                val intent = Intent(this, ConfirmationActivity::class.java).apply {
                    putExtra("foodName", foodNameTextView.text.toString())
                    putExtra("servings", servingsEditText.text.toString())
                    putExtra("name", nameEditText.text.toString())
                    putExtra("notes", notesEditText.text.toString())
                }
                startActivity(intent)
            } else {
                // Show an error if any fields are empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Apply insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Check that all fields are filled
    private fun isValidOrder(): Boolean {
        return foodNameTextView.text.isNotEmpty() &&
                servingsEditText.text.isNotEmpty() &&
                nameEditText.text.isNotEmpty() &&
                notesEditText.text.isNotEmpty()
    }

    // Handle the result from ListFoodActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_FOOD && resultCode == RESULT_OK) {
            val selectedFoodName = data?.getStringExtra("selectedFoodName")
            foodNameTextView.text = selectedFoodName
        }
    }

    companion object {
        private const val REQUEST_CODE_SELECT_FOOD = 1
    }
}
