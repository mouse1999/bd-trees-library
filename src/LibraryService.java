import model.Book;
import org.apache.commons.lang3.NotImplementedException;
import treestructure.BookNode;

import java.util.Stack;

/**
 * Application to test traversing Binary Trees and Binary Search Trees.
 *
 * Represents a Library with a collection of books.
 */
public class LibraryService {

    /**
     * The root node of our tree of Books.
     * Assume this tree is sorted by ISBN.
     * The tree and its nodes should not be modified by our application.
    */
    private final BookNode books;

    /**
     * Constructs our library with a default tree of books.
     * Assume this tree is sorted by ISBN.
     *
     * @param books The root node of a tree of Books
     */
    public LibraryService(final BookNode books) {
        this.books = books;
    }

    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given ISBN.
     *
     * @param isbn A given ISBN to search our library for
     * @return True if a book with the given ISBN is in our library and
     *         false otherwise
     */
    public boolean isBookInLibraryByIsbn(String isbn) {

        if(books == null || isbn == null){
            return false;

        }

        BookNode p = books;

        while (p != null) {
            int comparison = isbn.compareTo(p.getBook().getIsbn());

            if (comparison == 0) {
                return true;
            } else if (comparison < 0) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }
        return false;
    }


    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given Title and Author.
     *
     * @param title A given title to search our library for, alongside an author's name
     * @param author The name of a given author to search our library for, alongside a title
     * @return True if a book with the given title and author is in our library, and
     *         false otherwise
     */
    public boolean isBookInLibraryByTitleAndAuthor(String title, String author) {
        if (books == null) return false;

        Stack<BookNode> stack = new Stack<>();
        stack.push(books); // Start from the root

        while (!stack.isEmpty()) {
            BookNode current = stack.pop();
            Book book = current.getBook();

            // Check if this is the book we are looking for
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                return true;
            }

            // Push children onto the stack (right first so left is processed first)
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }

        return false; // Book not found
    }
}
