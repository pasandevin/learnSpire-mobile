package com.learnSpire.mobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learnSpire.mobile.R
import com.learnSpire.mobile.models.GetCourseMarksResponse

class CourseMarksAdapter(var context: Context, var courseMarksList: List<GetCourseMarksResponse>?) :
    RecyclerView.Adapter<CourseMarksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_add_marks, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (courseMarksList != null && courseMarksList!!.size > 0) {
            val (firstName, lastName, mark) = courseMarksList!![position]
            holder.textview_student_name.text = firstName + " " + lastName
            holder.textview_mark.text = mark.toString()
        } else {
            return
        }
    }

    override fun getItemCount(): Int {
        return courseMarksList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview_student_name: TextView
        var textview_mark: TextView

        init {
            textview_student_name = itemView.findViewById(R.id.text_student_name)
            textview_mark = itemView.findViewById(R.id.text_marks)
        }
    }
}