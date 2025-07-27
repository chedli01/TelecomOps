package com.coding.internship.generic;


public interface GenericMapper <E,D>{
    D mapToDto(E entity);
    E mapToEntity(D dto);
}
