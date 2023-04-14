package uz.tafakkoor.easyorder.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.domains.image.Image;
import uz.tafakkoor.easyorder.repositories.ImageRepository;
import uz.tafakkoor.easyorder.utils.BaseUtils;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;



    public Image saveImage(MultipartFile file) {
        return imageRepository.save(generateImage(file));
    }

    public Image generateImage(MultipartFile file) {
        return Image.builder()
                .type(file.getContentType())
                .size(file.getSize())
                .url("google/cloud")
                .generatedName(BaseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename())))
                .originalName(file.getOriginalFilename())
                .build();
    }
}
