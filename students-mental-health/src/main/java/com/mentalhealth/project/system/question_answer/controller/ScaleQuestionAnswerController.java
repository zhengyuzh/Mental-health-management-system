package com.mentalhealth.project.system.question_answer.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.question_answer.domain.ScaleQuestionAnswer;
import com.mentalhealth.project.system.question_answer.service.IScaleQuestionAnswerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测评问题选项Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Controller
@RequestMapping("/system/question_answer")
public class ScaleQuestionAnswerController extends BaseController
{
    private String prefix = "system/question_answer";

    @Autowired
    private IScaleQuestionAnswerService scaleQuestionAnswerService;

    @RequiresPermissions("system:question_answer:view")
    @GetMapping()
    public String question_answer()
    {
        return prefix + "/question_answer";
    }

    /**
     * 查询测评问题选项列表
     */
    @RequiresPermissions("system:question_answer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScaleQuestionAnswer scaleQuestionAnswer)
    {
        startPage();
        List<ScaleQuestionAnswer> list = scaleQuestionAnswerService.selectScaleQuestionAnswerList(scaleQuestionAnswer);
        return getDataTable(list);
    }

    /**
     * 导出测评问题选项列表
     */
    @RequiresPermissions("system:question_answer:export")
    @Log(title = "测评问题选项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScaleQuestionAnswer scaleQuestionAnswer)
    {
        List<ScaleQuestionAnswer> list = scaleQuestionAnswerService.selectScaleQuestionAnswerList(scaleQuestionAnswer);
        ExcelUtil<ScaleQuestionAnswer> util = new ExcelUtil<ScaleQuestionAnswer>(ScaleQuestionAnswer.class);
        return util.exportExcel(list, "测评问题选项数据");
    }

    /**
     * 新增测评问题选项
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存测评问题选项
     */
    @RequiresPermissions("system:question_answer:add")
    @Log(title = "测评问题选项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScaleQuestionAnswer scaleQuestionAnswer)
    {
        return toAjax(scaleQuestionAnswerService.insertScaleQuestionAnswer(scaleQuestionAnswer));
    }

    /**
     * 修改测评问题选项
     */
    @GetMapping("/edit/{questionId}")
    public String edit(@PathVariable("questionId") Long questionId, ModelMap mmap)
    {
        ScaleQuestionAnswer scaleQuestionAnswer = scaleQuestionAnswerService.selectScaleQuestionAnswerByQuestionId(questionId);
        mmap.put("scaleQuestionAnswer", scaleQuestionAnswer);
        return prefix + "/edit";
    }

    /**
     * 修改保存测评问题选项
     */
    @RequiresPermissions("system:question_answer:edit")
    @Log(title = "测评问题选项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScaleQuestionAnswer scaleQuestionAnswer)
    {
        return toAjax(scaleQuestionAnswerService.updateScaleQuestionAnswer(scaleQuestionAnswer));
    }

    /**
     * 删除测评问题选项
     */
    @RequiresPermissions("system:question_answer:remove")
    @Log(title = "测评问题选项", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scaleQuestionAnswerService.deleteScaleQuestionAnswerByQuestionIds(ids));
    }
}
