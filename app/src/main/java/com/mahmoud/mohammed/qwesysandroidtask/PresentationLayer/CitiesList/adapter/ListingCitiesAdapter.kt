package com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Local.Model.CityModel
import com.mahmoud.mohammed.qwesysandroidtask.PresentationLayer.CitiesList.presenter.ListingCitiesPresenter
import com.mahmoud.mohammed.qwesysandroidtask.R

import butterknife.BindView
import butterknife.ButterKnife
import kotterknife.bindView

class ListingCitiesAdapter(private val items: MutableList<CityModel>?, private val context: Context, private val presenter: ListingCitiesPresenter) : RecyclerView.Adapter<ListingCitiesAdapter.CitiesListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CitiesListHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_item_row, parent, false)
        return CitiesListHolder(v)
    }

    override fun onBindViewHolder(holder: CitiesListHolder, position: Int) {
        val cityModel = items!![position]
        holder.cityNameTv!!.text = cityModel.cityName
        //TODO Fill in your logic for binding the view.
    }

    fun updateCitiesList(items: List<CityModel>) {
        this.items!!.clear()
        this.items.addAll(items)
        this.notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }


    inner class CitiesListHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cityNameTv: TextView by bindView(R.id.city_bane_tv)

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            presenter.onItemInteraction(adapterPosition)
        }
    }
}