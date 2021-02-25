package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notes.Post;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "notes";
    private	static final String TABLE_POSTS = "Posts";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "post_name";
    private static final String COLUMN_DESC = "post_desc";
    private static final String KEY_DATE = "datetime";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_Post_TABLE = "CREATE TABLE " + TABLE_POSTS + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT, " +COLUMN_DESC + " TEXT, " + KEY_DATE + " DATE "+")";
        db.execSQL(CREATE_Post_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        onCreate(db);
    }

    public ArrayList<Post> listPost(){
        String sql = "select * from " + TABLE_POSTS;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Post> storePost = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String date = cursor.getString(3);
                storePost.add(new com.example.notes.Post(id, name, desc,date));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storePost;
    }

    public void addPost(com.example.notes.Post Post){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, Post.getName());
        values.put(COLUMN_DESC, Post.getDesc());
        values.put(KEY_DATE, Post.getDate());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_POSTS, null, values);
    }

    public void updatePost(Post Post){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, Post.getName());
        values.put(COLUMN_DESC, Post.getDesc());
        values.put(KEY_DATE, Post.getDate());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_POSTS, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(Post.getId())});
    }

    public com.example.notes.Post findPost(String name){
        String query = "Select * FROM "	+ TABLE_POSTS + " WHERE " + COLUMN_NAME + " = " + "name";
        SQLiteDatabase db = this.getWritableDatabase();
        com.example.notes.Post Post = null;
        Cursor cursor = db.rawQuery(query,	null);
        if	(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String PostName = cursor.getString(1);
            String PostDesc = cursor.getString(2);
            String PostDate = cursor.getString(4);
            Post = new com.example.notes.Post(id, PostName, PostDesc, PostDate);
        }
        cursor.close();
        return Post;
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POSTS, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
