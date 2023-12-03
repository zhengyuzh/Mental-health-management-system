package com.mentalhealth.project.system.comment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.comment.mapper.CommentMapper;
import com.mentalhealth.project.system.comment.service.ICommentService;
import com.mentalhealth.project.system.posts.domain.Comment;
import com.mentalhealth.project.system.posts.domain.Posts;
import com.mentalhealth.project.system.posts.mapper.PostsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论信息管理Service业务层处理
 *
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PostsMapper postsMapper;

    /**
     * 查询评论信息管理
     *
     * @param commentId 评论信息管理主键
     * @return 评论信息管理
     */
    @Override
    public Comment selectCommentByCommentId(Long commentId) {
        return commentMapper.selectCommentByCommentId(commentId);
    }

    /**
     * 查询评论信息管理列表
     *
     * @param comment 评论信息管理
     * @return 评论信息管理
     */
    @Override
    public List<Comment> selectCommentList(Comment comment) {
        return commentMapper.selectCommentList(comment);
    }


    /**
     * // 根据帖子id分页查询评论
     * @param postsId 帖子id
     * @param page
     * @param count
     * @return
     */
    @Override
    public PageInfo<Comment> selectCommentWithPage(Long postsId, int page, int count) {
        PageHelper.startPage(page,count);
        List<Comment> commentList = commentMapper.selectCommentWithPage(postsId);
        PageInfo<Comment> commentInfo = new PageInfo<>(commentList);
        return commentInfo;
    }

    public List<Comment> selectCommentByPostsId(Long postsId){
        return commentMapper.selectCommentByPostsId(postsId);
    }

    /**
     * 新增评论信息管理
     *
     * @param comment 评论信息管理
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment) {
        comment.setCreateTime(DateUtils.getNowDate());

        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论信息管理
     *
     * @param comment 评论信息管理
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment) {
        comment.setUpdateTime(DateUtils.getNowDate());
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除评论信息管理
     *
     * @param commentIds 需要删除的评论信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentIds(String commentIds) {
        Long[] commentIds1 = Convert.toLongArray(commentIds);  //转换为长数组
        for (Long commentId : commentIds1)  //循环遍历 commenIds 每一个值赋予 commenId
        {
            Comment comment = selectCommentByCommentId(commentId);
            //因为 parentCommentId =  commentId 所以参数为 commentId
            //查出来的是多条数据 所以用 list
            List<Comment> comments = commentMapper.selectCommentByParentCommentIdList(commentId);
            //上面找出 commentId  =  parentCommentId  的数据集合（list）
            for (Comment comment2 : comments) {  //循环遍历 再删除
                if (commentId == comment2.getParentCommentId()) {
                    commentMapper.deleteCommentByParentCommentId(comment2.getParentCommentId());
                    Posts posts = postsMapper.selectPostsByPostsId(comment.getPostsId());
                    Long num = posts.getCommentNum();
                    num = num - 1;
                    posts.setCommentNum(num);
                    postsMapper.updatePosts(posts);
                }
            }
            Posts posts = postsMapper.selectPostsByPostsId(comment.getPostsId());
            Long num = posts.getCommentNum();
            num = num - 1;
            posts.setCommentNum(num);
            postsMapper.updatePosts(posts);
        }
        return commentMapper.deleteCommentByCommentIds(Convert.toStrArray(commentIds));
    }

    /**
     * 删除评论信息管理信息
     *
     * @param commentId 评论信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentId(Long commentId) {
        return commentMapper.deleteCommentByCommentId(commentId);
    }
}
