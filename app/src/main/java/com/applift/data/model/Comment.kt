package com.applift.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(

    @ColumnInfo(name = "task_id")
    var task_id: Int,

    @ColumnInfo(name = "comment_str")
    var comment_str: String?
) {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    override fun toString(): String {
        return "{id=$id, task_id=$task_id, comment_str=$comment_str}"
    }
}