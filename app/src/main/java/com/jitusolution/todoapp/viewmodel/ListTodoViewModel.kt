package com.jitusolution.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.jitusolution.todoapp.model.Todo
import com.jitusolution.todoapp.model.TodoDatabase
import com.jitusolution.todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
//    val todoLoadErrorLD = MutableLiveData<Boolean>()
//    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    fun refresh() {
//        loadingLD.value = true
//        todoLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())

            todoLD.value = db.tododao().selectAllTodo()
        }
    }
    fun clearTask(todo: Todo) {
        launch {
            val db = buildDB(getApplication())
            db.tododao().todoDone(todo.uuid)
            todoLD.value = db.tododao().selectAllTodo()
        }
    }



    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}