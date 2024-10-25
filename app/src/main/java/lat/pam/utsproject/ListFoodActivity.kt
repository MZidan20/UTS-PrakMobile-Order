package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("Cappuccino", "Kopi cappuccino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino),
            Food("Tea", "Tea Segar dengan perasan lemon", R.drawable.sparkling_tea),
            Food("Cheesecake", "Kue Yang dibuat dengan bahan utama keju di selmuti cream", R.drawable.cheesecake),
            Food("donut", "donut lembut yang terbuat dari kentang serta tepung premium", R.drawable.donut),
            Food("Mie Goreng", "Mie yang dibuat denga telor serta beberapa toping dengan bumbu khas", R.drawable.mie_goreng),
            Food("Nasi Goreng Kampung", "Nasi yang digoreng dengan suhu tinggu dengan toping khas ayam kampung", R.drawable.nasigoreng )
        )


        adapter = FoodAdapter(foodList) { selectedFoodName ->
            val resultIntent = Intent().apply {
                putExtra("selectedFoodName", selectedFoodName)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        recyclerView.adapter = adapter
    }
}
