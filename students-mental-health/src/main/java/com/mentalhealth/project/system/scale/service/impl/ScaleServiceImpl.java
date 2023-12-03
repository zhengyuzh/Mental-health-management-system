package com.mentalhealth.project.system.scale.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;
import com.mentalhealth.project.system.scale.domain.Scale;
import com.mentalhealth.project.system.scale.mapper.ScaleMapper;
import com.mentalhealth.project.system.scale.service.IScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 心理测评量表管理Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Service("scaleService")
public class ScaleServiceImpl implements IScaleService 
{
    @Autowired
    private ScaleMapper scaleMapper;

    /**
     * 查询心理测评量表管理
     *
     * @param scaleId 心理测评量表管理主键
     * @return 心理测评量表管理
     */
    @Override
    public Scale selectScaleByScaleId(Long scaleId)
    {
        return scaleMapper.selectScaleByScaleId(scaleId);
    }

    /**
     * 查询心理测评量表管理列表
     * 
     * @param scale 心理测评量表管理
     * @return 心理测评量表管理
     */
    @Override
    public List<Scale> selectScaleList(Scale scale)
    {
        return scaleMapper.selectScaleList(scale);
    }

    /**
     * 新增心理测评量表管理
     * 
     * @param scale 心理测评量表管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertScale(Scale scale)
    {
        scale.setCreateTime(DateUtils.getNowDate());
        scale.setUserId(ShiroUtils.getUserId());
        int rows = scaleMapper.insertScale(scale);
        insertScaleQuestion(scale);
        return rows;
    }

    /**
     * 修改心理测评量表管理
     * 
     * @param scale 心理测评量表管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateScale(Scale scale)
    {

        return scaleMapper.updateScale(scale);
    }

    /**
     * 批量删除心理测评量表管理
     * 
     * @param scaleIds 需要删除的心理测评量表管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteScaleByScaleIds(String scaleIds)
    {
        scaleMapper.deleteScaleQuestionByScaleIds(Convert.toStrArray(scaleIds));
        return scaleMapper.deleteScaleByScaleIds(Convert.toStrArray(scaleIds));
    }

    /**
     * 删除心理测评量表管理信息
     * 
     * @param scaleId 心理测评量表管理主键
     * @return 结果
     */
    @Override
    public int deleteScaleByScaleId(Long scaleId)
    {
        scaleMapper.deleteScaleQuestionByScaleId(scaleId);
        return scaleMapper.deleteScaleByScaleId(scaleId);
    }

    /**
     * 新增测评量表问题信息信息
     * 
     * @param scale 心理测评量表管理对象
     */
    public void insertScaleQuestion(Scale scale)
    {
        List<ScaleQuestion> scaleQuestionList = scale.getScaleQuestionList();
        Long scaleId = scale.getScaleId();
        String scaleTitle = scale.getScaleTitle();
        if (StringUtils.isNotNull(scaleQuestionList))
        {
            List<ScaleQuestion> list = new ArrayList<ScaleQuestion>();
            for (ScaleQuestion scaleQuestion : scaleQuestionList)
            {
//                if (scaleQuestion.getCreateTime() == null){
                    scaleQuestion.setCreateTime(DateUtils.getNowDate());
//                }else {
//                    scaleQuestion.setUpdateTime(DateUtils.getNowDate());
//                }
                scaleQuestion.setScaleId(scaleId);
//                scaleQuestion.setScaleTitle(scaleTitle);
                list.add(scaleQuestion);
            }
            if (list.size() > 0)
            {
                scaleMapper.batchScaleQuestion(list);
            }
        }
    }

    @Override
    public List<Scale> selectScaleAll(){
        return scaleMapper.selectScaleAll();
    }

    /**
     *  前台 分页查询测评量表列表
     * @param page
     * @param count
     * @return
     */
    @Override
    public PageInfo<Scale> selectScaleWithPage(Integer page, Integer count,String scaleTitle) {
        PageHelper.startPage(page, count,scaleTitle);
        List<Scale> scales = scaleMapper.selectScaleWithPage(scaleTitle);
        PageInfo<Scale> pageInfo=new PageInfo<>(scales);
        return pageInfo;
    }


    @Override
    public int selectQuestionAll(Long scaleId){
        return scaleMapper.selectQuestionAll(scaleId);
    }

    @Override
    public Scale startScale(Long scaleId){
        return scaleMapper.startScale(scaleId);
    }


}
