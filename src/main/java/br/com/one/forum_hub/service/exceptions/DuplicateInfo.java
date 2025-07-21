package br.com.one.forum_hub.service.exceptions;

import br.com.one.forum_hub.DTO.DataCoursePost;
import br.com.one.forum_hub.DTO.DataCourseUpdate;
import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;
import br.com.one.forum_hub.reposity.ReposityCourse;
import br.com.one.forum_hub.reposity.ReposityResponse;
import br.com.one.forum_hub.reposity.ReposityTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateInfo implements Validate {
    @Autowired
    private ReposityTopic reposityTopic;
    @Autowired
    private ReposityResponse reposityResponse;
    @Autowired
    private ReposityCourse reposityCourse;
    @Override
    public void validatePostTopic(DataTopicPost data) {
        boolean duplicateTitle = reposityTopic.existsByTitle(data.title());
        boolean duplicateMessage = reposityTopic.existsByMessage(data.message());
        if (duplicateTitle){
            throw new ValidationException("Erro no envio, este título já existe!");
        }
        if (duplicateMessage){
            throw new ValidationException("Erro no envio, esta mensagem já existe!");
        }
    }
    @Override
    public void validateUpdateTopic(DataTopicUpdate data) {
        boolean duplicateTitle = reposityTopic.existsByTitle(data.title());
        boolean duplicateMessage = reposityTopic.existsByMessage(data.message());
        if (duplicateTitle){
            throw new ValidationException("Erro em atualizar, este título já existe!");
        }
        if (duplicateMessage){
            throw new ValidationException("Erro em atualizar, esta mensagem já existe!");
        }
    }

    @Override
    public void validatePostResponse(Long id, String message) {
        boolean duplicateMessage = reposityResponse.existsByTopicIdAndMessage(id, message);
        if (duplicateMessage){
            throw new ValidationException("Erro no envio, esta mensagem já existe neste tópico!");
        }
    }

    @Override
    public void validateUpdateResponse(Long id, String message) {
        boolean duplicateMessage = reposityResponse.existsByTopicIdAndMessage(id, message);
        if (duplicateMessage){
            throw new ValidationException("Erro em atualizar, esta mensagem já existe neste tópico!");
        }
    }

    @Override
    public void validatePostCourse(DataCoursePost data) {
        boolean duplicateTitle = reposityCourse.existsByTitleAndActiveTrue(data.title());
        if (duplicateTitle){
            throw new ValidationException("Erro ao adicionar, este curso já existe no sistema!");
        }
    }

    @Override
    public void validateUpdateCourse(DataCourseUpdate data) {
        boolean duplicateTitle = reposityCourse.existsByTitleAndActiveTrue(data.title());
        if (duplicateTitle){
            throw new ValidationException("Erro ao atualizar, este curso já existe no sistema!");
        }
    }
}
