package br.com.one.forum_hub.service.exceptions;

import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataResponseUpdate;
import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataTopicUpdate;
import br.com.one.forum_hub.reposity.ReposityResponse;
import br.com.one.forum_hub.reposity.ReposityTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateInfo implements ValidateTopics, ValidateResponse {
    @Autowired
    private ReposityTopic reposityTopic;
    @Autowired
    private ReposityResponse reposityResponse;
    @Override
    public void validatePost(DataTopicPost data) {
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
    public void validateUpdate(DataTopicUpdate data) {
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
    public void validatePost(DataResponsePost data) {
        boolean duplicateMessage = reposityTopic.existsByMessage(data.message());
        if (duplicateMessage){
            throw new ValidationException("Erro no envio, esta mensagem já existe!");
        }
    }
    @Override
    public void validateUpdate(DataResponseUpdate data) {
        boolean duplicateMessage = reposityTopic.existsByMessage(data.message());
        if (duplicateMessage){
            throw new ValidationException("Erro em atualizar, esta mensagem já existe!");
        }
    }
}
