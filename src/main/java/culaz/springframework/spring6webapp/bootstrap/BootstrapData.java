package culaz.springframework.spring6webapp.bootstrap;

import culaz.springframework.spring6webapp.domain.Author;
import culaz.springframework.spring6webapp.domain.Book;
import culaz.springframework.spring6webapp.repositories.AuthorRepository;
import culaz.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author ron = new Author();
        ron.setFirstName("Ron");
        ron.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author ronSaved = authorRepository.save(ron);
        Book noEJBSAved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        ronSaved.getBooks().add(noEJBSAved);

        authorRepository.save(ericSaved);
        authorRepository.save(ronSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
    }
}
