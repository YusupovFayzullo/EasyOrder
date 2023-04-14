package uz.tafakkoor.easyorder.mappers;


import org.mapstruct.Mapper;
import uz.tafakkoor.easyorder.domains.image.Image;
import uz.tafakkoor.easyorder.dtos.image.ImageCreateDTO;

@Mapper
public interface ImageMapper {
    ImageMapper IMAGE_MAPPER = org.mapstruct.factory.Mappers.getMapper(ImageMapper.class);

    Image toImageCreate(ImageCreateDTO dto);
}
