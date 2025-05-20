package com.unimib.lybrarysystem.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.unimib.lybrarysystem.DTO.BookDTO;
import com.unimib.lybrarysystem.model.Author;
import com.unimib.lybrarysystem.model.Book;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface BookMapper {

    @Mapping(source = "ISBN", target = "isbn") // Facoltativo se i nomi coincidono
    @Mapping(source = "genreList", target = "genre")
    BookDTO toDTO(Book book);

    Book toEntity(BookDTO dto);

    List<BookDTO> toDTOList(List<Book> books);

    List<Book> toEntityList(List<BookDTO> dtos);

    default Set<String> mapStringAuthors(Set<Author> authors) {
        return authors.stream()
                .map(Author::getName)
                .collect(Collectors.toSet());
    }

    default Set<Author> mapAuthors(Set<String> authors) {
        return authors.stream()
                .map(name -> {
                    final Author author = new Author();
                    author.setName(name);
                    return author;
                })
                .collect(Collectors.toSet());
    }
}

