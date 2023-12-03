package com.mentalhealth.project.system.answer.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.answer.domain.ScaleAnswer;
import com.mentalhealth.project.system.answer.service.IScaleAnswerService;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;
import com.mentalhealth.project.system.question.service.IScaleQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问题选项信息Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Controller
@RequestMapping("/system/answer")
public class ScaleAnswerController extends BaseController
{
    private String prefix = "system/answer";

    @Autowired
    private IScaleAnswerService scaleAnswerService;

    @Autowired
    private IScaleQuestionService  scaleQuestionService;

    @RequiresPermissions("system:answer:view")
    @GetMapping()
    public String answer()
    {
        return prefix + "/answer";
    }

    /**
     * 查询问题选项信息列表
     */
    @RequiresPermissions("system:answer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScaleAnswer scaleAnswer)
    {
        startPage();
        List<ScaleAnswer> list = scaleAnswerService.selectScaleAnswerList(scaleAnswer);
        return getDataTable(list);
    }

    /**
     * 导出问题选项信息列表
     */
    @RequiresPermissions("system:answer:export")
    @Log(title = "问题选项信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScaleAnswer scaleAnswer)
    {
        List<ScaleAnswer> list = scaleAnswerService.selectScaleAnswerList(scaleAnswer);
        ExcelUtil<ScaleAnswer> util = new ExcelUtil<ScaleAnswer>(ScaleAnswer.class);
        return util.exportExcel(list, "问题选项信息数据");
    }

    /**
     * 新增问题选项信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存问题选项信息
     */
    @RequiresPermissions("system:answer:add")
    @Log(title = "问题选项信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScaleAnswer scaleAnswer)
    {
        ScaleQuestion question = scaleQuestionService.selectScaleQuestionByQuestionId(scaleAnswer.getQuestionId());
        scaleAnswer.setFactorId(question.getFactorId());
        return toAjax(scaleAnswerService.insertScaleAnswer(scaleAnswer));
    }

    /**
     * 修改问题选项信息
     */
    @GetMapping("/edit/{answerId}")
    public String edit(@PathVariable("answerId") Long answerId, ModelMap mmap)
    {
        ScaleAnswer scaleAnswer = scaleAnswerService.selectScaleAnswerByAnswerId(answerId);
        //修改选项的时候查询所属问题
        ScaleQuestion scaleQuestion = scaleQuestionService.selectScaleQuestionByQuestionId(scaleAnswer.getQuestionId()) ;
        mmap.put("scaleAnswer", scaleAnswer);
        mmap.put("scaleQuestion", scaleQuestion);
        return prefix + "/edit";
    }

    /**
     * 修改保存问题选项信息
     */
    @RequiresPermissions("system:answer:edit")
    @Log(title = "问题选项信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScaleAnswer scaleAnswer)
    {

        return toAjax(scaleAnswerService.updateScaleAnswer(scaleAnswer));
    }

    /**
     * 删除问题选项信息
     */
    @RequiresPermissions("system:answer:remove")
    @Log(title = "问题选项信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scaleAnswerService.deleteScaleAnswerByAnswerIds(ids));
    }
}
