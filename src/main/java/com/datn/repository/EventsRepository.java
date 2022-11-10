package com.datn.repository;

import com.datn.dto.customer.event.EventsResponse;
import com.datn.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventsRepository extends JpaRepository<EventEntity,String> {

//	@Query("Select e From EventEntity e")
@Query("select DISTINCT new com.datn.dto.customer.event.EventsResponse(" +
		"e.id,\n" +
		"e.name,\n" +
		"e.status,\n" +
		"e.startDate,\n" +
		"e.endDate)\n" +
		"from EventEntity e")
	List<EventsResponse> getAllEvent();

//@Query("Select e from EventEntity e\n" +
//		"Where (Unaccent(upper(e.name)) Like Concat('%', unaccent(:textSearch), '%')\n" +
//		"or unaccent(upper(e.status)) Like Concat('%', Unaccent(:textSearch), '%'))\n")
//	Page<EventEntity> serch(@Param("textSearch"));
//)

}
