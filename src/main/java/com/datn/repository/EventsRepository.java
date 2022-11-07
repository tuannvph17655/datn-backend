package com.datn.repository;

import com.datn.dto.customer.event.EventsResponse;
import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventsRepository extends JpaRepository<EventEntity,Long> {

	@Query("select DISTINCT new com.datn.dto.customer.event.response.EventsResponse(" +
			"e.id,\n" +
			"e.name)\n" +
			"e.status)\n" +
			"e.startDate)\n" +
			"e.endDate)\n" +
			"from EventEntity e")
	List<EventsResponse> getAllEvent();
}
