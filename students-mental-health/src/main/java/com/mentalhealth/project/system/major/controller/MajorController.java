package com.mentalhealth.project.system.major.controller;

import java.util.List;

import com.mentalhealth.common.constant.UserConstants;
import com.mentalhealth.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.project.system.major.domain.Major;
import com.mentalhealth.project.system.major.service.IMajorService;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.web.page.TableDataInfo;

/**
 * 专业Controller
 * 
 * @author ruoyi
 * @date 2021-09-30
 */
@Controller
@RequestMapping("/system/major")
public class MajorController extends BaseController
{
    private String prefix = "system/major";

    @Autowired
    private IMajorService majorService;

    @RequiresPermissions("system:major:view")
    @GetMapping()
    public String major()
    {
        return prefix + "/major";
    }

    /**
     * 查询专业列表
     */
    @RequiresPermissions("system:major:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Major major)
    {
        startPage();  //此函数配合前端页面实现分页
        List<Major> list = majorService.selectMajorList(major);
        return getDataTable(list);
    }

    /**
     * 导出专业列表
     */
    @RequiresPermissions("system:major:export")
    @Log(title = "专业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Major major)
    {
        List<Major> list = majorService.selectMajorList(major);
        ExcelUtil<Major> util = new ExcelUtil<Major>(Major.class);
        return util.exportExcel(list, "专业数据");
    }

    /**
     * 新增专业
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存专业
     */
    @RequiresPermissions("system:major:add")
    @Log(title = "专业", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Major major)
    {
        if (StringUtils.isNotEmpty(major.getMajorName())
                && UserConstants.Major_NAME_NOT_UNIQUE.equals(majorService.checkMajorNameUnique(major)))
        {
            return error("新增专业信息'" + major.getMajorName() + "'失败，专业名称已存在 con");
        }
        return toAjax(majorService.insertMajor(major));
    }



    /**
     * 修改专业
     */
    @GetMapping("/edit/{majorId}")
    public String edit(@PathVariable("majorId") String majorId, ModelMap mmap)
    {
        Major major = majorService.selectMajorByMajorId(majorId);
        mmap.put("major", major);
        return prefix + "/edit";
    }

    /**
     * 修改保存专业
     */
    @RequiresPermissions("system:major:edit")
    @Log(title = "专业", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Major major)
    {
        return toAjax(majorService.updateMajor(major));
    }

    /**
     * 删除专业
     */
    @RequiresPermissions("system:major:remove")
    @Log(title = "专业", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(majorService.deleteMajorByMajorIds(ids));
    }

    /**
     * 校验专业名称
     */
    @PostMapping("/checkMajorNameUnique")
    @ResponseBody
    public String checkMajorNameUnique(Major major)
    {
        return majorService.checkMajorNameUnique(major);
    }

}
