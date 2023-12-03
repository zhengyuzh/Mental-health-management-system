package com.mentalhealth.project.system.knowledge.mapper;

import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 心理健康知识Mapper接口
 *
 * @author zhengyuzhu
 * @date 2023-08-09
 */
@Mapper
public interface MentalKnowledgeMapper
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
     * 删除心理健康知识
     *
     * @param mentalKnowledgeId 心理健康知识主键
     * @return 结果
     */
    public int deleteMentalKnowledgeByMentalKnowledgeId(Long mentalKnowledgeId);

    /**
     * 批量删除心理健康知识
     *
     * @param mentalKnowledgeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMentalKnowledgeByMentalKnowledgeIds(String[] mentalKnowledgeIds);

    /**
     * 校验标题名称是否唯一
     *
     * @param mentalKnowledgeTitle 标题名称
     * @return 结果
     */
    public MentalKnowledge checkMentalKnowledgeTitleUnique(String mentalKnowledgeTitle);

    // 文章发分页查询
    public List<MentalKnowledge> selectMentalKnowledgeWithPage(String mentalKnowledgeTitle);

    /**
     * 下一篇
     * @param mental_knowledge_id
     * @return
     */
    @Select("select * FROM sys_mental_knowledge where   mental_knowledge_id >  #{mentalKnowledgeId} order by mental_knowledge_id asc limit 1")
    public MentalKnowledge selectNext(@Param("mentalKnowledgeId")  Long mentalKnowledgeId);

    /**
     * 上一篇
     * @param mental_knowledge_id
     * @return
     */
    @Select("select * FROM sys_mental_knowledge where   mental_knowledge_id <  #{mentalKnowledgeId} order by mental_knowledge_id desc limit 1")
    public MentalKnowledge selectUp(@Param("mentalKnowledgeId")  Long mentalKnowledgeId);
}
