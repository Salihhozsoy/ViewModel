package com.example.viewmodel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.viewmodel.databinding.ActivityUsersBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UsersActivity : AppCompatActivity() {

    lateinit var binding: ActivityUsersBinding
    private val viewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeUserList()
        observeAdapterState()
    }

    private fun observeAdapterState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.adapter.collect {
                    when (it) {
                        is UsersViewModel.Adapter.Idle -> {}
                        is UsersViewModel.Adapter.Remove -> {
                            //silmeyi haber ver
                            adapter.notifyItemRemoved(it.position)
                        }

                        is UsersViewModel.Adapter.Add -> {
                            //eklemeyi haber ver
                            // adapter.notifyItemInserted(viewModel.userList.value.lastIndex)
                        }
                    }
                }
            }
        }
    }

    private fun observeUserList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.userList.collect {
                    adapter = UsersAdapter(this@UsersActivity, it) { user ->
                        viewModel.removeItem(user)
                    }
                    binding.rvUser.adapter = adapter
                }
            }
        }
    }
}
