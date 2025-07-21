package br.com.one.forum_hub.service;

import br.com.one.forum_hub.DTO.DataResponseList;
import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;
import br.com.one.forum_hub.model.Course;
import br.com.one.forum_hub.model.Topic;
import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.reposity.ReposityTopic;
import br.com.one.forum_hub.service.exceptions.ExceptionData;
import br.com.one.forum_hub.service.exceptions.Validate;
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
    private CourseService courseService;
    @Autowired
    private ReposityTopic reposityTopic;
    @Autowired
    private List<Validate> validateTopics;
    public Topic createTopic(DataTopicPost data) {
        validateTopics.forEach(e -> e.validatePostTopic(data));
        User user = userService.checkUserExists(data.idUser());
        Course course = courseService.checkCourseExistsTopic(data.idCourse());
        Topic topic = new Topic(data, user, course);
        reposityTopic.save(topic);
        return topic;
    }
    public Course checkUpdateTopic(DataTopicUpdate data) {
        validateTopics.forEach(e -> e.validateUpdateTopic(data));
        return courseService.checkCourseExistsTopic(data.idCourse());
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
