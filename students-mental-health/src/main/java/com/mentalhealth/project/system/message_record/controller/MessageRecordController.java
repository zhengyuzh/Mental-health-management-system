package com.mentalhealth.project.system.message_record.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.message_record.domain.MessageRecord;
import com.mentalhealth.project.system.message_record.service.IMessageRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 咨询记录信息Controller
 * 
 * @author zhengyuzhu
 * @date 2021-11-16
 */
@Controller
@RequestMapping("/system/message_record")
public class MessageRecordController extends BaseController
{
    private String prefix = "system/message_record";

    private static final String IMAGE_PREFIX = "D:/ruoyi/uploadPath/upload/";  //服务器储存上传图片地址的前缀

    @Autowired
    private IMessageRecordService messageRecordService;


    @RequiresPermissions("system:message_record:view")
    @GetMapping()
    public String message_record()
    {
        return prefix + "/message_record";
    }

    /**
     * 查询咨询记录信息列表
     */
    @RequiresPermissions("system:message_record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MessageRecord messageRecord)
    {
        startPage();

        String loginName = ShiroUtils.getLoginName();
        messageRecord.setLoginName(loginName);
        List<MessageRecord> list = messageRecordService.selectMessageRecordList(messageRecord);
        return getDataTable(list);
    }

    /**
     * 导出咨询记录信息列表
     */
    @RequiresPermissions("system:message_record:export")
    @Log(title = "咨询记录信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MessageRecord messageRecord)
    {
        List<MessageRecord> list = messageRecordService.selectMessageRecordList(messageRecord);
        ExcelUtil<MessageRecord> util = new ExcelUtil<MessageRecord>(MessageRecord.class);
        return util.exportExcel(list, "咨询记录信息数据");
    }

    /**
     * 新增咨询记录信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存咨询记录信息
     */
    @RequiresPermissions("system:message_record:add")
    @Log(title = "咨询记录信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MessageRecord messageRecord)
    {
        System.out.println(messageRecord);
        return toAjax(messageRecordService.insertMessageRecord(messageRecord));
    }



    /**
     * 新增保存咨询记录信息
     */
    @RequiresPermissions("system:message_record:add")
    @Log(title = "咨询记录信息", businessType = BusinessType.INSERT)
    @PostMapping("/addMessage")
    @ResponseBody
    public AjaxResult addMessage(MessageRecord messageRecord)
    {
        messageRecord.setLoginName(ShiroUtils.getLoginName());
        messageRecord.setMessageType(1);
        System.out.println(messageRecord);
//        String str2 = String.Format( messageRecord.toName, messageRecord.str);
//        return Json(str2);//--------对应请求中dataType: "json"，表示需要返回json类型
        //return String.Format("保存成功PostAlbum：{0} {1:d}", test.AlbumName, test.Entered);//------如果是string，则会报错
        messageRecordService.insertMessageRecord(messageRecord);
        return AjaxResult.success();
    }



    /**
     * 修改咨询记录信息
     */
    @GetMapping("/edit/{messageId}")
    public String edit(@PathVariable("messageId") Long messageId, ModelMap mmap)
    {
        MessageRecord messageRecord = messageRecordService.selectMessageRecordByMessageId(messageId);
        mmap.put("messageRecord", messageRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存咨询记录信息
     */
    @RequiresPermissions("system:message_record:edit")
    @Log(title = "咨询记录信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MessageRecord messageRecord)
    {
        return toAjax(messageRecordService.updateMessageRecord(messageRecord));
    }

    /**
     * 删除咨询记录信息
     */
    @RequiresPermissions("system:message_record:remove")
    @Log(title = "咨询记录信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(messageRecordService.deleteMessageRecordByMessageIds(ids));
    }

//
//    /**
//     * 接收转发图片
//     *
//     * @param request
//     * @param imageFile
//     * @param userName
//     * @return
//     */
//    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
//    @ResponseBody
//    public String handleUploadImage(HttpServletRequest request, @RequestParam("image") MultipartFile imageFile,
//                                    @RequestParam("userName") String userName, Model model) {
//        if (!imageFile.isEmpty()) {
//            String imageName = userName + "_" + UUID.randomUUID().toString() + ".jpg";
//            File file = new File(request.getSession().getServletContext().getRealPath(IMAGE_PREFIX) + "/" + imageName);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            try {
//                //上传图片到目录
//                imageFile.transferTo(file);
//                MessageRecord messageRecord = new MessageRecord();
//                messageRecord.setMessageType(2);
//                messageRecord.setUserName(userName);
//                messageRecord.setSendDate(new Date());
//                // 图片的src
//                messageRecord.setContent(request.getContextPath() + IMAGE_PREFIX + imageName);
//
//                //保存发送图片信息
//                User userByName = userService.getUserByName(message.getUserName());
//                MessageRecordDo messageRecordDo = MessageRecordDo.messageRecordBuilder()
//                        .userId(userByName == null ? null : userByName.getId())
//                        .userName(userName).content(message.getContent())
//                        .messageType(MessageTypeEnum.IMAGE.getCode()).createTime(new Date()).build();
//                userService.addUserMessageRecord(messageRecordDo);
//
//                messagingTemplate.convertAndSend(SUBSCRIBE_MESSAGE_URI, message);
//            } catch (IOException e) {
//                logger.error("图片上传失败：" + e.getMessage(), e);
//                return "upload false";
//            }
//        }
//        return "upload success";
//    }


}
