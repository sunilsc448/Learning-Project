package RXJava

import pojos.Task

class DataSourceTasks {
    companion object{
        fun getTasks():List<Task>{
           val list = ArrayList<Task>()
            list.add(Task("Task one", 3, false))
            list.add(Task("Task two", 5, false))
            list.add(Task("Task three", 0, true))
            list.add(Task("Task four", 1, false))
            list.add(Task("Task five", 2, true))
            return list
        }
    }
}