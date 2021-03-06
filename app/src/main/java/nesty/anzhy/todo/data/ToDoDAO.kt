package nesty.anzhy.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import nesty.anzhy.todo.data.models.ToDoData

@Dao
interface ToDoDAO {

    @Query("SELECT*FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>


    //suspend function mean that we want to run our function on a background thread later in the view model
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteData(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllData()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabaseData(searchQuery: String): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN  priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority():LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN  priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<ToDoData>>
}