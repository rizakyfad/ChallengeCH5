package com.rizaki.challengech5.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class DetailMovie(
    val image: String,
    val title: String,
    val media_type: String,
    val date: String,
    val overview: String
) : Parcelable