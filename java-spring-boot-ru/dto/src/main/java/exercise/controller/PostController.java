package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<PostDTO> index() {
        return postRepository.findAll().stream()
                             .map(this::convertToDto)
                             .toList();
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return convertToDto(post);
    }

    private PostDTO convertToDto(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());

        List<CommentDTO> comments = commentRepository.findByPostId(post.getId()).stream()
                                                     .map(comment -> {
                                                         CommentDTO commentDto = new CommentDTO();
                                                         commentDto.setId(comment.getId());
                                                         commentDto.setBody(comment.getBody());
                                                         return commentDto;
                                                     })
                                                     .toList();

        dto.setComments(comments);
        return dto;
    }
}
