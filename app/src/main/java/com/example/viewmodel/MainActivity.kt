package com.example.viewmodel
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.counterLiveData.observe(this) {
            binding.tvCounter.text = it.toString()
        }
        binding.btnCount.setOnClickListener {
            viewModel.counterButtonClicked()
        }


        viewModel.fruitListLiveData.observe(this) {
            binding.spFruit.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
        }
        binding.btnAdd.setOnClickListener {
            viewModel.addFruit(binding.etFruit.text.toString())
        }


        binding.btnNext.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }
}