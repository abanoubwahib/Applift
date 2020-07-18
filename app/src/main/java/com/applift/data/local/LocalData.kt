package com.applift.data.local

import com.applift.data.persistence.CommentDao
import com.applift.data.persistence.ProjectDao
import com.applift.data.persistence.TaskDao
import javax.inject.Inject

class LocalData @Inject constructor(
    projectDao: ProjectDao,
    taskDao: TaskDao,
    commentDao: CommentDao
) {


}
