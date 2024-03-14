package com.perpusku.perpusku.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
    private String judul;
    private String pengarang;
    private String penerbit;
    private String tahunTerbit;
    private String isbn;
}
