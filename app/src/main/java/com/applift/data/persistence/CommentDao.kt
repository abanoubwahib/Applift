package com.applift.data.persistence

import androidx.room.*
import com.applift.data.model.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: Comment): Long

    @Query("Select * from comment WHERE task_id = :task_id")
    suspend fun getAllComments(task_id: Int): List<Comment>
}