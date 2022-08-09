package com.example.moviestask.ui.adapters

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.moviestask.R
import com.example.moviestask.data.models.GenresModel
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.databinding.TabItemBinding
import com.google.android.material.button.MaterialButton
import kotlin.math.log

class TabsAdapter(
    private val listOfTabs: List<GenresModel>,
    private val onClick: (GenresModel,ViewGroup) -> Unit,)
    : BaseItemAdapter<MovieModel>() {

    init {
        // TODO: Set item stable ids
//        setHasStableIds(true)
    }


    override fun getItemCount(): Int = listOfTabs.size


    override fun adapterItems(): ArrayList<MovieModel> {
        return arrayListOf()
    }

    override fun hasMore(): Boolean {
        return false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TabItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        Log.i("TAG", "onCreateViewHolder: ")
        return TabViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemHolder = holder as TabViewHolder
        Log.i("TAG", "onBindViewHolder: ")
        itemHolder.setData(listOfTabs.get(position) , onClick)
        itemHolder.itemView.setOnClickListener {
            onClick(listOfTabs.get(position),itemHolder.layout)
            Log.i("TAG", "onBindViewHolder:a7aaaa "+it)
        }
    }


    class TabViewHolder(binding: TabItemBinding) : MyViewHolder(binding.root) {
         val tab: MaterialButton = binding.bTab
        val layout: ConstraintLayout = binding.tabLayout

        /**
         * Set item data
         */
        fun setData(genresModel: GenresModel,onClick: (GenresModel,ViewGroup) -> Unit) {
            Log.i("TAG", "setData:1 " )
            val index = layoutPosition
            val context = itemView.context
            tab.text = genresModel.name
            tab.setOnClickListener{
                Log.i("TAG", "setData:2 " )

                onClick(genresModel,layout)
            }
        }
    }


}