package com.example.mvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.databinding.ActivityMainBinding
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        val main = Main()
        main.str = "HelloZhangJun"
        binding.name = main
        var instance = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MyViewModel::class.java)
        instance.liveData.observe(this,object : Observer<String>{
            override fun onChanged(t: String?) {
                Toast.makeText(this@MainActivity,"I have changed" + t,Toast.LENGTH_SHORT).show()
                main.str = t
                main.url = "https://image.cnhnb.com/image/jpeg/head/2020/05/06/c02f5eb1b8f44e90a518b29591bfd5fc.jpeg?imageView2/2/w/750/format/jpg/ignore-error/1|watermark/3/image/aHR0cDovL2ltZy5jbmhuYi5jb20vd20ucG5n/gravity/SouthEast/dx/10/dy/10/ws/0.14/wst/2"
                binding.name = main
            }

        })
        binding.mytext.setOnClickListener {
            instance.liveData.value = "NoDontXhangJun"
        }
    }

    companion object {
        @BindingAdapter(value = ["imageUrl"], requireAll = false)
        @JvmStatic
        fun setImageUrl(view: ImageView, padding: String) {
            Toast.makeText(view.context,"xigailea",Toast.LENGTH_SHORT).show()
            Glide.with(view.context).load(padding).into(view)
        }
    }

}