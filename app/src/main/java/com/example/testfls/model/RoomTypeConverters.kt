//package com.example.testfls.model
//
//import androidx.room.TypeConverter
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//
//object RoomTypeConverters {
//
//    private val formatter = DateTimeFormatter.ofPattern("EEE, dd LLL yyyy HH:mm:ss")
//
//    @TypeConverter
//    @JvmStatic
//    fun toLocalDateTime(value: String?): LocalDateTime? {
//        return value?.let {
//            return formatter.parse(value, LocalDateTime::from)
//        }
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun fromLocalDateTime(date: LocalDateTime?): String? {
//        return date?.format(formatter)
//    }
//}