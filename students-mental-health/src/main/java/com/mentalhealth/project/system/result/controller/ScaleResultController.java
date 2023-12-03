package com.mentalhealth.project.system.result.controller;

import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.StringUtils;
import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.answer.domain.ScaleAnswer;
import com.mentalhealth.project.system.answer.service.IScaleAnswerService;
import com.mentalhealth.project.system.classes.domain.Classes;
import com.mentalhealth.project.system.classes.service.IClassesService;
import com.mentalhealth.project.system.dept.domain.Dept;
import com.mentalhealth.project.system.dept.service.IDeptService;
import com.mentalhealth.project.system.factor.domain.ScaleFactor;
import com.mentalhealth.project.system.factor.service.IScaleFactorService;
import com.mentalhealth.project.system.formula.domain.ScaleFormula;
import com.mentalhealth.project.system.formula.service.IScaleFormulaService;
import com.mentalhealth.project.system.question.domain.ScaleQuestion;
import com.mentalhealth.project.system.question.service.IScaleQuestionService;
import com.mentalhealth.project.system.result.domain.ScaleResult;
import com.mentalhealth.project.system.result.service.IScaleResultService;
import com.mentalhealth.project.system.result_user.domain.ScaleUserResult;
import com.mentalhealth.project.system.result_user.service.IScaleUserResultService;
import com.mentalhealth.project.system.scale.domain.Scale;
import com.mentalhealth.project.system.scale.service.IScaleService;
import com.mentalhealth.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 测评结果参考信息Controller
 *
 * @author zhengyuzhu
 * @date 2023-08-22
 */
@Controller
@RequestMapping("/system/result")
public class ScaleResultController extends BaseController {
    private String prefix = "system/result";

    @Autowired
    private IScaleResultService scaleResultService;

    @Autowired
    private IScaleService scaleService;
    @Autowired
    private IScaleAnswerService scaleAnswerService;
    @Autowired
    private IScaleQuestionService scaleQuestionService;
    @Autowired
    private IScaleFormulaService formulaService;
    @Autowired
    private IScaleFactorService scaleFactorService;
    @Autowired
    private IScaleUserResultService scaleUserResultService;
    @Autowired
    private IClassesService classesService;
    @Autowired
    private IDeptService deptService;

    @RequiresPermissions("system:result:view")
    @GetMapping()
    public String result() {
        return prefix + "/result";
    }


    /**
     * 查询测评结果参考信息列表
     */
    @RequiresPermissions("system:result:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScaleResult scaleResult) {
        startPage();
        List<ScaleResult> list = scaleResultService.selectScaleResultList(scaleResult);
        return getDataTable(list);
    }

    /**
     * 导出测评结果参考信息列表
     */
    @RequiresPermissions("system:result:export")
    @Log(title = "测评结果参考信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScaleResult scaleResult) {
        List<ScaleResult> list = scaleResultService.selectScaleResultList(scaleResult);
        ExcelUtil<ScaleResult> util = new ExcelUtil<ScaleResult>(ScaleResult.class);
        return util.exportExcel(list, "测评结果参考信息数据");
    }


    /**
     * 选择  导出数据
     */
    @PostMapping("/exportData")
    @ResponseBody
    public AjaxResult exportSelected(ScaleResult scaleResultModel, String resultIds) {
        List<ScaleResult> scaleResults = scaleResultService.selectScaleResultList(scaleResultModel);
        List<ScaleResult> scaleResultsList = new ArrayList<ScaleResult>(Arrays.asList(new ScaleResult[scaleResults.size()]));
        Collections.copy(scaleResultsList, scaleResults);

        // 条件过滤
        if (StringUtils.isNotEmpty(resultIds)) {
            scaleResultsList.clear();
            for (Long resultId : Convert.toLongArray(resultIds)) {
                for (ScaleResult scaleResult : scaleResults) {
                    if (scaleResult.getResultId() == resultId) {
                        scaleResultsList.add(scaleResult);
                    }
                }
            }
        }
        ExcelUtil<ScaleResult> util = new ExcelUtil<ScaleResult>(ScaleResult.class);
        return util.exportExcel(scaleResultsList, "结果报告数据");
    }


    /**
     * 新增测评结果参考信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存测评结果参考信息
     */
    @RequiresPermissions("system:result:add")
    @Log(title = "测评结果参考信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScaleResult scaleResult) {
        return toAjax(scaleResultService.insertScaleResult(scaleResult));
    }

    /**
     * 修改测评结果参考信息
     */
    @GetMapping("/edit/{resultId}")
    public String edit(@PathVariable("resultId") Long resultId, ModelMap mmap) {
        ScaleResult scaleResult = scaleResultService.selectScaleResultByResultId(resultId);
        mmap.put("scaleResult", scaleResult);
        return prefix + "/edit";
    }

    /**
     * 修改保存测评结果参考信息
     */
    @RequiresPermissions("system:result:edit")
    @Log(title = "测评结果参考信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScaleResult scaleResult) {
        return toAjax(scaleResultService.updateScaleResult(scaleResult));
    }

    /**
     * 删除测评结果参考信息
     */
    @RequiresPermissions("system:result:remove")
    @Log(title = "测评结果参考信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(scaleResultService.deleteScaleResultByResultIds(ids));
    }


    /**
     * 用户测评结果
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/scaleUserResult")
    public String ScaleUserResult(@RequestParam Map<String, String> map, HttpSession session, Model model) {
        User user = getSysUser();
        model.addAttribute("user", user);
        Classes classes = classesService.selectClassesByClassesId(user.getClassesId());
        System.out.println("classes:"+classes);
        Dept dept = deptService.selectDeptById(user.getDeptId());
        System.out.println("dept:"+dept);
        // 获取存入 session 的 scaleId
        Long scaleId = (Long) session.getAttribute("scaleId");
        System.out.println("session----------scaleId----" + scaleId);
//        public Set<K> keySet(): 获取Map集合中所有的键，存储到Set集合中。
//        获取Map中所有的键，由于键是唯一的，所以返回一个Set集合存储所有的键。方法提示:keyset()  遍历键的Set集合，得到每一个键。
//       根据键，获取键所对应的值。方法提示:get(K key)
        Scale scale = scaleService.selectScaleByScaleId(scaleId);   // 测评量表
        model.addAttribute("scale", scale);
        ScaleResult result = scaleResultService.selectScaleResultByScaleId(scaleId);    // 结果提示
        System.out.println("result----"+result);
        // formula 计算公式 系统的计算公式结构设计为： 最终分数 = 初始总分 或者 因子总分 * 系数 + 常量。
        // 每个 因子均分 即 因子总分* 系数（系数 = 因子分等于组成某一因子的各项总分 / 组成某一因子的项目数。当个体在某一因子的得分大于2时，即超出正常均分）
        ScaleFormula formula = formulaService.selectScaleFormulaByFormulaId(scale.getFormulaId());
        double formulaCoefficient = formula.getFormulaCoefficient();   //  系数
        double formulaConstant = formula.getFormulaConstant();         //  常量
        System.out.println("formula------------------" + formula);
        double countScore = 0;     // 定义总分
        double factorScore = 0.0;    // 因子分
        double standardScore = 0;  // 标准分
        int questionCount = 0;     // 题目总数
        Map<Long, Double> factorScoreMap = new HashMap<Long, Double>();   // 因子id - 因子总分 集合
        Map<String, Double> factorNameScoreMap = new HashMap<String, Double>();   // 因子名称-因子分数 集合
        Map<String, Double> factorAverageMap = new HashMap<String, Double>();  // 因子名称 - 因子均分 集合
        String userAnswer = "";  // 问题 - 选选择
        int userAnswerNum = 1;
        for (String questionId : map.keySet()) {
            String answerId = map.get(questionId);
            //转换为 Long
            Long answerId2 = Long.parseLong(answerId);
            //查出每一个 answer 统计总分数
            ScaleAnswer answer = scaleAnswerService.selectScaleAnswerByAnswerId(answerId2);
            // 查出每一个分数的因子id（factorId）
            Long factorId = answer.getFactorId();
            // 查出每一个因子的分数  （factorScore）
            factorScore = answer.getScore();
            // factorScoreMap.get(factorId) 根据 factorId 取出 对应的值 即原来的 factorScore
            if (factorScoreMap.containsKey(factorId)) {  // 如果存在 则分数相加
                ScaleFactor factor = scaleFactorService.selectScaleFactorByFactorId(factorId);
                String factorName = factor.getFactorName();
                // 因子总分数
                factorScore += factorScoreMap.get(factorId);
                // 放入列表 因子id - 因子分 集合
                factorScoreMap.put(factorId, factorScore);
                // 放入列表 因子名称-因子总分分数 集合
                factorNameScoreMap.put(factorName, factorScore);
            } else {
                ScaleFactor factor = scaleFactorService.selectScaleFactorByFactorId(factorId);
                String factorName = factor.getFactorName();
                factorScoreMap.put(factorId, factorScore);  // 不存在则新添加
                factorNameScoreMap.put(factorName, factorScore);
            }

            System.out.println("factorId------------------" + factorId);
            System.out.println("factorScore------------------" + factorScore);
            ScaleQuestion scaleQuestion = scaleQuestionService.selectScaleQuestionByQuestionId(Long.parseLong(questionId));

            userAnswer = userAnswer + userAnswerNum + ". " + scaleQuestion.getQuestionContent() + " ： " + answer.getAnswerOption()   + '\n';  // 问题 - 选选择
            userAnswerNum++;
            double score = answer.getScore();
            // 初始总分
            countScore += score;
            questionCount++;
        }
        System.out.println(" 因子id - 因子总分 集合：factorScoreMap:" + factorScoreMap);
        System.out.println("因子名称-因子分数 集合：factorNameScoreMap:" + factorNameScoreMap);
        double factorCountScore = 0;    // 因子总分
        String factorResult = "";     // 因子结果解释
        String factorResultShow = "";     // 因子名称 = 分数 , 均分 = ；
        int factorResultNum = 1;
        for (Long factorIdToAverage : factorScoreMap.keySet())// 通过因子id（factorId）获取 计算公式id（formulaId）
        {
            ScaleFactor factor = scaleFactorService.selectScaleFactorByFactorId(factorIdToAverage);
            String factorName = factor.getFactorName();
            factorCountScore = factorScoreMap.get(factorIdToAverage);   // 因子总分
            ScaleFactor scaleFactor = scaleFactorService.selectScaleFactorByFactorId(factorIdToAverage);  //查出因子
            ScaleFormula scaleFormula = formulaService.selectScaleFormulaByFormulaId(scaleFactor.getFormulaId());  //通过因子查出计算公式
            System.out.println("计算公式scaleFormula0+++++++" + scaleFormula);
            double Coefficient = scaleFormula.getFormulaCoefficient();   //  系数
            System.out.println("Coefficient  :" + Coefficient);
            double Constant = scaleFormula.getFormulaConstant();         //  常量
            System.out.println("Constant  :" + Constant);
            double factorAverage = factorCountScore * Coefficient + Constant;      // 计算出均分
            factorAverageMap.put(factorName, factorAverage);       // 放入集合

            factorResult = factorResult + '\n' + factorResultNum + "、" + factor.getFactorResult() + " " + ";"+'\n';    // 因子结果解释
            factorResultNum++;
            factorResultShow = factorResultShow +'\n' + factorName + " = " + factorCountScore + " ，" + "均分" + " = " + factorAverage  +";"+ '\n';  //因子总分，因子均分
        }
        System.out.println("因子名称 - 因子均分 集合：factorAverageMap:" + factorAverageMap);
        model.addAttribute("factorAverageMap", factorAverageMap);
        model.addAttribute("factorScoreMap", factorScoreMap);
        model.addAttribute("factorNameScoreMap", factorNameScoreMap);
        model.addAttribute("factorResult", factorResult);  // 因子结果解释
        model.addAttribute("factorResultShow", factorResultShow); // 因子总分 和 均分

        //  总均分：总分/题目总数
        double totalAverage = countScore / questionCount;
        System.out.println("questionCount题目总数：" + questionCount);
        //  标准分 = 初始总分 * 系数 + 常量。
        standardScore = countScore * formulaCoefficient + formulaConstant;
        System.out.println("标准分standardScore:" + standardScore);
        model.addAttribute("standardScore", standardScore);
        //  总分
        model.addAttribute("countScore", countScore);
        System.out.println("总分countScore:" + countScore);
        //  总均分
        model.addAttribute("totalAverage", totalAverage);
        System.out.println("总均分totalAverage:" + totalAverage);
        //  结果提示 和 建议
        if (result!=null ){
            model.addAttribute("result", result);
        }

        model.addAttribute("scaleTime", DateUtils.getNowDate());
        model.addAttribute("deptName", dept.getDeptName());
        if (classes !=null ){
            model.addAttribute("classesName", classes.getClassesName());
        }
        model.addAttribute("userAnswer", userAnswer);
        // 存入用户结果
        ScaleUserResult scaleUserResult = new ScaleUserResult();
        scaleUserResult.setUserId(user.getUserId());        // 用户id
        scaleUserResult.setLoginName(user.getLoginName());  // 用户名称
        scaleUserResult.setUserName(user.getUserName());  // 用户名称
        scaleUserResult.setScaleId(scale.getScaleId());        // 测评量表id
        scaleUserResult.setScaleTitle(scale.getScaleTitle());  // 测评量表
        scaleUserResult.setCountScore(countScore);      // 总分
        scaleUserResult.setFactorScore(factorResultShow);     // 因子分数
        if (result!=null){      scaleUserResult.setResultContent(result.getResultContent());  } //  结果提示
        if (classes!=null){    scaleUserResult.setClassesName(classes.getClassesName());   }

        scaleUserResult.setFactorResult(factorResult);                // 因子结果提示
        scaleUserResult.setUserAnswer(userAnswer);                     // 用户问题选择答案

        scaleUserResult.setDeptName(dept.getDeptName());
//        scaleUserResult.setCreateTime(DateUtils.getNowDate());
        scaleUserResultService.insertScaleUserResult(scaleUserResult);

        return "foreground/client/scaleResult";
    }
//
//    /**
//     * 用户测评结果
//     *
//     * @param map
//     * @return
//     */
//    @GetMapping(value = "/scaleUserResult")
//    public String ScaleUserResult(@RequestParam Map<String, String> map, HttpSession session, Model model) {
//        User user = getSysUser();
//        model.addAttribute("user",user);
//        // 获取存入 session 的 scaleId
//        Long scaleId = (Long) session.getAttribute("scaleId");
//        System.out.println("session----------scaleId----" + scaleId);
////        public Set<K> keySet(): 获取Map集合中所有的键，存储到Set集合中。
////        获取Map中所有的键，由于键是唯一的，所以返回一个Set集合存储所有的键。方法提示:keyset()  遍历键的Set集合，得到每一个键。
////       根据键，获取键所对应的值。方法提示:get(K key)
//        Scale scale = scaleService.selectScaleByScaleId(scaleId);   // 测评量表
//        model.addAttribute("scale",scale);
//        ScaleResult result = scaleResultService.selectScaleResultByResultId(scaleId);    // 结果提示
//        // formula 计算公式 系统的计算公式结构设计为： 最终分数 = 初始总分 或者 因子总分 * 系数 + 常量。
//        // 每个 因子均分 即 因子总分* 系数（系数 = 因子分等于组成某一因子的各项总分 / 组成某一因子的项目数。当个体在某一因子的得分大于2时，即超出正常均分）
//        ScaleFormula formula = formulaService.selectScaleFormulaByFormulaId(scale.getFormulaId());
//        double formulaCoefficient = formula.getFormulaCoefficient();   //  系数
//        double formulaConstant = formula.getFormulaConstant();         //  常量
//        System.out.println("formula------------------" + formula);
//        double countScore = 0;     // 定义总分
//        double factorScore = 0.0;    // 因子分
//        double standardScore = 0;  // 标准分
//        int questionCount = 0;     // 题目总数
//        Map<Long, Double> factorScoreMap = new HashMap<Long, Double>();   // 因子id - 因子总分 集合
//        Map<String, Double> factorNameScoreMap = new HashMap<String, Double>();   // 因子名称-因子分数 集合
//        Map<String, Double> factorAverageMap = new HashMap<String, Double>();  // 因子名称 - 因子均分 集合
//        for (String questionId : map.keySet()) {
//            String answerId = map.get(questionId);
//            //转换为 Long
//            Long answerId2 = Long.parseLong(answerId);
//            //查出每一个 answer 统计总分数
//            ScaleAnswer answer = scaleAnswerService.selectScaleAnswerByAnswerId(answerId2);
//            // 查出每一个分数的因子id（factorId）
//            Long factorId = answer.getFactorId();
//            // 查出每一个因子的分数  （factorScore）
//            factorScore = answer.getScore();
//            // factorScoreMap.get(factorId) 根据 factorId 取出 对应的值 即原来的 factorScore
//            if (factorScoreMap.containsKey(factorId)) {  // 如果存在 则分数相加
//                ScaleFactor factor = scaleFactorService.selectScaleFactorByFactorId(factorId);
//                String factorName = factor.getFactorName();
//                // 因子总分数
//                factorScore += factorScoreMap.get(factorId);
//                // 放入列表 因子id - 因子分 集合
//                factorScoreMap.put(factorId, factorScore);
//                // 放入列表 因子名称-因子总分分数 集合
//                factorNameScoreMap.put(factorName, factorScore);
//            } else {
//                ScaleFactor factor = scaleFactorService.selectScaleFactorByFactorId(factorId);
//                String factorName = factor.getFactorName();
//                factorScoreMap.put(factorId, factorScore);  // 不存在则新添加
//                factorNameScoreMap.put(factorName, factorScore);
//            }
//
//            System.out.println("factorId------------------" + factorId);
//            System.out.println("factorScore------------------" + factorScore);
//
//            double score = answer.getScore();
//            // 初始总分
//            countScore += score;
//            questionCount++;
//        }
//        System.out.println(" 因子id - 因子总分 集合：factorScoreMap:" + factorScoreMap);
//        System.out.println("因子名称-因子分数 集合：factorNameScoreMap:" + factorNameScoreMap);
//        double factorCountScore = 0;    // 因子总分
//        String factorResult = "";     // 因子结果解释
//        String factorResultShow = "";     // 因子名称 = 分数 , 均分 = ；
//        int factorResultNum = 1;
//        for (Long factorIdToAverage : factorScoreMap.keySet())// 通过因子id（factorId）获取 计算公式id（formulaId）
//        {
//            ScaleFactor factor = scaleFactorService.selectScaleFactorByFactorId(factorIdToAverage);
//            String factorName = factor.getFactorName();
//            factorCountScore = factorScoreMap.get(factorIdToAverage);   // 因子总分
//            ScaleFactor scaleFactor = scaleFactorService.selectScaleFactorByFactorId(factorIdToAverage);  //查出因子
//            ScaleFormula scaleFormula = formulaService.selectScaleFormulaByFormulaId(scaleFactor.getFormulaId());  //通过因子查出计算公式
//            System.out.println("计算公式scaleFormula0+++++++"+scaleFormula);
//            double Coefficient = scaleFormula.getFormulaCoefficient();   //  系数
//            System.out.println("Coefficient  :"+Coefficient);
//            double Constant = scaleFormula.getFormulaConstant();         //  常量
//            System.out.println("Constant  :"+Constant);
//            double factorAverage = factorCountScore * Coefficient + Constant;      // 计算出均分
//            factorAverageMap.put(factorName,factorAverage);       // 放入集合
//
//            factorResult = factorResult + factorResultNum + "、" + factor.getFactorResult()  +" "+'\n';    // 因子结果解释
//            factorResultNum++;
//            factorResultShow = factorResultShow + factorName + " = " + factorCountScore  + " ，" + "均分"+" = " +factorAverage + ";  "+'\n';  //因子分
//        }
//        System.out.println("因子名称 - 因子均分 集合：factorAverageMap:" + factorAverageMap);
//        model.addAttribute("factorAverageMap", factorAverageMap);
//        model.addAttribute("factorScoreMap", factorScoreMap);
//        model.addAttribute("factorNameScoreMap", factorNameScoreMap);
//        model.addAttribute("factorResult", factorResult);  // 因子结果解释
//        model.addAttribute("factorResultShow", factorResultShow); // 因子总分 和 均分
//
//        //  总均分：总分/题目总数
//        double totalAverage = countScore / questionCount;
//        System.out.println("questionCount题目总数：" + questionCount);
//        //  标准分 = 初始总分 * 系数 + 常量。
//        standardScore = countScore * formulaCoefficient + formulaConstant;
//        System.out.println("标准分standardScore:" + standardScore);
//        model.addAttribute("standardScore", standardScore);
//        //  总分
//        model.addAttribute("countScore", countScore);
//        System.out.println("总分countScore:" + countScore);
//        //  总均分
//        model.addAttribute("totalAverage", totalAverage);
//        System.out.println("总均分totalAverage:" + totalAverage);
//        //  结果提示 和 建议
//        model.addAttribute("result", result);
//        // 存入用户结果
//        ScaleUserResult scaleUserResult = new ScaleUserResult();
//        scaleUserResult.setUserId(user.getUserId());        // 用户id
//        scaleUserResult.setLoginName(user.getLoginName());  // 用户名称
//        scaleUserResult.setUserName(user.getUserName());  // 用户名称
//        scaleUserResult.setScaleId(scale.getScaleId());        // 测评量表id
//        scaleUserResult.setScaleTitle(scale.getScaleTitle());  // 测评量表
//        scaleUserResult.setCountScore(countScore);      // 总分
//        scaleUserResult.setFactorScore(factorResultShow);     // 因子分数
//        scaleUserResult.setResultContent(result.getResultContent());  //  结果提示
//        scaleUserResult.setFactorResult(factorResult);                // 因子结果提示
//        scaleUserResult.setCreateTime(DateUtils.getNowDate());
//        scaleUserResultService.insertScaleUserResult(scaleUserResult);
//
//        return "foreground/client/scaleResult";
//    }


}