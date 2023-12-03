package com.mentalhealth.project.system.formula.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.formula.domain.ScaleFormula;
import com.mentalhealth.project.system.formula.service.IScaleFormulaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测评计算公式Controller
 * 
 * @author zhengyuzhu
 * @date 2021-10-26
 */
@Controller
@RequestMapping("/system/formula")
public class ScaleFormulaController extends BaseController
{
    private String prefix = "system/formula";

    @Autowired
    private IScaleFormulaService scaleFormulaService;

    @RequiresPermissions("system:formula:view")
    @GetMapping()
    public String formula()
    {
        return prefix + "/formula";
    }

    /**
     * 查询测评计算公式列表
     */
    @RequiresPermissions("system:formula:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScaleFormula scaleFormula)
    {
        startPage();
        List<ScaleFormula> list = scaleFormulaService.selectScaleFormulaList(scaleFormula);
        return getDataTable(list);
    }

    /**
     * 导出测评计算公式列表
     */
    @RequiresPermissions("system:formula:export")
    @Log(title = "测评计算公式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScaleFormula scaleFormula)
    {
        List<ScaleFormula> list = scaleFormulaService.selectScaleFormulaList(scaleFormula);
        ExcelUtil<ScaleFormula> util = new ExcelUtil<ScaleFormula>(ScaleFormula.class);
        return util.exportExcel(list, "测评计算公式数据");
    }

    /**
     * 新增测评计算公式
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存测评计算公式
     */
    @RequiresPermissions("system:formula:add")
    @Log(title = "测评计算公式", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScaleFormula scaleFormula)
    {
        return toAjax(scaleFormulaService.insertScaleFormula(scaleFormula));
    }

    /**
     * 修改测评计算公式
     */
    @GetMapping("/edit/{formulaId}")
    public String edit(@PathVariable("formulaId") Long formulaId, ModelMap mmap)
    {
        ScaleFormula scaleFormula = scaleFormulaService.selectScaleFormulaByFormulaId(formulaId);
        mmap.put("scaleFormula", scaleFormula);
        return prefix + "/edit";
    }

    /**
     * 修改保存测评计算公式
     */
    @RequiresPermissions("system:formula:edit")
    @Log(title = "测评计算公式", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScaleFormula scaleFormula)
    {
        return toAjax(scaleFormulaService.updateScaleFormula(scaleFormula));
    }

    /**
     * 删除测评计算公式
     */
    @RequiresPermissions("system:formula:remove")
    @Log(title = "测评计算公式", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scaleFormulaService.deleteScaleFormulaByFormulaIds(ids));
    }
}
