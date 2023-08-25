package com.example.retrofit_coroutines.ui.main


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_coroutines.data.remote.ApiHelper
import com.example.retrofit_coroutines.data.remote.RemoteData
import com.example.retrofit_coroutines.databinding.ActivityMainBinding
import com.example.retrofit_coroutines.utils.Resource


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setUpObserver()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(ApiHelper((RemoteData.apiService)))
        )[MainViewModel::class.java]
    }



    private fun setUpObserver() {
        viewModel.getUsers().observe(this, Observer {resource->
            when (resource) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE // Hiển thị RecyclerView khi có dữ liệu
                    resource.data?.let { users ->
                        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = MainAdapter()
                        adapter.submitList(users)
                        binding.recyclerView.adapter = adapter
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE // Ẩn RecyclerView khi đang tải dữ liệu
                }
                is Resource.DataError -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE // Ẩn RecyclerView khi có lỗi
                }
            }
        })
    }



}