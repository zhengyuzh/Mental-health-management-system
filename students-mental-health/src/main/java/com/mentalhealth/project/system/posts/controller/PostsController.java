package com.mentalhealth.project.system.posts.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.comment.service.ICommentService;
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
import java.util.List;

/**
 * 帖子信息管理Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Controller
@RequestMapping("/system/posts")
public class PostsController extends BaseController
{
    private String prefix = "system/posts";

    @Autowired
    private IPostsService postsService;
    @Autowired
    private IUserService  userService;
    @Autowired
    private ICommentService commentService;

    @RequiresPermissions("system:posts:view")
    @GetMapping()
    public String posts()
    {
        return prefix + "/posts";
    }

    /**
     * 查询帖子信息管理列表
     */
    @RequiresPermissions("system:posts:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Posts posts)
    {
        startPage();
        List<Posts> list = postsService.selectPostsList(posts);
        return getDataTable(list);
    }

    /**
     * 导出帖子信息管理列表
     */
    @RequiresPermissions("system:posts:export")
    @Log(title = "帖子信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Posts posts)
    {
        List<Posts> list = postsService.selectPostsList(posts);
        ExcelUtil<Posts> util = new ExcelUtil<Posts>(Posts.class);
        return util.exportExcel(list, "帖子信息管理数据");
    }

    /**
     * 新增帖子信息管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存帖子信息管理
     */
    @RequiresPermissions("system:posts:add")
    @Log(title = "帖子信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Posts posts)
    {


        Long userId = ShiroUtils.getUserId();
        posts.setUserId(userId);
        posts.setLastComUserId(userId);

        User user = userService.selectUserById(userId);
        posts.setCommentNum((long) 0);
        return toAjax(postsService.insertPosts(posts));
    }

//    /**
//     * 新增保存帖子信息管理
//     */
//    @RequiresPermissions("system:posts:add")
//    @Log(title = "帖子信息管理", businessType = BusinessType.INSERT)
//    @PostMapping("/addPosts")
//    @ResponseBody
//    public AjaxResult addPosts(Posts posts)
//    {
//        return addSave(posts);
//    }

    /**
     * 修改帖子信息管理
     */
    @GetMapping("/edit/{postsId}")
    public String edit(@PathVariable("postsId") Long postsId, ModelMap mmap)
    {
        Posts posts = postsService.selectPostsByPostsId(postsId);
        mmap.put("posts", posts);
        return prefix + "/edit";
    }

    /**
     * 修改保存帖子信息管理
     */
    @RequiresPermissions("system:posts:edit")
    @Log(title = "帖子信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Posts posts)
    {
        return toAjax(postsService.updatePosts(posts));
    }

    /**
     * 删除帖子信息管理
     */
    @RequiresPermissions("system:posts:remove")
    @Log(title = "帖子信息管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(postsService.deletePostsByPostsIds(ids));
    }


    /**
     * 查询帖子评论列表
     */
    @RequiresPermissions("system:posts:list")
    @GetMapping("/postsDetail/{postsId}")
    public String detail(@PathVariable("postsId") Long postsId, ModelMap mmap)
    {
        mmap.put("posts", postsService.selectPostsByPostsId(postsId));

        mmap.put("postsList", postsService.selectPostsAll());   //查询所有帖子
        return "system/comment/comment";
    }


    /**
     * 查询被举报的帖子
     */
    @RequiresPermissions("system:posts:list")
    @GetMapping("/postsReport/{postsId}")
    public String postsReport(@PathVariable("postsId") Long postsId, ModelMap mmap)
    {
        mmap.put("posts", postsService.selectPostsByPostsId(postsId));
        mmap.put("postsList", postsService.selectPostsAll());   //查询所有帖子
        return "system/posts/postsReport";
    }




    /**
     * 新增评论信息管理
     */
    @RequiresPermissions("system:comment:add")
    @GetMapping("/addComment/{postsId}")
    public String addComment(@PathVariable("postsId") Long postsId, ModelMap mmp)
    {
        Comment comment = new Comment();
        comment.setPostsId(postsId);
        mmp.put("comment",comment);
        return "system/comment/add";
    }




    /**
     * 前台新增评论信息 窗口
     */
    @RequiresPermissions("system:comment:add")
    @GetMapping("/addForegroundComment/{postsId}")
    public String addForegroundComment(@PathVariable("postsId") Long postsId, ModelMap mmp)
    {
        Comment comment = new Comment();
        comment.setPostsId(postsId);
        mmp.put("comment",comment);
        return "foreground/client/addComment";
    }


}
