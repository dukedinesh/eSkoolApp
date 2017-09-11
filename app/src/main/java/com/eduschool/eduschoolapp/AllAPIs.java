package com.eduschool.eduschoolapp;

import com.eduschool.eduschoolapp.AddAlbumPOJO.AddAlbumBean;
import com.eduschool.eduschoolapp.AssignHomeWrkPOJO.AssignHWbean;
import com.eduschool.eduschoolapp.AssingCwPOJO.AssignClsWrkBean;
import com.eduschool.eduschoolapp.AttendanceSummaryPOJO.AttendanceSummaryBean;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceListBean;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuListBean;
import com.eduschool.eduschoolapp.BirthTechrListPOJO.BirthTechrListBean;
import com.eduschool.eduschoolapp.BookDetailsPOJO.BookListBean1;
import com.eduschool.eduschoolapp.ChangePssPOJO.PasswrdChngebean;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.ClassResultPOJO.ClassResultBean;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkListBean;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.ClasssubjectListBean;
import com.eduschool.eduschoolapp.ClssWrkPOJO.ClasswrkBean;
import com.eduschool.eduschoolapp.ClswrkDetailsPOJO.ClasssworkDetailBean;
import com.eduschool.eduschoolapp.CreateEventTecherPOJO.CreateEventTechrBean;
import com.eduschool.eduschoolapp.DeleteAlbumPOJO.DeleteAlbumBean;
import com.eduschool.eduschoolapp.ExamSchedulListPOJO.ExamSchedulListBean;
import com.eduschool.eduschoolapp.ExmListParentPOJO.ExamTypeListBean;
import com.eduschool.eduschoolapp.ExmNresultPOJO.ExmTypeListBean;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryListBean;
import com.eduschool.eduschoolapp.HolidayPOJO.HolidayListBean;
import com.eduschool.eduschoolapp.HomeWrkDetailsPOJO.HomeWrkDetailsBean;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeWorkListBean;
import com.eduschool.eduschoolapp.HomeWrkUpdPOJO.HomeworkUpdateBean;
import com.eduschool.eduschoolapp.HomewrkPOJO.HomewrkBean;
import com.eduschool.eduschoolapp.HomewrkParentPOJO.ParentSubjectListBean;
import com.eduschool.eduschoolapp.LibraryBookPOJO.BookListBean;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.MarkAttendancePOJO.MarkAttendanceBean;
import com.eduschool.eduschoolapp.OnlineTestListPOJO.OnlineTestListBean;
import com.eduschool.eduschoolapp.ParentContactPOJO.ParentContactBean;
import com.eduschool.eduschoolapp.ParentInfoPOJO.ParentInfoBean;
import com.eduschool.eduschoolapp.ParentPersnlPOJO.ParentPrsnlBean;
import com.eduschool.eduschoolapp.RaiseRequestPOJO.RaiseRequestBean;
import com.eduschool.eduschoolapp.RaiseRequstPOJO.RaiseReqstBean;
import com.eduschool.eduschoolapp.RecvRequstPOJO.RequestList;
import com.eduschool.eduschoolapp.RecvRequstPOJO.RequstListBean;
import com.eduschool.eduschoolapp.ReserveBookPOJO.ReserveBookBean;
import com.eduschool.eduschoolapp.ResultPOJO.ResultListBean;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.SendBdyTeacherPOJO.SendBdyTeacherBean;
import com.eduschool.eduschoolapp.SendBirthPOJO.SendBirthBean;
import com.eduschool.eduschoolapp.SendParentRequstPOJO.RequestListBean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.SurveyAnsPOJO.SurveyAnsBean;
import com.eduschool.eduschoolapp.SurveyListPOJO.SurveyListBean;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.SurveyListBeanParent;
import com.eduschool.eduschoolapp.SurveyQusPOJO.SurveyQusBean;
import com.eduschool.eduschoolapp.SyllabusPOJO.SyllabusListBean;
import com.eduschool.eduschoolapp.SyllbsMngTeacherPOJO.SyllabusListBean1;
import com.eduschool.eduschoolapp.UpdateAlbumPOJO.UpdateAlbumBean;
import com.eduschool.eduschoolapp.UpdateAttndncPOJO.UpdateAttendncBean;
import com.eduschool.eduschoolapp.UpdateCwPOJO.UpdateCwBean;
import com.eduschool.eduschoolapp.UpdateHwPOJO.UpdateHwBean;
import com.eduschool.eduschoolapp.UpdateSurveyPOJO.UpdateSurveyBean;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomewrkListbean;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 7/14/2017.
 */

public interface AllAPIs {

    @Multipart
    @POST("eduschool/eduschool_app/teacher_login.php")
    Call<Loginbean> login(@Part("user") String user, @Part("pass") String pass);

    @Multipart
    @POST("eduschool/eduschool_app/change_password.php")
    Call<PasswrdChngebean> chngrPass(@Part("school_id") String school_id, @Part("type") String type, @Part("newpass") String newpass, @Part("oldpass") String oldpass, @Part("userid") String userid);


    @Multipart
    @POST("eduschool/eduschool_app/class_list.php")
    Call<ClassListbean> classList(@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool/eduschool_app/section_list.php")
    Call<SectionListbean> sectionList(@Part("school_id") String school_id, @Part("class_id") String class_id );

    @Multipart
    @POST("eduschool/eduschool_app/subject_list.php")
    Call<SubjectListBean> subjectList(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id );

    @Multipart
    @POST("eduschool/eduschool_app/chapter_list.php")
    Call<ChapterListbean> chapterList(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("subject_id") String subject_id );


    @Multipart
    @POST("eduschool/eduschool_app/view_homeworklist.php")
    Call<HomewrkListbean> homwwrk_list(@Part("school_id") String school_id, @Part("teacher_id") String teacher_id );

    @Multipart
    @POST("eduschool/eduschool_app/assign_homework.php")
    Call<AssignHWbean> assign_hw(@Part("school_id") String school_id , @Part("class") String classid, @Part("section") String section, @Part("subject") String subject, @Part("chapter") String chapter, @Part("notes") String notes, @Part("duedate") String duedate, @Part("student") List<String> student, @Part MultipartBody.Part file,  @Part("teacher_id") String teacher_id);


    @Multipart
    @POST("eduschool/eduschool_app/assign_classwork.php")
    Call<AssignClsWrkBean> assign_cw(@Part("school_id") String school_id, @Part("class") String classid, @Part("section") String section, @Part("subject") String subject, @Part("chapter") String chapter, @Part("notes") String notes, @Part("classwork_status") String classwork_status, @Part("stulist") List<String> stulist, @Part("classattach") String classattach,@Part("teacher_id") String teacher_id);

    @Multipart
    @POST("eduschool/eduschool_app/student_list.php")
    Call<StudentListbean> student_list(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id );

    @Multipart
    @POST("eduschool/eduschool/eduschool_app/homework_searchlist.php")
    Call<HomewrkListbean> homwwrk_list(@Part("school_id") String school_id, @Part("teacher_id") String teacher_id, @Part("class") String className, @Part("section") String section, @Part("subject") String subject );

    @Multipart
    @POST("eduschool/eduschool_app/classlistsearch.php")
    Call<ClassWrkListbean> classwrk_list(@Part("school_id") String school_id, @Part("teacher_id") String teacher_id, @Part("class") String className, @Part("section") String section, @Part("subject") String subject );


    @Multipart
    @POST("eduschool/eduschool_app/classworklist.php")
    Call<ClassWrkListbean> classwrk_list(@Part("school_id") String school_id, @Part("teacher_id") String teacher_id );


    @Multipart
    @POST("eduschool/eduschool_app/homeworkbyid.php")
    Call<HomewrkBean> homewrk(@Part("school_id") String school_id, @Part("homework_id") String homework_id );

    @Multipart
    @POST("eduschool/eduschool_app/album_list.php")
    Call<AlbumListBean> album_list(@Part("school_id") String school_id );

    @Multipart
    @POST("eduschool/eduschool_app/searchby_album.php")
    Call<AlbumListBean> album_list(@Part("school_id") String school_id,@Part("date") String date );

    @Multipart
    @POST("eduschool/eduschool_app/create_album.php")
    Call<AddAlbumBean> add_album(@Part("album_name") String album_name,@Part("school_id") String school_id,@Part("userid") String userid );


    @Multipart
    @POST("eduschool/eduschool_app/update_album.php")
    Call<UpdateAlbumBean> update_album(@Part("school_id") String school_id,@Part("album_id") String album_id,@Part("album_name") String album_name );


    @Multipart
    @POST("eduschool/eduschool_app/album_deleteid.php")
    Call<DeleteAlbumBean> delete_album(@Part("school_id") String school_id, @Part("album_id") String album_id );

    @Multipart
    @POST("eduschool/eduschool_app/classworkbyid.php")
    Call<ClasswrkBean> clss_wrk(@Part("school_id") String school_id, @Part("classwork_id") String classwork_id );


    @Multipart
    @POST("eduschool/eduschool_app/update_homework.php")
    Call<UpdateHwBean> update_hw(@Part("homeworkid") String homeworkid, @Part("school_id") String school_id, @Part("teacher_id") String teacher_id, @Part("class") String classId, @Part("section") String section, @Part("subject") String subject, @Part("chapter") String chapter, @Part("notes")String notes, @Part("duedate") String duedate, @Part("stulist")List<String>  stulist, @Part("homeattach") String homeattach);

    @Multipart
    @POST("eduschool/eduschool_app/update_classwork.php")
    Call<UpdateCwBean> update_cw(@Part("classworkid") String classworkid, @Part("school_id") String school_id, @Part("teacher_id") String teacher_id, @Part("class") String classId, @Part("section") String section, @Part("subject") String subject, @Part("chapter") String chapter, @Part("additionlnote")String additionlnote, @Part("classwork_status")String classwork_status, @Part("stulist")List<String>  stulist, @Part("classattach") String classattach);



    @Multipart
    @POST("eduschool/eduschool_app/view_survey_teacher.php")
    Call<SurveyListBean> survey_list(@Part("school_id") String school_id, @Part("type") String type, @Part("userid") String userid );


    @Multipart
    @POST("eduschool/eduschool_app/survey_questionbyid.php")
    Call<SurveyQusBean> survey_qus(@Part("survey_id") String survey_id, @Part("question_id") String question_id );


    @Multipart
    @POST("eduschool/eduschool_app/homework_subject_list.php")
    Call<ParentSubjectListBean> sub_list(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id, @Part("userid") String userid );


    @Multipart
    @POST("eduschool/eduschool_app/homeworklist_subjectid.php")
    Call<HomeWorkListBean> home_wrk(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id, @Part("userid") String userid, @Part("subject_id") String subject_id );

    @Multipart
    @POST("eduschool/eduschool_app/parenthomework_search.php")
    Call<HomeWorkListBean> home_wrk(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id, @Part("userid") String userid, @Part("subject_id") String subject_id, @Part("date") String date );


    @Multipart
    @POST("eduschool/eduschool_app/homeworkby_id.php")
    Call<HomeWrkDetailsBean> hw_details(@Part("school_id") String school_id, @Part("userid") String userid, @Part("homework_id") String homework_id );


    @Multipart
    @POST("eduschool/eduschool_app/changehomework_status.php")
    Call<HomeworkUpdateBean> hw_update(@Part("userid") String userid, @Part("homework_id") String homework_id );

    @Multipart
    @POST("eduschool/eduschool_app/classwork_subject_list.php")
    Call<ClasssubjectListBean> class_subject(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id, @Part("userid") String userid );


    @Multipart
    @POST("eduschool/eduschool_app/classworkbysubjectid.php")
    Call<ClasssworkListBean> claass_wrk(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id, @Part("userid") String userid, @Part("subject_id") String subject_id );


    @Multipart
    @POST("eduschool/eduschool_app/filterclassworkbydate.php")
    Call<ClasssworkListBean> claass_wrk(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id, @Part("userid") String userid, @Part("subject_id") String subject_id, @Part("date") String date );



    @Multipart
    @POST("eduschool/eduschool_app/image_listforparent.php")
    Call<GalleryListBean> gallery_img(@Part("school_id") String school_id, @Part("album_id") String album_id, @Part("stu_id") String stu_id );


    @Multipart
    @POST("eduschool/eduschool_app/take_question_teacher.php")
    Call<UpdateSurveyBean> update_qus(@Part("school_id") String school_id, @Part("type") String type, @Part("userid") String userid, @Part("survey_id") String survey_id, @Part("question_id") String question_id, @Part("answer") String answer );


    @Multipart
    @POST("eduschool/eduschool_app/classworkby_id.php")
    Call<ClasssworkDetailBean> clss_wrk(@Part("school_id") String school_id, @Part("userid") String userid, @Part("classwork_id") String classwork_id );


    @Multipart
    @POST("eduschool/eduschool_app/parent_survey_list.php")
    Call<SurveyListBeanParent> survey_list1(@Part("school_id") String school_id, @Part("type") String type, @Part("userid") String userid, @Part("class") String classname, @Part("section") String section );



    @Multipart
    @POST("eduschool/eduschool_app/mark_attendance.php")
    Call<MarkAttendanceBean> mark_attendance(@Part("school_id") String school_id, @Part("class") String classname, @Part("section") String section, @Part("date") String date, @Part("userid") String userid, @Part("class_teacher") String class_teacher, @Part("month") String month, @Part("year") String year, @Part("day") String day, @Part("stu_id") String stu_id, @Part("attend") String attend );


    @Multipart
    @POST("eduschool/eduschool_app/get_attendancedata.php")
    Call<AttendanceListBean> attnce_list(@Part("school_id") String school_id, @Part("date") String date, @Part("class") String classname, @Part("section") String section);


    @Multipart
    @POST("eduschool/eduschool_app/update_markattendace.php")
    Call<UpdateAttendncBean> update_attndnc(@Part("attendace_id") String attendace_id, @Part("stu_id") String stu_id, @Part("attend") String attend );


    @Multipart
    @POST("eduschool/eduschool_app/total_attendance_summary.php")
    Call<AttendanceSummaryBean> attendnc_summry(@Part("school_id") String school_id, @Part("userid") String userid, @Part("date") String date );


    @Multipart
    @POST("eduschool/eduschool_app/holiday_list.php")
    Call<HolidayListBean> holiday_list(@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool/eduschool_app/view_today_birth_stu.php")
    Call<BirthStuListBean> stu_bithday(@Part("school_id") String school_id);


    @Multipart
    @POST("eduschool/eduschool_app/get_syllabus_data_examwise.php")
    Call<SyllabusListBean> syllabus_mng(@Part("school_id") String school_id,@Part("subject_id") String subject_id,@Part("class") String classname,@Part("section") String section);

    @Multipart
    @POST("eduschool/eduschool_app/send_birth_card.php")
    Call<SendBirthBean> send_card(@Part("school_id") String school_id,@Part("from_type") String from_type,@Part("from_userid") String from_userid,@Part("to_type") String to_type,@Part("birth_card") String birth_card,@Part("stu_id") String stu_id);


    @Multipart
    @POST("eduschool/eduschool_app/get_library_book.php")
    Call<BookListBean> book_list(@Part("school_id") String school_id);


    @Multipart
    @POST("eduschool/eduschool_app/book_detailbyid.php")
    Call<BookListBean1> book_details1(@Part("school_id") String school_id, @Part("book_no_id") String book_no_id, @Part("book_statu_id") String book_statu_id);

    @Multipart
    @POST("eduschool/eduschool_app/bookborrow_detailbyid.php")
    Call<com.eduschool.eduschoolapp.BorrowedBookPOJO.BookListBean> book_details(@Part("school_id") String school_id, @Part("book_no_id") String book_no_id, @Part("book_statu_id") String book_statu_id);

    @Multipart
    @POST("eduschool/eduschool_app/bookreserv_detailbyid.php")
    Call<com.eduschool.eduschoolapp.ReservBookPOJO.BookListBean> book_reserv(@Part("school_id") String school_id, @Part("book_no_id") String book_no_id, @Part("book_statu_id") String book_statu_id);


    @Multipart
    @POST("eduschool/eduschool_app/book_reserved.php")
    Call<ReserveBookBean> reserve_book(@Part("school_id") String school_id, @Part("userid") String userid, @Part("type") String type, @Part("book_id") String book_id, @Part("book_no_id") String book_no_id);

    @Multipart
    @POST("eduschool/eduschool_app/search_book.php")
    Call<BookListBean> book_list(@Part("school_id") String school_id,@Part("keyword") String keyword);

    @Multipart
    @POST("eduschool/eduschool_app/get_online_test.php")
    Call<OnlineTestListBean> online_test_bean(@Part("school_id") String school_id, @Part("class") String classname, @Part("section") String section, @Part("userid") String userid);

    @Multipart
    @POST("eduschool/eduschool_app/teacher_syllabus_data.php")
    Call<com.eduschool.eduschoolapp.SyllabusTeacherPOJO.SyllabusListBean> syllabus(@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool/eduschool_app/teacher_syllabus_data.php")
    Call<SyllabusListBean1> syllbus_teacher(@Part("school_id") String school_id,@Part("teacher_id") String teacher_id);

    @Multipart
    @POST("eduschool/eduschool_app/sllabus_databyclass.php")
    Call<SyllabusListBean1> syllbus_teacher(@Part("school_id") String school_id,@Part("subject_id") String subject_id,@Part("class") String className,@Part("section") String section);


    @Multipart
    @POST("eduschool/eduschool_app/list_exam_type.php")
    Call<ExmTypeListBean> exm_list(@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool/eduschool_app/get_own_result.php")
    Call<ResultListBean> result(@Part("school_id") String school_id,@Part("class") String classname,@Part("section") String section,@Part("exam_id") String exam_id);


    @Multipart
    @POST("eduschool/eduschool_app/teacher_survey_answer.php")
    Call<SurveyAnsBean> survey_ans(@Part("type") String type,@Part("userid") String userid,@Part("survey_id") String survey_id,@Part("question_id") String question_id);


    @Multipart
    @POST("eduschool/eduschool_app/class_result.php")
    Call<ClassResultBean> class_result(@Part("school_id") String school_id,@Part("class") String classname,@Part("section") String section,@Part("exam_id") String exam_id);

    @Multipart
    @POST("eduschool/eduschool_app/parentlist_exam_type.php")
    Call<ExamTypeListBean> exm_list_parent(@Part("school_id") String school_id,@Part("class") String classNae,@Part("section") String section);

    @Multipart
    @POST("eduschool/eduschool_app/view_today_birth_techer.php")
    Call<BirthTechrListBean> birth_teacher(@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool/eduschool_app/sendbirthcard.php")
    Call<SendBdyTeacherBean> bdy_teacher(@Part("school_id") String school_id,@Part("from_type") String from_type,@Part("from_userid") String from_userid,@Part("to_type") String to_type,@Part("birth_card") String birth_card,@Part("to_id") String to_id);


    @Multipart
    @POST("eduschool/eduschool_app/parent_create_request.php")
    Call<RaiseRequestBean> raise_requst(@Part("school_id") String school_id, @Part("userid") String userid, @Part("type") String type, @Part("class") String classname, @Part("section") String section, @Part("from_date") String from_date, @Part("to_date") String to_date, @Part("reason") String reason,@Part("request_type") String request_type);


    @Multipart
    @POST("eduschool/eduschool_app/parent_create_request.php")
    Call<RaiseReqstBean> raise_request(@Part("school_id") String school_id, @Part("userid") String userid, @Part("type") String type, @Part("class") String classname, @Part("section") String section, @Part("query") String query, @Part("type_exam") String type_exam,@Part("request_type") String request_type);


    @Multipart
    @POST("eduschool/eduschool_app/view_exam_list.php")
    Call<ExamSchedulListBean> exm_list_parent(@Part("school_id") String school_id,@Part("class") String classname,@Part("section") String section,@Part("userid") String userid);

    @Multipart
    @POST("eduschool/eduschool_app/sent_parent_request.php")
    Call<RequestListBean> request_list(@Part("school_id") String school_id, @Part("class_id") String classname, @Part("section_id") String section, @Part("userid") String userid, @Part("type") String type);

    @Multipart
    @POST("eduschool/eduschool_app/recev_comunication_teachr.php")
    Call<com.eduschool.eduschoolapp.RequestListPOJO.RequestListBean> request_list1(@Part("school_id") String school_id,@Part("userid") String userid,@Part("type") String type);


    @Multipart
    @POST("eduschool/eduschool_app/create_eventby_teacher.php")
    Call<CreateEventTechrBean> crt_event(@Part("school_id") String school_id, @Part("start_date") String start_date, @Part("end_date") String end_date, @Part("time") String time, @Part("hr") String hr, @Part("min") String min, @Part("type_event") String type_event, @Part("additional_detail") String additional_detail, @Part("from_id") String from_id, @Part("from_type") String from_type, @Part("to_type") String to_type, @Part("class_id") String class_id, @Part("section_id") String section_id);

    @Multipart
    @POST("eduschool/eduschool_app/sent_comunication_teachr.php")
    Call<com.eduschool.eduschoolapp.SendCommunicationTechrPOJO.RequestListBean> sent_requst(@Part("school_id") String school_id, @Part("userid") String userid, @Part("type") String type);


    @Multipart
    @POST("eduschool/eduschool_app/student_contact_dettail.php")
    Call<ParentContactBean> parent_contact(@Part("school_id") String school_id,@Part("userid") String userid);

    @Multipart
    @POST("eduschool/eduschool_app/stu_child_info.php")
    Call<ParentInfoBean> parent_info(@Part("school_id") String school_id, @Part("userid") String userid);

    @Multipart
    @POST("eduschool/eduschool_app/stu_prsnl_detail.php")
    Call<ParentPrsnlBean> parent_personl(@Part("school_id") String school_id, @Part("userid") String userid);
}


