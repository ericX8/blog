package com.ec.service;

import com.ec.entity.Tag;
import com.ec.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);
    //分页查询
    Page<Tag> listTag(Pageable Pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
