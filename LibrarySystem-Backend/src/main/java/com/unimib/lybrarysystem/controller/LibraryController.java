package com.unimib.lybrarysystem.controller;

import com.unimib.lybrarysystem.DTO.*;
import com.unimib.lybrarysystem.mapper.BookMapper;
import com.unimib.lybrarysystem.mapper.GenreMapper;
import com.unimib.lybrarysystem.model.*;
import com.unimib.lybrarysystem.service.LibraryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService service;

    @Autowired
    private BookMapper bookMapper;

    // ------------------- GET METHODS -------------------

    /**
     * Handles the GET request for the start page.
     * @return A response entity containing a welcome message.
     */
    @GetMapping("/api/startpage/")
    public ResponseEntity<String> showStartPage() {
        return ResponseEntity.ok("{\"message\": \"Welcome to the start page\"}");
    }

    /**
     * Handles the GET request for the sign in page.
     * @return The name of the sign in page view.
     */
    @GetMapping("/api/auth/")
    public ResponseEntity<String> showSignIn() {
        return ResponseEntity.ok("{\"message\": \"Welcome to the sign in page\"}");
    }

    /**
     * Handles the GET request for the new user registration form.
     * @return The name of the new user registration form view.
     */
    @GetMapping("/api/auth/register/")
    public ResponseEntity<String> showRegistrationUser() {
        return ResponseEntity.ok("{\"message\": \"Welcome to the registration page\"}");

    }
    /**
     * Handles the GET request for the home page.
     * @param session The current HTTP session.
     * @return A response entity containing user and book information.
     */
    @GetMapping("/api/homepage/")
    public ResponseEntity<Map<String, Object>> showHomePage(HttpSession session) {

        User actualUser = (User) session.getAttribute("actualUser");
        System.out.println("User info: " + actualUser + " " + actualUser.getLibraryMember());
        if (actualUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not authenticated"));
        }

        List<Book> borrowedBooks = service.findBookByLibraryMember(actualUser.getLibraryMember());
        List<Book> historianBooks = service.findHistoricalBookByLibraryMember(actualUser.getLibraryMember());
        List<Book> listAllBooks = service.findAllBooks();

        List<BookDTO> borrowedBookDTOs = borrowedBooks.stream().map(bookMapper::toDTO).toList();
        List<BookDTO> historianBookDTOs = historianBooks.stream().map(bookMapper::toDTO).toList();
        List<BookDTO> listAllBookDTOs = listAllBooks.stream().map(bookMapper::toDTO).toList();

        Map<String, Object> response = new HashMap<>();

        response.put("user", new UserDTO(actualUser));

        response.put("borrowedBooks", borrowedBookDTOs);
        response.put("historianBooks", historianBookDTOs);
        response.put("listAllBooks", listAllBookDTOs);

        return ResponseEntity.ok(response);
    }


    /**
     * Handles the GET request for the book search page.
     * @return The name of the book search page view.
     */
    @GetMapping("/api/searchBook/")
    public ResponseEntity<String> showSearchBook() {
        return ResponseEntity.ok("{\"message\": \"Welcome to the search books page\"}");
    }

    /**
     * Handles the GET request for the full book search page.
     * Adds a new Book and Author object to the model for the search form.
     * @return The name of the full book search page view.
     */
    @GetMapping("/api/searchBookFull/")
    public ResponseEntity<String> showAdvancedSearchBook() {
        return ResponseEntity.ok("{\"message\": \"Welcome to the search advanced books page\"}");

    }

    /**
     * Handles the GET request for the book details page.
     * Retrieves a book from the repository that matches the provided ISBN and adds it to the model.
     * @param isbn The ISBN of the book to be retrieved.
     * @return The name of the book details page view.
     */
    @GetMapping(value = "/api/detailBooks/{isbn}/")
    public ResponseEntity<BookDTO> getDetailBooks(@PathVariable("isbn") Integer isbn) {

        Book bookRetrieve = service.findBookByISBN(isbn);
        if (bookRetrieve == null) {
            return ResponseEntity.notFound().build();
        }

        BookDTO bookRetrieveDTO = bookMapper.toDTO(bookRetrieve);
        return ResponseEntity.ok(bookRetrieveDTO);
    }

    @GetMapping(value = "/api/detailBooksBorrowed/{isbn}/")
    public ResponseEntity<BookDTO> getDetailBooksBorrowed(@PathVariable("isbn") Integer isbn) {
        Book bookRetrieve = service.findBookByISBN(isbn);
        if (bookRetrieve == null) {
            return ResponseEntity.notFound().build();
        }

        BookDTO bookRetrieveDTO = bookMapper.toDTO(bookRetrieve);
        return ResponseEntity.ok(bookRetrieveDTO);
    }

    // ------------------- POST METHODS -------------------
    /**
     * Handles the POST request for checking user account.
     * @param user The user to check
     * @param session The current HTTP session.
     * @return A response entity containing the result of the operation.
     */
    @PostMapping("/api/auth/checkaccount/")
    @ResponseBody
    public ResponseEntity<?> checkAccount(
            @RequestBody User user, HttpSession session) {
        boolean validCheck = service.checkLoginAccount(user);

        if(validCheck) {

            User actualUser = service.findUser(user);
            session.setAttribute("actualUser", actualUser);

            LibraryMember actualLibraryMember = service.findLibraryMember(actualUser.getLibraryMember());
            session.setAttribute("actualLibraryMember", actualLibraryMember);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("username", actualUser.getUsername());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }

    /**
     * Handles the POST request for saving a new user account.
     * @param accountDTO The account data transfer object containing user and library member information.
     * @param session The current HTTP session.
     * @return A response entity containing the result of the operation.
     */
    @PostMapping("/api/auth/saveaccount/")
    @ResponseBody
    public ResponseEntity<?> addNewAccount(
            @RequestBody AccountDTO accountDTO, HttpSession session) {

        boolean validAccount = service.checkSaveAccount(accountDTO.getUser(), accountDTO.getLibraryMember());

        if (validAccount) {
            User actualUser = service.findUser(accountDTO.getUser());
            LibraryMember actualLibraryMember = service.findLibraryMember(accountDTO.getLibraryMember());

            session.setAttribute("actualUser", actualUser);
            session.setAttribute("actualLibraryMember", actualLibraryMember);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registration successful");
            response.put("username", actualUser.getUsername());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "The user already exists, please try again"));
        }
    }

    /**
     * Handles the POST request for searching books.
     * @return The name of the book list page view.
     */
    @PostMapping("/api/searchBook/check/")
    public ResponseEntity<?> searchBooks(
            @RequestParam(required = false, defaultValue = "false") boolean ebooksOnly,
            @RequestBody BookDTO bookDTO) {

        List<Book> foundBooks;
        if(ebooksOnly) {
            System.out.println("Ebook");
            foundBooks = service.findEBookByAttributes(bookDTO);
        }
        else {
            System.out.println("Book");
            foundBooks = service.findBookByAttributes(bookDTO);
        }

        System.out.println("Found books size: " + foundBooks.size());

        if (foundBooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Your search result generated no matches, please try again"));
        }

        // 🔄 Usa il mapper per convertire
        List<BookDTO> foundBookDTOs = bookMapper.toDTOList(foundBooks);
        List<String> genres = foundBooks.stream()
                .map(b -> b.getGenreList().getName())  // supponendo sia un Genre, cambia se diverso
                .distinct()
                .toList();

        // Costruisci la risposta
        Map<String, Object> response = Map.of(
                "foundBooks", foundBookDTOs,
                "genres", genres
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Handles the POST request for the advanced book search.
     * Retrieves books from the repository that match the provided publisher and author nationality and adds them to the model.
     * @return The name of the book list page view.
     */
    @PostMapping("/api/searchBookAdvanced/check/")
    public ResponseEntity<?> searchBooksAdvanced(
            @RequestBody AdvancedSearchDTO advancedSearchDTO) {

        List<Book> foundBooks = service.findBooksByPublisherAndAuthorNationality(
                advancedSearchDTO.getPublisher(), advancedSearchDTO.getAuthorNationality());

        if (foundBooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Your search result generated no matches, please try again"));
        }

        List<BookDTO> bookDTOs = bookMapper.toDTOList(foundBooks);
        List<String> genres = foundBooks.stream()
                .map(book -> book.getGenreList().getName())
                .distinct()
                .toList();

        Map<String, Object> response = Map.of(
                "foundBooks", bookDTOs,
                "genres", genres
        );

        return ResponseEntity.ok(response);
    }


    /**
     * Handles the POST request for adding a book to a library member.
     * @param session The current HTTP session.
     * @return The name of the redirect view.
     */
    @PostMapping("/api/addBook/{isbn}/")
    public ResponseEntity<String> addBook(@PathVariable("isbn") Integer isbn, HttpSession session) {

        LibraryMember libraryMember = (LibraryMember) session.getAttribute("actualLibraryMember");

        if (libraryMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }

        Book actualBook = service.findBookByISBN(isbn);
        if (actualBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }

        session.setAttribute("actualBook", actualBook);
        service.addLinkBookToLibraryMember(actualBook, libraryMember);

        return ResponseEntity.ok("Book added to user's list");
    }

    /**
     * Handles the POST request for removing a book from a library member.
     * @param isbn The ISBN of the book to remove.
     * @param session The current HTTP session.
     * @return The name of the redirect view.
     */
    @PostMapping("/api/removeBook/{isbn}/")
    public ResponseEntity<String> removeBook(@PathVariable("isbn") Integer isbn, HttpSession session) {

        // Retrieve the actual libraryMember and actual book from the database
        LibraryMember libraryMember = (LibraryMember) session.getAttribute("actualLibraryMember");
        if (libraryMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }

        Book actualBook = service.findBookByISBN(isbn);
        // Remove the book from the library member
        service.removeBookFromLibraryMember(actualBook, libraryMember.getId());

        return ResponseEntity.ok("Book remove to user's list");

    }
}