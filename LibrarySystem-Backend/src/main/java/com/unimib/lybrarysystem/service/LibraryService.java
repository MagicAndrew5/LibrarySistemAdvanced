package com.unimib.lybrarysystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unimib.lybrarysystem.DTO.BookDTO;
import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.LibraryMember;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.repository.BookRepository;
import com.unimib.lybrarysystem.repository.LibraryMemberRepository;
import com.unimib.lybrarysystem.repository.UserRepository;

@Service
public class LibraryService {

    private final UserRepository userRepo;

    private final BookRepository bookRepo;
    
    private final LibraryMemberRepository libraryMemberRepo;

    public LibraryService(UserRepository userRepo, BookRepository bookRepo, LibraryMemberRepository libraryMemberRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.libraryMemberRepo = libraryMemberRepo;
    }

    public boolean checkSaveAccount(User user, LibraryMember libraryMember) {
        final User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            return false;
        } else {
            libraryMember.setName(user.getName());
            libraryMember.setSurname(user.getSurname());

            final Random random = new Random();
            final int randomId = random.nextInt(900000000) + 100000000;
            libraryMember.setId(randomId);

            libraryMember.setMembershipDate(LocalDate.now().toString());

            user.setLibraryMember(libraryMember);

            libraryMemberRepo.save(libraryMember);
            userRepo.save(user);
            return true;
        }
    }

    public boolean checkLoginAccount(User user) {
        if (userRepo.findByUsernamePassword(user.getUsername(), user.getPassword()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addLinkBookToLibraryMember(Book book, LibraryMember libraryMember) {
        book.getBorrowingMembers().add(libraryMember);
        book.getHistorianMembers().add(libraryMember);
        bookRepo.save(book);
    }

    @Transactional
    public void removeBookFromLibraryMember(Book actualBook, Integer libraryMemberId) {
        final LibraryMember libraryMember = libraryMemberRepo.findLibraryMemberWithBorrowedBooksById(libraryMemberId);
        libraryMember.removeBorrowedBook(actualBook);
        libraryMemberRepo.save(libraryMember);
    }

    public User findUser(User user) {
        return userRepo.findByUsernamePassword(user.getUsername(), user.getPassword());
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public LibraryMember findLibraryMember(LibraryMember libraryMember) {
        return libraryMemberRepo.findLibraryMemberById(libraryMember.getId());
    }

    public Book findBook(Book book) {
        return bookRepo.findByISBN(book.getISBN());
    }

    public List<Book> findAllBooks() {
        return bookRepo.findAllBooks();
    }

    public List<Book> findBookByAttributes(BookDTO book) {
        return bookRepo.findBookByAttributes(book.getIsbn(), book.getAuthor(), book.getTitle());
    }

    public List<Book> findEBookByAttributes(BookDTO book) {
        return bookRepo.findEBookByAttributes(book.getIsbn(), book.getAuthor(), book.getTitle());
    }

    public List<Book> findBookByLibraryMember(LibraryMember libraryMember) {
        return bookRepo.findBookByLibraryMember(libraryMember);
    }

    public List<Book> findHistoricalBookByLibraryMember(LibraryMember libraryMember) {
        return bookRepo.findHistoricalBookByLibraryMember(libraryMember);
    }

    public Book findBookByISBN(Integer isbn) {
        return bookRepo.findByISBN(isbn);
    }

    public List<Book> findBooksByPublisherAndAuthorNationality(String publisher, String nationality) {
        return bookRepo.findBooksByPublisherAndAuthorNationality(publisher, nationality);
    }
}
