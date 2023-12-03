package com.mentalhealth.project.system.posts.service;

import com.github.pagehelper.PageInfo;

import com.mentalhealth.project.system.posts.domain.Posts;

import java.util.List;

/**
 * 帖子信息管理Service接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public interface IPostsService 
{
    /**
     * 查询帖子信息管理
     * 
     * @param postsId 帖子信息管理主键
     * @return 帖子信息管理
     */
    public Posts selectPostsByPostsId(Long postsId);

    /**
     * 查询帖子信息管理列表
     * 
     * @param posts 帖子信息管理
     * @return 帖子信息管理集合
     */
    public List<Posts> selectPostsList(Posts posts);

    /**
     * 前台分页查询
     * @param page
     * @param count
     * @param communityType
     * @return
     */
    public PageInfo<Posts> selectPostsWithPage(Integer page, Integer count, String postsTitle, Long communityType);

    /**
     * 新增帖子信息管理
     * 
     * @param posts 帖子信息管理
     * @return 结果
     */
    public int insertPosts(Posts posts);

    /**
     * 修改帖子信息管理
     * 
     * @param posts 帖子信息管理
     * @return 结果
     */
    public int updatePosts(Posts posts);

    /**
     * 批量删除帖子信息管理
     * 
     * @param postsIds 需要删除的帖子信息管理主键集合
     * @return 结果
     */
    public int deletePostsByPostsIds(String postsIds);

    /**
     * 删除帖子信息管理信息
     * 
     * @param postsId 帖子信息管理主键
     * @return 结果
     */
    public int deletePostsByPostsId(Long postsId);


    /**
     * 根据帖子ID查询帖子信息
     *
     * @param postsId 帖子ID
     * @return 帖子
     */
    public Posts selectPostsById(Long postsId);


    /**
     * 根据所有PostsId
     *
     * @return PostsId集合信息
     */
    public List<Posts> selectPostsAll();



    /**
     * 查询所有PostsId的评论
     *
     * @return PostsId的评论集合信息
     */
    public Long selectAllComment(Long postsId);

    /**
     * 查询讨论数前十的帖子
     * @return
     */
    public List<Posts> getStatistic();

    /**
     * 查询讨论数前十的帖子 按照测评社区分类
     * @return
     */
    List<Posts> getStatisticTwo(Long communityType);
}
