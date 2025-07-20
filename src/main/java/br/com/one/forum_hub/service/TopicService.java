package br.com.one.forum_hub.service;

import br.com.one.forum_hub.DTO.DataResponseList;
import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;
import br.com.one.forum_hub.model.Topic;
import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.reposity.ReposityTopic;
import br.com.one.forum_hub.service.exceptions.ExceptionData;
import br.com.one.forum_hub.service.exceptions.ValidateTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private UserService userService;
    @Autowired
    private ReposityTopic reposityTopic;
    @Autowired
    private List<ValidateTopics> validateTopicsList;
    public Topic createTopic(DataTopicPost data) {
        validateTopicsList.forEach(e -> e.validatePost(data));
        User user = userService.checkUserExistsTopic(data);
        Topic topic = new Topic(data, user);
        reposityTopic.save(topic);
        return topic;
    }
    public void checkUpdateTopic(DataTopicUpdate data) {
        validateTopicsList.forEach(e -> e.validateUpdate(data));
    }
    public Topic checkTopicExistsResponse(DataResponsePost data) {
        Optional<Topic> topic = reposityTopic.findById(data.idTopic());
        if (topic.isPresent()){
            return topic.get();
        } else throw new ExceptionData("Este tópico não existe no sistema!");
    }
    public Page checkResponses(Page<DataResponseList> page, Long id) {
        return (Page) page.stream()
                .filter(r -> r.idTopic().equals(id))
                .toList();
    }
}
