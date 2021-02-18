package stas.batura.bookreader.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import stas.batura.bookreader.R

class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {

    private val colors = intArrayOf(
        android.R.color.black,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return PagerVH(view)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PagerVH, position: Int)  {

        val text = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        text.text = "item $position"
//        holder.itemView.run {
//            tvTitle.text = "item $position"
//            container.setBackgroundResource(colors[position])
//        }
    }


}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun test() {

    }

}