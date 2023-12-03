package com.mentalhealth.project.system.user.controller;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.constant.UserConstants;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import com.mentalhealth.project.system.knowledge.service.IMentalKnowledgeService;
import com.mentalhealth.project.system.notice.domain.Notice;
import com.mentalhealth.project.system.notice.service.INoticeService;
import com.mentalhealth.project.system.post.service.IPostService;
import com.mentalhealth.project.system.posts.domain.Posts;
import com.mentalhealth.project.system.posts.service.IPostsService;
import com.mentalhealth.project.system.role.domain.Role;
import com.mentalhealth.project.system.role.service.IRoleService;
import com.mentalhealth.project.system.user.domain.User;
import com.mentalhealth.project.system.user.service.IUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private IPostsService postsService;
    @Autowired
    private IMentalKnowledgeService mentalKnowledgeService ;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user) {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user) {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        List<User> userList = util.importExcel(file.getInputStream());
        String message = userService.importUser(userList, updateSupport);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated User user) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在 controller层");
        } else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在 controller层");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在 controller层");
        }
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        userService.checkUserDataScope(userId);
        List<Role> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", User.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated User user) {
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在 controller层");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在 controller层");
        }
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(User user) {
        userService.checkUserAllowed(user);
        if (userService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue()) {
                setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    /**
     * 进入授权角色页
     */
    @GetMapping("/authRole/{userId}")
    public String authRole(@PathVariable("userId") Long userId, ModelMap mmap) {
        User user = userService.selectUserById(userId);
        // 获取用户所属的角色列表
        List<Role> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", user);
        mmap.put("roles", User.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return prefix + "/authRole";
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/authRole/insertAuthRole")
    @ResponseBody
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (ArrayUtils.contains(Convert.toLongArray(ids), getUserId())) {
            return error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(ids));
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user) {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user) {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(User user) {
        userService.checkUserAllowed(user);
        return toAjax(userService.changeStatus(user));
    }

    /**
     * 选择  导出数据
     */
    @PostMapping("/exportData")
    @ResponseBody
    public AjaxResult exportSelected(User userModel, String userIds) {
        List<User> users = userService.selectUserList(userModel);

        List<User> userList = new ArrayList<User>(Arrays.asList(new User[users.size()]));
        Collections.copy(userList, users);

        // 条件过滤
        if (StringUtils.isNotEmpty(userIds)) {
            userList.clear();
            for (Long userId : Convert.toLongArray(userIds)) {
                for (User user : users) {
                    if (user.getUserId() == userId) {
                        userList.add(user);
                    }
                }
            }
        }
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(userList, "用户数据");
    }

    /**
     * 去首页
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex(ModelMap map,MentalKnowledge mentalKnowledgeTitle) {
        List<Notice> noticeList =  noticeService.getNotice();
        List<Posts> postsList = postsService.getStatistic();
        List<MentalKnowledge> mentalKnowledgeList = mentalKnowledgeService.selectMentalKnowledgeListIndext(mentalKnowledgeTitle);
        User user = getSysUser();
        map.put("user", user);
        map.put("noticeList", noticeList);
        map.put("postsList", postsList);
        map.put("mentalKnowledgeList", mentalKnowledgeList);
        return "foreground/client/index";
    }




    /**
     * 去Chat 测试用例
     *
     * @return
     */
    @RequestMapping("/toChat")
    public String toChat2(Model model,Long Id) {
        User userTeacher = userService.selectUserById(Id);
        String teacherLoginName  = userTeacher.getLoginName();
        User user = ShiroUtils.getSysUser();

//        String username = ShiroUtils.getSysUser().getUserName();
//        model.addAttribute("username", username);
        model.addAttribute("user", user);   //用来处理头像问题
        model.addAttribute("userTeacher", userTeacher);
        model.addAttribute("teacherLoginName", teacherLoginName);
        if ( user.getUserId() == userTeacher.getUserId() ){
            return "foreground/client/websocketTeacher";
        }
        return "foreground/client/websocket";
    }


    /**
     * 去心理知识展示页面
     */
    @GetMapping(value = "/toConsulting")
    private String toConsulting(HttpServletRequest request, ModelMap mp) {
        User user1 = ShiroUtils.getSysUser();
        mp.put("user", user1);
        return this.toConsulting(request, 1, 10, mp);
    }

    @GetMapping(value = "/pageConsulting/{p}")
    public String toConsulting(HttpServletRequest request, @PathVariable("p") int page, @RequestParam(value = "count", defaultValue = "10") int count, ModelMap mp) {
        PageInfo<User> userPageInfo = userService.selectTeacherList(page, count);
        User user1 = ShiroUtils.getSysUser();
        mp.put("user", user1);
        request.setAttribute("userPageInfo", userPageInfo);
        logger.info("分页获取文章信息: 页码 " + page + ",条数 " + count);
        return "foreground/client/show_Consulting";
    }


    // 详情
    @GetMapping(value = "/teacherDetails/{teacherUserId}")
    public String getScaleById(@PathVariable("teacherUserId") Long teacherUserId, HttpServletRequest request, ModelMap mp) {
        User user1 = ShiroUtils.getSysUser();
        mp.put("user", user1);
        request.setAttribute("userId", user1.getUserId());
        User teacher = userService.selectUserById(teacherUserId);
        if (teacher != null) {
            request.setAttribute("teacher", teacher);
            return "foreground/client/teacherDetails";
        } else {
            logger.warn("查询测试量表详情结果为空，测试量表id: " + teacherUserId);
            // 未找到对应文章页面，跳转到提示页
            return "foreground/comm/error_404";
        }
    }

}
