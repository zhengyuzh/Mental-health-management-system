package com.mentalhealth.project.system.comment.service;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.project.system.posts.domain.Comment;

import java.util.List;

/**
 * 评论信息管理Service接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public interface ICommentService 
{
    /**
     * 查询评论信息管理
     * 
     * @param commentId 评论信息管理主键
     * @return 评论信息管理
     */
    public Comment selectCommentByCommentId(Long commentId);

    /**
     * 查询评论信息管理列表
     * 
     * @param comment 评论信息管理
     * @return 评论信息管理集合
     */
    public List<Comment> selectCommentList(Comment comment);

    /**
     * 通过帖子id 查询评论集合
     * @param postsId
     * @param page
     * @param count
     * @return
     */
    public PageInfo<Comment> selectCommentWithPage(Long postsId, int page, int count);

    public List<Comment> selectCommentByPostsId(Long postsId);

    /**
     * 新增评论信息管理
     * 
     * @param comment 评论信息管理
     * @return 结果
     */
    public int insertComment(Comment comment);

    /**
     * 修改评论信息管理
     * 
     * @param comment 评论信息管理
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 批量删除评论信息管理
     * 
     * @param commentIds 需要删除的评论信息管理主键集合
     * @return 结果
     */
    public int deleteCommentByCommentIds(String commentIds);

    /**
     * 删除评论信息管理信息
     * 
     * @param commentId 评论信息管理主键
     * @return 结果
     */
    public int deleteCommentByCommentId(Long commentId);
}
