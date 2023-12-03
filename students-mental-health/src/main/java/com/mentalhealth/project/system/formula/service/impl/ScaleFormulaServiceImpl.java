package com.mentalhealth.project.system.formula.service.impl;

import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.formula.domain.ScaleFormula;
import com.mentalhealth.project.system.formula.mapper.ScaleFormulaMapper;
import com.mentalhealth.project.system.formula.service.IScaleFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测评计算公式Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2021-10-26
 */
@Service("scaleFormulaService")
public class ScaleFormulaServiceImpl implements IScaleFormulaService 
{
    @Autowired
    private ScaleFormulaMapper scaleFormulaMapper;

    /**
     * 查询测评计算公式
     * 
     * @param formulaId 测评计算公式主键
     * @return 测评计算公式
     */
    @Override
    public ScaleFormula selectScaleFormulaByFormulaId(Long formulaId)
    {
        return scaleFormulaMapper.selectScaleFormulaByFormulaId(formulaId);
    }

    /**
     * 查询测评计算公式列表
     * 
     * @param scaleFormula 测评计算公式
     * @return 测评计算公式
     */
    @Override
    public List<ScaleFormula> selectScaleFormulaList(ScaleFormula scaleFormula)
    {
        return scaleFormulaMapper.selectScaleFormulaList(scaleFormula);
    }

    /**
     * 新增测评计算公式
     * 
     * @param scaleFormula 测评计算公式
     * @return 结果
     */
    @Override
    public int insertScaleFormula(ScaleFormula scaleFormula)
    {
        return scaleFormulaMapper.insertScaleFormula(scaleFormula);
    }

    /**
     * 修改测评计算公式
     * 
     * @param scaleFormula 测评计算公式
     * @return 结果
     */
    @Override
    public int updateScaleFormula(ScaleFormula scaleFormula)
    {
        return scaleFormulaMapper.updateScaleFormula(scaleFormula);
    }

    /**
     * 批量删除测评计算公式
     * 
     * @param formulaIds 需要删除的测评计算公式主键
     * @return 结果
     */
    @Override
    public int deleteScaleFormulaByFormulaIds(String formulaIds)
    {
        return scaleFormulaMapper.deleteScaleFormulaByFormulaIds(Convert.toStrArray(formulaIds));
    }

    /**
     * 删除测评计算公式信息
     * 
     * @param formulaId 测评计算公式主键
     * @return 结果
     */
    @Override
    public int deleteScaleFormulaByFormulaId(Long formulaId)
    {
        return scaleFormulaMapper.deleteScaleFormulaByFormulaId(formulaId);
    }
}
