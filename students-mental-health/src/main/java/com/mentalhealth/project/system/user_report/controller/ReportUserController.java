package com.mentalhealth.project.system.user_report.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.report.domain.Report;
import com.mentalhealth.project.system.report.service.IReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 个人举报信息Controller
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Controller
@RequestMapping("/system/user_report")
public class ReportUserController extends BaseController
{
    private String prefix = "system/user_report";

    @Autowired
    private IReportService reportService;

    @RequiresPermissions("system:user_report:view")
    @GetMapping()
    public String user_report()
    {
        return prefix + "/user_report";
    }

    /**
     * 查询个人举报信息列表
     */
    @RequiresPermissions("system:user_report:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Report report)
    {
        startPage();
        Long userId = ShiroUtils.getUserId();
        report.setReportUserId(userId);
        List<Report> list = reportService.selectReportList(report);
        return getDataTable(list);
    }

    /**
     * 导出个人举报信息列表
     */
    @RequiresPermissions("system:user_report:export")
    @Log(title = "个人举报信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Report report)
    {
        List<Report> list = reportService.selectReportList(report);
        ExcelUtil<Report> util = new ExcelUtil<Report>(Report.class);
        return util.exportExcel(list, "个人举报信息数据");
    }

    /**
     * 新增个人举报信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存个人举报信息
     */
    @RequiresPermissions("system:user_report:add")
    @Log(title = "个人举报信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Report report)
    {
        return toAjax(reportService.insertReport(report));
    }

    /**
     * 修改个人举报信息
     */
    @GetMapping("/edit/{reportId}")
    public String edit(@PathVariable("reportId") Long reportId, ModelMap mmap)
    {
        Report report = reportService.selectReportByReportId(reportId);
        mmap.put("report", report);
        return prefix + "/edit";
    }

    /**
     * 修改保存个人举报信息
     */
    @RequiresPermissions("system:user_report:edit")
    @Log(title = "个人举报信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Report report)
    {
        return toAjax(reportService.updateReport(report));
    }

    /**
     * 删除个人举报信息
     */
    @RequiresPermissions("system:user_report:remove")
    @Log(title = "个人举报信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(reportService.deleteReportByReportIds(ids));
    }
}
