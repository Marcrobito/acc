package dev.eighteentech.acc.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.eighteentech.acc.databinding.ActivityMainBinding
import dev.eighteentech.acc.entity.Response.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: HomeAdapter
    private lateinit var manager: LinearLayoutManager

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = HomeAdapter()
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recycler.adapter = adapter
            recycler.layoutManager = manager
            viewModel.data.observe(this@MainActivity) {
                when (it) {
                    is NotLoaded -> loader.visibility = View.GONE
                    is Loading -> loader.visibility = View.VISIBLE
                    is Success -> {
                        loader.visibility = View.GONE
                        adapter.updateItems(it.data)
                    }
                    is Error -> {
                        loader.visibility = View.GONE
                        Log.d("TAG", it.data.message.toString())
                        Toast.makeText(this@MainActivity, it.data.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        viewModel.fetchData()
    }
}