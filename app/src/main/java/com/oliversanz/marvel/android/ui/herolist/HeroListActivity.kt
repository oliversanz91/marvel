package com.oliversanz.marvel.android.ui.herolist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliversanz.marvel.android.base.BaseActivity
import com.oliversanz.marvel.android.ui.herodetail.HeroDetailActivity
import com.oliversanz.marvel.databinding.ActivityHeroListBinding
import com.oliversanz.marvel.domain.model.ResultEvent
import com.oliversanz.marvel.domain.model.ResultObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListActivity : BaseActivity() {

    private val binding: ActivityHeroListBinding by lazy {
        ActivityHeroListBinding.inflate(layoutInflater)
    }

    private val viewModel: HeroListViewModel by viewModel()
    private val heroAdapter = HeroListAdapter(ClickHandler())

    private val heroLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private val scrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (heroLayoutManager.findLastVisibleItemPosition() > (heroAdapter.itemCount - 10)) {
                binding.rvHeroList.removeOnScrollListener(this)
                showLoading()
                viewModel.nextPage().observe(this@HeroListActivity) {
                    hideLoading()
                    when (it) {
                        is ResultObject.Success -> {
                            if (it.data) {
                                binding.rvHeroList.addOnScrollListener(this)
                            }
                        }
                        is ResultObject.Error -> showError(it.throwable)
                    }
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvHeroList.apply {
            this.adapter = heroAdapter
            this.layoutManager = heroLayoutManager
        }

        viewModel.heroList.observe(this, heroAdapter::updateData)

        showLoading()
        viewModel.loadHeroList().observe(this) {
            hideLoading()
            when (it) {
                is ResultEvent.Success -> { binding.rvHeroList.addOnScrollListener(scrollListener) }
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