package com.mentalhealth.project.system.report.service;

import com.mentalhealth.project.system.report.domain.Report;

import java.util.List;

/**
 * 举报信息Service接口
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
public interface IReportService 
{
    /**
     * 查询举报信息
     * 
     * @param reportId 举报信息主键
     * @return 举报信息
     */
    public Report selectReportByReportId(Long reportId);

    /**
     * 查询举报信息列表
     * 
     * @param report 举报信息
     * @return 举报信息集合
     */
    public List<Report> selectReportList(Report report);

    /**
     * 新增举报信息
     * 
     * @param report 举报信息
     * @return 结果
     */
    public int insertReport(Report report);

    /**
     * 修改举报信息
     * 
     * @param report 举报信息
     * @return 结果
     */
    public int updateReport(Report report);

    /**
     * 批量删除举报信息
     * 
     * @param reportIds 需要删除的举报信息主键集合
     * @return 结果
     */
    public int deleteReportByReportIds(String reportIds);

    /**
     * 删除举报信息信息
     * 
     * @param reportId 举报信息主键
     * @return 结果
     */
    public int deleteReportByReportId(Long reportId);
}
