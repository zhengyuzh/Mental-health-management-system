package com.mentalhealth.project.system.formula.mapper;

import com.mentalhealth.project.system.formula.domain.ScaleFormula;

import java.util.List;

/**
 * 测评计算公式Mapper接口
 * 
 * @author zhengyuzhu
 * @date 2021-10-26
 */
public interface ScaleFormulaMapper 
{
    /**
     * 查询测评计算公式
     * 
     * @param formulaId 测评计算公式主键
     * @return 测评计算公式
     */
    public ScaleFormula selectScaleFormulaByFormulaId(Long formulaId);

    /**
     * 查询测评计算公式列表
     * 
     * @param scaleFormula 测评计算公式
     * @return 测评计算公式集合
     */
    public List<ScaleFormula> selectScaleFormulaList(ScaleFormula scaleFormula);

    /**
     * 新增测评计算公式
     * 
     * @param scaleFormula 测评计算公式
     * @return 结果
     */
    public int insertScaleFormula(ScaleFormula scaleFormula);

    /**
     * 修改测评计算公式
     * 
     * @param scaleFormula 测评计算公式
     * @return 结果
     */
    public int updateScaleFormula(ScaleFormula scaleFormula);

    /**
     * 删除测评计算公式
     * 
     * @param formulaId 测评计算公式主键
     * @return 结果
     */
    public int deleteScaleFormulaByFormulaId(Long formulaId);

    /**
     * 批量删除测评计算公式
     * 
     * @param formulaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScaleFormulaByFormulaIds(String[] formulaIds);
}
