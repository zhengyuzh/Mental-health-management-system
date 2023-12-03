package com.mentalhealth.project.system.music.service;

import com.github.pagehelper.PageInfo;
import com.mentalhealth.project.system.music.domain.Music;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-08-15
 */
public interface IMusicService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param musicid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Music selectMusicByMusicid(Long musicid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param music 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Music> selectMusicList(Music music);

    /**
     * 新增【请填写功能名称】
     * 
     * @param music 【请填写功能名称】
     * @return 结果
     */
    public int insertMusic(Music music);

    /**
     * 修改【请填写功能名称】
     * 
     * @param music 【请填写功能名称】
     * @return 结果
     */
    public int updateMusic(Music music);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param musicids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMusicByMusicids(String musicids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param musicid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMusicByMusicid(Long musicid);


    /**
     * 添加音乐
     * @param music
     */
    int addMusic(Music music);

    /**
     * 音乐前台 分页展示
     * @param page
     * @param count
     * @param musicTitle
     * @return
     */
    PageInfo<Music> selectMusicWithPage(int page, int count, String musicTitle);
}
