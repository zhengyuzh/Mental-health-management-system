package com.mentalhealth.project.system.knowledge_examine.controller;

import java.util.List;

import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.project.system.knowledge.service.IMentalKnowledgeService;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.web.page.TableDataInfo;

/**
 * 心理健康知识审核Controller
 * 
 * @author zhengyuzhu
 * @date 2021-10-13
 */
@Controller
@RequestMapping("/system/knowledge_examine")
public class MentalKnowledgeExamineController extends BaseController
{
    private String prefix = "system/knowledge_examine";

    @Autowired
    private IMentalKnowledgeService mentalKnowledgeService;

    @RequiresPermissions("system:knowledge_examine:view")
    @GetMapping()
    public String knowledge_examine()
    {
        return prefix + "/knowledge_examine";
    }

    /**
     * 查询心理健康知识审核列表
     */
    @RequiresPermissions("system:knowledge_examine:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MentalKnowledge mentalKnowledge)
    {
        startPage();
        List<MentalKnowledge> list = mentalKnowledgeService.selectMentalKnowledgeList(mentalKnowledge);
        return getDataTable(list);
    }

    /**
     * 导出心理健康知识审核列表
     */
    @RequiresPermissions("system:knowledge_examine:export")
    @Log(title = "心理健康知识审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MentalKnowledge mentalKnowledge)
    {
        List<MentalKnowledge> list = mentalKnowledgeService.selectMentalKnowledgeList(mentalKnowledge);
        ExcelUtil<MentalKnowledge> util = new ExcelUtil<MentalKnowledge>(MentalKnowledge.class);
        return util.exportExcel(list, "心理健康知识审核数据");
    }


    /**
     * 修改心理健康知识审核
     */
    @GetMapping("/edit/{mentalKnowledgeId}")
    public String edit(@PathVariable("mentalKnowledgeId") Long mentalKnowledgeId, ModelMap mmap)
    {
        MentalKnowledge mentalKnowledge = mentalKnowledgeService.selectMentalKnowledgeByMentalKnowledgeId(mentalKnowledgeId);
        mmap.put("mentalKnowledge", mentalKnowledge);
        return prefix + "/edit";
    }

    /**
     * 修改保存心理健康知识审核
     */
    @RequiresPermissions("system:knowledge_examine:edit")
    @Log(title = "心理健康知识审核", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MentalKnowledge mentalKnowledge)
    {
        return toAjax(mentalKnowledgeService.updateMentalKnowledge(mentalKnowledge));
    }

    /**
     * 删除心理健康知识审核
     */
    @RequiresPermissions("system:knowledge_examine:remove")
    @Log(title = "心理健康知识审核", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mentalKnowledgeService.deleteMentalKnowledgeByMentalKnowledgeIds(ids));
}
}
