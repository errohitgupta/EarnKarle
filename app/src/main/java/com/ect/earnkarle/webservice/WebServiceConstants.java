package com.ect.earnkarle.webservice;


public class WebServiceConstants {

    public static final String URL = "http://app.evctweb.in/web_service/";
    //	http://lla.techvalens.net/services/Service.svc/AddBookMark
    public static final int PID_LOGIN = 1;
    public static final int PID_FORGOTPASSWORD = 2;
    public static final int PID_REGISTERUSER = 3;
    public static final int PID_GETAPPLIST = 4;
    public static final int PID_APPCALLBACK = 5;
    public static final int PID_GETAPPDETAIL = 6;
    public static final int PID_GETUNISTALLED_APP = 7;
    public static final int PID_ALLAPP_LIST = 8;

    public static final String METHOD_LOGIN = "login";
    public static final String METHOD_FORGOTPASSWORD = "forgot_password";
    public static final String METHOD_REGISTERUSER = "sign_up";
    public static final String METHOD_GETAPPLIST = "app_list";
    public static final String METHOD_APPCALLBACK = "appsBack";
    public static final String METHOD_GETAPPDETAIL = "app_detail";
    public static final String METHOD_GETUNISTALLED_APP = "uninstall_app";
    public static final String METHOD_ALLAPP_LIST = "all_list";

/*	public static final int PID_LOGIN                          = 1;
    public static final int PID_FORGOTPASSWORD                 = 2;
	public static final int PID_REGISTERUSER                   = 3;
	public static final int PID_REGISTERDOCTOR                 = 4;
	public static final int PID_REGDRCONTACTUS                 = 5;
	public static final int PID_CHANGEPASSWORD                 = 6;
	public static final int PID_GETUSERPROFILE                 = 7;
	public static final int PID_GETDOCTORPROFILE               = 8;
	public static final int PID_UPDATEUSERPROFILE              = 9;
	public static final int PID_UPDATEDOCTORPROFILE            = 10;
	public static final int PID_GETDOCTORLIST                  = 11;
	public static final int PID_SEARCHBYSPECIALITY             = 12;
	public static final int PID_SEARCHDOCTOR                   = 13;
	public static final int PID_GETDOCTORDETAIL                = 14;
	public static final int PID_REVIEW_DOCTOR                  = 15;
	 


	public static final String METHOD_LOGIN                   = "login.php";
	public static final String METHOD_FORGOTPASSWORD          = "forgotPasswd.php";
	public static final String METHOD_REGISTERUSER            = "registerUser.php";
	public static final String METHOD_REGISTERDOCTOR          = "registerDr.php";
	public static final String METHOD_REGDRCONTACTUS          = "contactUs.php";
	public static final String METHOD_CHANGEPASSWORD          = "changePasswd.php";
	public static final String METHOD_GETUSERPROFILE          = "getUserProfile.php";
	public static final String METHOD_GETDOCTORPROFILE        = "getDrProfile.php";
	public static final String METHOD_UPDATEUSERPROFILE       = "updateUserProfile.php";
	public static final String METHOD_UPDATEDOCTORPROFILE     = "updateDrProfile.php";
	public static final String METHOD_GETDOCTORLIST           = "getListDr.php";
	public static final String METHOD_SEARCHBYSPECIALITY      = "searchDrbySpeciality.php";
	public static final String METHOD_SEARCHDOCTOR            = "searchDr.php";
	public static final String METHOD_GETDOCTORDETAIL         = "getOneDr.php";
	public static final String METHOD_REVIEW_DOCTOR           = "reviewDr.php";*/

    public static String getMethodUrl(String methodName) {
        String url = "";
        url = URL + methodName;
        return url;
    }


}
