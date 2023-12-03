package com.mentalhealth.project.system.music.controller;

import com.mentalhealth.common.utils.poi.ExcelUtil;
import com.mentalhealth.framework.aspectj.lang.annotation.Log;
import com.mentalhealth.framework.aspectj.lang.enums.BusinessType;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.framework.web.domain.AjaxResult;
import com.mentalhealth.framework.web.page.TableDataInfo;
import com.mentalhealth.project.system.music.domain.Music;
import com.mentalhealth.project.system.music.service.IMusicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-08-15
 */
@Controller
@RequestMapping("/system/music")
public class MusicController extends BaseController
{
    private String prefix = "system/music";
    private static final String BASE_PATH = "D:\\mentalHealth\\upload\\music\\";
    private static final String VIRTUAL_FILE_PATH = "http://localhost:80/music/";


    @Autowired
    private IMusicService musicService;

    @RequiresPermissions("system:music:view")
    @GetMapping()
    public String music()
    {
        return prefix + "/music";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:music:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Music music)
    {
        startPage();
        List<Music> list = musicService.selectMusicList(music);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:music:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Music music)
    {
        List<Music> list = musicService.selectMusicList(music);
        ExcelUtil<Music> util = new ExcelUtil<Music>(Music.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:music:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Music music)
    {
        return toAjax(musicService.insertMusic(music));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:music:edit")
    @GetMapping("/edit/{musicid}")
    public String edit(@PathVariable("musicid") Long musicid, ModelMap mmap)
    {
        Music music = musicService.selectMusicByMusicid(musicid);
        mmap.put("music", music);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:music:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Music music)
    {
        return toAjax(musicService.updateMusic(music));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:music:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(musicService.deleteMusicByMusicids(ids));
    }






        @RequestMapping("/upload")
        public String insertMusic(@RequestParam String singer, @RequestParam("filename") MultipartFile file, HttpServletRequest request){
        Music music = new Music();

            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println("文件名: " + fileName);
            //替换名称
            int index = fileName.lastIndexOf(".");
            UUID uuid = UUID.randomUUID();
            String fileName1 = uuid+fileName.substring(index);

            // 指定文件保存路径  BASE_PATH
            String path = BASE_PATH+ fileName1;
            File destFile = new File(path);
            System.out.println("文件路径: " + destFile.getPath());

            if (!destFile.exists()){
                destFile.mkdirs();// 别少加了 s
            }

            try {
                // 保存文件到指定路径
                file.transferTo(destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }


            //数据库上传
            assert fileName != null;
            String title = fileName.substring(0, index);
            String final_musicName = VIRTUAL_FILE_PATH + fileName1;


            //http请求
            music.setTitle(title);
            music.setUrl(final_musicName);
            music.setSinger(singer);
            music.setCreateTime(music.getCreateTime());
            int rs = musicService.addMusic(music);

            return prefix + "/music";
        }



}
