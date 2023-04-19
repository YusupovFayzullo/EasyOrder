package uz.tafakkoor.easyorder.mappers.menu;


import lombok.NonNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryUpdateDTO;

@Mapper
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);


    Category toCategoryEntity(CategoryCreateDTO dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateCategoryEntity(CategoryUpdateDTO dto, @MappingTarget @NonNull Category category);



}
