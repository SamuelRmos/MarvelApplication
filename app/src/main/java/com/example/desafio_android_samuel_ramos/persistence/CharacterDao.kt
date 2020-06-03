package com.example.desafio_android_samuel_ramos.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.desafio_android_samuel_ramos.model.Characters

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterList(characters: MutableList<Characters>?)

    @Query("SELECT * FROM Characters WHERE id = :id_")
    fun getCharacter(id_: Int): Characters

    @Query("SELECT * FROM Characters")
    fun getCharacterList(): MutableList<Characters>
}