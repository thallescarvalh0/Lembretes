package br.edu.ifsp.scl.sdm.pa2.lembretes.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.pa2.lembretes.databinding.LembreteViewBinding

class LembretesAdapter(private val lembretesList: MutableList<String>):RecyclerView.Adapter<LembretesAdapter.LembreteViewHolder>() {
    class LembreteViewHolder(lembreteViewBinding: LembreteViewBinding):RecyclerView.ViewHolder(lembreteViewBinding.root){
        val lembreteTv: TextView = lembreteViewBinding.lembreteTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LembreteViewHolder =
        LembreteViewHolder(LembreteViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LembreteViewHolder, position: Int) =
        holder.lembreteTv.setText(lembretesList[position])

    override fun getItemCount(): Int = lembretesList.size
}