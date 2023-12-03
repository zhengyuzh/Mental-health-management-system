package com.mentalhealth.project.system.music.domain;

import com.mentalhealth.framework.aspectj.lang.annotation.Excel;
import com.mentalhealth.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 sys_music
 * 
 * @author ruoyi
 * @date 2023-08-15
 */
public class Music extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 歌曲id */
    private Long musicid;

    /** 歌曲名称 */
    @Excel(name = "歌曲名称")
    private String title;

    /** 歌手 */
    @Excel(name = "歌手")
    private String singer;

    /** 存放歌曲的路径 */
    @Excel(name = "存放歌曲的路径")
    private String url;


    public void setMusicid(Long musicid)
    {
        this.musicid = musicid;
    }

    public Long getMusicid()
    {
        return musicid;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setSinger(String singer)
    {
        this.singer = singer;
    }

    public String getSinger()
    {
        return singer;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("musicid", getMusicid())
            .append("title", getTitle())
            .append("singer", getSinger())
            .append("createTime", getCreateTime())
            .append("url", getUrl())
            .toString();
    }
}
