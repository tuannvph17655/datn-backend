package com.datn.repository;

import com.datn.dto.admin.category.CategoryDto;
import com.datn.dto.admin.category.CategoryRes;
import com.datn.dto.admin.category.CategoryRes4Admin;
import com.datn.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    CategoryEntity findByIdAndActive(String id, Boolean active);

    Boolean existsByIdAndActive(String id, Boolean active);

    Boolean existsByNameIgnoreCaseAndActiveAndIdNot(String trim, Boolean aTrue, String id);

    Boolean existsByNameIgnoreCaseAndActive(String toLowerCase, Boolean aTrue);

    CategoryEntity findByNameIgnoreCaseAndActive(String categoryName, Boolean aTrue);

    @Query("select new com.datn.dto.customer.suggest.CategoryDto(o.id, o.name) from CategoryEntity o where o.active = true order by o.name")
    List<CategoryDto> findSuggestCategories();

    @Query("select c from CategoryEntity c where c.active = true order by c.name")
    List<CategoryEntity> noPage();

    @Query("SELECT c FROM CategoryEntity c\n" +
            "WHERE (UNACCENT(UPPER(c.name)) LIKE CONCAT('%', UNACCENT(:textSearch), '%')\n" +
            "OR UNACCENT(UPPER(c.des)) LIKE CONCAT('%', UNACCENT(:textSearch), '%'))\n" +
            "AND (:active IS NULL OR c.active = :active)")
    Page<CategoryEntity> search(@Param("textSearch") String textSearch,
                                @Param("active") Boolean active, Pageable pageable);

    @Query("SELECT new com.datn.dto.admin.category.CategoryRes4Admin(o.id, o.name) from CategoryEntity o where o.active = true")
    List<CategoryRes4Admin> findAllCategoryActive();
}
