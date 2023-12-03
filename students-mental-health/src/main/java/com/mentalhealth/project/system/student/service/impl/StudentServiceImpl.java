package com.mentalhealth.project.system.student.service.impl;

import java.util.List;

import com.mentalhealth.common.constant.UserConstants;
import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.framework.shiro.service.PasswordService;
import com.mentalhealth.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mentalhealth.project.system.student.mapper.StudentMapper;
import com.mentalhealth.project.system.student.domain.Student;
import com.mentalhealth.project.system.student.service.IStudentService;
import com.mentalhealth.common.utils.text.Convert;

/**
 * 学生信息表Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2021-10-15
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PasswordService passwordService;

    /**
     * 查询学生信息表
     * 
     * @param stuId 学生信息表主键
     * @return 学生信息表
     */
    @Override
    public Student selectStudentByStuId(Long stuId)
    {
        return studentMapper.selectStudentByStuId(stuId);
    }

    /**
     * 查询学生信息表列表
     * 
     * @param student 学生信息表
     * @return 学生信息表
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学生信息表
     * 
     * @param student 学生信息表
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        student.setCreateTime(DateUtils.getNowDate());
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生信息表
     * 
     * @param student 学生信息表
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        student.setUpdateTime(DateUtils.getNowDate());
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生信息表
     * 
     * @param stuIds 需要删除的学生信息表主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStuIds(String stuIds)
    {
        return studentMapper.deleteStudentByStuIds(Convert.toStrArray(stuIds));
    }

    /**
     * 删除学生信息表信息
     * 
     * @param stuId 学生信息表主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStuId(Long stuId)
    {
        return studentMapper.deleteStudentByStuId(stuId);
    }

    /**
     * 校验登录名称是否唯一
     *
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName)
    {
        int count = studentMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.STUDENT_NAME_NOT_UNIQUE;
        }
        return UserConstants.STUDENT_NAME_UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param student 学生信息
     * @return
     */
    @Override
    public String checkPhoneUnique(Student student)
    {
        Long stuId = StringUtils.isNull(student.getStuId()) ? -1L : student.getStuId();
        Student info = studentMapper.checkPhoneUnique(student.getPhone());
        if (StringUtils.isNotNull(info) && info.getStuId().longValue() != stuId.longValue())
        {
            return UserConstants.STUDENT_PHONE_NOT_UNIQUE;
        }
        return UserConstants.STUDENT_PHONE_UNIQUE;
    }

//    /**
//     * 校验学号是否唯一
//     *
//     * @param student 学生信息
//     * @return
//     */
//    @Override
//    public String checkStuNumUnique(Student student)
//    {
//        Long stuId = StringUtils.isNull(student.getStuId()) ? -1L : student.getStuId();
//        Student info = studentMapper.checkStuNumUnique(student.getStuNum());
//        if (StringUtils.isNotNull(info) && info.getStuId().longValue() != stuId.longValue())
//        {
//            return UserConstants.STUDENT_STUDENT_NUMBER_NOT_UNIQUE;
//        }
//        return UserConstants.STUDENT_STUDENT_NUMBER_UNIQUE;
//    }

    /**
     * 学生登录
     * @param student
     * @return
     */
    @Override
    public boolean login(Student student) {
        String newPassword= passwordService.encryptPassword(student.getLoginName(),student.getPassword());
        student.setPassword(newPassword);

        Student i=studentMapper.login(student);

        return i!=null?true:false;
    }
}
