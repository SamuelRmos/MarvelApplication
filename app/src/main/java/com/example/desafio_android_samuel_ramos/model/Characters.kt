package com.example.desafio_android_samuel_ramos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = "Characters")
data class Characters(
    @PrimaryKey var id: Int,
    var name: String,
    var description: String,
    var thumbnail: @RawValue Thumbnail
) : Parcelable
