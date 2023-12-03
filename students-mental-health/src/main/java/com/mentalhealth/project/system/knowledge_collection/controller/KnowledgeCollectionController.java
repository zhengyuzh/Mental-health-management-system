package com.mentalhealth.project.system.knowledge_collection.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.knowledge_collection.domain.KnowledgeCollection;
import com.mentalhealth.project.system.knowledge_collection.service.IKnowledgeCollectionService;
import com.mentalhealth.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生收藏信息Controller
 *
 * @author zhengyuzhu
 * @date 2021-11-18
 */
@Controller
@RequestMapping("/system/knowledge_collection")
public class KnowledgeCollectionController extends BaseController {
    private String prefix = "system/knowledge_collection";

    @Autowired
    private IKnowledgeCollectionService knowledgeCollectionService;

    @Autowired
    private IUserService userService;

    @RequiresPermissions("system:knowledge_collection:view")
    @GetMapping()
    public String knowledge_collection() {
        return prefix + "/knowledge_collection";
    }

    /**
     * 查询学生收藏信息列表
     */
    @RequiresPermissions("system:knowledge_collection:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KnowledgeCollection knowledgeCollection) {
        startPage();
        Long roleId =    userService.selectRoleId();
        if (roleId!= 1   ){
            if (roleId != 2){
                knowledgeCollection.setUserId(ShiroUtils.getUserId());
            }
        }
        List<KnowledgeCollection> list = knowledgeCollectionService.selectKnowledgeCollectionList(knowledgeCollection);
        return getDataTable(list);
    }

    /**
     * 导出学生收藏信息列表
     */
    @RequiresPermissions("system:knowledge_collection:export")
    @Log(title = "学生收藏信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KnowledgeCollection knowledgeCollection) {
        List<KnowledgeCollection> list = knowledgeCollectionService.selectKnowledgeCollectionList(knowledgeCollection);
        ExcelUtil<KnowledgeCollection> util = new ExcelUtil<KnowledgeCollection>(KnowledgeCollection.class);
        return util.exportExcel(list, "学生收藏信息数据");
    }

    /**
     * 新增学生收藏信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存学生收藏信息
     */
    @RequiresPermissions("system:knowledge_collection:add")
    @Log(title = "学生收藏信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KnowledgeCollection knowledgeCollection) {
        return toAjax(knowledgeCollectionService.insertKnowledgeCollection(knowledgeCollection));
    }

    /**
     * 修改学生收藏信息
     */
    @GetMapping("/edit/{collectionId}")
    public String edit(@PathVariable("collectionId") Long collectionId, ModelMap mmap) {
        KnowledgeCollection knowledgeCollection = knowledgeCollectionService.selectKnowledgeCollectionByCollectionId(collectionId);
        mmap.put("knowledgeCollection", knowledgeCollection);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生收藏信息
     */
    @RequiresPermissions("system:knowledge_collection:edit")
    @Log(title = "学生收藏信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KnowledgeCollection knowledgeCollection) {
        return toAjax(knowledgeCollectionService.updateKnowledgeCollection(knowledgeCollection));
    }

    /**
     * 删除学生收藏信息
     */
    @RequiresPermissions("system:knowledge_collection:remove")
    @Log(title = "学生收藏信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(knowledgeCollectionService.deleteKnowledgeCollectionByCollectionIds(ids));
    }


    /**
     * 添加收藏
     */
    @Log(title = "收藏管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:knowledge_collection:add")
    @PostMapping("/addStatus")
    @ResponseBody
    public AjaxResult addStatus(KnowledgeCollection knowledgeCollection) {
            knowledgeCollection.setUserId(ShiroUtils.getUserId());
        System.out.println("添加收藏knowledgeCollection-----"+knowledgeCollection);
            return toAjax(knowledgeCollectionService.insertKnowledgeCollection(knowledgeCollection));
    }

    /**
     * 取消收藏
     */
    @Log(title = "收藏管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:knowledge_collection:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(KnowledgeCollection knowledgeCollection) {
        System.out.println("取消收藏knowledgeCollection-----"+knowledgeCollection);
            return toAjax(knowledgeCollectionService.updateKnowledgeCollection(knowledgeCollection));
    }


}
