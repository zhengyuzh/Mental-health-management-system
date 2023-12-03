package com.mentalhealth.project.system.knowledge_collection.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 学生收藏信息对象 sys_knowledge_collection
 * 
 * @author zhengyuzhu
 * @date 2021-11-18
 */
public class KnowledgeCollection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章收藏id */
    private Long collectionId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 健康知识id */
    @Excel(name = "健康知识id")
    private Long mentalKnowledgeId;

    private String mentalKnowledgeTitle;

    private MentalKnowledge mentalKnowledge;

    /** 收藏状态判断(0,未收藏,1,已收藏) */
    @Excel(name = "收藏状态判断(0,未收藏,1,已收藏)")
    private String collectionState;

    public String getMentalKnowledgeTitle() {
        return mentalKnowledgeTitle;
    }

    public void setMentalKnowledgeTitle(String mentalKnowledgeTitle) {
        this.mentalKnowledgeTitle = mentalKnowledgeTitle;
    }

    public MentalKnowledge getMentalKnowledge() {
        return mentalKnowledge;
    }

    public void setMentalKnowledge(MentalKnowledge mentalKnowledge) {
        this.mentalKnowledge = mentalKnowledge;
    }

    public void setCollectionId(Long collectionId)
    {
        this.collectionId = collectionId;
    }

    public Long getCollectionId()
    {
        return collectionId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setMentalKnowledgeId(Long mentalKnowledgeId)
    {
        this.mentalKnowledgeId = mentalKnowledgeId;
    }

    public Long getMentalKnowledgeId()
    {
        return mentalKnowledgeId;
    }
    public void setCollectionState(String collectionState)
    {
        this.collectionState = collectionState;
    }

    public String getCollectionState()
    {
        return collectionState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("collectionId", getCollectionId())
            .append("userId", getUserId())
            .append("mentalKnowledgeId", getMentalKnowledgeId())
            .append("collectionState", getCollectionState())
            .append("createTime", getCreateTime())
            .append("mentalKnowledge", getMentalKnowledge())
            .append("mentalKnowledgeTitle", getMentalKnowledgeTitle())
            .toString();
    }
}
