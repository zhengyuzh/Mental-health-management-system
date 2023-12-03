package com.mentalhealth.project.system.scale.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.question.service.IScaleQuestionService;
import com.mentalhealth.project.system.scale.domain.Scale;
import com.mentalhealth.project.system.scale.service.IScaleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 心理测评量表管理Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-13
 */
@Controller
@RequestMapping("/system/scale")
public class ScaleController extends BaseController
{
    private String prefix = "system/scale";

    @Autowired
    private IScaleService scaleService;

    @Autowired
    private IScaleQuestionService scaleQuestionService;

    @RequiresPermissions("system:scale:view")
    @GetMapping()
    public String scale()
    {
        return prefix + "/scale";
    }

    /**
     * 查询心理测评量表管理列表
     */
    @RequiresPermissions("system:scale:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Scale scale)
    {
        startPage();
        List<Scale> list = scaleService.selectScaleList(scale);
        return getDataTable(list);
    }

    /**
     * 导出心理测评量表管理列表
     */
    @RequiresPermissions("system:scale:export")
    @Log(title = "心理测评量表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Scale scale)
    {
        List<Scale> list = scaleService.selectScaleList(scale);
        ExcelUtil<Scale> util = new ExcelUtil<Scale>(Scale.class);
        return util.exportExcel(list, "心理测评量表管理数据");
    }

    /**
     * 新增心理测评量表管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存心理测评量表管理
     */
    @RequiresPermissions("system:scale:add")
    @Log(title = "心理测评量表管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Scale scale)
    {
        return toAjax(scaleService.insertScale(scale));
    }

    /**
     * 修改心理测评量表管理
     */
    @GetMapping("/edit/{scaleId}")
    public String edit(@PathVariable("scaleId") Long scaleId, ModelMap mmap)
    {
        Scale scale = scaleService.selectScaleByScaleId(scaleId);
        mmap.put("scale", scale);
        return prefix + "/edit";
    }

    /**
     * 修改保存心理测评量表管理
     */
    @RequiresPermissions("system:scale:edit")
    @Log(title = "心理测评量表管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Scale scale)
    {
        return toAjax(scaleService.updateScale(scale));
    }

    /**
     * 删除心理测评量表管理
     */
    @RequiresPermissions("system:scale:remove")
    @Log(title = "心理测评量表管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scaleService.deleteScaleByScaleIds(ids));
    }


    /**
     * 查询测评量表问题列表
     */
    @RequiresPermissions("system:question:list")
    @GetMapping("/questionDetail/{scaleId}")
    public String questionDetail(@PathVariable("scaleId") Long scaleId, ModelMap mmap)
    {
        mmap.put("scale", scaleService.selectScaleByScaleId(scaleId));
        mmap.put("scaleList", scaleService.selectScaleAll());   //查询所有帖子
        return "system/question/question";
    }


    /**
     * 查询测评量表问题列表
     */
    @RequiresPermissions("system:question:list")
    @GetMapping("/factorDetail/{scaleId}")
    public String factorDetail(@PathVariable("scaleId") Long scaleId, ModelMap mmap)
    {
        mmap.put("scale", scaleService.selectScaleByScaleId(scaleId));
        mmap.put("scaleList", scaleService.selectScaleAll());   //查询所有帖子
        return "system/factor/factor";
    }



    /**
     * 新增问题
     */
    @GetMapping("/addQuestion/{scaleId}")    //传递id
    public String addQuestion(@PathVariable("scaleId") Long scaleId, ModelMap mmp)
    {
        Scale scale = scaleService.selectScaleByScaleId(scaleId);
        //查询总数 方便排序
        int num =scaleService.selectQuestionAll(scaleId);
//        System.out.println("num++++++++++++"+num);
        num = num+1;
        mmp.put("scale", scale);
        mmp.put("num", num);

        return "system/scale/addQuestion";
    }



}
