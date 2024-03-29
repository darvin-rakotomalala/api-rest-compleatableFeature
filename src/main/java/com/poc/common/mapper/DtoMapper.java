package com.poc.common.mapper;

import java.util.List;

public interface DtoMapper<D, E> {

    E toDO(D dto);

    D toDTO(E entity);

    List<E> toDO(List<D> dtoList);

    List<D> toDTO(List<E> entityList);

    /*default HelpPage<D> toDTO(Page<E> enityPage, Pageable pageable) {
        List<D> dtoList = toDTO(enityPage.getContent());
        return new HelpPage<>(dtoList, pageable, enityPage.getTotalElements());
    }*/

}