package com.learnSpire.mobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learnSpire.mobile.R
import com.learnSpire.mobile.models.MarksResponse

class MarksAdapter(var context: Context, var marksList: List<MarksResponse>?) :
    RecyclerView.Adapter<MarksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_marks, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (marksList != null && marksList!!.size > 0) {
            val (courseName, marks, grade) = marksList!![position]
            holder.textview_course_name.text = courseName.toString()
            holder.textview_marks.text = marks.toString()
            holder.textview_grade.text = grade.toString()
        } else {
            return
        }
    }

    override fun getItemCount(): Int {
        return marksList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview_course_name: TextView
        var textview_marks: TextView
        var textview_grade: TextView

        init {
            textview_course_name = itemView.findViewById(R.id.textview_course_name)
            textview_marks = itemView.findViewById(R.id.textview_marks)
            textview_grade = itemView.findViewById(R.id.textview_grade)
        }
    }
}