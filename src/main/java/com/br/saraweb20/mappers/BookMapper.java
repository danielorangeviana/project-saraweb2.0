package com.br.saraweb20.mappers;

import com.br.saraweb20.dto.BookDTO;
import com.br.saraweb20.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book entity);
    Book toEntity(BookDTO dto);
}
