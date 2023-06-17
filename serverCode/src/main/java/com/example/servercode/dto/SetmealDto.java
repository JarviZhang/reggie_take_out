package com.example.servercode.dto;


import com.example.servercode.entity.Setmeal;
import com.example.servercode.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
