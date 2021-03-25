package com.example.sample_log_app.util;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public final class StringUtils {

    public static final String EMPTY_STR = "";
    private static final float costForMIN = 2.0f;
    private static double mWaitingCost = 0.0;

    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isEmpty(CharSequence sequence) {
        final String str = sequence == null ? null : sequence.toString();
        return (str == null) || (str.isEmpty()) || (str.trim().isEmpty());
    }


    public static boolean isNotEmpty(CharSequence sequence) {
        return !isEmpty(sequence);
    }

    public static boolean equals(String lh, String rh) {
        return lh == null ? rh == null : lh.equals(rh);
    }

    public static boolean equalsIgnoreCase(String lh, String rh) {
        return lh == null ? rh == null : lh.equalsIgnoreCase(rh);
    }

    public static boolean containsIgnoreCase(String value, String pattern) {
        return !((value == null) || (pattern == null)) && value.toLowerCase().contains(pattern.toLowerCase());
    }

    public static boolean startWithIgnoreCase(String value, String pattern) {
        return !((value == null) || (pattern == null)) && value.toLowerCase().startsWith(pattern);
    }

    public static float stringToFloat(String input) {
        return isEmpty(input) ? 0.0f : Float.parseFloat(input);
    }

    public static int stringToInt(String input) {
        return Integer.parseInt(input);
    }

    public static Double stringToDouble(String input) {
        return Double.parseDouble(input);
    }

    public static String formatDouble(double val) {
        return String.format("%.4f", val);
    }

    public static String getTemp(double val) {
        double temp = val - 273.15f;
//    " \u2103"
        return String.format("%.0f", temp)+" \u00B0";
    }

    public static String formatRating(double val) {
        return String.format("%.1f", val);
    }

    public static String toTitleCase(String str) {
        if (str == null) {
            return null;
        }
        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidPhone(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target).matches();
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return parseFormat.format(date);
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return parseFormat.format(date);
    }

    public static String getCurrentTime() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm a");
        Date date = new Date();
        return parseFormat.format(date);
    }

    public static String getDisplayCurrentTime() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = new Date();
        return parseFormat.format(date);
    }

    public static String getCurrentDateTime(Date date) {
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return parseFormat.format(date);
    }

    public static boolean compareTime(String startTime, String endTime) {
        String getCurrentTime = eventTimeFormat(startTime);
        String getTestTime = eventTimeFormat(endTime);

        if (getCurrentTime.compareTo(getTestTime) < 0) {
            return true;
        } else {
            return false;
        }

    }

    private static String eventTimeFormat(String dateF) {
        dateF = dateF.replace("T", " ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm a");

        Date date = null;
        try {
            date = dateFormat.parse(dateF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseFormat.format(date);

    }

    public static String getDDateFromString(String dateF) {
        //dateF = dateF.replace("T", " ");
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = getTimeFromString(dateF);
//        try {
//            date = dateFormat.parse(dateF);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }

    public static String getDobDateFromString(String dateF) {
        //dateF = dateF.replace("T", " ");
        SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date date = null;
        try {
            date = dateFormat.parse(dateF);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }

    public static String getDobDateFromDb(String dateF) {
        //dateF = dateF.replace("T", " ");
        SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date date = null;
        try {
            date = dateFormat.parse(dateF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }

    public static String getDTimeFromString(String dateF) {
        //dateF = dateF.replace("T", " ");
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm a");

        Date date = getTimeFromString(dateF);
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }

    public static String getDisplayTimeFromString(String dateF) {
        //dateF = dateF.replace("T", " ");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");

        Date date = getTimeFromString(dateF);
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }


    public static Date getTimeFromString(String dateF) {
        // dateF = dateF.replace("T", " ");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date date = null;
        try {
            date = df.parse(dateF);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    public static Date getDateFromString(String dateF) {
        //dateF = dateF.replace("T", " ");
        //SimpleDateFormat parseFormat = new SimpleDateFormat("MMMM dd,yyyy   hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        Date date = null;
        try {
            date = dateFormat.parse(dateF);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getFormatDate(String input, boolean isFromPreview) {
        Date dateInput = null;
        if (isFromPreview) {
            dateInput = getDateFromString(input);
        } else {
            dateInput = getTimeFromString(input);
        }
        SimpleDateFormat format = new SimpleDateFormat("d");
        String date = format.format(dateInput);

        if (date.endsWith("1") && !date.endsWith("11"))
            format = new SimpleDateFormat("EE, d'st' MMMM hh:mm a");
        else if (date.endsWith("2") && !date.endsWith("12"))
            format = new SimpleDateFormat("EE, d'nd' MMMM hh:mm a");
        else if (date.endsWith("3") && !date.endsWith("13"))
            format = new SimpleDateFormat("EE, d'rd' MMMM hh:mm a");
        else
            format = new SimpleDateFormat("EE, d'th' MMMM hh:mm a");

        return format.format(dateInput);
    }


    public static SpannableString formatString(String input, int start, int end, float size, int color) {
        SpannableString ss = new SpannableString(input);
        ss.setSpan(new RelativeSizeSpan(size), start, end, 0); // set size
        ss.setSpan(new ForegroundColorSpan(color), start, end, 0);// set color
        return ss;
    }


    public static String textToTitleCase(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0))).append(words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        return sb.toString();
    }

    public static String getFormatPrice(String price) {
        return "Rs " + price;
    }

    public static String getDropOffText() {
        String text = "<font color=#969696>Enter </font> <font color=#e53e2c>Drop of</font><font color=#969696> Location</font>";
        return text;
    }

    public static String getTimeZone() {

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        L.d("Time zone", "=" + tz.getID());

        return tz.getID();
    }


    public static String getDummyImage() {
        return "https://i.stack.imgur.com/l60Hf.png";
    }

    public static String getTimeDifference(String start, String end) {
        String time = "";

        try {
            Date dateStart = getDateFromString(start);
            Date dateEnd = getDateFromString(end);

            long different = dateEnd.getTime() - dateStart.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            if (24 > elapsedHours) {
                if (elapsedHours != 0) {
                    time = elapsedHours + " hours ";
                }

                long elapsedMinutes = different / minutesInMilli;
                different = different % minutesInMilli;
                if (elapsedMinutes != 0) {
                    time += elapsedMinutes + " minutes ";
                }

                long elapsedSeconds = different / secondsInMilli;
                if (elapsedSeconds != 0) {
                    time += elapsedSeconds + " seconds ";
                }
            } else if (48 > elapsedHours && elapsedHours > 24) {
                time = "One day";

            } else if (72 > elapsedHours && elapsedHours >= 48) {

                time = "Two days";
            } else if (96 > elapsedHours && elapsedHours >= 72) {

                time = "Three days";
            } else if (120 > elapsedHours && elapsedHours >= 96) {

                time = "Four days";
            } else if (144 > elapsedHours && elapsedHours >= 120) {

                time = "Five days";
            } else if (168 > elapsedHours && elapsedHours >= 144) {

                time = "Six days";
            } else if (192 > elapsedHours && elapsedHours >= 168) {

                time = "One Week";
            } else if (336 > elapsedHours && elapsedHours >= 192) {

                time = "Two Weeks";
            } else if (504 > elapsedHours && elapsedHours >= 336) {

                time = "Three Weeks";
            } else if (672 > elapsedHours && elapsedHours >= 504) {

                time = "One Month";
            } else if (1460 > elapsedHours && elapsedHours >= 672) {

                time = "Two Months";
            } else if (2190 > elapsedHours && elapsedHours >= 1460) {

                time = "Three Months";
            } else if (2920 > elapsedHours && elapsedHours >= 2190) {

                time = "Four Months";
            } else if (3650 > elapsedHours && elapsedHours >= 2920) {

                time = "Five Months";
            } else if (4380 > elapsedHours && elapsedHours >= 3650) {

                time = "Six Months";
            } else if (5110 > elapsedHours && elapsedHours >= 4380) {

                time = "Seven Months";
            } else if (5840 > elapsedHours && elapsedHours >= 5110) {

                time = "Eight Months";
            } else if (6570 > elapsedHours && elapsedHours >= 5840) {

                time = "Nine Months";
            } else if (7300 > elapsedHours && elapsedHours >= 6570) {

                time = "Ten Months";
            } else if (8030 > elapsedHours && elapsedHours >= 7300) {

                time = "Eleven Months";
            } else if (8760 > elapsedHours && elapsedHours >= 8030) {

                time = "Twelve Months";
            } else if (17520 > elapsedHours && elapsedHours >= 8760) {

                time = "One Year";
            }else if (26280 > elapsedHours && elapsedHours >= 17520) {

                time = "Two Years";
            }else if (35040 > elapsedHours && elapsedHours >= 26280) {

                time = "Three Years";
            }else if (43800 > elapsedHours && elapsedHours >= 35040) {

                time = "Four Years";
            }else if (52560 > elapsedHours && elapsedHours >= 43800) {

                time = "Five Years";
            }else {
                time = "Long Time";
            }
        } catch (NullPointerException e) {

        }

        return time;
    }


    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()) {
            vId = matcher.group(1);
        }
        return vId;
    }

    public static String getPreviewDateTime(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = null;
        try {
            date = dateFormat.parse(dateTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        return df.format(date);
    }

    public static String getEventDateTime(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
        Date date = null;
        try {
            date = dateFormat.parse(dateTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
        return utcFormatter.format(date);
    }

    public static String getGreetingMsg() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String msg = "";

        if (timeOfDay >= 0 && timeOfDay < 12) {
            msg = "Good Morning";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            msg = "Good Afternoon";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            msg = "Good Evening";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            msg = "Good Night";
        }
        return msg;
    }


}

