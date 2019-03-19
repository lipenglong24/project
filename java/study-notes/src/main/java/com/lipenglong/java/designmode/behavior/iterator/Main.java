package com.lipenglong.java.designmode.behavior.iterator;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("红楼梦"));
        bookShelf.appendBook(new Book("西游记"));
        bookShelf.appendBook(new Book("三国演义"));
        bookShelf.appendBook(new Book("水浒传"));

        Iterator<Book> iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book.getName());
        }
    }
}
