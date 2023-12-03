package com.mentalhealth.project.system.report.service.impl;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.report.domain.Report;
import com.mentalhealth.project.system.report.mapper.ReportMapper;
import com.mentalhealth.project.system.report.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 举报信息Service业务层处理
 * 
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Service
public class ReportServiceImpl implements IReportService 
{
    @Autowired
    private ReportMapper reportMapper;

    /**
     * 查询举报信息
     * 
     * @param reportId 举报信息主键
     * @return 举报信息
     */
    @Override
    public Report selectReportByReportId(Long reportId)
    {
        return reportMapper.selectReportByReportId(reportId);
    }

    /**
     * 查询举报信息列表
     * 
     * @param report 举报信息
     * @return 举报信息
     */
    @Override
    public List<Report> selectReportList(Report report)
    {
        return reportMapper.selectReportList(report);
    }

    /**
     * 新增举报信息
     * 
     * @param report 举报信息
     * @return 结果
     */
    @Override
    public int insertReport(Report report)
    {
        report.setCreateTime(DateUtils.getNowDate());
        return reportMapper.insertReport(report);
    }

    /**
     * 修改举报信息
     * 
     * @param report 举报信息
     * @return 结果
     */
    @Override
    public int updateReport(Report report)
    {
        report.setUpdateTime(DateUtils.getNowDate());
        return reportMapper.updateReport(report);
    }

    /**
     * 批量删除举报信息
     * 
     * @param reportIds 需要删除的举报信息主键
     * @return 结果
     */
    @Override
    public int deleteReportByReportIds(String reportIds)
    {
        return reportMapper.deleteReportByReportIds(Convert.toStrArray(reportIds));
    }

    /**
     * 删除举报信息信息
     * 
     * @param reportId 举报信息主键
     * @return 结果
     */
    @Override
    public int deleteReportByReportId(Long reportId)
    {
        return reportMapper.deleteReportByReportId(reportId);
    }
}
