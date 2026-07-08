package io.github.bookstore.back_end.mapper;

import io.github.bookstore.back_end.model.EntityDTO.BookCreateDTO;
import io.github.bookstore.back_end.model.EntityDTO.BookUpdateDTO;
import io.github.bookstore.back_end.model.Entity.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Book toEntity(BookCreateDTO bookCreateDTO);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(BookUpdateDTO bookUpdateDTO, @MappingTarget Book book);
}
