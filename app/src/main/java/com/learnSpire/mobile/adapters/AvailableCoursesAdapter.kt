package com.learnSpire.mobile.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.learnSpire.mobile.R
import com.learnSpire.mobile.models.Course

class AvailableCoursesAdapter(private val courseList: List<Course>): RecyclerView.Adapter<AvailableCoursesAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_available_courses, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course: Course = courseList[position]

        // set the course name
        holder.courseNameText.text = course.name

        // generate random color
        val generator = ColorGenerator.MATERIAL

        // generate thumbnail image
        val drawable = TextDrawable.builder().buildRect("", generator.getRandomColor())

        // set the image
        holder.imageView.setImageDrawable(drawable)

        // enroll the user to the course
        holder.enrollButton.setOnClickListener {

        }

    }

    //number of the items in the list
    override fun getItemCount(): Int {
        return courseList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardview_available_courses)
        val courseNameText: TextView = itemView.findViewById(R.id.text_course_name)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val enrollButton: Button = itemView.findViewById(R.id.enrollButton)
    }
}