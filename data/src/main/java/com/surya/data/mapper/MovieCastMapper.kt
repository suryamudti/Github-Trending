package com.surya.data.mapper

import com.surya.data.entity.Cast
import com.surya.data.entity.MovieCast

/**
 * @author by furqan on 02/12/2019
 */
object MovieCastMapper {

    fun transformFromCastList(cast: List<Cast>): List<MovieCast> {
        val list = arrayListOf<MovieCast>()
        cast.forEach { list.add(transformFromCast(it)) }
        return list
    }

    private fun transformFromCast(cast: Cast): MovieCast =
        MovieCast(cast.getProfileUrl(), cast.name)

}