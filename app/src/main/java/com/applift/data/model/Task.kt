package com.applift.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "created_date")
    var createdDate: String?,

    @ColumnInfo(name = "project_id")
    var project_id: String
) {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "status")
    var status: String? = "To-Do"

    override fun toString(): String {
        return "{id=$id, title='$title', description=$description, createdDate=$createdDate, project_id='$project_id', status=$status}"
    }
}