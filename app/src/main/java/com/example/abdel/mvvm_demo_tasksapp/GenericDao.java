package com.example.abdel.mvvm_demo_tasksapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public abstract class GenericDao<Template> {

    @Insert(onConflict = IGNORE)
    abstract void insert(Template t);

    @Delete
    abstract void delete(Template t);

    @Update
    abstract void update(Template t);
}
