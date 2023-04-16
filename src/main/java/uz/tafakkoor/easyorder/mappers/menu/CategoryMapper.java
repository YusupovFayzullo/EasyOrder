package uz.tafakkoor.easyorder.mappers.menu;


import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;

@Mapper
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);


    Category toCategoryCreate(CategoryCreateDTO dto);


}
