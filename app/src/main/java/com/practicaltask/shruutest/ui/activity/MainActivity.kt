package com.practicaltask.shruutest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicaltask.shruutest.ui.adapter.PostAdapter
import com.practicaltask.shruutest.util.ApiState
import com.practicaltask.shruutest.data.viewModel.MainViewModel
import com.practicaltask.shruutest.databinding.ActivityMainBinding
import com.practicaltask.shruutest.util.ConnectivityLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()

        connectivityLiveData= ConnectivityLiveData(application)
        connectivityLiveData.observe(this, Observer {isAvailable->
            when(isAvailable)
            {
                true-> {

                    binding.recyclerview.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.noInternet.isVisible=false

                    Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()

                    //if we call below method then only the data will load again else data will not load,
                    //data will load every time from the server when this function is called,
                    mainViewModel.getPost()

                }

                false-> {

                    binding.recyclerview.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.noInternet.isVisible=true

                    Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show()



                }
            }
        })



        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Start listening for values.
                // This happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                mainViewModel._postStateFlow.collect() {it->
                    when(it){
                        is ApiState.Loading->{
                            binding.recyclerview.isVisible=false
                            binding.progressBar.isVisible=true
                        }
                        is ApiState.Failure -> {
                            binding.recyclerview.isVisible = false
                            binding.progressBar.isVisible = false
                            Log.d("main", "onCreate: ${it.msg}")
                        }
                        is ApiState.Success-> {
                            binding.recyclerview.isVisible = true
                            binding.progressBar.isVisible = false

                            postAdapter.setData(it.data.body()?.products)

                        }
                        is ApiState.Empty->{

                        }
                    }
                }
            }
        }

    }

    private fun initRecyclerview() {
        postAdapter= PostAdapter(ArrayList())
        binding.recyclerview.apply {

            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=postAdapter
        }
    }
}