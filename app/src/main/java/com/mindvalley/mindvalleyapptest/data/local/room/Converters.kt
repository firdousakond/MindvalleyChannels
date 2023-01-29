package com.mindvalley.mindvalleyapptest.data.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.mindvalleyapptest.data.model.*

class Converters {

    @TypeConverter
    fun fromCoverAsset(cover: CoverAsset?): String? {
        val type = object : TypeToken<CoverAsset>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toCoverAsset(str: String?): CoverAsset? {
        val type = object : TypeToken<CoverAsset>() {}.type
        return Gson().fromJson<CoverAsset>(str, type)
    }

    @TypeConverter
    fun fromIconAsset(cover: IconAsset?): String? {
        val type = object : TypeToken<IconAsset>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toIconAsset(str: String?): IconAsset? {
        val type = object : TypeToken<IconAsset>() {}.type
        return Gson().fromJson<IconAsset>(str, type)
    }

    @TypeConverter
    fun fromChannelX(cover: ChannelX?): String? {
        val type = object : TypeToken<ChannelX>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toChannelX(str: String?): ChannelX? {
        val type = object : TypeToken<ChannelX>() {}.type
        return Gson().fromJson<ChannelX>(str, type)
    }

    @TypeConverter
    fun fromSeryList(seryList: List<Sery?>?): String? {
        val type = object : TypeToken<List<Sery>>() {}.type
        return Gson().toJson(seryList, type)
    }

    @TypeConverter
    fun toSeryList(seryString: String?): List<Sery>? {
        val type = object : TypeToken<List<Sery>>() {}.type
        return Gson().fromJson<List<Sery>>(seryString, type)
    }

    @TypeConverter
    fun fromMediaList(mediaList: List<LatestMedia?>?): String? {
        val type = object : TypeToken<List<LatestMedia>>() {}.type
        return Gson().toJson(mediaList, type)
    }

    @TypeConverter
    fun toMediaList(mediaString: String?): List<LatestMedia>? {
        val type = object : TypeToken<List<LatestMedia>>() {}.type
        return Gson().fromJson<List<LatestMedia>>(mediaString, type)
    }

    @TypeConverter
    fun fromLatestMedia(cover: LatestMedia?): String? {
        val type = object : TypeToken<LatestMedia>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toLatestMedia(str: String?): LatestMedia? {
        val type = object : TypeToken<LatestMedia>() {}.type
        return Gson().fromJson<LatestMedia>(str, type)
    }

    @TypeConverter
    fun fromSery(cover: Sery?): String? {
        val type = object : TypeToken<Sery>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toSery(str: String?): Sery? {
        val type = object : TypeToken<Sery>() {}.type
        return Gson().fromJson<Sery>(str, type)
    }

}