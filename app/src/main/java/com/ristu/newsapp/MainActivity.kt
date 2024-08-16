package com.ristu.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var newsAdapter: NewsAdapter? = null

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setViews()
        val apiService = RetrofitService.getRetrofitService().create(RetrofitInterface::class.java)

        GlobalScope.launch {
            val response = apiService.getAllNews()

            if (response.isSuccessful) {
                val data = response.body()
                Log.d("API_CALL", data.toString())

                withContext(Dispatchers.Main) {
                    if (data != null) {
                        newsAdapter?.setItem(ArrayList(data.articles))
                    }
                }
            } else {
                Log.e("API_CALL", "Error: ${response.code()}")
            }
        }

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.main_recycler_view)
        newsAdapter = NewsAdapter()
    }

    private fun setViews() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }
}


