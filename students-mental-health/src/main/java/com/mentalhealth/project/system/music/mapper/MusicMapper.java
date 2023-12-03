package com.mentalhealth.project.system.music.mapper;

import com.mentalhealth.project.system.music.domain.Music;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-15
 */
public interface MusicMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param musicid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMusicByMusicid(Long musicid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param musicids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMusicByMusicids(String[] musicids);

    /**
     * 添加音乐
     * @param music
     * @return
     */
    int addMusice(Music music);

    /**
     * 前台音乐分页
     * @param musicTitle
     * @return
     */
    List<Music> selectMusicWithPage(String musicTitle);
}
