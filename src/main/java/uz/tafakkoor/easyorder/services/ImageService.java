package uz.tafakkoor.easyorder.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.repositories.ImageRepository;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;


    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
