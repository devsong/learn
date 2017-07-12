package com.gzs.learn.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.gzs.learn.elasticsearch.domain.JkxOrder;

public interface JkxRepository extends ElasticsearchCrudRepository<JkxOrder, String> {

}
