package com.flickspick.common.model.converter;

import com.flickspick.common.utils.MapperUtil;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MapConverter implements AttributeConverter<Map<Long, Long>, String> {
    @Override
    public String convertToDatabaseColumn(Map<Long, Long> attribute) {
        return MapperUtil.writeValueAsString(attribute);
    }

    @Override
    public Map<Long, Long> convertToEntityAttribute(String dbData) {
        return MapperUtil.readValue(dbData, Map.class);
    }
}
