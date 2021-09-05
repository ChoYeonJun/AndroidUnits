package com.cyj.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var personList: List<Person>
    private lateinit var adapter: ExpandableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_list)

        personList = ArrayList()
        personList = loadData()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpandableAdapter(this, personList)
        recyclerView.adapter = adapter

//        adapter.setItemClickListener(object : ExpandableAdapter.OnItemClickListener{
//            override fun onClick(view: View, position: Int) {
//                val show = toggleLayout(!personList[position].isExpanded, it, layoutExpand)
//                person.isExpanded = show
//            }
//        })

    }

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout): Boolean {
        // 2
        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        } else {
            ToggleAnimation.collapse(layoutExpand)
        }
        return isExpanded
    }

    private fun loadData(): List<Person> {
        val people = ArrayList<Person>()

        val persons = resources.getStringArray(R.array.people)

        for (i in persons.indices) {
            val person = Person().apply {
                name = persons[i]
            }
            people.add(person)
        }
        return people
    }
}