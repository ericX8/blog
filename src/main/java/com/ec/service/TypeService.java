package com.ec.service;

import com.ec.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);
    //分页查询
    Page<Type> listType(Pageable Pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
