package com.cyj.chatui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val context: Context) : RecyclerView.Adapter<ChatAdapter.ViewHolder>()  {
    var mChat = mutableListOf<Chat>()
    val MSG_TYPE_LEFT = 0
    val MSG_TYPE_RIGHT = 1


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val show_message: TextView = itemView.findViewById(R.id.show_message)

        fun bind(item: Chat) {
            show_message.text = item.chat
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == MSG_TYPE_RIGHT) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false)
            return ViewHolder(view)
        }else{
            val view =
                LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false)
            return ViewHolder(view)
        }
    }

    override fun getItemCount(): Int = mChat.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mChat[position])
    }

    override fun getItemViewType(position: Int): Int {
        if (mChat.get(position).isOther) {
            return MSG_TYPE_RIGHT
        } else {
           return MSG_TYPE_LEFT
        }
    }
}