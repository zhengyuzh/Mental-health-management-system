package com.mentalhealth.project.system.knowledge_collection.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.knowledge_collection.domain.KnowledgeCollection;
import com.mentalhealth.project.system.knowledge_collection.mapper.KnowledgeCollectionMapper;
import com.mentalhealth.project.system.knowledge_collection.service.IKnowledgeCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生收藏信息Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2021-11-18
 */
@Service
public class KnowledgeCollectionServiceImpl implements IKnowledgeCollectionService 
{
    @Autowired
    private KnowledgeCollectionMapper knowledgeCollectionMapper;

    /**
     * 查询学生收藏信息
     * 
     * @param collectionId 学生收藏信息主键
     * @return 学生收藏信息
     */
    @Override
    public KnowledgeCollection selectKnowledgeCollectionByCollectionId(Long collectionId)
    {
        return knowledgeCollectionMapper.selectKnowledgeCollectionByCollectionId(collectionId);
    }
    @Override
    public KnowledgeCollection selectKnowledgeCollectionByUserId(Long userId,Long mentalKnowledgeId)
    {
        return knowledgeCollectionMapper.selectKnowledgeCollectionByUserId(userId,mentalKnowledgeId);
    }

    /**
     * 查询学生收藏信息列表
     * 
     * @param knowledgeCollection 学生收藏信息
     * @return 学生收藏信息
     */
    @Override
    public List<KnowledgeCollection> selectKnowledgeCollectionList(KnowledgeCollection knowledgeCollection)
    {
        return knowledgeCollectionMapper.selectKnowledgeCollectionList(knowledgeCollection);
    }

    /**
     * 新增学生收藏信息
     * 
     * @param knowledgeCollection 学生收藏信息
     * @return 结果
     */
    @Override
    public int insertKnowledgeCollection(KnowledgeCollection knowledgeCollection)
    {
        knowledgeCollection.setCreateTime(DateUtils.getNowDate());
        return knowledgeCollectionMapper.insertKnowledgeCollection(knowledgeCollection);
    }

    /**
     * 修改学生收藏信息
     * 
     * @param knowledgeCollection 学生收藏信息
     * @return 结果
     */
    @Override
    public int updateKnowledgeCollection(KnowledgeCollection knowledgeCollection)
    {
        return knowledgeCollectionMapper.updateKnowledgeCollection(knowledgeCollection);
    }

    /**
     * 批量删除学生收藏信息
     * 
     * @param collectionIds 需要删除的学生收藏信息主键
     * @return 结果
     */
    @Override
    public int deleteKnowledgeCollectionByCollectionIds(String collectionIds)
    {
        return knowledgeCollectionMapper.deleteKnowledgeCollectionByCollectionIds(Convert.toStrArray(collectionIds));
    }

    /**
     * 删除学生收藏信息信息
     * 
     * @param collectionId 学生收藏信息主键
     * @return 结果
     */
    @Override
    public int deleteKnowledgeCollectionByCollectionId(Long collectionId)
    {
        return knowledgeCollectionMapper.deleteKnowledgeCollectionByCollectionId(collectionId);
    }
}
