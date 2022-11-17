package ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi.util;

import ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi.model.Book;

public class Mapper {
    public static Book toBook(int id, String code, String title, int year) {
        return new Book(id, code, title, year);
    }
}