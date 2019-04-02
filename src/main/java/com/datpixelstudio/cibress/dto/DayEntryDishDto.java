package com.datpixelstudio.cibress.dto;

import com.datpixelstudio.cibress.entity.Unit;

import java.time.LocalTime;
import java.util.List;

public class DayEntryDishDto {

    private long id;
    private LocalTime localTime;
    private String dishName;
    private List<DishIngredientDto> dishIngredientDtos;
    private String commentText;
    private int quantity;
    private Unit unit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public List<DishIngredientDto> getDishIngredientDtos() {
        return dishIngredientDtos;
    }

    public void setDishIngredientDtos(List<DishIngredientDto> dishIngredientDtos) {
        this.dishIngredientDtos = dishIngredientDtos;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "DayEntryDishDto{" +
                "id=" + id +
                ", localTime=" + localTime +
                ", dishName='" + dishName + '\'' +
                ", ingredients=" + dishIngredientDtos +
                ", commentText='" + commentText + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                '}';
    }
}
