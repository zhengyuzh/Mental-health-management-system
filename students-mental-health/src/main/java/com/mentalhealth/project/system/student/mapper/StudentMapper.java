package com.mentalhealth.project.system.student.mapper;

import java.util.List;
import com.mentalhealth.project.system.student.domain.Student;
import com.mentalhealth.project.system.user.domain.User;

/**
 * 学生信息表Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2021-10-15
 */
public interface StudentMapper 
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
     * 删除学生信息表
     * 
     * @param stuId 学生信息表主键
     * @return 结果
     */
    public int deleteStudentByStuId(Long stuId);

    /**
     * 批量删除学生信息表
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByStuIds(String[] stuIds);


    /**
     * 校验登录名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone 手机号码
     * @return 结果
     */
    public Student checkPhoneUnique(String phone);

//    /**
//     * 校验学号是否唯一
//     *
//     * @param stuNum 学号
//     * @return 结果
//     */
//    public Student checkStuNumUnique(String stuNum);

    /**
     * 登录
     *
     * @param student 学号
     * @return 结果
     */
    public Student login(Student student);
}
