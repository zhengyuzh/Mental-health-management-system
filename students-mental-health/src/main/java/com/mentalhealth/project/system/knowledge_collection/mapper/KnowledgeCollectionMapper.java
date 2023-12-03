package com.mentalhealth.project.system.knowledge_collection.mapper;

import com.mentalhealth.project.system.knowledge_collection.domain.KnowledgeCollection;

import java.util.List;

/**
 * 学生收藏信息Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2021-11-18
 */
public interface KnowledgeCollectionMapper 
{
    /**
     * 查询学生收藏信息
     * 
     * @param collectionId 学生收藏信息主键
     * @return 学生收藏信息
     */
    public KnowledgeCollection selectKnowledgeCollectionByCollectionId(Long collectionId);

    /**
     * 查询学生收藏信息 userId
     *
     * @param userId 学生id主键
     * @return 学生收藏信息
     */
    public KnowledgeCollection selectKnowledgeCollectionByUserId(Long userId,Long mentalKnowledgeId);

    /**
     * 查询学生收藏信息列表
     * 
     * @param knowledgeCollection 学生收藏信息
     * @return 学生收藏信息集合
     */
    public List<KnowledgeCollection> selectKnowledgeCollectionList(KnowledgeCollection knowledgeCollection);

    /**
     * 新增学生收藏信息
     * 
     * @param knowledgeCollection 学生收藏信息
     * @return 结果
     */
    public int insertKnowledgeCollection(KnowledgeCollection knowledgeCollection);

    /**
     * 修改学生收藏信息
     * 
     * @param knowledgeCollection 学生收藏信息
     * @return 结果
     */
    public int updateKnowledgeCollection(KnowledgeCollection knowledgeCollection);

    /**
     * 删除学生收藏信息
     * 
     * @param collectionId 学生收藏信息主键
     * @return 结果
     */
    public int deleteKnowledgeCollectionByCollectionId(Long collectionId);

    /**
     * 批量删除学生收藏信息
     * 
     * @param collectionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKnowledgeCollectionByCollectionIds(String[] collectionIds);
}
