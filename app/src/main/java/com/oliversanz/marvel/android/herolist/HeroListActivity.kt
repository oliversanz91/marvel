package com.oliversanz.marvel.android.herolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliversanz.marvel.data.network.repository.HeroRepository
import com.oliversanz.marvel.databinding.ActivityHeroListBinding
import com.oliversanz.marvel.domain.model.HeroListModel
import com.oliversanz.marvel.domain.model.ImageModel
import com.oliversanz.marvel.domain.model.ResultEvent
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class HeroListActivity : AppCompatActivity() {

    private val binding: ActivityHeroListBinding by lazy {
        ActivityHeroListBinding.inflate(layoutInflater)
    }

    private val viewModel: HeroListViewModel by viewModel()
    private val heroAdapter = HeroListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.rvHeroList.apply {
            this.adapter = heroAdapter
            this.layoutManager = LinearLayoutManager(this@HeroListActivity, RecyclerView.VERTICAL, false)
        }

        viewModel.heroList.observe(this, heroAdapter::updateData)
        viewModel.loadHeroList().observe(this) {
            when (it) {
                is ResultEvent.Success -> { /* Ha ido ok */ }
                is ResultEvent.Error -> showError(it.throwable)
            }
        }

        setContentView(binding.root)
    }

    private fun showError(throwable: Throwable) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(throwable.message ?: throwable.localizedMessage)
        builder.setNeutralButton("Aceptar") { _, _ -> }
        builder.show()
    }
}