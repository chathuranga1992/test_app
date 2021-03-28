package com.example.sample_log_app.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.sample_log_app.data.db.entity.User;
import com.example.sample_log_app.data.db.entity.UserDAO;


@Database(entities = {User.class,}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class SampleAppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "SampleAppDatabase";
    private static SampleAppDatabase INSTANCE;

    public static SampleAppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (SampleAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, SampleAppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDAO getUserDAO();
}
