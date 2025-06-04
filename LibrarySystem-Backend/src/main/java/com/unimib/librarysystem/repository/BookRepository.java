package com.unimib.librarysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unimib.librarysystem.model.Book;
import com.unimib.librarysystem.model.LibraryMember;

/**
 * The repository for the book entity.
 * This interface extends CrudRepository and provides methods to perform CRUD operations on the book entity.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    /**
     * Finds books by ISBN, author, and title (all optional).
     */
    @Query("SELECT b FROM Book b " +
           "WHERE (:isbn IS NULL OR b.ISBN = :isbn)" +
             "AND (:author IS NULL OR LOWER(b.author) = LOWER(:author))" +
             "AND (:title IS NULL OR LOWER(b.title) = LOWER(:title))")
    List<Book> findBookByAttributes(@Param("isbn") Integer isbn,
                                    @Param("author") String author,
                                    @Param("title") String title);

    /**
     * Finds ebooks by optional attributes.
     */
    @Query("SELECT b FROM Book b WHERE TYPE(b) = EBook " +
             "AND (:isbn IS NULL OR b.ISBN = :isbn)" +
             "AND (:author IS NULL OR b.author = :author) " +
             "AND (:title IS NULL OR b.title = :title)")
    List<Book> findEBookByAttributes(@Param("isbn") Integer isbn,
                                     @Param("author") String author,
                                     @Param("title") String title);

    /**
     * Finds book by ISBN.
     */
    @Query("SELECT b FROM Book b WHERE b.ISBN = :isbn")
    Book findByISBN(@Param("isbn") Integer isbn);

    /**
     * Gets all books.
     */
    @Query("SELECT b from Book b")
    List<Book> findAllBooks();

    /**
     * Gets books currently borrowed by a member.
     */
    @Query("SELECT b FROM Book b WHERE :libraryMember MEMBER OF b.borrowingMembers")
    List<Book> findBookByLibraryMember(LibraryMember libraryMember);

    /**
     * Gets previously borrowed books.
     */
    @Query("SELECT b FROM Book b WHERE :libraryMember MEMBER OF b.historianMembers")
    List<Book> findHistoricalBookByLibraryMember(LibraryMember libraryMember);

    /**
     * Finds books by publisher and author's nationality.
     */
    @Query(" SELECT b FROM Book b JOIN b.authors a " +
           " WHERE b.publisher = :publisher AND a.nationality = :nationality ")
    List<Book> findBooksByPublisherAndAuthorNationality(String publisher, String nationality);
}
