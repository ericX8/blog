package com.ec.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private boolean adminComment;//是否管理员
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ToString.Exclude
    @ManyToOne
    private Blog blog;
    @ToString.Exclude
    @OneToMany(mappedBy = "parentComments")
    private List<Comment> replyComments=new ArrayList<>();
    @ToString.Exclude
    @ManyToOne
    private Comment parentComments;

}
