package com.cyj.slidingrecyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class ExpandableAdapter(private val context: Context, private val personList: List<Person> ) : RecyclerView.Adapter<ExpandableAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personList[position])

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
        val imgMore = itemView.findViewById<ImageButton>(R.id.img_more)
        val layoutExpand = itemView.findViewById<LinearLayout>(R.id.layout_expand)
        val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.parent)

        fun active(person: Person, it: View){
            ToggleAnimation.toggleArrow(imgMore, !person.isExpanded)
            val show = toggleLayout(!person.isExpanded, it, layoutExpand)
            person.isExpanded = show
        }
        fun bind(person: Person){
            txtName.text = person.name

            imgMore.setOnClickListener {
                // 1
                active(person, it)
            }

            txtName.setOnClickListener {
                active(person, it)
            }

            constraintLayout.setOnClickListener {
                active(person, it)
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout): Boolean {
        // 2
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        } else {
            ToggleAnimation.collapse(layoutExpand)
        }
        return isExpanded
    }

    override fun getItemCount(): Int {

        return personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }



}
