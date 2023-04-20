package uz.tafakkoor.easyorder.dtos.menu.category;

import java.util.List;

public record CategoriesDTO(List<CategoryDTO> categories, Long restaurantID) {
}
