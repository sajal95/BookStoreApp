package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/post")
    public ResponseEntity<ResponseDto> newBookPost(@Valid @RequestBody BookDto bookDto){
        ResponseDto responseDto = new ResponseDto("New book added.", bookService.insert(bookDto));
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAllBooks(){
        ResponseDto responseDto = new ResponseDto("Get all books.",bookService.getAllBooks());
        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getById/{bookId}")
    public ResponseEntity<ResponseDto> getById( @PathVariable int bookId){
        ResponseDto responseDto = new ResponseDto("Book by ID: "+bookId, bookService.getById(bookId));
        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int bookId){
        return bookService.deleteById(bookId);
    }

    @GetMapping("/searchByBookName")
    public ResponseEntity<ResponseDto> searchByBookName(@RequestParam String bookName){
        ResponseDto responseDto = new ResponseDto("Book by Name: " + bookName, bookService.searchByBookName(bookName));
        return new ResponseEntity<>(responseDto,HttpStatus.FOUND);
    }

    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<ResponseDto> updateBookById(@PathVariable int bookId,@Valid @RequestBody BookDto bookDto){
        ResponseDto responseDto = new ResponseDto("Updated Book by Id: " + bookId, bookService.updateBookById(bookId, bookDto));
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PutMapping("/updateQuantity/{bookId}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable int bookId,@RequestParam int quantity){
        return bookService.updateQuantity(bookId, quantity);
    }

    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDto> sortAsc(){
        ResponseDto responseDto = new ResponseDto("Sorting books ascending by bookName",bookService.sortAsc());
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDto> sortDesc(){
        ResponseDto responseDto = new ResponseDto("Sorting books descending by bookName",bookService.sortDesc());
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
