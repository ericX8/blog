package com.ec.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "标签名称不能为空")
    private String name;
    @ToString.Exclude
    @ManyToMany(mappedBy = "tag")
    private List<Blog> blogs=new ArrayList<>();
}
