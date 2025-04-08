package fr.tmsconsult.myproject.topic.service.interfaces;

import fr.tmsconsult.myproject.topic.dto.PostDto;
import fr.tmsconsult.myproject.topic.model.Post;

import java.util.List;

public interface IPostService {
    public List<PostDto> findAllFeeds() ;
    public PostDto getPostById(Long id) ;
    public Post findById(Long id) ;


    public PostDto create(PostDto postDto) ;

    public void createBulk(List<PostDto> postDtoList);
}
