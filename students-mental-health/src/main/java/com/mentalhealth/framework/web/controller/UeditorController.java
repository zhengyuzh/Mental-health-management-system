package com.mentalhealth.framework.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mentalhealth.common.utils.file.FileUploadUtils;
import com.mentalhealth.framework.config.RuoYiConfig;
import com.mentalhealth.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Ueditor 请求处理
 *
 * @author ruoyi
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/ajax/libs/ueditor")
public class UeditorController extends BaseController
{
    private final String METHOD_HEAD = "ueditor";
    private final String IMGE_PATH = "/ueditor/images/";
    private final String VIDEO_PATH = "/ueditor/videos/";
    private final String FILE_PATH = "/ueditor/files/";

    @Autowired
    private ServerConfig serverConfig;

    /**
     * ueditor
     */
    @ResponseBody
    @RequestMapping(value = "/ueditor/controller")
    public Object ueditor(HttpServletRequest request, @RequestParam(value = "action", required = true) String action,
            MultipartFile upfile) throws Exception
    {
        List<Object> param = new ArrayList<Object>()
        {
            {
                add(action);
                add(upfile);
            }
        };
        Method method = this.getClass().getMethod(METHOD_HEAD + action, List.class, String.class);
        return method.invoke(this.getClass().newInstance(), param, serverConfig.getUrl());
    }

    /**
     * 读取配置文件
     */
    public JSONObject ueditorconfig(List<Object> param, String fileSuffixUrl) throws Exception
    {
        ClassPathResource classPathResource = new ClassPathResource("ueditor-config.json");
        String jsonString = new BufferedReader(new InputStreamReader(classPathResource.getInputStream())).lines().parallel().collect(Collectors.joining(System.lineSeparator()));
        JSONObject json = JSON.parseObject(jsonString, JSONObject.class);
        return json;
    }

    /**
     * 上传图片
     */
    public JSONObject ueditoruploadimage(List<Object> param, String fileSuffixUrl) throws Exception
    {
        JSONObject json = new JSONObject();
        json.put("state", "SUCCESS");
        json.put("url", ueditorcore(param, IMGE_PATH, false, fileSuffixUrl));
        return json;
    }

    /**
     * 上传视频
     */
    public JSONObject ueditoruploadvideo(List<Object> param, String fileSuffixUrl) throws Exception
    {
        JSONObject json = new JSONObject();
        json.put("state", "SUCCESS");
        json.put("url", ueditorcore(param, VIDEO_PATH, false, fileSuffixUrl));
        return json;
    }

    /**
     * 上传附件
     */
    public JSONObject ueditoruploadfile(List<Object> param, String fileSuffixUrl) throws Exception
    {
        JSONObject json = new JSONObject();
        json.put("state", "SUCCESS");
        json.put("url", ueditorcore(param, FILE_PATH, true, fileSuffixUrl));
        return json;
    }

    public String ueditorcore(List<Object> param, String path, boolean isFileName, String fileSuffixUrl)
            throws Exception
    {
        MultipartFile upfile = (MultipartFile) param.get(1);
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        String fileName = FileUploadUtils.upload(filePath, upfile);
        String url = fileSuffixUrl + fileName;
        return url;
    }
}
