package fr.tmsconsult.myproject.topic.controller;

import fr.tmsconsult.myproject.topic.dto.PostDto;

import fr.tmsconsult.myproject.topic.payload.response.MessageResponse;
import fr.tmsconsult.myproject.topic.service.interfaces.IPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController  {
    private final IPostService postService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PostDto postDto) {
            PostDto savedPostDto = postService.create(postDto );
        return ResponseEntity.ok().body(savedPostDto);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createBulk(@Valid @RequestBody List<PostDto> postDtoList) {
        postService.createBulk(postDtoList );
        return ResponseEntity.ok().body(new MessageResponse("Post created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllFeeds() {
        return ResponseEntity.ok().body(postService.findAllFeeds());
    }
}