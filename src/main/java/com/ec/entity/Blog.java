package com.ec.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * jpa会自动帮我们建表*/

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_blog")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Blog {
    @Id //主键
    @GeneratedValue  //id自动增加
    private Long id;
    @Transient //单独属性，不如数据库
    private String tagIds;//tagId集合
    private String archives;

    private String title; //标题
   /* @Basic(fetch = FetchType.LAZY) //需要的时候才加载lob,不需要时不加载（懒加载）
    @Lob  //声明字符长度*/
    private String content;//博客内容
    private String description; //博客描述
    private String firstPicture;//首图
    private String flag;//原创标记
    private Integer views;//浏览量
    private boolean appreciation;//赞赏开关
    private boolean shareStatement;//转载声明
    private boolean published;//是否发布
    private boolean commentabled;//评论开关
//    private boolean recommend;//是否推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;//更新时间
    @ManyToOne
    private  Type type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tag=new ArrayList<>();
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments=new ArrayList<>();

    public void init(){
        this.tagIds=tagsToIds(this.getTag());
    }
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            for (Tag tag : tags) {
                ids.append(tag.getId());
                ids.append(",");
                }
            String tagsStr=ids.toString();
            tagsStr=tagsStr.substring(0, tagsStr.length()-1);
            return tagsStr;
        }
        else {
            return tagIds;
        }
    }
}
