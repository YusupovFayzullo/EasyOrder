package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.domains.restaurant.Table;
import uz.tafakkoor.easyorder.dtos.restaurant.ImageDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableUpdate;
import uz.tafakkoor.easyorder.repositories.ImageRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.TableRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final ImageRepository imageRepository;

    public Table saveRestaurant(TableCreateDto dto) {
        ImageDto dto1 = dto.getImage();
        Image image = Image.builder()
                .url(dto1.getUrl())
                .size(dto1.getSize())
                .generatedName(dto1.getGeneratedName())
                .originalName(dto1.getOriginalName())
                .build();
        Image saveImage = imageRepository.save(image);

        if (restaurantRepository.findById(dto.getRestaurantId()).isEmpty()) {
            return null;
        }

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).get();

        Table table = Table.builder().
                number(dto.getNumber())
                .qrCode(saveImage)
                .capacity(dto.getCapacity())
                .isBooked(dto.isBooked())
                .restaurant(restaurant)
                .build();
        return tableRepository.save(table);
    }

    public Table updateRestaurant(TableUpdate dto, Long id) {
        Optional<Table> byId = tableRepository.findById(id);
        Table table = byId.get();
        Image qrCode = table.getQrCode();
        Optional<Image> byImage = imageRepository.findById(qrCode.getId());
        Restaurant restaurant = table.getRestaurant();
        Optional<Restaurant> byRestaurant = restaurantRepository.findById(restaurant.getId());

        return null;

    }
}
