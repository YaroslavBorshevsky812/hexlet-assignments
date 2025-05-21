package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                             .map(bookMapper::map)
                             .toList();
    }

    public BookDTO getById(Long id) {
        var book = bookRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return bookMapper.map(book);
    }

    public BookDTO create(BookCreateDTO dto) {
        if (dto.getAuthorId() != null && !authorRepository.existsById(dto.getAuthorId())) {
            throw new ResourceNotFoundException("Author not found");
        }
        var book = bookMapper.map(dto);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO update(Long id, BookUpdateDTO dto) {
        var book = bookRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (dto.getAuthorId().isPresent() && !authorRepository.existsById(dto.getAuthorId().get())) {
            throw new ResourceNotFoundException("Author not found");
        }
        bookMapper.update(dto, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
