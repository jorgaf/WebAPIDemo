package ec.edu.utpl.mad.ti.pintegrativa.chatacadbimi.model;

public class Book {
    private final int id;
    private String code;
    private String title;
    private int year;

    public Book(int id, String code, String title, int year) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        String pattern = "{id = %d, code = %s, title = %s, year = %d}";
        return String.format(pattern, id, code, title, year);
    }
}