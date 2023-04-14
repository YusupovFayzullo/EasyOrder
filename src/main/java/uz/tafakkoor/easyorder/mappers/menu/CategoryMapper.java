package uz.tafakkoor.easyorder.mappers.menu;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;

@Mapper
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);


    @Mappings({
            @Mapping(target = "image", source = "imageDTO"),
            @Mapping(target = "restaurant", source = "restaurantID", qualifiedByName = "toRestaurantCreate")
    })
    Category toCategoryCreate(CategoryCreateDTO dto);

    default Restaurant toRestaurantCreate(Long id) {
        return null;
    }
}
