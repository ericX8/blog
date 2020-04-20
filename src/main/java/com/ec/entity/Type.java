package com.ec.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<>();
}
