package fr.tmsconsult.myproject.topic.impl.service.impl;

import fr.tmsconsult.myproject.topic.dto.CommentDto;
import fr.tmsconsult.myproject.topic.dto.PostDto;
import fr.tmsconsult.myproject.topic.mapper.CommentMapper;
import fr.tmsconsult.myproject.topic.mapper.PostMapper;
import fr.tmsconsult.myproject.topic.model.Post;
import fr.tmsconsult.myproject.topic.model.Comment;
import fr.tmsconsult.myproject.topic.model.Topic;
import fr.tmsconsult.myproject.security.model.User;
import fr.tmsconsult.myproject.topic.repository.CommentRepository;
import fr.tmsconsult.myproject.topic.service.impl.CommentService;
import fr.tmsconsult.myproject.topic.service.impl.PostService;
import fr.tmsconsult.myproject.security.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentService commentService;
    @Mock
    private PostService postService;
    private Comment comment;
    private Post post;
    private PostDto postDto;
    private User user;
    @Mock
    PostMapper postMapper;
    @Mock
    CommentMapper commentMapper;
    @Mock
    UserService userService;

    private CommentDto commentDto;

    @BeforeEach
    void setUp() {
        commentService = new CommentService(commentRepository, postService,commentMapper, userService);
        user = User.builder().username("Doe").email("test@gmail.com").password("pwd").build();
        Topic topic = Topic.builder().subject("Test Topic").build();

        post = Post.builder().id(1L).topic(topic).title("Tutorial JUnit").
                    content("Avec Maven ou  gradle, JUnit peut être facilement ajouté à votre projet")
                    .author(user).build();

        postDto = PostDto.builder().topicSubject(topic.getSubject()).title("Tutorial JUnit").
                content("Avec Maven ou  gradle, JUnit peut être facilement ajouté à votre projet")
                .build();
        commentDto = CommentDto.builder().content("Le TDD m'intéresse!")
                .username(user.getUsername()).postId(1L).build();
        comment = comment.builder().id(1L).content("Le TDD m'intéresse!").build();
    }

    @Test
    public void testCommentToPost() {
        Comment expectedComment = Comment.builder().content("Le TDD m'intéresse!").post(post).build();
        when(postService.findById(1L)).thenReturn(post);
        when(commentMapper.toEntity(commentDto)).thenReturn(expectedComment);
        when(commentRepository.save(expectedComment)).thenReturn(expectedComment);
        commentService.commentToPost(commentDto);
        verify(commentRepository,times(1)).save(expectedComment);
        Comment commentToBeSaved = commentMapper.toEntity(commentDto);

    }

    @Test
    public void testGetCommentsByPost_NoComment(){
        //should return empty list

        List<CommentDto> expectedComments = new ArrayList<>();
        //                List<CommentDto> commentDtoList = commentMapper.toDtoList(comments);
        when(commentRepository.findByPostId(1L)).thenReturn(new ArrayList<>());
//        when(commentMapper.toDtoList(new ArrayList<>())).thenReturn(new ArrayList<>());
        List<CommentDto> actualComments = commentService.getAllCommentsByPostId(1L);
        Assertions.assertEquals(expectedComments, actualComments);


    }

    @Test
    public void testGetCommentsByPost_OneComment(){
        //should return a list with 1 el

        List<CommentDto> expectedComments = List.of(commentDto);
        when(commentRepository.findByPostId(1L)).thenReturn(List.of(comment));
        when(commentMapper.toDto(comment)).thenReturn(commentDto);
        List<CommentDto> actualComments = commentService.getAllCommentsByPostId(1L);
        Assertions.assertEquals(expectedComments, actualComments);


    }
}
