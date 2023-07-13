package com.fastcampus.toy3.domain.post;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category attribute) {
        return attribute.getDescription();
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        return Category.fromDescription(dbData);
    }
}
