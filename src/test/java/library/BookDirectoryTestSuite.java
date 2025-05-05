package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookDirectoryTestSuite {

    @Mock
    private LibraryDatabase libraryDatabaseMock;

    private BookLibrary bookLibrary;

    @BeforeEach
    public void setup() {
        bookLibrary = new BookLibrary(libraryDatabaseMock);
    }

    private List<Book> generateListOfNBooks(int booksQuantity) {
        List<Book> resultList = new ArrayList<>();
        for (int n = 1; n <= booksQuantity; n++) {
            Book theBook = new Book("Title " + n, "Author " + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }

    @Test
    void testListBooksWithConditionReturnList() {
        List<Book> resultListOfBooks = new ArrayList<>();
        resultListOfBooks.add(new Book("Secrets of Alamo", "John Smith", 2008));
        resultListOfBooks.add(new Book("Secretaries and Directors", "Dilbert Michigan", 2012));
        resultListOfBooks.add(new Book("Secret life of programmers", "Steve Wolkowitz", 2016));
        resultListOfBooks.add(new Book("Secrets of Java", "Ian Tenewitch", 2010));

        when(libraryDatabaseMock.listBooksWithCondition("Secret")).thenReturn(resultListOfBooks);

        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");

        assertEquals(4, theListOfBooks.size());
    }

    @Test
    void testListBooksWithConditionMoreThan20() {
        List<Book> resultListOf0Books = new ArrayList<>();
        List<Book> resultListOf15Books = generateListOfNBooks(15);
        List<Book> resultListOf40Books = generateListOfNBooks(40);

        when(libraryDatabaseMock.listBooksWithCondition(anyString())).thenReturn(resultListOf15Books);
        when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks")).thenReturn(resultListOf0Books);
        when(libraryDatabaseMock.listBooksWithCondition("FortyBooks")).thenReturn(resultListOf40Books);

        assertEquals(0, bookLibrary.listBooksWithCondition("ZeroBooks").size());
        assertEquals(15, bookLibrary.listBooksWithCondition("Any title").size());
        assertEquals(0, bookLibrary.listBooksWithCondition("FortyBooks").size());
    }

    @Test
    void testListBooksWithConditionFragmentShorterThan3() {
        List<Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("An");

        assertEquals(0, theListOfBooks10.size());
        verify(libraryDatabaseMock, times(0)).listBooksWithCondition(anyString());
    }

    @Test
    void testListBooksInHandsOf_NoBooks() {
        LibraryUser user = new LibraryUser("John", "Smith", "123456789");
        List<Book> resultListOfBooks = new ArrayList<>();

        when(libraryDatabaseMock.listBooksInHandsOf(user)).thenReturn(resultListOfBooks);

        assertEquals(0, bookLibrary.listBooksInHandsOf(user).size());
    }

    @Test
    void testListBooksInHandsOf_OneBook() {
        LibraryUser user = new LibraryUser("John", "Smith", "123456789");
        List<Book> resultListOfBooks = new ArrayList<>();
        resultListOfBooks.add(new Book("Secrets of Java", "Ian Tenewitch", 2010));

        when(libraryDatabaseMock.listBooksInHandsOf(user)).thenReturn(resultListOfBooks);

        assertEquals(1, bookLibrary.listBooksInHandsOf(user).size());
    }

    @Test
    void testListBooksInHandsOf_FiveBooks() {
        LibraryUser user = new LibraryUser("John", "Smith", "123456789");
        List<Book> resultListOfBooks = generateListOfNBooks(5);

        when(libraryDatabaseMock.listBooksInHandsOf(user)).thenReturn(resultListOfBooks);

        assertEquals(5, bookLibrary.listBooksInHandsOf(user).size());
    }
}
