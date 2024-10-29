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

        foodNameTextView = findViewById(R.id.etFoodName)
        servingsEditText = findViewById(R.id.etServings)
        nameEditText = findViewById(R.id.etName)
        notesEditText = findViewById(R.id.etNotes)
        orderButton = findViewById(R.id.btnOrder)

        if (foodNameTextView.text.isEmpty()) {
            openFoodSelection()
        }

        foodNameTextView.setOnClickListener {
            openFoodSelection()
        }

        orderButton.setOnClickListener {
            if (isValidOrder()) {
                val intent = Intent(this, ConfirmationActivity::class.java).apply {
                    putExtra("foodName", foodNameTextView.text.toString())
                    putExtra("servings", servingsEditText.text.toString())
                    putExtra("name", nameEditText.text.toString())
                    putExtra("notes", notesEditText.text.toString())
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openFoodSelection() {
        val intent = Intent(this, ListFoodActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_SELECT_FOOD)
    }

    private fun isValidOrder(): Boolean {
        return foodNameTextView.text.isNotEmpty() &&
                servingsEditText.text.isNotEmpty() &&
                nameEditText.text.isNotEmpty() &&
                notesEditText.text.isNotEmpty()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_FOOD && resultCode == RESULT_OK) {
            val selectedFoodName = data?.getStringExtra("selectedFoodName")
            foodNameTextView.text = selectedFoodName // Update selected food name here
        }
    }

    companion object {
        private const val REQUEST_CODE_SELECT_FOOD = 1
    }
}
