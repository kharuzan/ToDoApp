package com.jitusolution.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.jitusolution.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton,
                       isChecked:Boolean,
                       obj: Todo)
}
interface TodoEditClickListener {
    fun onTodoEditClick(v: View)
}
interface RadioClickListener {
    fun onRadioClick(v:View, priority:Int, obj:Todo)
}
interface TodoSaveChangesClickListener {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}



