package com.mentalhealth.project.system.posts.controller;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.project.system.comment.service.ICommentService;
import com.mentalhealth.project.system.posts.domain.Comment;
import com.mentalhealth.project.system.posts.domain.Posts;
import com.mentalhealth.project.system.posts.service.IPostsService;
import com.mentalhealth.project.system.user.domain.User;
import com.mentalhealth.project.system.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 帖子信息管理Controller
 *
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Controller
public class PostsShowController extends BaseController {
    @Autowired
    private IPostsService postsService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICommentService commentService;

    /**
     * 去心理知识展示页面
     */
    @RequestMapping(value = "/system/PostsOne/toPostsOne/{communityType}")
    private String ShowPostsOne(@PathVariable("communityType") Long communityType,HttpServletRequest request, ModelMap mp,String postsTitle) {
        Long scaleTypeR = communityType;
        System.out.println("测评文章类型："+ scaleTypeR);
        User user = ShiroUtils.getSysUser();
        mp.put("user", user);
        return this.ShowPosts(scaleTypeR,request, 1, 10, mp,postsTitle);
    }

    /**
     * 去心理知识展示页面
     */
    @RequestMapping(value = "/system/Posts/toPosts/{scaleType}")
        private String ShowPosts(@PathVariable("scaleType") int scaleType,HttpServletRequest request, ModelMap mp,String postsTitle) {
        int scaleTypeR = scaleType;
        Long communityType = 0L;
        System.out.println("测评文章类型："+ scaleTypeR);
        if(scaleTypeR < 6 ){
            //大众化评论区
            communityType = 0L;
            System.out.println(communityType.equals(0));
        }else if(scaleTypeR == 6 || scaleType == 7) {
            communityType = 1L;
            System.out.println(communityType.equals(1));
        }
        User user = ShiroUtils.getSysUser();
        mp.put("user", user);
        return this.ShowPosts(communityType,request, 1, 10, mp,postsTitle);
    }



    @GetMapping(value = "/pagePosts/{p}")
    public String ShowPosts(Long communityType, HttpServletRequest request, @PathVariable("p") int page, @RequestParam(value = "count", defaultValue = "10") int count, ModelMap mp,String postsTitle) {
        HttpSession session = request.getSession();
        Long communityTypeFind = (Long) session.getAttribute("communityType");

        if (communityTypeFind != null) {
            communityType = communityTypeFind;
        }

        PageInfo<Posts> postsPageInfo = postsService.selectPostsWithPage(page, count,postsTitle,communityType);
        //查询讨论数前十的帖子
        List<Posts> postsList = postsService.getStatisticTwo(communityType);
        User user1 = ShiroUtils.getSysUser();
        mp.put("user", user1);
        request.setAttribute("postsPageInfo", postsPageInfo);
        request.setAttribute("postsList", postsList);
        request.setAttribute("postsTitle", postsTitle);
        session.setAttribute("communityType",communityType);
        logger.info("分页获取文章信息: 页码 " + page + ",条数 " + count);
        return "foreground/client/show_posts";
    }


    // 文章详情查询
    @GetMapping(value = "/posts/{postsId}")
    public String getPostsById(@PathVariable("postsId") Long postsId, HttpServletRequest request, ModelMap mp) {

        User user1 = ShiroUtils.getSysUser();
        mp.put("user", user1);
        Posts posts = postsService.selectPostsByPostsId(postsId);
        if (posts == null){  // 举报信息查看的时候判断
            return "foreground/comm/deleteOk";
        }
        User userPosts = userService.selectUserById(posts.getUserId());

        if (posts != null) {
            // 查询封装评论相关数据
            getPostsComments(request, posts);
            request.setAttribute("posts", posts);
            request.setAttribute("userPosts", userPosts);
            return "foreground/client/postsDetails";
        } else {
            logger.warn("查询文章详情结果为空，查询文章id: " + postsId);
            // 未找到对应文章页面，跳转到提示页
            return "foreground/comm/deleteOk";
        }
    }

    // 查询文章的评论信息，并补充到文章详情里面
    private void getPostsComments(HttpServletRequest request, Posts posts) {
        // cp表示评论页码，commentPage
        String cp = request.getParameter("cp");
        cp = StringUtils.isBlank(cp) ? "1" : cp;
        request.setAttribute("cp", cp);
        PageInfo<Comment> comments = commentService.selectCommentWithPage(posts.getPostsId(), Integer.parseInt(cp), 10);
        // 先查出属于这个 posts 的评论 commentList 集合
        List<Comment> replyComments = commentService.selectCommentByPostsId(posts.getPostsId());

        // 再查出 commentId = parentCommentId 的评论
        // 把相同的添加进 replyComments 集合
//            List<Comment> replyComments=new ArrayList<Comment>();
        replyComments.removeIf(comment -> comment.getParentCommentId() == null);
//        System.out.println(replyComments);
        request.setAttribute("cp", cp);
        request.setAttribute("comments", comments);
        request.setAttribute("replyComments", replyComments);
    }

    /**
     * 删除个人帖子信息管理
     */
    @RequiresPermissions("system:posts_user:remove")
    @Log(title = "个人帖子信息管理", businessType = BusinessType.DELETE)
    @RequestMapping( "/system/posts_user/removePosts")
    public String removePosts(Long postsId,ModelMap modelMap)
    {
        User user =userService.selectUserById(ShiroUtils.getUserId());
        int row = postsService.deletePostsByPostsId(postsId);
        modelMap.put("user",user);
        if (row>0){
            return "foreground/comm/deleteOk";
        }
        else {
            return "foreground/comm/error_404";
        }

    }

 /**
     * 删除个人帖子信息管理
     */
    @RequestMapping( "/system/posts_user/toPosts")
    public String toPosts()
    {
        return "redirect:/system/Posts/toPosts";
    }


}
