package com.example.sample_log_app.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;


public final class IOUtils {
    public static String loadJsonFromFile(Context context, String filename) {

        final StringBuilder output = new StringBuilder();
        final char[] buffer = new char[1024];
        BufferedReader reader = null;
        try {
            AssetFileDescriptor descriptor =  context.getAssets().openFd(filename);
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            for (int i = reader.read(buffer); i != -1; i = reader.read(buffer)) {
                output.append(buffer);
            }
        } catch (IOException ioe) {
            Log.d("Failed loading json from file", ioe.getMessage());
        } finally {
            closeQuietly(reader);
        }
        return output.toString();
    }

    public static void write(byte data[], OutputStream output) throws IOException {
        try {
            if (data != null) {
                output.write(data);
            }
        } finally {
            closeQuietly(output);
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            // not a big deal
        }
    }


}
