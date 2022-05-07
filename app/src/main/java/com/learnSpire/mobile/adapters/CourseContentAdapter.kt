package com.learnSpire.mobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.learnSpire.mobile.R
import com.learnSpire.mobile.models.Content

class CourseContentAdapter(private val contentList: List<Content>): RecyclerView.Adapter<CourseContentAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_course_content, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content: Content = contentList[position]

        // set the course name
        holder.contentTitleText.text = content.title

        // set the date
        holder.contentDateText.text = content.timeStamp.subSequence(0, 10)

        // set the content body
        holder.contentBodyText.text = content.content

    }

    //number of the items in the list
    override fun getItemCount(): Int {
        return contentList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardview_course_content)
        val contentTitleText: TextView = itemView.findViewById(R.id.content_text_content_title)
        val contentDateText: TextView = itemView.findViewById(R.id.content_text_date)
        val contentBodyText: TextView = itemView.findViewById(R.id.content_text_body)
    }
}