package com.learnSpire.mobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.learnSpire.mobile.R
import com.learnSpire.mobile.models.Course
import com.learnSpire.mobile.models.GetNotificationsResponse

class NotificationsAdapter(private val notificationsList: List<GetNotificationsResponse>): RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    companion object {
        public var courseId = ""
        public var courseName = ""
        public var title = ""
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_notifications, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification: GetNotificationsResponse = notificationsList[position]

        // generate random color
        val generator = ColorGenerator.MATERIAL

        // get the first letter of the course name
        val letter = notification.courseName[0].toString()

        // generate thumbnail image
        val drawable = TextDrawable.builder()
            .buildRound(letter, generator.getRandomColor())

        // set the thumbnail image
        holder.imageView.setImageDrawable(drawable)

        // set the course name
        holder.courseNameText.text = notification.courseName

        // set the notification title
        holder.courseTitleText.text = notification.title

        // navigate to the notification content fragment and transfer data bundle
        holder.cardView.setOnClickListener {

            // set the course id and notification title to access from other fragments or activities
            courseId = notification.courseId
            courseName = notification.courseName
            title = notification.title

            // navigate to the course content activity
            it.findNavController().navigate(R.id.action_NotificationsFragment_to_NotificationContentActivity)
        }
    }

    //number of the items in the list
    override fun getItemCount(): Int {
        return notificationsList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardview_notifications)
        val courseNameText: TextView = itemView.findViewById(R.id.text_course_name)
        val courseTitleText: TextView = itemView.findViewById(R.id.text_notification_title)
        val imageView: ImageView = itemView.findViewById(R.id.profilePictureImageView)
    }
}