package com.mentalhealth.project.system.question.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;
import com.mentalhealth.project.system.question.service.IScaleQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测评量表问题信息Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Controller
@RequestMapping("/system/question")
public class ScaleQuestionController extends BaseController
{
    private String prefix = "system/question";

    @Autowired
    private IScaleQuestionService scaleQuestionService;

    @RequiresPermissions("system:question:view")
    @GetMapping()
    public String question()
    {
        return prefix + "/question";
    }

    /**
     * 查询测评量表问题信息列表
     */
    @RequiresPermissions("system:question:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScaleQuestion scaleQuestion)
    {
        startPage();
        List<ScaleQuestion> list = scaleQuestionService.selectScaleQuestionList(scaleQuestion);
        return getDataTable(list);
    }

    /**
     * 导出测评量表问题信息列表
     */
    @RequiresPermissions("system:question:export")
    @Log(title = "测评量表问题信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScaleQuestion scaleQuestion)
    {
        List<ScaleQuestion> list = scaleQuestionService.selectScaleQuestionList(scaleQuestion);
        ExcelUtil<ScaleQuestion> util = new ExcelUtil<ScaleQuestion>(ScaleQuestion.class);
        return util.exportExcel(list, "测评量表问题信息数据");
    }

    /**
     * 新增测评量表问题信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存测评量表问题信息
     */
    @RequiresPermissions("system:question:add")
    @Log(title = "测评量表问题信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScaleQuestion scaleQuestion)
    {
        return toAjax(scaleQuestionService.insertScaleQuestion(scaleQuestion));
    }

    /**
     * 修改测评量表问题信息
     */
    @GetMapping("/edit/{questionId}")
    public String edit(@PathVariable("questionId") Long questionId, ModelMap mmap)
    {
        ScaleQuestion scaleQuestion = scaleQuestionService.selectScaleQuestionByQuestionId(questionId);
//        System.out.println( " ScaleQuestion scaleQuestion = scaleQuestionService.selectScaleQuestionByQuestionId(questionId)  :"+scaleQuestion.getScaleAnswerList());
        mmap.put("scaleQuestion", scaleQuestion);
        return prefix + "/edit";
    }

    /**
     * 修改保存测评量表问题信息
     */
    @RequiresPermissions("system:question:edit")
    @Log(title = "测评量表问题信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScaleQuestion scaleQuestion)
    {
        return toAjax(scaleQuestionService.updateScaleQuestion(scaleQuestion));
    }

    /**
     * 删除测评量表问题信息
     */
    @RequiresPermissions("system:question:remove")
    @Log(title = "测评量表问题信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scaleQuestionService.deleteScaleQuestionByQuestionIds(ids));
    }



    /**
     * 查询测评量表问题列表
     */
    @RequiresPermissions("system:answer:list")
    @GetMapping("/answerDetail/{questionId}")
    public String answerDetail(@PathVariable("questionId") Long questionId, ModelMap mmap)
    {
        mmap.put("question", scaleQuestionService.selectScaleQuestionByQuestionId(questionId));
//        System.out.println("scaleQuestionService.selectScaleQuestionByQuestionId(questionId)---:"+scaleQuestionService.selectScaleQuestionByQuestionId(questionId));
        mmap.put("questionList", scaleQuestionService.selectScaleQuestionList(new ScaleQuestion()));   //查询所有
        return "system/answer/answer";
    }
}
