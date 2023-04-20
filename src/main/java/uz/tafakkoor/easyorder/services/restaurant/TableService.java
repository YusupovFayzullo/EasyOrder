package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.domains.restaurant.Table;
import uz.tafakkoor.easyorder.dtos.restaurant.TableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableUpdate;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.TableRepository;

@Service
@RequiredArgsConstructor
public class TableService {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;

    public Table saveRestaurant(TableCreateDto dto) {

        if (restaurantRepository.findById(dto.getRestaurantId()).isEmpty()) {
            return null;
        }

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).get();

        Table table = Table.builder().
                number(dto.getNumber())
                .qrCodeURL(dto.getImage())
                .capacity(dto.getCapacity())
                .isBooked(dto.isBooked())
                .restaurant(restaurant)
                .build();
        return tableRepository.save(table);
    }

    public Table updateRestaurant(TableUpdate dto, Long id) {
        Table table = tableRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Table not found"));
        String qrCode = table.getQrCodeURL();
        Restaurant restaurant = table.getRestaurant();
        Restaurant restaurant1 = restaurantRepository.findById(restaurant.getId()).orElseThrow(() -> new ItemNotFoundException("Restaurant not found"));


        table.setRestaurant(restaurant1);
        table.setDeleted(dto.isDeleted());
        table.setNumber(dto.getNumber());
        table.setBooked(dto.isBooked());
        table.setCapacity(dto.getCapacity());
        table.setQrCodeURL(dto.getImage());
        return tableRepository.save(table);

    }
}
