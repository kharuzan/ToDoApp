package com.jitusolution.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jitusolution.todoapp.R
import com.jitusolution.todoapp.databinding.TodoItemLayoutBinding
import com.jitusolution.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Any) -> Unit): RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>()
        ,TodoCheckedChangeListener,TodoEditClickListener {
    class TodoListViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent, false)
        return TodoListViewHolder(view)

    }
    override fun onCheckChanged(cb: CompoundButton,
                                isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }
    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)

        Navigation.findNavController(v).navigate(action)
    }


    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this
//        holder.view.checkTask.setText(todoList[position].title + " " + todoList[position].priority)
//
//        holder.view.imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//
//            Navigation.findNavController(it).navigate(action)
//
//        }
//
//        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
////            adapterOnClick(todoList[position])
//            if(isChecked) {
//                adapterOnClick(todoList[position])
//            }
//
//        }
    }
    override fun getItemCount(): Int {
        return todoList.size
    }


}