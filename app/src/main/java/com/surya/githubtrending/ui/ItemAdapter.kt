package com.surya.githubtrending.ui

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.surya.githubtrending.R
import com.surya.githubtrending.data.local.entities.Trend
import com.surya.githubtrending.util.hide
import de.hdodenhof.circleimageview.CircleImageView
import io.github.hendraanggrian.expandablelayoutrecyclerview.ExpandableLayoutItem
import io.github.hendraanggrian.expandablelayoutrecyclerview.ExpandableLayoutItem.OnExpandListener
import io.github.hendraanggrian.expandablelayoutrecyclerview.ExpandableLayoutRecyclerView


/**
 * Created by suryamudti on 14/11/2019.
 */

class ItemAdapter (
    layout:LinearLayoutManager
):ExpandableLayoutRecyclerView.Adapter<ItemAdapter.ViewHolder>(layout){

    private val data = arrayListOf<Trend>()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.isClickable = true

        val trend = data[position]

        holder.author.text = trend.author
        holder.fork.text = trend.forks.toString()
        holder.language.text = trend.language
        holder.repoName.text = trend.name
        holder.star.text = trend.stars.toString()
        holder.url.text = trend.url

        if(!trend.languageColor.isNullOrEmpty()){
            val color = Color.parseColor(trend.languageColor)
            holder.languageColor.setBackgroundResource(R.drawable.circle)
            holder.languageColor.circleBackgroundColor = color
        }else{
            holder.languageColor.hide()
            holder.language.hide()
        }

        holder.item.setOnExpandListener(object : OnExpandListener {
            override fun onExpanding() {
                holder.item.background =  context.resources.getDrawable(R.drawable.custom_ripple)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.divider.apply {
                        elevation = 50f
                    }
                }
            }

            override fun onCollapsing() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.divider.apply {
                        elevation = 0f
                    }
                }
            }
        })

        Glide.with(context)
            .load(trend.avatar)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.imageHeader)
    }

    fun setData(list: List<Trend>){
        data.addAll(list)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : ExpandableLayoutRecyclerView.ViewHolder(itemView){

        val imageHeader = item.headerLayout.findViewById<ImageView>(R.id.repo_image)
        val author = item.headerLayout.findViewById<TextView>(R.id.repo_author)
        val repoName = item.headerLayout.findViewById<TextView>(R.id.repo_name)

        val url = item.contentLayout.findViewById<TextView>(R.id.repo_url)
        val language = item.contentLayout.findViewById<TextView>(R.id.repo_language)
        val languageColor = item.contentLayout.findViewById<CircleImageView>(R.id.language_indicator)
        val star = item.contentLayout.findViewById<TextView>(R.id.repo_stars_count)
        val fork = item.contentLayout.findViewById<TextView>(R.id.repo_forks_count)
        var divider = itemView.findViewById<LinearLayout>(R.id.divider)

        override fun getItem(): ExpandableLayoutItem = itemView.findViewById<ExpandableLayoutItem>(R.id.row)

    }

}