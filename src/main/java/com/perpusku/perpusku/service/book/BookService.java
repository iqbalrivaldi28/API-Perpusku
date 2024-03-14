package com.perpusku.perpusku.service.book;

import com.perpusku.perpusku.payloads.request.BookRequest;
import com.perpusku.perpusku.payloads.response.Response;

public interface BookService {
    Response addBook(BookRequest request);
    Response getBooks();
    Response getBookById(String uuid);
    Response updateBookById(String uuid, BookRequest request);
    Response deleteBookById(String uuid);

}
