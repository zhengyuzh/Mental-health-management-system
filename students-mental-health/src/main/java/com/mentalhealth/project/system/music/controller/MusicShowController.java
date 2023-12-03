package com.mentalhealth.project.system.music.controller;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.security.ShiroUtils;
import com.mentalhealth.framework.web.controller.BaseController;
import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import com.mentalhealth.project.system.knowledge.service.IMentalKnowledgeService;
import com.mentalhealth.project.system.music.domain.Music;
import com.mentalhealth.project.system.music.service.IMusicService;
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
 * @author zyz
 * @version 1.0
 * @data 2023/8/20 19:38
 * @Description:
 */
@Controller
public class MusicShowController extends BaseController {

    @Autowired
    private IMusicService musicService;

    @Autowired
    private IMentalKnowledgeService mentalKnowledgeService;

    /**
     *  去心理音乐展示页面
     */
    @RequestMapping(value ="/system/Music/toMusicPage")
    private String ShowMusic(HttpServletRequest request, ModelMap mp, String musicTitle ) {
        User user = ShiroUtils.getSysUser();
        mp.put("user",user);
        return this.ShowMusic(request, 1, 5,mp,musicTitle);

    }
    @GetMapping(value = "/pageMusic/{p}")
    public String ShowMusic(HttpServletRequest request, @PathVariable("p") int page,
                                      @RequestParam(value = "count", defaultValue = "5") int count, ModelMap modelMap, String musicTitle) {
        PageInfo<Music> musicPageInfo = musicService.selectMusicWithPage(page, count,musicTitle);
        List<Music> musicList = musicService.selectMusicList(null);
        User user1 = ShiroUtils.getSysUser();
        modelMap.put("user",user1);
        request.setAttribute("mentalKnowledges", musicPageInfo);
        request.setAttribute("mentalKnowledgeTitle", musicTitle);
        request.setAttribute("mentalKnowledgeList", musicList);
        logger.info("分页获取文章信息: 页码 "+page+",条数 "+count);
        return "foreground/client/show_music";
    }

}
