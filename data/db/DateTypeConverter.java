package com.example.sample_log_app.data.db;

import androidx.room.TypeConverter;

public class DateTypeConverter {
    @TypeConverter
    public static Double toDouble(String value) {
        return value == null ? null : Double.parseDouble(value);
    }

    @TypeConverter
    public static String toString(double value) {
        return value == 0 ? null : String.valueOf(value);
    }
}
