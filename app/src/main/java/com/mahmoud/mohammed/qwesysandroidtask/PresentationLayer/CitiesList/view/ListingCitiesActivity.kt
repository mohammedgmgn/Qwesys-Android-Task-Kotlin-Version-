package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.view

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.ListingCitiesContract
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.adapter.ListingCitiesAdapter
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.presenter.ListingCitiesPresenter
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.WeatherResult.view.WeatherResultActivity
import com.mahmoud.mohammed.qwesysandroidtask.R

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.login.widget.LoginButton
import kotterknife.bindView

class ListingCitiesActivity : AppCompatActivity(), ListingCitiesContract.ListCitiesView {
    val addNewCityBtn: FloatingActionButton by bindView(R.id.add_city_btn_id)
    val recyclerView: RecyclerView by bindView(R.id.cities_rec_view)
    val progressBar: ProgressBar by bindView(R.id.list_progress_bar)

    internal lateinit var toolbar: Toolbar
    internal lateinit var presenter: ListingCitiesPresenter
    internal lateinit var adapter: ListingCitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_list)
        ButterKnife.bind(this)
        presenter = ListingCitiesPresenter(this, this)
        initUi()

        presenter.getCities()
        addNewCityBtn.setOnClickListener { view -> showChangeLangDialog() }
    }

    @SuppressLint("NewApi")
    private fun initUi() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(R.string.cities_list_title)
        setSupportActionBar(toolbar)
        adapter = ListingCitiesAdapter(ArrayList(), this, presenter)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        val defaultItemAnimator = DefaultItemAnimator()
        defaultItemAnimator.addDuration = 1000
        defaultItemAnimator.removeDuration = 1000
        recyclerView.itemAnimator = defaultItemAnimator
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0)
                    addNewCityBtn.hide()
                else if (dy < 0)
                    addNewCityBtn.show()

            }
        })
        recyclerView.adapter = adapter

    }

    fun showChangeLangDialog() {
        val alertDialog = AlertDialog.Builder(this@ListingCitiesActivity)
        alertDialog.setTitle(getString(R.string.add_new_city))
        alertDialog.setMessage(getString(R.string.city_name))
        val input = EditText(this@ListingCitiesActivity)
        val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp
        alertDialog.setView(input)
        alertDialog.setPositiveButton(getString(R.string.done)
        ) { dialog, which -> presenter.saveNewCity(input.text.toString()) }

        alertDialog.setNegativeButton(getString(R.string.cancel)
        ) { dialog, which -> dialog.cancel() }

        alertDialog.show()
    }

    override fun startWeatherResultActivity(selectedCity: CityModel) {
        val intent = Intent(this,   WeatherResultActivity::class.java)
        intent.putExtra(getString(R.string.city_obj_key), selectedCity)
        startActivity(intent)
    }

    override fun onCitySavedSuccesufully() {
        Toast.makeText(applicationContext, getString(R.string.success), Toast.LENGTH_SHORT).show()
    }

    override fun onCityFaillToSave() {
        Toast.makeText(applicationContext, getString(R.string.faill_save_error), Toast.LENGTH_SHORT).show()

    }

    override fun onGetCitiesSuccess(cityModelList: List<CityModel>) {
        adapter.updateCitiesList(cityModelList)
        Log.v("sizeList", cityModelList.size.toString() + "")
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE

    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }

    override fun showErrMsg(err: String) {
        Toast.makeText(applicationContext, err, Toast.LENGTH_SHORT).show()

    }

    override fun showNoInternetMsg() {

    }
}

