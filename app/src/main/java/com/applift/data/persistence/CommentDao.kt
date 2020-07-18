package com.applift.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.applift.data.model.Comment
import io.reactivex.Single

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: Comment): Single<Long>

    @Query("Select * from comment WHERE task_id = :task_id")
    fun getAllComments(task_id: Int): LiveData<List<Comment>>
}