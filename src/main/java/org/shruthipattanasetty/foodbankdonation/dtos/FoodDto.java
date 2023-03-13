package org.shruthipattanasetty.foodbankdonation.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDto {
    private String message;
    private String status;
    private FoodDto foodDto;
}
