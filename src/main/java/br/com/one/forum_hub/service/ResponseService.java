package br.com.one.forum_hub.service;

import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataResponseUpdate;
import br.com.one.forum_hub.model.ResponseT;
import br.com.one.forum_hub.model.Topic;
import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.reposity.ReposityResponse;
import br.com.one.forum_hub.service.exceptions.ValidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResponseService {
    @Autowired
    private ReposityResponse reposityResponse;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    List<ValidateResponse> validateResponses;
    public ResponseT createResponse(DataResponsePost data) {
        validateResponses.forEach(v -> v.validatePost(data));
        User user = userService.checkUserExistsResponse(data);
        Topic topic = topicService.checkTopicExistsResponse(data);
        ResponseT response = new ResponseT(data, user, topic);
        reposityResponse.save(response);
        return response;
    }
    public void checkUpdateResponse(DataResponseUpdate data) {
        validateResponses.forEach(v -> v.validateUpdate(data));
    }
}
