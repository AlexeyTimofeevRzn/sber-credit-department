package com.example.sbercreditdepartment.utils.mapper;

import com.example.sbercreditdepartment.dto.GenericDTO;
import com.example.sbercreditdepartment.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDTOs(List<E> entities);
}
