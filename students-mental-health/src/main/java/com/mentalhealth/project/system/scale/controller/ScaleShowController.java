package com.mentalhealth.project.system.scale.controller;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.project.system.answer.service.IScaleAnswerService;
import com.mentalhealth.project.system.question.service.IScaleQuestionService;
import com.mentalhealth.project.system.scale.domain.Scale;
import com.mentalhealth.project.system.scale.service.IScaleService;
import com.mentalhealth.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 心理测评量表管理Controller
 *
 * @author zhengyuzhu
 * @date 2023-08-13
 */

@Controller
public class ScaleShowController extends BaseController {
    private String prefix = "system/scale";

    @Autowired
    private IScaleService scaleService;

    @Autowired
    private IScaleQuestionService scaleQuestionService;


    @Autowired
    private IScaleAnswerService scaleAnswerService;

    /**
     * 去心理测评展示页面
     */
    @GetMapping(value = "/system/Scale/toScale")
    private String ShowScale(HttpServletRequest request, ModelMap mp, String scaleTitle) {
//        User user = ShiroUtils.getSysUser();
//        mp.put("user", user);
        return this.ShowScale(request, 1, 5, scaleTitle, mp);
    }

    @GetMapping(value = "/pageScale/{p}")
    public String ShowScale(HttpServletRequest request, @PathVariable("p") int page,
                            @RequestParam(value = "count", defaultValue = "5") int count, String scaleTitle, ModelMap mp) {
        PageInfo<Scale> scalePageInfo = scaleService.selectScaleWithPage(page, count, scaleTitle);
        User user1 = ShiroUtils.getSysUser();
        mp.put("user", user1);
        request.setAttribute("scalePageInfo", scalePageInfo);
//        System.out.println(scalePageInfo);
        request.setAttribute("scaleTitle", scaleTitle);
        logger.info("分页获取文章信息: 页码 " + page + ",条数 " + count);
        return "foreground/client/show_scale";
    }

    // 测评详情查询
    @GetMapping(value = "/scale/{scaleId}")
    public String getScaleById(@PathVariable("scaleId") Long scaleId, HttpServletRequest request, ModelMap mp) {
        User user1 = ShiroUtils.getSysUser();
        mp.put("user", user1);
        Scale scale = scaleService.selectScaleByScaleId(scaleId);

        if (scale != null) {
            request.setAttribute("scale", scale);
            return "foreground/client/scaleDetails";
        } else {
            logger.warn("查询测试量表详情结果为空，测试量表id: " + scaleId);
            // 未找到对应文章页面，跳转到提示页
            return "foreground/comm/error_404";
        }
    }


    // 开始测评

    @RequestMapping(value = "/startScale")
    public String startScale(Long scaleId, Model mp, HttpSession session) {
        mp.addAttribute("scales",scaleService.startScale(scaleId));

        // 把 id 存入 session
        session.setAttribute("scaleId",scaleId);
//        System.out.println("scaleService.startScale(scaleId)"+scaleService.startScale(scaleId));
        return "foreground/client/start_scale";
    }


//
//
//    // 开始测评
//
//    @RequestMapping(value = "/startScale")
//    public String startScale( Long scaleId, HttpServletRequest request, ModelMap mp) {
//        User user = ShiroUtils.getSysUser();
//        mp.put("user", user);
//        Scale scale = scaleService.selectScaleByScaleId(scaleId);
//        //查询问题总数
//        int  questionsNum = scaleService.selectQuestionAll(scaleId);
//        //查询问题列表
//        List<ScaleQuestion> scaleQuestions = scaleQuestionService.selectScaleQuestionListByScaleId(scaleId);
//        System.out.println("scaleQuestions--------"+scaleQuestions);
////        List<List<ScaleAnswer>> scaleAnswers =new ArrayList<>();
//        for (ScaleQuestion scaleQuestion:scaleQuestions){
//
//            List<ScaleAnswer>  ScaleAnswers= scaleQuestion.getScaleAnswerList();
//            System.out.println("ScaleAnswers--------"+ScaleAnswers);
////            System.out.println("scaleQuestion--------"+scaleQuestion);
////            List<ScaleAnswer> scaleAnswer= scaleAnswerService.selectScaleAnswerByQuestionId(scaleQuestion.getQuestionId());
////            scaleAnswers.add(scaleAnswer);
//
//        }
//
////        System.out.println("scaleQuestions:"+scaleQuestions);
//        request.setAttribute("scale", scale);
//        mp.put("questionsNum", questionsNum);
//        mp.put("scaleQuestions", scaleQuestions);
////        mp.put("scaleAnswers", scaleAnswers);
//
//        return "foreground/client/start_scale";
//    }

}
