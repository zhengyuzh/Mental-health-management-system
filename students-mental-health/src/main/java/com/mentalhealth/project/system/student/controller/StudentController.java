package com.mentalhealth.project.system.student.controller;

import java.util.List;

import com.mentalhealth.common.constant.UserConstants;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.project.system.student.domain.Student;
import com.mentalhealth.project.system.student.service.IStudentService;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpSession;

/**
 * 学生信息表Controller
 * 
 * @author zhengyuzhu
 * @date 2021-10-15
 */
@Controller
@RequestMapping("/system/student")
public class StudentController extends BaseController
{
    private String prefix = "system/student";

    @Autowired
    private IStudentService studentService;

    @RequiresPermissions("system:student:view")
    @GetMapping()
    public String student()
    {
        return prefix + "/student";
    }

    /**
     * 查询学生信息表列表
     */
    @RequiresPermissions("system:student:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 导出学生信息表列表
     */
    @RequiresPermissions("system:student:export")
    @Log(title = "学生信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Student student)
    {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "学生信息表数据");
    }

    /**
     * 新增学生信息表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生信息表
     */
    @RequiresPermissions("system:student:add")
    @Log(title = "学生信息表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Student student)
    {
//        if (UserConstants.STUDENT_NAME_NOT_UNIQUE.equals(studentService.checkLoginNameUnique(student.getLoginName())))
//        {
//            return error("新增学生'" + student.getLoginName() + "'失败，登录账号已存在 controller层");
//        }
//        else if (StringUtils.isNotEmpty(student.getPhone())
//                && UserConstants.STUDENT_PHONE_NOT_UNIQUE.equals(studentService.checkPhoneUnique(student)))
//        {
//            return error("新增学生'" + student.getLoginName() + "'失败，手机号码已存在 controller层");
//        }
//        else if (StringUtils.isNotEmpty(student.getEmail())
//                && UserConstants.STUDENT_STUNUM_NOT_UNIQUE.equals(studentService.checkStuNumUnique(student)))
//        {
//            return error("新增学生'" + student.getLoginName() + "'失败，邮箱账号已存在 controller层");
//        }
        return toAjax(studentService.insertStudent(student));
    }

    /**
     * 修改学生信息表
     */
    @GetMapping("/edit/{stuId}")
    public String edit(@PathVariable("stuId") Long stuId, ModelMap mmap)
    {
        Student student = studentService.selectStudentByStuId(stuId);
        mmap.put("student", student);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生信息表
     */
    @RequiresPermissions("system:student:edit")
    @Log(title = "学生信息表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Student student)
    {
//        if (StringUtils.isNotEmpty(student.getPhone())
//                && UserConstants.STUDENT_PHONE_NOT_UNIQUE.equals(studentService.checkPhoneUnique(student)))
//        {
//            return error("修改学生'" + student.getLoginName() + "'失败，手机号码已存在 controller层");
//        }
//        else if (StringUtils.isNotEmpty(student.getEmail())
//                && UserConstants.STUDENT_STUNUM_NOT_UNIQUE.equals(studentService.checkStuNumUnique(student)))
//        {
//            return error("修改学生'" + student.getLoginName() + "'失败，学号已存在 controller层");
//        }
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生信息表
     */
    @RequiresPermissions("system:student:remove")
    @Log(title = "学生信息表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(studentService.deleteStudentByStuIds(ids));
    }



    /**
     * 校验学生登录用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(Student student)
    {
        return studentService.checkLoginNameUnique(student.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(Student student)
    {
        return studentService.checkPhoneUnique(student);
    }

//    /**
//     * 校验学生学号
//     */
//    @PostMapping("/checkStuNumUnique")
//    @ResponseBody
//    public String checkStuNumUnique(Student student)
//    {
//        return studentService.checkStuNumUnique(student);
//    }
//





}
