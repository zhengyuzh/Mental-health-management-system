package com.mentalhealth.project.system.factor.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.factor.domain.ScaleFactor;
import com.mentalhealth.project.system.factor.service.IScaleFactorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测评因子信息Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Controller
@RequestMapping("/system/factor")
public class ScaleFactorController extends BaseController
{
    private String prefix = "system/factor";

    @Autowired
    private IScaleFactorService scaleFactorService;

    @RequiresPermissions("system:factor:view")
    @GetMapping()
    public String factor()
    {
        return prefix + "/factor";
    }

    /**
     * 查询测评因子信息列表
     */
    @RequiresPermissions("system:factor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScaleFactor scaleFactor)
    {
        startPage();
        List<ScaleFactor> list = scaleFactorService.selectScaleFactorList(scaleFactor);
        return getDataTable(list);
    }

    /**
     * 导出测评因子信息列表
     */
    @RequiresPermissions("system:factor:export")
    @Log(title = "测评因子信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScaleFactor scaleFactor)
    {
        List<ScaleFactor> list = scaleFactorService.selectScaleFactorList(scaleFactor);
        ExcelUtil<ScaleFactor> util = new ExcelUtil<ScaleFactor>(ScaleFactor.class);
        return util.exportExcel(list, "测评因子信息数据");
    }

    /**
     * 新增测评因子信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存测评因子信息
     */
    @RequiresPermissions("system:factor:add")
    @Log(title = "测评因子信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScaleFactor scaleFactor)
    {
        return toAjax(scaleFactorService.insertScaleFactor(scaleFactor));
    }

    /**
     * 修改测评因子信息
     */
    @GetMapping("/edit/{factorId}")
    public String edit(@PathVariable("factorId") Long factorId, ModelMap mmap)
    {
        ScaleFactor scaleFactor = scaleFactorService.selectScaleFactorByFactorId(factorId);
        mmap.put("scaleFactor", scaleFactor);
        return prefix + "/edit";
    }

    /**
     * 修改保存测评因子信息
     */
    @RequiresPermissions("system:factor:edit")
    @Log(title = "测评因子信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScaleFactor scaleFactor)
    {
        return toAjax(scaleFactorService.updateScaleFactor(scaleFactor));
    }

    /**
     * 删除测评因子信息
     */
    @RequiresPermissions("system:factor:remove")
    @Log(title = "测评因子信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scaleFactorService.deleteScaleFactorByFactorIds(ids));
    }
}
