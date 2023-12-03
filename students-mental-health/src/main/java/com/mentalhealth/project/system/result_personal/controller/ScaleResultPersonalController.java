package com.mentalhealth.project.system.result_personal.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.result_user.domain.ScaleUserResult;
import com.mentalhealth.project.system.result_user.service.IScaleUserResultService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 个人测评成绩报告Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Controller
@RequestMapping("/system/result_personal")
public class ScaleResultPersonalController extends BaseController
{
    private String prefix = "system/result_personal";

    @Autowired
    private IScaleUserResultService scaleUserResultService;

    @RequiresPermissions("system:result_personal:view")
    @GetMapping()
    public String result_personal()
    {
        return prefix + "/result_personal";
    }

    /**
     * 查询个人测评成绩报告列表
     */
    @RequiresPermissions("system:result_personal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScaleUserResult scaleUserResult)
    {
        startPage();
        scaleUserResult.setUserId(ShiroUtils.getUserId());
        List<ScaleUserResult> list = scaleUserResultService.selectScaleUserResultList(scaleUserResult);
        return getDataTable(list);
    }

    /**
     * 导出个人测评成绩报告列表
     */
    @RequiresPermissions("system:result_personal:export")
    @Log(title = "个人测评成绩报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScaleUserResult scaleUserResult)
    {
        List<ScaleUserResult> list = scaleUserResultService.selectScaleUserResultList(scaleUserResult);
        ExcelUtil<ScaleUserResult> util = new ExcelUtil<ScaleUserResult>(ScaleUserResult.class);
        return util.exportExcel(list, "个人测评成绩报告数据");
    }

    /**
     * 新增个人测评成绩报告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存个人测评成绩报告
     */
    @RequiresPermissions("system:result_personal:add")
    @Log(title = "个人测评成绩报告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScaleUserResult scaleUserResult)
    {
        return toAjax(scaleUserResultService.insertScaleUserResult(scaleUserResult));
    }

    /**
     * 修改个人测评成绩报告
     */
    @GetMapping("/edit/{resultId}")
    public String edit(@PathVariable("resultId") Long resultId, ModelMap mmap)
    {
        ScaleUserResult scaleUserResult = scaleUserResultService.selectScaleUserResultByResultId(resultId);
        mmap.put("scaleUserResult", scaleUserResult);
        return prefix + "/edit";
    }

    /**
     * 修改保存个人测评成绩报告
     */
    @RequiresPermissions("system:result_personal:edit")
    @Log(title = "个人测评成绩报告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScaleUserResult scaleUserResult)
    {
        return toAjax(scaleUserResultService.updateScaleUserResult(scaleUserResult));
    }

    /**
     * 删除个人测评成绩报告
     */
    @RequiresPermissions("system:result_personal:remove")
    @Log(title = "个人测评成绩报告", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scaleUserResultService.deleteScaleUserResultByResultIds(ids));
    }
}
