package com.oliversanz.marvel.android.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.oliversanz.marvel.data.network.exceptions.ShowMessageException
import com.oliversanz.marvel.databinding.ActivityBaseBinding

open class BaseActivity : AppCompatActivity() {

    private val binding: ActivityBaseBinding by lazy {
        ActivityBaseBinding.inflate(layoutInflater)
    }

    private val isLoading = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.loading = isLoading
    }

    override fun setContentView(view: View?) {
        binding.frContent.addView(view)
    }

    fun showLoading() { isLoading.value = true }
    fun hideLoading() { isLoading.value = false }

    fun showError(throwable: Throwable) {
        val builder = AlertDialog.Builder(this)

        if (throwable is ShowMessageException) {
            builder.setTitle(throwable.title)
            builder.setMessage(throwable.message)
        }
        else {
            builder.setTitle("Error")
            builder.setMessage(throwable.message ?: throwable.localizedMessage)
        }

        builder.setPositiveButton("Aceptar") { _, _ -> }
        builder.show()
    }

}