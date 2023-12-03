package com.mentalhealth.project.system.comment.mapper;

import com.mentalhealth.project.system.posts.domain.Comment;

import java.util.List;

/**
 * 评论信息管理Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public interface CommentMapper 
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
     * 查询评论通过 帖子id  // 分页展示某个文章的评论
     * @param posts_id 帖子id
     * @return
     */
    public List<Comment> selectCommentWithPage(Long posts_id);

    public List<Comment> selectCommentByPostsId(Long postsId);

    /**
     * 查询评论信息管理列表By父评论id
     *
     * @param parentCommentId 父评论id
     * @return 评论信息管理集合
     */
    public List<Comment> selectCommentByParentCommentIdList(Long parentCommentId);

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
     * 删除评论信息管理
     * 
     * @param commentId 评论信息管理主键
     * @return 结果
     */
    public int deleteCommentByCommentId(Long commentId);
    /**
     * 删除评论信息管理 通过 parentCommentId
     *
     * @param parentCommentId 评论信息管理主键
     * @return 结果
     */
    public int deleteCommentByParentCommentId(Long parentCommentId);

    /**
     * 批量删除评论信息管理
     * 
     * @param commentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentByCommentIds(String[] commentIds);

    /**
     * 批量删除评论信息通过 parentCommentId
     *
     * @param parentCommentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentByParentCommentIds(String[] parentCommentIds);

}
