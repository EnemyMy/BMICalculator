package com.example.app_25_weightapp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_25_weightapp.R
import com.example.app_25_weightapp.computeBmi
import com.example.app_25_weightapp.databinding.RecyclerItemBinding
import com.example.app_25_weightapp.model.ProfileEntity
import com.example.app_25_weightapp.model.WeightLogEntity

class RecyclerAdapter: ListAdapter<WeightLogEntity, RecyclerAdapter.RecyclerHolder>(DIFF_CALLBACK) {

    private lateinit var profileEntity: ProfileEntity

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeightLogEntity>() {
            override fun areItemsTheSame(oldItem: WeightLogEntity, newItem: WeightLogEntity) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WeightLogEntity, newItem: WeightLogEntity) =
                oldItem.date == newItem.date && oldItem.weight == newItem.weight
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        return RecyclerHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitProfile(profileEntity: ProfileEntity) {
        this.profileEntity = profileEntity
        if (itemCount > 0) {
            notifyDataSetChanged()
        }
    }

    inner class RecyclerHolder(item: RecyclerItemBinding): RecyclerView.ViewHolder(item.root) {

        private val root = item.root
        private val weight = item.recyclerItemWeight
        private val bmi = item.recyclerItemBmi
        private val date = item.recyclerItemDate

        fun bind(weightLogEntity: WeightLogEntity) {
            weight.text = weightLogEntity.weight
            bmi.text = computeBmi(weightLogEntity.weight, profileEntity.height)
            date.text = weightLogEntity.date
            val bmiF = bmi.text.toString().toFloat()
            when {
                bmiF <= 16 -> root.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBMIVeryLow))
                bmiF in 16.0..18.5 -> root.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBMILow))
                bmiF in 18.5..25.0 -> root.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBMINormal))
                bmiF in 25.0..30.0 -> root.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBMIExcess))
                bmiF in 30.0..35.0 -> root.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBMIHigh))
                bmiF in 35.0..40.0 -> root.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBMISuperHigh))
                bmiF >= 40 -> root.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBMIUltraHigh))
            }
        }

    }

}