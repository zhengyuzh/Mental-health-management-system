package com.mentalhealth.project.system.scale.service;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.project.system.scale.domain.Scale;

import java.util.List;

/**
 * 心理测评量表管理Service接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
public interface IScaleService 
{
    /**
     * 查询心理测评量表管理
     *
     * @param scaleId 心理测评量表管理主键
     * @return 心理测评量表管理
     */
    public Scale selectScaleByScaleId(Long scaleId);

    /**
     * 查询心理测评量表管理列表
     * 
     * @param scale 心理测评量表管理
     * @return 心理测评量表管理集合
     */
    public List<Scale> selectScaleList(Scale scale);
    /**
     * 查询心理测评量表管理
     *
     *
     * @return 心理测评量表管理集合
     */
    public List<Scale> selectScaleAll();

    public PageInfo<Scale> selectScaleWithPage(Integer page, Integer count,String scaleTitle);

    /**
     * 新增心理测评量表管理
     * 
     * @param scale 心理测评量表管理
     * @return 结果
     */
    public int insertScale(Scale scale);

    /**
     * 修改心理测评量表管理
     * 
     * @param scale 心理测评量表管理
     * @return 结果
     */
    public int updateScale(Scale scale);

    /**
     * 批量删除心理测评量表管理
     * 
     * @param scaleIds 需要删除的心理测评量表管理主键集合
     * @return 结果
     */
    public int deleteScaleByScaleIds(String scaleIds);

    /**
     * 删除心理测评量表管理信息
     * 
     * @param scaleId 心理测评量表管理主键
     * @return 结果
     */
    public int deleteScaleByScaleId(Long scaleId);

    /**
     * 查询问题总数
     * @param scaleId
     * @return
     */
    public int selectQuestionAll(Long scaleId);

    public Scale startScale(Long scaleId);
}
