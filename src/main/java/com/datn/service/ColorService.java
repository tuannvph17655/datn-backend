package com.datn.service;

import com.datn.dtos.request.ColorRequest;
import com.datn.dtos.response.ColorResponse;
import com.datn.entity.Color;
import com.datn.exception.ServiceException;
import com.datn.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.datn.message.ResponseMessageText.CATEGORY_NOT_FOUND;

@Service
@AllArgsConstructor
public class ColorService {
    private final ColorRepository colorRepository;

    public Page<ColorResponse> findColor(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Color> colors = colorRepository.findAll(pageable);
        List<ColorResponse> colorResponses = colors.stream().map(ColorResponse::from).collect(Collectors.toList());
        return new PageImpl<>(colorResponses,pageable,colors.getTotalElements());
    }

    //findColor
    public List<ColorResponse> findAllColor(){
        return colorRepository.findAll()
                .stream()
                .map(ColorResponse::from)
                .collect(Collectors.toList());
    }

    //create
    public Color createColor(ColorRequest colorRequest){
        Color color = Color.from(colorRequest);
        return colorRepository.save(color);
    }

    public ColorResponse findColorById(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(
                () -> new ServiceException(HttpStatus.BAD_REQUEST,CATEGORY_NOT_FOUND));
        return ColorResponse.from(color);
    }

//    public void updateCategory(Long id, CategoryRequest categoryRequest) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(()-> new ServiceException(HttpStatus.BAD_REQUEST,CATEGORY_NOT_FOUND)).from(categoryRequest);
//        categoryRepository.save(category);
//    }

//    public void updateColor(Long id, ColorRequest colorRequest){
//        Color color = colorRepository.findById(id)
//                .orElseThrow(()-> new ServiceException(HttpStatus.BAD_REQUEST,C)
//    }

    //delete
    public ColorResponse deleteColor(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(
                ()->new ServiceException(HttpStatus.BAD_REQUEST, CATEGORY_NOT_FOUND)
        );
        color.setActive(false);
        colorRepository.save(color);
        return ColorResponse.from(color);
    }
}
