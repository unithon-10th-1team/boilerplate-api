package com.goofy.boilerplate.common.model.converter;

import com.goofy.boilerplate.common.utils.MapperUtil;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MapConverter implements AttributeConverter<Map<String, Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        return MapperUtil.writeValueAsString(attribute);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        return MapperUtil.readValue(dbData, Map.class);
    }
}
