package com.mentalhealth.project.system.posts_user.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.posts.domain.Comment;
import com.mentalhealth.project.system.posts.domain.Posts;
import com.mentalhealth.project.system.posts.service.IPostsService;
import com.mentalhealth.project.system.user.domain.User;
import com.mentalhealth.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 个人帖子信息管理Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Controller
@RequestMapping("/system/posts_user")
public class PostsUserController extends BaseController
{
    private String prefix = "system/posts_user";

    @Autowired
    private IPostsService postsService;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("system:posts_user:view")
    @GetMapping()
    public String posts_user()
    {
        return prefix + "/posts_user";
    }

    /**
     * 查询个人帖子信息管理列表
     */
    @RequiresPermissions("system:posts_user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Posts posts)
    {
        startPage();
        Long userId = ShiroUtils.getUserId();  //个人发帖获取 该用户id
        posts.setUserId(userId);

        List<Posts> list = postsService.selectPostsList(posts);
        return getDataTable(list);
    }

    /**
     * 导出个人帖子信息管理列表
     */
    @RequiresPermissions("system:posts_user:export")
    @Log(title = "个人帖子信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Posts posts)
    {
        List<Posts> list = postsService.selectPostsList(posts);
        ExcelUtil<Posts> util = new ExcelUtil<Posts>(Posts.class);
        return util.exportExcel(list, "个人帖子信息管理数据");
    }

    /**
     * 新增个人帖子信息管理
     */
    @GetMapping("/add")
    public String add(ModelMap mp)
    {
        User user = ShiroUtils.getSysUser();
        mp.put("user",user);
        return prefix + "/add";
    }

    /**
     * 新增保存个人帖子信息管理
     */
    @RequiresPermissions("system:posts_user:add")
    @Log(title = "个人帖子信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Posts posts)
    {
        Long userId = ShiroUtils.getUserId();  //个人发帖获取 该用户id
        posts.setUserId(userId);
        User user = userService.selectUserById(userId);  //通过userId 查出 用户信息
        posts.setLastComUserId(userId);
        posts.setCommentNum((long) 0);

        return toAjax(postsService.insertPosts(posts));
    }


    /**
     * 新增个人帖子信息管理
     */
    @GetMapping("/addPosts")
    public String addPosts(ModelMap mp)
    {
        User user = getSysUser();
        mp.put("user",user);
        return "/foreground/client/addPosts";
    }

    /**
     * 新增保存帖子信息管理
     */
    @RequiresPermissions("system:posts_user:add")
    @Log(title = "帖子信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/addPosts")
    @ResponseBody
    public AjaxResult addPosts(Posts posts, HttpServletRequest request)
    {

        HttpSession session = request.getSession();
        Long communityType = (Long) session.getAttribute("communityType");
        System.out.println("查看添加的贴子类型："+ communityType);
        posts.setCommunityType(communityType);
        if(communityType == null){
            posts.setCommunityType(2L);
        }
        addSave(posts);
        return AjaxResult.success();
    }


    /**
     * 修改个人帖子信息管理
     */
    @GetMapping("/edit/{postsId}")
    public String edit(@PathVariable("postsId") Long postsId, ModelMap mmap)
    {
        Posts posts = postsService.selectPostsByPostsId(postsId);
        mmap.put("posts", posts);
        return prefix + "/edit";
    }

    /**
     * 修改保存个人帖子信息管理
     */
    @RequiresPermissions("system:posts_user:edit")
    @Log(title = "个人帖子信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Posts posts)
    {
        return toAjax(postsService.updatePosts(posts));
    }

    /**
     * 删除个人帖子信息管理
     */
    @RequiresPermissions("system:posts_user:remove")
    @Log(title = "个人帖子信息管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(postsService.deletePostsByPostsIds(ids));
    }



    /**
     * 查询帖子评论列表
     */
    @RequiresPermissions("system:comment:list")
    @GetMapping("/detail/{postsId}")
    public String detail(@PathVariable("postsId") Long postsId, ModelMap mmap)
    {
        mmap.put("posts", postsService.selectPostsByPostsId(postsId));

        mmap.put("postsList", postsService.selectPostsAll());   //查询所有帖子
        return "system/comment/comment";
    }


    /**
     * 新增评论信息管理
     */
    @GetMapping("/addComment/{postsId}")
    public String addComment(@PathVariable("postsId") Long postsId, ModelMap mmp)
    {
        Comment comment = new Comment();
        comment.setPostsId(postsId);
        mmp.put("comment",comment);
        return "system/comment/add";
    }



}
