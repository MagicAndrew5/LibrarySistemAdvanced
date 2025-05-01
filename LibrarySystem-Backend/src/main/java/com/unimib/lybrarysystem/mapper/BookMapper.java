package com.unimib.lybrarysystem.mapper;

import com.unimib.lybrarysystem.DTO.BookDTO;
import com.unimib.lybrarysystem.model.Author;
import com.unimib.lybrarysystem.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
        BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

        BookDTO toDTO(Book book);
        Book toEntity(BookDTO dto);

        default Set<String> mapStringAuthors(Set<Author> authors) {
            return authors.stream()
                    .map(Author::getName)
                    .collect(Collectors.toSet());
        }

        default Set<Author> mapAuthors(Set<String> authors) {
            return authors.stream()
                    .map(name -> {
                        Author author = new Author();
                        author.setName(name);
                        return author;
                    })
                    .collect(Collectors.toSet());
        }


        List<BookDTO> toDTOList(List<Book> books);
        List<Book> toEntityList(List<BookDTO> dtos);
}