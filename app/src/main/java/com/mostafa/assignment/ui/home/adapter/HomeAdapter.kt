package com.mostafa.assignment.ui.home.adapter

import android.content.Context
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.mostafa.assignment.BR.item
import com.mostafa.assignment.R
import com.mostafa.assignment.databinding.RecordItemRowBinding
import com.mostafa.assignment.domain.model.Record
import com.mostafa.assignment.domain.model.RecordYear
import com.mostafa.assignment.ui.DataBindingViewHolder
import kotlin.coroutines.coroutineContext

class HomeAdapter(
    private var items: ArrayList<RecordYear> = arrayListOf<RecordYear>()
) : androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.SimpleHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])

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

    inline fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding =
            RecordItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding) :
        DataBindingViewHolder<RecordYear>(dataBinding) {
        override fun onBind(t: RecordYear): Unit = with(t) {
            dataBinding.setVariable(item, t)
        }

        val q1Icon: ImageView = itemView.findViewById(R.id.q1_pic)
        val q2Icon: ImageView = itemView.findViewById(R.id.q2_pic)
        val q3Icon: ImageView = itemView.findViewById(R.id.q3_pic)
        val q4Icon: ImageView = itemView.findViewById(R.id.q4_pic)
    }

    fun add(list: ArrayList<RecordYear>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}