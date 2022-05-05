package com.learnSpire.mobile.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.learnSpire.mobile.R
import com.learnSpire.mobile.models.Course

class EnrolledCoursesAdapter(private val courseList: List<Course>): RecyclerView.Adapter<EnrolledCoursesAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_enrolled_courses, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course: Course = courseList[position]

        holder.courseNameText.text = course.name

        //create a bundle to pass data to other fragments
        val bundle = Bundle()
        bundle.putString("courseId", course.id)

        // navigate to map fragment and transfer data bundle
        holder.cardView.setOnClickListener {
//            it.findNavController().navigate(R.id.action_FirstFragment_to_MapsFragment, bundle)
        }
    }

    //number of the items in the list
    override fun getItemCount(): Int {
        return courseList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardview_enrolled_courses)
        val courseNameText: TextView = itemView.findViewById(R.id.text_course_name)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}