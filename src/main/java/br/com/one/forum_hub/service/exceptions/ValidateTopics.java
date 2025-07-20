package br.com.one.forum_hub.service.exceptions;

import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;

public interface ValidateTopics {
    void validatePost(DataTopicPost data);
    void validateUpdate(DataTopicUpdate data);
}
