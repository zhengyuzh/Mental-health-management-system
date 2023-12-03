package com.mentalhealth.project.system.posts.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.comment.mapper.CommentMapper;
import com.mentalhealth.project.system.posts.domain.Posts;
import com.mentalhealth.project.system.posts.mapper.PostsMapper;
import com.mentalhealth.project.system.posts.service.IPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 帖子信息管理Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Service("postsService")
public class PostsServiceImpl implements IPostsService 
{
    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询帖子信息管理
     * 
     * @param postsId 帖子信息管理主键
     * @return 帖子信息管理
     */
    @Override
    public Posts selectPostsByPostsId(Long postsId)
    {
        return postsMapper.selectPostsByPostsId(postsId);
    }

    /**
     * 查询帖子信息管理列表
     * 
     * @param posts 帖子信息管理
     * @return 帖子信息管理
     */
    @Override
    public List<Posts> selectPostsList(Posts posts)
    {
        return postsMapper.selectPostsList(posts);
    }

    /**
     *  前台 分页查询文章列表
     * @param page
     * @param count
     * @param communityType
     * @return
     */
    @Override
    public PageInfo<Posts> selectPostsWithPage(Integer page, Integer count, String postsTitle, Long communityType) {
        PageHelper.startPage(page, count);
        List<Posts> posts = postsMapper.selectPostsWithPage(postsTitle,communityType);
        PageInfo<Posts> pageInfo=new PageInfo<>(posts);
        return pageInfo;
    }


    /**
     * 新增帖子信息管理
     * 
     * @param posts 帖子信息管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPosts(Posts posts)
    {
        posts.setCreateTime(DateUtils.getNowDate());
        posts.setLastComTime(DateUtils.getNowDate());
        return postsMapper.insertPosts(posts);
    }

    /**
     * 修改帖子信息管理
     * 
     * @param posts 帖子信息管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePosts(Posts posts)
    {
        posts.setUpdateTime(DateUtils.getNowDate());

        return postsMapper.updatePosts(posts);
    }

    /**
     * 批量删除帖子信息管理
     * 
     * @param postsIds 需要删除的帖子信息管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePostsByPostsIds(String postsIds)
    {
        postsMapper.deleteCommentByPostsIds(Convert.toStrArray(postsIds));
        return postsMapper.deletePostsByPostsIds(Convert.toStrArray(postsIds));
    }

    /**
     * 删除帖子信息管理信息
     * 
     * @param postsId 帖子信息管理主键
     * @return 结果
     */
    @Override
    public int deletePostsByPostsId(Long postsId)
    {
        postsMapper.deleteCommentByPostsId(postsId);
        return postsMapper.deletePostsByPostsId(postsId);
    }




    /**
     * 根据所有帖子ID
     *
     * @return 帖子ID集合信息
     */
    @Override
    public List<Posts> selectPostsAll()
    {
        return postsMapper.selectPostsAll();
    }


    /**
     * 根据所有帖子的评论
     *
     * @return 帖子ID评论集合信息
     */
    @Override
    public Long selectAllComment(Long postsId)
    {
        return postsMapper.selectAllComment(postsId);
    }
    /**
     * 根据帖子ID查询帖子信息
     *
     * @param postsId 帖子ID
     * @return 字典类型
     */
    @Override
    public Posts selectPostsById(Long postsId)
    {
        return postsMapper.selectPostsById(postsId);
    }

    /**
     * 查询讨论数前十的帖子
     * @return
     */
    @Override
    public List<Posts> getStatistic( ) {

        return postsMapper.getStatistic();
    }

    @Override
    public List<Posts> getStatisticTwo(Long communityType) {
        return postsMapper.getStatisticTwo(communityType);
    }
}
