package com.mentalhealth.utils;

import com.mentalhealth.project.system.knowledge.domain.MentalKnowledge;
import com.mentalhealth.project.system.posts.domain.Posts;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 页面数据展示封装类
 */
 @Component
public class Commons {

    /**
     * 返回音乐的播放地址
     */
    public static String music_url(String url) {
         return url;
    }
    /**
     * 网站链接
     *
     * @return
     */
    public static String site_url() {
        return site_url("/page/1");
    }
    /**
     * 返回网站链接下的全址
     *
     * @param sub 后面追加的地址
     * @return
     */
    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    /**
     * 网站配置项
     *
     * @param key
     * @return
     */
    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     *
     * @param key
     * @param defalutValue 默认值
     * @return
     */
    public static String site_option(String key, String defalutValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return defalutValue;
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * 返回日期
     *
     * @return
     */
     public static String dateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
     }

    /**
     * 返回日期 + 时间
     *
     * @return
     */
     public static String dateTimeFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
     }

    /**
     * 返回文章链接地址
     *
     * @param aid
     * @return
     */
    public static String mentalKnowledgePermalink(Long aid) {
        return site_url("/system/knowledge/mentalKnowledge/" + aid.toString());
    }

    /**
     * 返回测试链接地址
     * @param u 链接前缀
     * @param aid id
     * @return
     */
    public static String scalePermalink(String u,Long aid) {

        return site_url("/"+u+"/" + aid.toString());
    }

    /**
     * 返回测试链接地址
     * @param u 链接前缀
     * @param aid id
     * @return
     */
    public static String returnPermalink(String u,Long aid) {

        return site_url("/"+u+"/" + aid.toString());
    }

    /**
     * 返回帖子链接地址
     *
     * @param postsId
     * @return
     */
    public static String postsPermalink(Long postsId) {
        return site_url("/posts/" + postsId.toString());
    }

    /**
     * 截取文章摘要
     *
     * @param mentalKnowledge 文章
     * @param len   要截取文字的个数
     * @return
     */
    public static String intro(MentalKnowledge mentalKnowledge, int len) {
        String value = mentalKnowledge.getMentalKnowledgeContent();
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    }

    /**
     * 截取文章摘要
     *
     * @param posts 文章
     * @param len   要截取文字的个数
     * @return
     */
    public static String introPosts(Posts posts, int len) {
        String value = posts.getPostsContent();
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    }

    /**
     * 截取文章摘要
     *
     * @param value 文章
     * @param len   要截取文字的个数
     * @return
     */
    public static String introScale(String value, int len) {
//        String value = scale.getScaleDetails();
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    } /**
     * 截取文章摘要
     *
     * @param value 文章
     * @param len   要截取文字的个数
     * @return
     */
    public static String introAll(String value, int len) {
//        String value = scale.getScaleDetails();
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    }
    /**
     * 截取文章摘要
     *
     * @param posts 文章
     * @param len   要截取文字的个数
     * @return
     */
    public static String introPostsPostsTitle(Posts posts, int len) {
        String value = posts.getPostsTitle();
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    }

    /**
     * 对文章内容进行格式转换，将Markdown为Html
     * @param value
     * @return  ok
     */
    public static String content(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            return MyUtils.mdToHtml(value);
        }
        return "";
    }

    /**
     * 显示文章缩略图，顺序为：文章第一张图 -> 随机获取
     *
     * @return
     */
    public static String show_thumb(MentalKnowledge mentalKnowledge) {
        if (StringUtils.isNotBlank(mentalKnowledge.getMentalKnowledgeFile())){
            return mentalKnowledge.getMentalKnowledgeFile();
        }
        Long cid = mentalKnowledge.getMentalKnowledgeId();
        Long size = cid % 24;
        size = size == 0 ? 1 : size;
        return "/foreground/user/img/rand/" + size + ".png";
    }

    /**
     * 音乐播放缩略图
     * @return
     */
    public static String show_music(){
        /**
         * 生成一个正整数
         */
        Long number = (long) (100 + Math.random() * (100 - 24 + 1));
        Long size = number % 24;
        size = size == 0 ? 1 : size;
        return "/foreground/user/img/rand/" + size + ".png";

    }

    /**
     * 显示文章缩略图，顺序为：文章第一张图 -> 随机获取
     *
     * @return
     */
    public static String show_thumbPosts(Posts posts) {
        if (StringUtils.isNotBlank(posts.getPostsImage())){
            return posts.getPostsImage();
        }
        Long cid = posts.getPostsId();
        Long size = cid % 24;
        size = size == 0 ? 1 : size;
        return "/foreground/user/img/rand/" + size + ".png";
    }

    /**
     * 这种格式的字符转换为emoji表情
     *
     * @param value
     * @return
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }

}
