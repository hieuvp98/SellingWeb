package com.bksoftware.sellingweb.service;


import com.bksoftware.sellingweb.entities.Reply;

import java.util.List;

public interface ReplyService {

    List<Reply> findAllReplies();

    Reply saveReply(Reply reply);
}
