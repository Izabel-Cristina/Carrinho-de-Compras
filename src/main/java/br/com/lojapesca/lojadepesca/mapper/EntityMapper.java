package br.com.lojapesca.lojadepesca.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;


public interface EntityMapper <D,E>{
    E toEntity(D dto);

    D toDto(E entity, CycleAvoidingMappingContext context);

    default List<E> toEntity(List<D> dtoList, @Context CycleAvoidingMappingContext context) {
        List<E> entityList = new ArrayList<>();
        if (dtoList != null) {
            dtoList.stream().forEach(dto -> entityList.add(toEntity(dto)));
        }
        return entityList;
    }

    default List<D> toDto(List<E> entityList, @Context CycleAvoidingMappingContext context) {
        List<D> DTOList = new ArrayList<>();
        if (entityList != null) {
            entityList.stream().forEach(entity -> DTOList.add(toDto(entity, new CycleAvoidingMappingContext())));
        }
        return DTOList;
    }
}


