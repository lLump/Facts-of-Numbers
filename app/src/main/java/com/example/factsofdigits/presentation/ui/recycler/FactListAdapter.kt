package com.example.factsofdigits.presentation.ui.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.factsofdigits.presentation.ui.recycler.model.FactView
import com.example.factsofdigits.presentation.viewmodel.model.FactInfo

class FactListAdapter(private val onItemClick: (fact: FactInfo) -> Unit) : RecyclerView.Adapter<FactListAdapter.FactHolder>() {
    private var factList = arrayListOf<FactInfo>()

    class FactHolder(var factItemView: FactView): RecyclerView.ViewHolder(factItemView) {
        @SuppressLint("SetTextI18n")
        fun updateFact(fact: FactInfo) {
            factItemView.numberView?.text = "${fact.number}:"
            factItemView.infoView?.text = fact.info
        }
    }

    fun addFact(fact: FactInfo) {
        factList.add(fact)
        notifyItemInserted(factList.size - 1)
    }

    fun addHistory(list: List<FactInfo>) {
        factList = ArrayList(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        factList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        holder.updateFact(factList[position])
        holder.factItemView.setOnClickListener {
            onItemClick.invoke(factList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        return FactHolder(FactView(parent.context))
    }

    override fun getItemCount() = factList.size
}