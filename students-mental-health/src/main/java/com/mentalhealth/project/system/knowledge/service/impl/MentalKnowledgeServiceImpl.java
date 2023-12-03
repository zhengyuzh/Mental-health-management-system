package com.mentalhealth.project.system.knowledge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.constant.UserConstants;
import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import com.mentalhealth.project.system.knowledge.mapper.MentalKnowledgeMapper;
import com.mentalhealth.project.system.knowledge.service.IMentalKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 心理健康知识Service业务层处理
 *
 * @author zhengyuzhu
 * @date 2023-08-09
 */
@Service
public class MentalKnowledgeServiceImpl implements IMentalKnowledgeService
{
    @Autowired
    private MentalKnowledgeMapper mentalKnowledgeMapper;

    /**
     * 查询心理健康知识
     *
     * @param mentalKnowledgeId 心理健康知识主键
     * @return 心理健康知识
     */
    @Override
    public MentalKnowledge selectMentalKnowledgeByMentalKnowledgeId(Long mentalKnowledgeId)
    {
        return mentalKnowledgeMapper.selectMentalKnowledgeByMentalKnowledgeId(mentalKnowledgeId);
    }

    /**
     * 查询心理健康知识列表
     *
     * @param mentalKnowledge 心理健康知识
     * @return 心理健康知识
     */
    @Override
    public List<MentalKnowledge> selectMentalKnowledgeList(MentalKnowledge mentalKnowledge)
    {
        return mentalKnowledgeMapper.selectMentalKnowledgeList(mentalKnowledge);
    }
    @Override
public List<MentalKnowledge> selectMentalKnowledgeListIndext(MentalKnowledge mentalKnowledge)
{
    return mentalKnowledgeMapper.selectMentalKnowledgeListIndext(mentalKnowledge);
}

    /**
     *  前台 分页查询文章列表
     * @param page
     * @param count
     * @return
     */
    @Override
    public PageInfo<MentalKnowledge> selectMentalKnowledgeWithPage(Integer page, Integer count,String mentalKnowledgeTitle) {
        PageHelper.startPage(page, count);
        List<MentalKnowledge> mentalKnowledgeList = mentalKnowledgeMapper.selectMentalKnowledgeWithPage(mentalKnowledgeTitle);
        PageInfo<MentalKnowledge> pageInfo=new PageInfo<>(mentalKnowledgeList);
        return pageInfo;
    }


    /**
     * 新增心理健康知识
     *
     * @param mentalKnowledge 心理健康知识
     * @return 结果
     */
    @Override
    public int insertMentalKnowledge(MentalKnowledge mentalKnowledge)
    {
        mentalKnowledge.setCreateTime(DateUtils.getNowDate());
        return mentalKnowledgeMapper.insertMentalKnowledge(mentalKnowledge);
    }

    /**
     * 修改心理健康知识
     *
     * @param mentalKnowledge 心理健康知识
     * @return 结果
     */
    @Override
    public int updateMentalKnowledge(MentalKnowledge mentalKnowledge)
    {
        mentalKnowledge.setUpdateTime(DateUtils.getNowDate());
        return mentalKnowledgeMapper.updateMentalKnowledge(mentalKnowledge);
    }

    /**
     * 批量删除心理健康知识
     *
     * @param mentalKnowledgeIds 需要删除的心理健康知识主键
     * @return 结果
     */
    @Override
    public int deleteMentalKnowledgeByMentalKnowledgeIds(String mentalKnowledgeIds)
    {
        return mentalKnowledgeMapper.deleteMentalKnowledgeByMentalKnowledgeIds(Convert.toStrArray(mentalKnowledgeIds));
    }

    /**
     * 删除心理健康知识信息
     *
     * @param mentalKnowledgeId 心理健康知识主键
     * @return 结果
     */
    @Override
    public int deleteMentalKnowledgeByMentalKnowledgeId(Long mentalKnowledgeId)
    {
        return mentalKnowledgeMapper.deleteMentalKnowledgeByMentalKnowledgeId(mentalKnowledgeId);
    }


    /**
     * 校验标题名称是否唯一
     *
     * @param mentalKnowledge 心理知识信息
     * @return 结果
     */
    @Override
    public String checkMentalKnowledgeTitleUnique(MentalKnowledge mentalKnowledge){
        Long MentalKnowledgeId = StringUtils.isNull(mentalKnowledge.getMentalKnowledgeId()) ? -1L : mentalKnowledge.getMentalKnowledgeId();
        MentalKnowledge info = mentalKnowledgeMapper.checkMentalKnowledgeTitleUnique(mentalKnowledge.getMentalKnowledgeTitle());
        if (StringUtils.isNotNull(info) && info.getMentalKnowledgeId().longValue() != MentalKnowledgeId.longValue())
        {
            return UserConstants.MentalKnowledge_NAME_NOT_UNIQUE;
        }
        return UserConstants.MentalKnowledge_NAME_UNIQUE;
    }

    /**
     * 下一篇
     * @param mentalKnowledgeId
     * @return
     */
    @Override
    public MentalKnowledge selectNext(Long mentalKnowledgeId){
        MentalKnowledge mentalKnowledge = mentalKnowledgeMapper.selectNext(mentalKnowledgeId);
        return mentalKnowledge;
    }

    /**
     * 上一篇
     * @param mentalKnowledgeId
     * @return
     */
    @Override
    public MentalKnowledge selectUp(Long mentalKnowledgeId){
        MentalKnowledge mentalKnowledge = mentalKnowledgeMapper.selectUp(mentalKnowledgeId);
        return mentalKnowledge;
    }

}
