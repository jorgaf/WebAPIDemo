package ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi;

import static spark.Spark.*;

import com.google.gson.Gson;
import ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    static Logger logger = Logger.getLogger(App.class.getName());
    public static Gson gson = new Gson();

    public static void main(String[] args) {

        after(((request, response) ->
            response.header("Content-Type", "application/json")
        ));

        get("/books", ((request, response) -> {
            List<Book> books = BibRepository.getBooks();
            logger.log(Level.INFO, books.toString());
            return gson.toJson(books);

        }));

        get("/book/:code", (request, response) -> {
            Optional<Book> optionalBook = BibRepository.getByCode(request.params(":code"));
            if (optionalBook.isPresent()) {
                return gson.toJson(optionalBook.get());
            } else {
                response.status(404);
            }
            return "";
        });

    }
}
