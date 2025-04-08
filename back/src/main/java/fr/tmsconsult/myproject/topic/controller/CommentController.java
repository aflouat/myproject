package fr.tmsconsult.myproject.topic.controller;

import fr.tmsconsult.myproject.topic.dto.CommentDto;
import fr.tmsconsult.myproject.topic.service.interfaces.ICommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @PostMapping
    public ResponseEntity<?> comment(@Valid @RequestBody CommentDto commentDto) {
            commentService.commentToPost(commentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok().body(commentService.getAllCommentsByPostId(postId));
    }
}