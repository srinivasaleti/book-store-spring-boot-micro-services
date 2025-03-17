package com.srinivas.bookstore.catalog.domain;

import com.srinivas.bookstore.catalog.ApplicationProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties properties;


    public ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.properties = applicationProperties;
    }

    public PageResult<ProductEntity> getProducts(int pageNumber) {
        Sort sort = Sort.by("name").ascending();
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        PageRequest pageRequest = PageRequest.of(pageNumber, properties.pageSize(), sort);
        Page<ProductEntity> page = productRepository.findAll(pageRequest);

        return new PageResult<>(
                page.getContent(),
                page.getNumber() + 1,
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious(),
                page.isLast(),
                page.isFirst()
        );
    }

}
