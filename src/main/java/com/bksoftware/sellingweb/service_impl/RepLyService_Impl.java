package com.bksoftware.sellingweb.service_impl;


import com.bksoftware.sellingweb.entities.Reply;
import com.bksoftware.sellingweb.repository.ReplyRepository;
import com.bksoftware.sellingweb.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RepLyService_Impl implements ReplyService {

    private static final Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());

    @Autowired
    ReplyRepository replyRepository;

    @Override
    public List<Reply> findAllReplies() {
        try {
            return replyRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-reply-error : {0}");
        }
        return null;
    }

    @Override
    public Reply saveReply(Reply reply) {
        try {
            return replyRepository.save(reply);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-reply-error : {0}", ex.getMessage());
        }
        return null;
    }
}
