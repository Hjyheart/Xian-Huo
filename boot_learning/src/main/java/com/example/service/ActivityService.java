package com.example.service;

import com.example.entity.Activity;
import com.example.entity.Comment;
import com.example.entity.Student;
import com.example.service.repository.ActivityRepository;
import com.example.service.repository.CommentRepository;
import com.example.service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deado on 2016/10/23.
 */
@Service
public class ActivityService {
    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private CommentRepository commentRepository;


    @Transactional
    public void save(Activity activity){
        activityRepository.save(activity);
    }

    @Transactional
    public void addStudentToActivity(String studentId, Long activityId) throws Exception{

        try{
            Student student = this.studentRepository.findByMId(studentId).iterator().next();
            Activity activity = this.activityRepository.findByMId(activityId).iterator().next();

            //add
            activity.getStudents().add(student);
            student.getActivities().add(activity);
        }catch(Exception ex){
            Exception ex_nf = new Exception("Not found");
            throw ex_nf;
        }

    }

    @Transactional
    public void deleteStudentFromActivity(String studentId, Long activityId) throws Exception{

        try{
            Activity activity = this.activityRepository.findByMId(activityId).iterator().next();
            Student  student = this.studentRepository.findByMId(studentId).iterator().next();
            //delete student from activity
            Iterator<Student> actItr = activity.getStudents().iterator();

            while(actItr.hasNext()){
                if(0 == actItr.next().getmId().compareTo(studentId)){
                    actItr.remove();
                    break;
                }
            }

            //delete activity from student
            Iterator<Activity> stuItr = student.getActivities().iterator();

            while(stuItr.hasNext()){
                if(0 == stuItr.next().getmId().compareTo(activityId)){
                    stuItr.remove();
                    break;
                }
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    @Transactional
    public void addCommentToActivity(Comment comment, Long activityId) throws Exception{

        try{
            Activity activity = this.activityRepository.findByMId(activityId).iterator().next();

            //save comment
            this.commentRepository.save(comment);

            //add
            activity.getComments().add(comment);
        }catch(Exception ex){
            throw ex;
        }
    }

    @Transactional
    public void addPraise(Long activityId) throws Exception{

        try{
            Activity activity = this.activityRepository.findByMId(activityId).iterator().next();
            //add one
            activity.setmPraise(activity.getmPraise() + 1);
        }catch(Exception ex){
            throw ex;
        }


    }


    @Transactional
    public List<Activity> findActivityById(Long activityId) throws Exception {
        try{
            return this.activityRepository.findByMId(activityId);
        }catch(Exception e){
            throw e;
        }
    }


    //temple
    @Transactional
    public List<Activity> findByName(String name){ return this.activityRepository.findByMName(name);}


    @Transactional
    public List<Activity> findAllActivities() throws Exception{
        try{
            return this.activityRepository.findAll();
        }catch(Exception e){
            throw e;
        }
    }

    @Transactional
    public void deleteActivityBymId(Long id){
        activityRepository.deleteActivityById(id);
    }

}
