package com.mentalhealth.project.system.comment_user.controller;

import com.mentalhealth.common.utils.DateUtils;
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

import java.util.List;

/**
 * 个人评论信息管理Controller
 * 
 * @author zhengyuzhu
 * @date 2021-10-22
 */
@Controller
@RequestMapping("/system/comment_user")
public class CommentUserController extends BaseController
{
    private String prefix = "system/comment_user";

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostsService postsService;


    @RequiresPermissions("system:comment_user:view")
    @GetMapping()
    public String comment_user()
    {
        return prefix + "/comment_user";
    }

    /**
     * 查询个人评论信息管理列表
     */
    @RequiresPermissions("system:comment_user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Comment comment)
    {
        startPage();
        Long userId = ShiroUtils.getUserId();
        comment.setUserId(userId);
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }

    /**
     * 导出个人评论信息管理列表
     */
    @RequiresPermissions("system:comment_user:export")
    @Log(title = "个人评论信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Comment comment)
    {
        List<Comment> list = commentService.selectCommentList(comment);
        ExcelUtil<Comment> util = new ExcelUtil<Comment>(Comment.class);
        return util.exportExcel(list, "个人评论信息管理数据");
    }

    /**
     * 新增个人评论信息管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存个人评论信息管理
     */
    @RequiresPermissions("system:comment_user:add")
    @Log(title = "个人评论信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Comment comment)
    {
        Long userId = ShiroUtils.getUserId();
        comment.setUserId(userId);
        User user = userService.selectUserById(userId);
        comment.setAvatar(user.getAvatar());
        return toAjax(commentService.insertComment(comment));
    }

    /**
     * 新增保存评论信息管理
     */
    @RequiresPermissions("system:comment_user:add")
    @Log(title = "评论信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/addForegroundComment")
    @ResponseBody
    public AjaxResult addForegroundComment(Comment comment) {
        Long user_id = ShiroUtils.getUserId();
        String userName = ShiroUtils.getLoginName();
        User user = userService.selectUserById(user_id);
        comment.setAvatar(user.getAvatar());
        comment.setUserId(user_id);
        comment.setUserName(userName);
        //增加评论数
        Posts posts1 = postsService.selectPostsByPostsId(comment.getPostsId());
        Long num = postsService.selectAllComment(posts1.getPostsId());
        System.out.println("num----------" + num);
        Long commentNum = posts1.getCommentNum();
        System.out.println("commentNum----------" + commentNum);
        if (num != commentNum) {    //查出原来的总数  不等就赋予 再加 1
            posts1.setCommentNum(num + 1);
        }
//        Posts posts1= postsService.selectPostsByPostsId(comment.getPostsId());
//        Long num = posts1.getCommentNum();
        num = num+1;     //相等 就加 1 再赋予
        posts1.setCommentNum(num);

        posts1.setLastComTime(DateUtils.getNowDate());//最后评论时间
        posts1.setLastComUserId(user_id);  //最后评论用户id
        postsService.updatePosts(posts1);
        commentService.insertComment(comment);
        return AjaxResult.success();
    }

    /**
     * 新增评论信息管理
     */
    @GetMapping("/addReply/{commentId}")
    public String addReply(@PathVariable("commentId") Long commentId, ModelMap mmp) {

        if (commentId != null) {
            Comment comment = commentService.selectCommentByCommentId(commentId);
//            comment.setParentCommentId(comment.getCommentId());
//            comment.setParentUserId(comment.getUserId());
//            comment.setPostsId(comment.getPostsId());
            mmp.put("comment", comment);
        }

        return prefix + "/addReply";
    }

    /**
     * 新增保存评论信息管理
     */
    @RequiresPermissions("system:comment:add")
    @Log(title = "评论信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/addReply")
    @ResponseBody
    public AjaxResult addReply(Comment comment) {
        Long user_id = ShiroUtils.getUserId();
        String userName = ShiroUtils.getLoginName();
        User user = userService.selectUserById(user_id);
        comment.setAvatar(user.getAvatar());
        comment.setUserId(user_id);
        comment.setUserName(userName);
        //增加评论数
        Posts posts1 = postsService.selectPostsByPostsId(comment.getPostsId());
        Long num = postsService.selectAllComment(posts1.getPostsId());
        System.out.println("num----------" + num);
        Long commentNum = posts1.getCommentNum();
        System.out.println("commentNum----------" + commentNum);
        if (num != commentNum) {    //查出原来的总数  不等就赋予 再加 1
            posts1.setCommentNum(num + 1);
        }
//        Posts posts1= postsService.selectPostsByPostsId(comment.getPostsId());
//        Long num = posts1.getCommentNum();
        num = num+1;     //相等 就加 1 再赋予
        posts1.setCommentNum(num);

        posts1.setLastComTime(DateUtils.getNowDate());//最后评论时间
        posts1.setLastComUserId(user_id);  //最后评论用户id
        toAjax(postsService.updatePosts(posts1));
        commentService.insertComment(comment);
        return AjaxResult.success();
    }



    /**
     * 修改个人评论信息管理
     */
    @GetMapping("/edit/{commentId}")
    public String edit(@PathVariable("commentId") Long commentId, ModelMap mmap)
    {
        Comment comment = commentService.selectCommentByCommentId(commentId);
        mmap.put("comment", comment);
        return prefix + "/edit";
    }

    /**
     * 修改保存个人评论信息管理
     */
    @RequiresPermissions("system:comment_user:edit")
    @Log(title = "个人评论信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Comment comment)
    {
        return toAjax(commentService.updateComment(comment));
    }

    /**
     * 删除个人评论信息管理
     */
    @RequiresPermissions("system:comment_user:remove")
    @Log(title = "个人评论信息管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(commentService.deleteCommentByCommentIds(ids));
    }
}
