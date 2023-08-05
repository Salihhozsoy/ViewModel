package com.example.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.viewmodel.databinding.ActivitySecondBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
        observeCounter()
        observeTimer()
        observeState()
        observeMessage()

    }
    private fun observeMessage(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.message.collect{
                    AlertDialog.Builder(this@SecondActivity).setMessage("message").create().show()
                }
            }
        }
    }
    private fun observeState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    Toast.makeText(this@SecondActivity,it.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun observeTimer(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.timer.collect{
                    binding.tvTimer.text =it.toString()
                }
            }
        }
    }
    private  fun observeCounter(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.counter.collect{
                    binding.tvCount.text=it.toString()
                }
            }
        }
    }

    private fun listeners(){
        binding.btnShowData.setOnClickListener{
            viewModel.getMessage()
        }
        binding.btnGetData.setOnClickListener{
            viewModel.getData()
        }
        binding.btnCount.setOnClickListener{
            viewModel.increase()
        }

        binding.btnNextPageRecyc.setOnClickListener{
            val intent= Intent(this,UsersActivity::class.java)
            startActivity(intent)
        }

    }
}