package br.com.one.forum_hub.service.exceptions;

import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataResponseUpdate;
import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;

public interface ValidateResponse {
    void validatePost(DataResponsePost data);

    void validateUpdate(DataResponseUpdate data);
}
