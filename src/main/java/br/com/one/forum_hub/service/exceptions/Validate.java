package br.com.one.forum_hub.service.exceptions;

import br.com.one.forum_hub.DTO.DataCoursePost;
import br.com.one.forum_hub.DTO.DataCourseUpdate;
import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;

public interface Validate {
    void validatePostTopic(DataTopicPost data);
    void validateUpdateTopic(DataTopicUpdate data);
    void validatePostResponse(Long id, String message);
    void validateUpdateResponse(Long id, String message);
    void validatePostCourse(DataCoursePost data);
    void validateUpdateCourse(DataCourseUpdate data);
}
