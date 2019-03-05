package com.bksoftware.sellingweb.repository.product;


import com.bksoftware.sellingweb.entities.product.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
