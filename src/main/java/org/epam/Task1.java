package org.epam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Task: find books with page count > 150. Print Books Names and Authors full names at all these books.
public class Task1 {

    public static void main(String[] args) {
        List<Book> books = setBooksData();

        //Solution:
        System.out.println("SOLUTION");
        books.stream().filter(book -> book.getPageCount() > 150)
                .forEach(Task1::concatBookNameAndAuthorsFullNames);
    }
    private static void concatBookNameAndAuthorsFullNames(Book book) {
        System.out.println(String.format("Book name: '%s'; Authors [%s]",
                book.getName(),
                book.getAuthorsList().stream()
                        .map(author -> author.getFullName() + ", ")
                        .reduce("", String::concat)
                        .replaceFirst("(, )$", "")));
    }

    private static List<Book> setBooksData() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Algebra. Book", new Date(), 221,
                List.of(
                        new Author("Vasyl Kravchuk", new Date(1900, 10, 10)),
                        new Author("Mable Pidruchko", new Date(2000, 10, 10)),
                        new Author("Galyna Yanchenko", new Date(1950, 1, 1)))));
        books.add(new Book("Practice on Grammar", new Date(), 270,
                List.of(
                        new Author("Biduk T.T.", new Date(2010, 10, 10)))));
        books.add(new Book("Cafe on Unity", new Date(), 150,
                List.of(
                        new Author("Jack B. Stradox", new Date(2020, 2, 2)))));
        return books;
    }

    public static class Author {

        private String fullName;
        private Date BirthDate;
        public Author(String fullName, Date birthDate) {
            this.fullName = fullName;
            BirthDate = birthDate;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Date getBirthDate() {
            return BirthDate;
        }

        public void setBirthDate(Date birthDate) {
            BirthDate = birthDate;
        }

    }

    public static class Book {

        private String name;
        private Date creationDate;
        private int pageCount;
        private List<Author> authorsList;
        public Book(String name, Date creationDate, int pageCount, List<Author> authorsList) {
            this.name = name;
            this.creationDate = creationDate;
            this.pageCount = pageCount;
            this.authorsList = authorsList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<Author> getAuthorsList() {
            return authorsList;
        }

        public void setAuthorsList(List<Author> authorsList) {
            this.authorsList = authorsList;
        }

    }

}
