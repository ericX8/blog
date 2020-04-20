package com.ec.service;

import com.ec.entity.Blog;
import com.ec.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);


    //通过标题和分类id组合查询
   Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);


    Page<Blog> listBlog(Long tagId,Pageable pageable);

    //最近文章
    List<Blog> listRecentlyBlogTop(Integer size);

    //根据标题、内容搜索
    Page<Blog> listBlog(String Query,Pageable pageable);

    //归档
    Map<String,List<Blog>> archiveBlog();
    //博客条数
    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
