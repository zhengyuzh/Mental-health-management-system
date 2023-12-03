package com.mentalhealth.project.system.knowledge_collection.service;

import com.mentalhealth.project.system.knowledge_collection.domain.KnowledgeCollection;

import java.util.List;

/**
 * 学生收藏信息Service接口
 * 
 * @author zhengyuzhu
 * @date 2021-11-18
 */
public interface IKnowledgeCollectionService 
{
    /**
     * 查询学生收藏信息
     * 
     * @param collectionId 学生收藏信息主键
     * @return 学生收藏信息
     */
    public KnowledgeCollection selectKnowledgeCollectionByCollectionId(Long collectionId);


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
     * 批量删除学生收藏信息
     * 
     * @param collectionIds 需要删除的学生收藏信息主键集合
     * @return 结果
     */
    public int deleteKnowledgeCollectionByCollectionIds(String collectionIds);

    /**
     * 删除学生收藏信息信息
     * 
     * @param collectionId 学生收藏信息主键
     * @return 结果
     */
    public int deleteKnowledgeCollectionByCollectionId(Long collectionId);
}
