package uz.tafakkoor.easyorder.mappers.menu.restaurant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.dtos.restaurant.ImageDto;

@Mapper
public interface ImageMapper {

    @Mapping(target = "originalName", source = "originalName")
    Image toImage(ImageDto dto);
}
