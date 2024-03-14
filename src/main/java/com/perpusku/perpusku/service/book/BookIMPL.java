package com.perpusku.perpusku.service.book;

import com.perpusku.perpusku.model.Book;
import com.perpusku.perpusku.payloads.request.BookRequest;
import com.perpusku.perpusku.payloads.response.Response;
import com.perpusku.perpusku.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookIMPL implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Response addBook(BookRequest request) {
        // Buat object entitas Buku
        Book book = new Book();

        // transfer data from request to entity
        book.setTitle(request.getJudul());
        book.setAuthor(request.getPengarang());
        book.setPublisher(request.getPenerbit());
        book.setYear(request.getTahunTerbit());
        book.setIsbn(request.getIsbn());

        // Simpan buku
        book = bookRepository.save(book);

        // Buat object respon
        Response response = new Response();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Success add book");
        response.setData(book);

        // Return OBject Response
        return response;
    }

    @Override
    public Response getBooks() {
        // Buat object list buku yang diisi dari repository find all
        List<Book> books = bookRepository.findAll();

        // Buat object reponse
        Response response = new Response(HttpStatus.OK.value(), "Success", books);
        return response;
    }

    @Override
    public Response getBookById(String uuid) {
        // Buat object buku yang dicari berdasarkan id
        Book book = bookRepository.findById(uuid).orElseThrow(() -> {
            throw new NoSuchElementException("Books is not found");
        });

        return new Response(HttpStatus.OK.value(), "Success", book);
    }

    @Override
    public Response updateBookById(String uuid, BookRequest request) {
        // Cari buku yang mau diperbarui berdasarkan id
        Book book = bookRepository.findById(uuid).orElseThrow(() -> {
            throw new NoSuchElementException("Books is not found");
        });

        // Perbarui data dengan reqquet baru
        book.setTitle(request.getJudul());
        book.setAuthor(request.getPengarang());
        book.setPublisher(request.getPenerbit());
        book.setYear(request.getTahunTerbit());
        book.setIsbn(request.getIsbn());

        // simpan buku
        book = bookRepository.save(book);

        return new Response(HttpStatus.OK.value(), "Success", book);
    }

    @Override
    public Response deleteBookById(String uuid) {
        // Cari buku berdasarkan id nya
        Book book = bookRepository.findById(uuid).orElseThrow(() -> {
            throw new NoSuchElementException("Boks is not found");
        });

        // Hapus buku dengan mengubah status is deleted menjadi true
        book.setIsDeleted(true);

        // SImpan buku
        book = bookRepository.save(book);

        return new Response(HttpStatus.OK.value(), "Succes Deleted Book", null);
    }
}
