package com.datn.service;

import com.datn.dtos.request.SizeRequest;
import com.datn.dtos.response.SizeResponse;
import com.datn.entity.Size;
import com.datn.exception.ServiceException;
import com.datn.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.datn.message.ResponseMessageText.SIZE_NOT_FOUND;

@Service
@AllArgsConstructor
public class SizeService {

    private  final SizeRepository sizeRepository;

    public Page<SizeResponse> findSize(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Size>  sizes = sizeRepository.findAll(pageable);
        List<SizeResponse> sizeResponses = sizes.stream().map(SizeResponse::from).collect(Collectors.toList());
        return new PageImpl<>(sizeResponses,pageable,sizes.getTotalElements());
    }

    public List<SizeResponse> findAllSize() {
        return sizeRepository.findAll()
                .stream()
                .map(SizeResponse::from)
                .collect(Collectors.toList());
    }

    public Size createSize(SizeRequest sizeRequest) {
        Size size = Size.from(sizeRequest);
        return sizeRepository.save(size);
    }

    public Size updateSize(Long id, SizeRequest sizeRequest){
        Size size = sizeRepository.findById(id).orElseThrow(
                ()->new ServiceException(HttpStatus.BAD_REQUEST, SIZE_NOT_FOUND)
        );
        size.setName(sizeRequest.getName());
        size.setCode(sizeRequest.getCode());
        return sizeRepository.save(size);
    }

    public SizeResponse deleteSize(Long id) {
        Size size = sizeRepository.findById(id).orElseThrow(
                ()->new ServiceException(HttpStatus.BAD_REQUEST, SIZE_NOT_FOUND)
        );
        size.setActive(false);
        sizeRepository.save(size);
        return SizeResponse.from(size);
    }
}
