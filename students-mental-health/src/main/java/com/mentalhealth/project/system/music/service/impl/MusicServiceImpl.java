package com.mentalhealth.project.system.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mentalhealth.common.utils.DateUtils;
import com.mentalhealth.common.utils.text.Convert;
import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import com.mentalhealth.project.system.music.domain.Music;
import com.mentalhealth.project.system.music.mapper.MusicMapper;
import com.mentalhealth.project.system.music.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-15
 */
@Service
public class MusicServiceImpl implements IMusicService 
{
    @Autowired
    private MusicMapper musicMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param musicid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Music selectMusicByMusicid(Long musicid)
    {
        return musicMapper.selectMusicByMusicid(musicid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param music 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Music> selectMusicList(Music music)
    {
        return musicMapper.selectMusicList(music);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param music 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMusic(Music music)
    {
        music.setCreateTime(DateUtils.getNowDate());
        return musicMapper.insertMusic(music);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param music 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMusic(Music music)
    {
        return musicMapper.updateMusic(music);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param musicids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMusicByMusicids(String musicids)
    {
        return musicMapper.deleteMusicByMusicids(Convert.toStrArray(musicids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param musicid 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMusicByMusicid(Long musicid)
    {
        return musicMapper.deleteMusicByMusicid(musicid);
    }

    @Override
    public int addMusic(Music music) {
        return musicMapper.addMusice(music);
    }

    /**
     *  前台首页 音乐展示
     * @param page
     * @param count
     * @param musicTitle
     * @return
     */
    @Override
    public PageInfo<Music> selectMusicWithPage(int page, int count, String musicTitle) {
        PageHelper.startPage(page, count);
        List<Music> musicList = musicMapper.selectMusicWithPage(musicTitle);
        PageInfo<Music> pageInfo=new PageInfo<>(musicList);
        return pageInfo;
    }
}
