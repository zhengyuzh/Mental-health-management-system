package com.mentalhealth.project.system.knowledge.service;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;

import java.util.List;

/**
 * 心理健康知识Service接口
 *
 * @author zhengyuzhu
 * @date 2023-08-09
 */
public interface IMentalKnowledgeService
{
    /**
     * 查询心理健康知识
     *
     * @param mentalKnowledgeId 心理健康知识主键
     * @return 心理健康知识
     */
    public MentalKnowledge selectMentalKnowledgeByMentalKnowledgeId(Long mentalKnowledgeId);

    /**
     * 查询心理健康知识列表
     *
     * @param mentalKnowledge 心理健康知识
     * @return 心理健康知识集合
     */
    public List<MentalKnowledge> selectMentalKnowledgeList(MentalKnowledge mentalKnowledge);
    public List<MentalKnowledge> selectMentalKnowledgeListIndext(MentalKnowledge mentalKnowledge);

    /**
     * 新增心理健康知识
     *
     * @param mentalKnowledge 心理健康知识
     * @return 结果
     */
    public int insertMentalKnowledge(MentalKnowledge mentalKnowledge);

    /**
     * 修改心理健康知识
     *
     * @param mentalKnowledge 心理健康知识
     * @return 结果
     */
    public int updateMentalKnowledge(MentalKnowledge mentalKnowledge);

    /**
     * 批量删除心理健康知识
     *
     * @param mentalKnowledgeIds 需要删除的心理健康知识主键集合
     * @return 结果
     */
    public int deleteMentalKnowledgeByMentalKnowledgeIds(String mentalKnowledgeIds);

    /**
     * 删除心理健康知识信息
     *
     * @param mentalKnowledgeId 心理健康知识主键
     * @return 结果
     */
    public int deleteMentalKnowledgeByMentalKnowledgeId(Long mentalKnowledgeId);

    /**
     * 校验标题名称是否唯一
     *
     * @param mentalKnowledge 心理知识信息
     * @return 结果
     */
    public String checkMentalKnowledgeTitleUnique(MentalKnowledge mentalKnowledge);



    /**
     * 前台 分页查询文章列表
     * @param page
     * @param count
     * @return
     */
    public PageInfo<MentalKnowledge> selectMentalKnowledgeWithPage(Integer page, Integer count,String mentalKnowledgeTitle);

    public MentalKnowledge selectNext(Long mentalKnowledgeId);

    public MentalKnowledge selectUp(Long mentalKnowledgeId);
}
