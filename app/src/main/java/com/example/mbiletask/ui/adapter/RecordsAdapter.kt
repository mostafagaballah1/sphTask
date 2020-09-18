package com.example.mbiletask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mbiletask.R
import com.example.mbiletask.data.remote.model.RecordYear
import com.example.mbiletask.databinding.RecordsYearItemBinding

class RecordsAdapter : RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {

    var items = ArrayList<RecordYear>()

    fun setRecordsList(records: ArrayList<RecordYear>) {
        this.items = records
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return ViewHolder(
            RecordsYearItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(items[position])
        holder.q1Icon.setOnClickListener {
            if (items[position].quarters[0].decrease > 0)
                Toast.makeText(it.context, it.context.getString(R.string.decrease_msg), Toast.LENGTH_LONG).show()
            else
                Toast.makeText(it.context, it.context.getString(R.string.increase_msg), Toast.LENGTH_LONG).show()
        }

        holder.q2Icon.setOnClickListener {
            if (items[position].quarters[1].decrease > 0)
                Toast.makeText(it.context, it.context.getString(R.string.decrease_msg), Toast.LENGTH_LONG).show()
            else
                Toast.makeText(it.context, it.context.getString(R.string.increase_msg), Toast.LENGTH_LONG).show()
        }

        holder.q3Icon.setOnClickListener {
            if (items[position].quarters[2].decrease > 0)
                Toast.makeText(it.context, it.context.getString(R.string.decrease_msg), Toast.LENGTH_LONG).show()
            else
                Toast.makeText(it.context, it.context.getString(R.string.increase_msg), Toast.LENGTH_LONG).show()
        }

        holder.q4Icon.setOnClickListener {
            if (items[position].quarters[3].decrease > 0)
                Toast.makeText(it.context, it.context.getString(R.string.decrease_msg), Toast.LENGTH_LONG).show()
            else
                Toast.makeText(it.context, it.context.getString(R.string.increase_msg), Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(private val itemBinding: RecordsYearItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun binding(record: RecordYear) {
            itemBinding.item = record
        }
        val q1Icon: ImageView = itemView.findViewById(R.id.q1_pic)
        val q2Icon: ImageView = itemView.findViewById(R.id.q2_pic)
        val q3Icon: ImageView = itemView.findViewById(R.id.q3_pic)
        val q4Icon: ImageView = itemView.findViewById(R.id.q4_pic)
    }
}