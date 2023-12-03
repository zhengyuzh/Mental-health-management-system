package com.mentalhealth.project.system.classes.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.classes.domain.Classes;
import com.mentalhealth.project.system.classes.service.IClassesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级信息Controller
 * 
 * @author zhengyuzhu
 * @date 2021-11-12
 */
@Controller
@RequestMapping("/system/classes")
public class ClassesController extends BaseController
{
    private String prefix = "system/classes";

    @Autowired
    private IClassesService classesService;

    @RequiresPermissions("system:classes:view")
    @GetMapping()
    public String classes()
    {
        return prefix + "/classes";
    }

    /**
     * 查询班级信息列表
     */
    @RequiresPermissions("system:classes:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Classes classes)
    {
        startPage();
        List<Classes> list = classesService.selectClassesList(classes);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @RequiresPermissions("system:classes:export")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Classes classes)
    {
        List<Classes> list = classesService.selectClassesList(classes);
        ExcelUtil<Classes> util = new ExcelUtil<Classes>(Classes.class);
        return util.exportExcel(list, "班级信息数据");
    }

    /**
     * 新增班级信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存班级信息
     */
    @RequiresPermissions("system:classes:add")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Classes classes)
    {
        return toAjax(classesService.insertClasses(classes));
    }

    /**
     * 修改班级信息
     */
    @GetMapping("/edit/{classesId}")
    public String edit(@PathVariable("classesId") Long classesId, ModelMap mmap)
    {
        Classes classes = classesService.selectClassesByClassesId(classesId);
        mmap.put("classes", classes);
        return prefix + "/edit";
    }

    /**
     * 修改保存班级信息
     */
    @RequiresPermissions("system:classes:edit")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Classes classes)
    {
        return toAjax(classesService.updateClasses(classes));
    }

    /**
     * 删除班级信息
     */
    @RequiresPermissions("system:classes:remove")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classesService.deleteClassesByClassesIds(ids));
    }
}
