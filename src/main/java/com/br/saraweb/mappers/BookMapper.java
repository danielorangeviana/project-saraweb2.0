package com.br.saraweb.mappers;

import com.br.saraweb.dto.BookDTO;
import com.br.saraweb.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(Book entity);

    @Mapping(target = "pages", ignore = true)
    Book toEntity(BookDTO dto);
}