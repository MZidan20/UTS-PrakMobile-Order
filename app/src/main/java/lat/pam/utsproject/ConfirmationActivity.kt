package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        val foodName = intent.getStringExtra("foodName")
        val servings = intent.getStringExtra("servings")
        val name = intent.getStringExtra("name")
        val notes = intent.getStringExtra("notes")


        findViewById<TextView>(R.id.tvTitle).text = "Order Food"
        findViewById<TextView>(R.id.foodNameTextView).text = "Food Name: $foodName"
        findViewById<TextView>(R.id.servingsTextView).text = "Number of Servings: $servings pax"
        findViewById<TextView>(R.id.orderingNameTextView).text = "Ordering Name: $name"
        findViewById<TextView>(R.id.notesTextView).text = "Additional Notes: $notes"

        findViewById<Button>(R.id.backtoMenu).setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}