package br.com.lojapesca.lojadepesca.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.IdentityHashMap;
import java.util.Map;
@Component
@Scope("prototype")
public class CycleAvoidingMappingContext {
    private Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return (T) knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put(source, target);
    }
}