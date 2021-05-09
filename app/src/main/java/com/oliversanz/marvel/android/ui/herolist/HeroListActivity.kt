package com.oliversanz.marvel.android.ui.herolist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliversanz.marvel.android.base.BaseActivity
import com.oliversanz.marvel.android.ui.herodetail.HeroDetailActivity
import com.oliversanz.marvel.databinding.ActivityHeroListBinding
import com.oliversanz.marvel.domain.model.ResultEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListActivity : BaseActivity() {

    private val binding: ActivityHeroListBinding by lazy {
        ActivityHeroListBinding.inflate(layoutInflater)
    }

    private val viewModel: HeroListViewModel by viewModel()
    private val heroAdapter = HeroListAdapter(ClickHandler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.rvHeroList.apply {
            this.adapter = heroAdapter
            this.layoutManager = LinearLayoutManager(this@HeroListActivity, RecyclerView.VERTICAL, false)
        }

        viewModel.heroList.observe(this, heroAdapter::updateData)

        showLoading()
        viewModel.loadHeroList().observe(this) {
            hideLoading()
            when (it) {
                is ResultEvent.Success -> { /* Ha ido ok */ }
                is ResultEvent.Error -> showError(it.throwable)
            }
        }

        setContentView(binding.root)
    }

    inner class ClickHandler {

        fun onItemClick(heroId: String) {
            startActivity(HeroDetailActivity.getIntent(this@HeroListActivity, heroId))
        }

    }

}