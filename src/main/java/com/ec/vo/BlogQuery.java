package com.ec.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogQuery {
    private String title;
    private Long typeId;
}
