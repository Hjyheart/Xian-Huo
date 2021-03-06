package com.example.service;

import com.example.entity.Club;
import com.example.entity.Comment;
import com.example.entity.Student;
import com.example.service.repository.ClubRepository;
import com.example.service.repository.CommentRepository;
import com.example.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by stanforxc on 2016/11/2.
 */


@Service
public class ClubService {
    @Autowired
    @Resource
    private ClubRepository clubRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Resource
    private CommentRepository commentRepository;


    @Transactional
    public void save(Club club) {
        clubRepository.save(club);
    }

    @Transactional
    public void remove(Club club){
        clubRepository.delete(club);
    }

    public List<Club> findByMId(Long id) {
        return clubRepository.findByMId(id);
    }

    public List<Club> findByMName(String name) {
        return clubRepository.findByMName(name);
    }

    @Transactional
    public boolean studentQuitClub(Long clubid, String stuid) throws Exception {  //AoQ申请true或者退出false

        try {
            Club c = clubRepository.findByMId(clubid).iterator().next();
            Student s = studentRepository.findByMId(stuid).iterator().next();
            Iterator<Student> iterStu = c.getStudents().iterator();
            Iterator<Club> iterClub = s.getClubs().iterator();
            while (iterClub.hasNext()) {
                if (0 == iterClub.next().getmId().compareTo(clubid)) {
                    iterClub.remove();
                    break;
                }
            }

            while (iterStu.hasNext()) {
                if (0 == iterStu.next().getmId().compareTo(stuid)) {
                    iterStu.remove();
                    break;
                }
            }
            clubRepository.setDClubMemberNumberById(clubid);

            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public boolean studentApplyClub(Long clubid, String stuid) {
        List<Club> clubs = clubRepository.findByMId(clubid);
        List<Student> students = studentRepository.findByMId(stuid);
        Iterator<Club> iterClub = clubs.iterator();
        Iterator<Student> iterStudent = students.iterator();
        if (!iterStudent.hasNext() && !iterClub.hasNext()) {
            return false;
        } else {
            Club c = iterClub.next();
            Student s = iterStudent.next();

            List<Student> studentList = c.getStudents();

            // 拒绝重复添加
            for (Student stu : studentList){
                if (stu.getmId().equals(stuid)){
                    return false;
                }
            }

            c.getStudents().add(s);
            s.getClubs().add(c);
            clubRepository.setAClubMemberNumberById(clubid);

            return true;
        }
    }

    @Transactional
    public List<Comment> getClubComment(Long clubid) {
        Club club = clubRepository.findByMId(clubid).iterator().next();
        return club.getComments();

    }

    @Transactional
    public List<Club> findAll() throws Exception {
        try {
            return this.clubRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void addCommentToClub(Comment comment, Long clubId) throws Exception {

        try {
            Club club = this.clubRepository.findByMId(clubId).iterator().next();

            //save comment
            this.commentRepository.save(comment);

            //add
            club.getComments().add(comment);
        } catch (Exception ex) {
            throw ex;
        }

    }
}
