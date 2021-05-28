package com.gft.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.delivery.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
