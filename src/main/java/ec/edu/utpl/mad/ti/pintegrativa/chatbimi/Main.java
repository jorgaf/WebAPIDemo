package ec.edu.utpl.mad.ti.pintegrativa.chatbimi;

import com.google.gson.Gson;
import ec.edu.utpl.mad.ti.pintegrativa.chatbimi.model.Book;
import ec.edu.utpl.mad.ti.pintegrativa.chatbimi.repositories.BibRepository;

import java.util.List;
import java.util.Optional;

import static spark.Spark.*;

public class Main {
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        after(((request, response) ->
                response.header("Content-Type", "application/json"))
        );

        get("/books", (req, res) -> {
            List<Book> books = BibRepository.getBooks();
            if(books.isEmpty()) {
                res.status(404);
                return "";
            } else {
                System.out.println(books);
                return gson.toJson(books);
            }
        });

        get("/book/:code", (req, res) -> {
            Optional<Book> optionalBook = BibRepository.getBookByCode(req.params(":code"));
            if(optionalBook.isPresent()) {
                return gson.toJson(optionalBook.get());
            } else {
                res.status(404);
            }
            return "";
        });
    }
}