package com.mentalhealth.project.system.knowledge.controller;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import com.mentalhealth.project.system.knowledge.service.IMentalKnowledgeService;
import com.mentalhealth.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 心理健康知识Controller
 *
 * @author zhengyuzhu
 * @date 2023-08-09
 */
@Controller
public class MentalKnowledgeShowController extends BaseController
{
    @Autowired
    private IMentalKnowledgeService mentalKnowledgeService;
    /**
     *  去心理知识展示页面
     */
    @RequestMapping(value ="/system/knowledge/toKnowledgePage")
    private String ShowMentalKnowledge(HttpServletRequest request,ModelMap mp,String mentalKnowledgeTitle ) {
        User user = ShiroUtils.getSysUser();
        mp.put("user",user);
        return this.ShowMentalKnowledge(request, 1, 5,mp,mentalKnowledgeTitle);
    }
    @GetMapping(value = "/pageMentalKnowledge/{p}")
    public String ShowMentalKnowledge(HttpServletRequest request, @PathVariable("p") int page,
                                      @RequestParam(value = "count", defaultValue = "5") int count,ModelMap modelMap,String mentalKnowledgeTitle) {
        PageInfo<MentalKnowledge> mentalKnowledges = mentalKnowledgeService.selectMentalKnowledgeWithPage(page, count,mentalKnowledgeTitle);
        List<MentalKnowledge> mentalKnowledgeList = mentalKnowledgeService.selectMentalKnowledgeList(null);
        User user1 = ShiroUtils.getSysUser();
        modelMap.put("user",user1);
        request.setAttribute("mentalKnowledges", mentalKnowledges);
        request.setAttribute("mentalKnowledgeTitle", mentalKnowledgeTitle);
        request.setAttribute("mentalKnowledgeList", mentalKnowledgeList);
        logger.info("分页获取文章信息: 页码 "+page+",条数 "+count);
        return "foreground/client/show_knowledge";
    }



}
