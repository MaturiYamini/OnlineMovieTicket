package com.example.onlinemovieticket.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.onlinemovieticket.models.User;
import com.example.onlinemovieticket.models.Booking;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MovieTicket.db";
    private static final int DATABASE_VERSION = 2;

    // Users table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_PHONE = "user_phone";

    // Bookings table
    private static final String TABLE_BOOKINGS = "bookings";
    private static final String COLUMN_BOOKING_ID = "booking_id";
    private static final String COLUMN_BOOKING_USER_EMAIL = "user_email";
    private static final String COLUMN_MOVIE_TITLE = "movie_title";
    private static final String COLUMN_SHOW_TIME = "show_time";
    private static final String COLUMN_SEATS = "seats";
    private static final String COLUMN_TOTAL_AMOUNT = "total_amount";
    private static final String COLUMN_BOOKING_DATE = "booking_date";
    private static final String COLUMN_PAYMENT_METHOD = "payment_method";

    // Create tables SQL queries
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT UNIQUE,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_PHONE + " TEXT" + ")";

    private String CREATE_BOOKING_TABLE = "CREATE TABLE " + TABLE_BOOKINGS + "("
            + COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BOOKING_USER_EMAIL + " TEXT,"
            + COLUMN_MOVIE_TITLE + " TEXT,"
            + COLUMN_SHOW_TIME + " TEXT,"
            + COLUMN_SEATS + " TEXT,"
            + COLUMN_TOTAL_AMOUNT + " REAL,"
            + COLUMN_BOOKING_DATE + " TEXT,"
            + COLUMN_PAYMENT_METHOD + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BOOKING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_PHONE, user.getPhone());

        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public long addBooking(Booking booking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOKING_USER_EMAIL, booking.getUserEmail());
        values.put(COLUMN_MOVIE_TITLE, booking.getMovieTitle());
        values.put(COLUMN_SHOW_TIME, booking.getShowTime());
        values.put(COLUMN_SEATS, String.join(",", booking.getSelectedSeats()));
        values.put(COLUMN_TOTAL_AMOUNT, booking.getTotalAmount());
        values.put(COLUMN_BOOKING_DATE, booking.getBookingDate());
        values.put(COLUMN_PAYMENT_METHOD, booking.getPaymentMethod());

        long id = db.insert(TABLE_BOOKINGS, null, values);
        db.close();
        return id;
    }

    public List<Booking> getUserBookings(String userEmail) {
        List<Booking> bookings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selection = COLUMN_BOOKING_USER_EMAIL + " = ?";
        String[] selectionArgs = {userEmail};
        
        Cursor cursor = db.query(TABLE_BOOKINGS, null, selection, selectionArgs, 
                                null, null, COLUMN_BOOKING_DATE + " DESC");
        
        if (cursor.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BOOKING_ID)));
                booking.setUserEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOKING_USER_EMAIL)));
                booking.setMovieTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_TITLE)));
                booking.setShowTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SHOW_TIME)));
                
                String seatsStr = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SEATS));
                booking.setSelectedSeats(Arrays.asList(seatsStr.split(",")));
                
                booking.setTotalAmount(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_AMOUNT)));
                booking.setBookingDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOKING_DATE)));
                booking.setPaymentMethod(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAYMENT_METHOD)));
                
                bookings.add(booking);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return bookings;
    }
}