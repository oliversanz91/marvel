package com.oliversanz.marvel.android.herodetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliversanz.marvel.R
import com.oliversanz.marvel.android.base.BaseActivity
import com.oliversanz.marvel.android.ext.openUrl
import com.oliversanz.marvel.data.network.repository.HeroApi
import com.oliversanz.marvel.databinding.ActivityHeroDetailBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroDetailActivity : BaseActivity() {

    companion object {
        private const val HERO_ID_PARAM = "hero_id"
        fun getIntent(context: Context, heroId: String) : Intent =
            Intent(context, HeroDetailActivity::class.java).apply {
                this.putExtra(HERO_ID_PARAM, heroId)
            }
    }

    private val viewModel: HeroDetailViewModel by viewModel()
    private val binding: ActivityHeroDetailBinding by lazy {
        ActivityHeroDetailBinding.inflate(layoutInflater)
    }

    private val heroId: String by lazy { intent.getStringExtra(HERO_ID_PARAM).orEmpty() }
    private val urlAdapter = UrlListAdapter(UrlClickHandler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.hero = viewModel.hero
        binding.rvUrls.adapter = urlAdapter
        binding.lifecycleOwner = this

        viewModel.hero.observe(this) {
            title = it.name
            urlAdapter.updateData(it.url)
        }

        showLoading()
        viewModel.loadHero(heroId).observe(this) {
            hideLoading()
        }

        setContentView(binding.root)
    }

    inner class UrlClickHandler {
        fun openUrl(url: String) {
            this@HeroDetailActivity.openUrl(url)
        }
    }
}