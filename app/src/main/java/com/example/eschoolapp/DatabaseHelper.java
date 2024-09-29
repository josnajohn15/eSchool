package com.example.eschoolapp;
import android.util.Log;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.text.Editable;
import android.widget.Toast;


import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME = "Student.db";
    //student table
    public static final String TABLE_NAME = "student_table";
    public static final String TABLE_COL_1 = "Admission_Number";
    public static final String TABLE_COL_2 = "Fname";
    public static final String TABLE_COL_3 = "Lname";
    public static final String TABLE_COL_4 = "Enrollment_id";
    public static final String TABLE_COL_5 = "Department_id";
    public static final String TABLE_COL_6 = "Course_id";
    public static final String TABLE_COL_7 = "Faculty_id";
    // Department Table
    public static final String DEPARTMENT_TABLE = "department_table";
    public static final String DEPT_COL_1 = "Department_id";
    public static final String DEPT_COL_2 = "Department_name";

    // Course Table
    public static final String COURSE_TABLE = "course_table";
    public static final String COURSE_COL_1 = "Course_id";
    public static final String COURSE_COL_2 = "Course_name";
    public static final String COURSE_COL_3 = "Department_id"; // Foreign Key

    // Faculty Table
    public static final String FACULTY_TABLE = "faculty_table";
    public static final String FACULTY_COL_1 = "Faculty_id";
    public static final String FACULTY_COL_2 = "Faculty_name";
    public static final String FACULTY_COL_3 = "Department_id"; // Foreign Key
    // Enrollment Table
    public static final String ENROLLMENT_TABLE = "enrollment_table";
    public static final String ENROLL_COL_1 = "Enrollment_id";
    public static final String ENROLL_COL_2 = "Course_id";        // Foreign Key
    public static final String ENROLL_COL_3 = "Faculty_id";       // Foreign Key
    public static final String ENROLL_COL_4 = "Enrollment_year";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DatabaseHelper", "Creating tables and populating initial data");
        createTables(sqLiteDatabase);
        populateInitialData(sqLiteDatabase); // Populate Departments, Courses, Faculty,Enrollment
    }
    public void createTables(SQLiteDatabase sqLiteDatabase) {

        // Create Department Table
        sqLiteDatabase.execSQL("CREATE TABLE " + DEPARTMENT_TABLE + " (" +
                DEPT_COL_1 + " TEXT PRIMARY KEY, " +
                DEPT_COL_2 + " TEXT)");

        // Create Course Table
        sqLiteDatabase.execSQL("CREATE TABLE " + COURSE_TABLE + " (" +
                COURSE_COL_1 + " TEXT PRIMARY KEY, " +
                COURSE_COL_2 + " TEXT, " +
                COURSE_COL_3 + " TEXT, " +
                "FOREIGN KEY (" + COURSE_COL_3 + ") REFERENCES " + DEPARTMENT_TABLE + "(" + DEPT_COL_1 + "))");

        // Create Faculty Table
        sqLiteDatabase.execSQL("CREATE TABLE " + FACULTY_TABLE + " (" +
                FACULTY_COL_1 + " TEXT PRIMARY KEY, " +
                FACULTY_COL_2 + " TEXT, " +
                FACULTY_COL_3 + " TEXT, " +
                "FOREIGN KEY (" + FACULTY_COL_3 + ") REFERENCES " + DEPARTMENT_TABLE + "(" + DEPT_COL_1 + "))");
        // Create Enrollment Table
        sqLiteDatabase.execSQL("CREATE TABLE " + ENROLLMENT_TABLE + " (" +
                ENROLL_COL_1 + " TEXT PRIMARY KEY, " +
                ENROLL_COL_2 + " TEXT, " +
                ENROLL_COL_3 + " TEXT, " +
                ENROLL_COL_4 + " TEXT, " +
                "FOREIGN KEY (" + ENROLL_COL_2 + ") REFERENCES " + COURSE_TABLE + "(" + COURSE_COL_1 + "), " +
                "FOREIGN KEY (" + ENROLL_COL_3 + ") REFERENCES " + FACULTY_TABLE + "(" + FACULTY_COL_1 + "))");
        // Create Student Table
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                TABLE_COL_1 + " TEXT PRIMARY KEY, " +
                TABLE_COL_2 + " TEXT, " +
                TABLE_COL_3 + " TEXT, " +
                TABLE_COL_4 + " TEXT, " +
                TABLE_COL_5 + " TEXT, " +
                TABLE_COL_6 + " TEXT, " +
                TABLE_COL_7 + " TEXT, " +
                "FOREIGN KEY (" + TABLE_COL_4 + ") REFERENCES " + ENROLLMENT_TABLE + "(" + ENROLL_COL_1 + "), " +
                "FOREIGN KEY (" + TABLE_COL_5 + ") REFERENCES " + DEPARTMENT_TABLE + "(" + DEPT_COL_1 + "), " +
                "FOREIGN KEY (" + TABLE_COL_6 + ") REFERENCES " + COURSE_TABLE + "(" + COURSE_COL_1 + "), " +
                "FOREIGN KEY (" + TABLE_COL_7 + ") REFERENCES " + FACULTY_TABLE + "(" + FACULTY_COL_1 + "))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ENROLLMENT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FACULTY_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DEPARTMENT_TABLE);
        onCreate(sqLiteDatabase);
        populateInitialData(sqLiteDatabase);

    }
    public void populateInitialData(SQLiteDatabase db) {
        // Insert Departments
        insertDepartment(db, "D1", "Computer Science");
        insertDepartment(db, "D2", "Electrical Engineering");
        insertDepartment(db, "D3", "Mechanical Engineering");
        insertDepartment(db, "D4", "Civil Engineering");
        insertDepartment(db, "D5", "Biotechnology");

        // Insert Courses
        insertCourse(db, "C1", "Data Structures", "D1");
        insertCourse(db, "C2", "Digital Circuits", "D2");
        insertCourse(db, "C3", "Thermodynamics", "D3");
        insertCourse(db, "C4", "Structural Analysis", "D4");
        insertCourse(db, "C5", "Genetics", "D5");

        // Insert Faculty
        insertFaculty(db, "F1", "Dr. Alice Smith", "D1");
        insertFaculty(db, "F2", "Prof. Bob Johnson", "D2");
        insertFaculty(db, "F3", "Dr. Charlie Brown", "D3");
        insertFaculty(db, "F4", "Dr. Diana Prince", "D4");
        insertFaculty(db, "F5", "Prof. Ethan Hunt", "D5");

        insertEnrollment(db, "100", "C1", "F1", "2023");
        insertEnrollment(db, "101", "C2", "F2", "2024");
        insertEnrollment(db, "102", "C3", "F3", "2025");
        insertEnrollment(db, "103", "C4", "F4", "2021");
        insertEnrollment(db, "104", "C5", "F5", "2029");

    }
    public void insertDepartment(SQLiteDatabase db, String departmentId, String departmentName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEPT_COL_1, departmentId);
        contentValues.put(DEPT_COL_2, departmentName);
        db.insert(DEPARTMENT_TABLE, null, contentValues);
    }
    public void insertCourse(SQLiteDatabase db, String courseId, String courseName, String departmentId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COL_1, courseId);
        contentValues.put(COURSE_COL_2, courseName);
        contentValues.put(COURSE_COL_3, departmentId);
        db.insert(COURSE_TABLE, null, contentValues);
    }

    public void insertFaculty(SQLiteDatabase db, String facultyId, String facultyName, String departmentId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FACULTY_COL_1, facultyId);
        contentValues.put(FACULTY_COL_2, facultyName);
        contentValues.put(FACULTY_COL_3, departmentId);
        db.insert(FACULTY_TABLE, null, contentValues);
    }
    public void insertEnrollment(SQLiteDatabase db,String enrollmentId, String courseId, String facultyId, String enrollmentYear) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENROLL_COL_1, enrollmentId); // Enrollment ID
        contentValues.put(ENROLL_COL_2, courseId);     // Course ID (Foreign Key)
        contentValues.put(ENROLL_COL_3, facultyId);    // Faculty ID (Foreign Key)
        contentValues.put(ENROLL_COL_4, enrollmentYear); // Enrollment Year

        // Insert the new record into the enrollment table
        db.insert(ENROLLMENT_TABLE, null, contentValues);
    }

    private boolean isFacultyIdValid(String Faculty_id, String Department_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(FACULTY_TABLE, null, FACULTY_COL_1 + "=? AND " + FACULTY_COL_3 + "=?", new String[]{Faculty_id, Department_id}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    private boolean isEnrollmentIdValid(String Enrollment_id, String Course_id, String Faculty_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ENROLLMENT_TABLE, null, ENROLL_COL_1 + "=? AND " + ENROLL_COL_2 + "=? AND " + ENROLL_COL_3 + "=?",
                new String[]{Enrollment_id,Course_id,Faculty_id}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    private boolean isCourseIdValid(String Course_id, String Department_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(COURSE_TABLE, null, COURSE_COL_1 + "=? AND " + COURSE_COL_3 + "=?", new String[]{Course_id,Department_id}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    //method to validate departmentid
    private boolean isDepartmentIdValid(String Department_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DEPARTMENT_TABLE, new String[]{DEPT_COL_1}, DEPT_COL_1 + "=?", new String[]{Department_id}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Method to validate if Admission Number exists
    private boolean isAdmissionNumberValid(String Admission_Number) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{TABLE_COL_1}, "Admission_Number = ?", new String[]{Admission_Number}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public boolean insertData(String Admission_Number, String Fname, String Lname, String Enrollment_id, String Department_id, String Course_id, String Faculty_id) {
        // Validate foreign keys
        if (!isEnrollmentIdValid(Enrollment_id,Course_id,Faculty_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isFacultyIdValid(Faculty_id,Department_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isCourseIdValid(Course_id,Department_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isDepartmentIdValid(Department_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COL_1, Admission_Number);
        contentValues.put(TABLE_COL_2, Fname);
        contentValues.put(TABLE_COL_3, Lname);
        contentValues.put(TABLE_COL_4, Enrollment_id);
        contentValues.put(TABLE_COL_5, Department_id);
        contentValues.put(TABLE_COL_6, Course_id);
        contentValues.put(TABLE_COL_7, Faculty_id);
        // Insert contents into database
        long success = db.insert(TABLE_NAME, null, contentValues);

        if (success == -1) { // when query not inserted into database
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Added+", Toast.LENGTH_SHORT).show();
            return true;

        }
    }
    public Integer deleteData(String Admission_Number){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Admission_Number = ?",new String[]{Admission_Number});

    }
    // Update Data of Database table
    public boolean updateData(String Admission_Number,String Fname,String Lname,String Enrollment_id,String Department_id,String Course_id,String Faculty_id){
        if (!isAdmissionNumberValid(Admission_Number)) {
            Toast.makeText(context, "Admission Number does not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isEnrollmentIdValid(Enrollment_id,Course_id,Faculty_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isFacultyIdValid(Faculty_id,Department_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isCourseIdValid(Course_id,Department_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isDepartmentIdValid(Department_id)) {
            Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // Accessing content for overwrite
        contentValues.put(TABLE_COL_1,Admission_Number);
        contentValues.put(TABLE_COL_2,Fname);
        contentValues.put(TABLE_COL_3,Lname);
        contentValues.put(TABLE_COL_4,Enrollment_id);
        contentValues.put(TABLE_COL_5, Department_id);
        contentValues.put(TABLE_COL_6, Course_id);
        contentValues.put(TABLE_COL_7, Faculty_id);
        db.update(TABLE_NAME,contentValues, "Admission_Number = ?", new String[]{Admission_Number});
        return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " +TABLE_NAME,null);
        return cur;
    }
    public Cursor getStudentData(String Admission_Number) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from "  + TABLE_NAME  +  " WHERE Admission_Number = ?", new String[]{Admission_Number});
        return cur;
    }
}