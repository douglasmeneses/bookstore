package io.github.bookstore.back_end.mapper;

import io.github.bookstore.back_end.model.Entity.Publisher;
import io.github.bookstore.back_end.model.EntityDTO.PublisherDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher toEntity(PublisherDTO publisherUpdateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PublisherDTO publisherUpdateDTO, @MappingTarget Publisher publisher);

}
