package com.mentalhealth.project.system.student.service;

import java.util.List;
import com.mentalhealth.project.system.student.domain.Student;
import com.mentalhealth.project.system.user.domain.User;

/**
 * 学生信息表Service接口
 * 
 * @author zhengyuzhu
 * @date 2021-10-15
 */
public interface IStudentService 
{
    /**
     * 查询学生信息表
     * 
     * @param stuId 学生信息表主键
     * @return 学生信息表
     */
    public Student selectStudentByStuId(Long stuId);

    /**
     * 查询学生信息表列表
     * 
     * @param student 学生信息表
     * @return 学生信息表集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生信息表
     * 
     * @param student 学生信息表
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生信息表
     * 
     * @param student 学生信息表
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学生信息表
     * 
     * @param stuIds 需要删除的学生信息表主键集合
     * @return 结果
     */
    public int deleteStudentByStuIds(String stuIds);

    /**
     * 删除学生信息表信息
     * 
     * @param stuId 学生信息表主键
     * @return 结果
     */
    public int deleteStudentByStuId(Long stuId);


    /**
     * 校验学生登录账号是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param student 学生信息
     * @return 结果
     */
    public String checkPhoneUnique(Student student);

//    /**
//     * 校验学号是否唯一
//     *
//     * @param student 学生信息
//     * @return 结果
//     */
//    public String checkStuNumUnique(Student student);

    /**
     * 登录
     * @param student
     * @return
     */
    public boolean login(Student student);

}
