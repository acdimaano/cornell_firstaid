package com.cornell.firstaid.dao;

/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public interface FirstAidDatabase {
	
	//Database Details
	public final static int    DATABASE_VERSION                      = 1;
	public final static String DATABASE_NAME                         = "DB_FIRST_AID";
	
	//DB Tables
	public final static String TB_COMMENT                            = "TB_COMMENT";
	public final static String TB_DISEASE                            = "TB_DISEASE";
	public final static String TB_DESC_SYMP_TREAT                    = "TB_DESC_SYMP_TREAT";
	public final static String TB_CALLBACK_FUNC                      = "TB_CALLBACK_FUNC";
	public final static String TB_DEVELOPER_DETAILS                  = "TB_DEVELOPER_DETAILS";
	
	 //Universal variables
	public final static String OID                                   = "OID";
	public final static String COL_TIMESTAMP                         = "COL_TIMESTAMP";

	//DB Details for Heart Disease
	public static final String COL_DISEASE_NAME                      = "COL_DISEASE_NAME";
	public static final String COL_DISEASE_OID                       = "COL_DISEASE_OID";
	public static final String COL_DESC_ID                           = "COL_DESC_ID";
	public static final String COL_SYMP_ID                           = "COL_SYMP_ID";
	public static final String COL_TREAT_ID                          = "COL_TREAT_ID";
	public static final String COL_TAGS                              = "COL_TAGS";
	public static final String COL_CLASS_NAME                        = "COL_CLASS_NAME";

	//DB Details for Feedbacks
	public static final String COL_NAME                              = "COL_NAME";
	public static final String COL_PHONE                             = "COL_PHONE";
	public static final String COL_EMAIL                             = "COL_EMAIL";
	public static final String COL_COMMENT                           = "COL_COMMENT";
	public static final String COL_DEV_NUMBER                        = "COL_DEV_NUMBER";
	public static final String COL_STATUS                            = "COL_STATUS";
	
	//Callback Functionality
	public final static String COL_CALLBACK_NUMBER                   = "COL_CALLBACK_NUMBER";
	
	//Developer Details
	public final static String COL_DEV_NAME                          = "COL_DEV_NAME";
	public final static String COL_DEV_ADDRESS                       = "COL_DEV_ADDRESS";
	public final static String COL_DEV_EMAIL                         = "COL_DEV_EMAIL";
	public final static String COL_DEV_PHONE                         = "COL_DEV_PHONE";
	
	//Disease Table Creation
	public static final String DATABASE_CREATE_DISEASE_TABLE         = "CREATE TABLE " + TB_DISEASE +
                                                                       " ( " + OID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                                                                       COL_DISEASE_NAME + " STRING, " + 
                                                                       COL_TAGS + " STRING, " +
                                                                       COL_CLASS_NAME + " STRING, " +
                                                                       COL_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP)";

	//Disease Description/Symptoms/Treatment Table Creation
	public static final String DATABASE_CREATE_DESC_SYMP_TREAT_TABLE = "CREATE TABLE " + TB_DESC_SYMP_TREAT + 
                                                                       " ( " + OID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                                                                       COL_DISEASE_OID + " INTEGER, " +
                                                                       COL_DESC_ID + " INTEGER, " +
                                                                       COL_SYMP_ID + " INTEGER, " +
                                                                       COL_TREAT_ID + " INTEGER, " +
                                                                       COL_TAGS + " STRING, " +
                                                                       COL_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                                                                       "FOREIGN KEY(" + COL_DISEASE_OID + ") REFERENCES " + TB_DISEASE + "(OID))";

	//Comment Table Creation
	public static final String DATABASE_CREATE_COMMENT_TABLE         = "CREATE TABLE " + TB_COMMENT +
                                                                       " ( " + OID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                                       COL_NAME + " STRING, " + COL_PHONE + " STRING, " +
                                                                       COL_EMAIL + " STRING, " + COL_COMMENT + " STRING, " +
                                                                       COL_DEV_NUMBER + " STRING, " + COL_STATUS + " INTEGER, " + 
                                                                       COL_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP)";

	//Callback Table Creation
	public final static String DATABASE_CREATE_CALLBACK_TABLE        = "CREATE TABLE " + TB_CALLBACK_FUNC + 
			                                                           " ( " + OID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			                                                           COL_CALLBACK_NUMBER + " TEXT, " + 
			                                                           COL_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP)";
	
	//Developer Table Creation
	public final static String DATABASE_CREATE_DEVELOPER_TABLE       = "CREATE TABLE " + TB_DEVELOPER_DETAILS +
			                                                           " ( " + OID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
			                                                           COL_DEV_NAME + " INTEGER, " + COL_DEV_ADDRESS + " INTEGER, " +
			                                                           COL_DEV_EMAIL + " INTEGER, " + COL_DEV_PHONE + " STRING, " + 
			                                                           COL_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP)";
	
	public final static String DATABASE_SELECT_DEVELOPER_TABLE       = "SELECT * FROM " + TB_DEVELOPER_DETAILS;
	
	//Select unsent comments/feedbacks
	public final static String DATABASE_SELECT_COMMENTS              = "SELECT * FROM " + TB_COMMENT + " WHERE " + COL_STATUS + " = '0'";
	
	//Select last call emergency calls
	public final static String DATABASE_SELECT_LAST_CALLS            = "SELECT * FROM TB_CALLBACK_FUNC ORDER BY COL_TIMESTAMPDATETIME DESC";
	
	//DB Dummy
	public static final String DATABASE_CREATE_MAIN_TABLE            = "CREATE TABLE TB_MAIN (OID INTEGER PRIMARY KEY AUTOINCREMENT, COL_MAIN_NAME STRING, " + COL_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP)";

}
