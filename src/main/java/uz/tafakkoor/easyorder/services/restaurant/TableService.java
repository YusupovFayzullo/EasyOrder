package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.domains.restaurant.Table;
import uz.tafakkoor.easyorder.dtos.restaurant.TableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableUpdate;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.DocumentRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.TableRepository;

@Service
@RequiredArgsConstructor
public class TableService {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final DocumentRepository documentRepository;

    public Table saveRestaurant(TableCreateDto dto) {
        Restaurant restaurant= restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(() -> new ItemNotFoundException("Restaurant not found"));

        if (restaurant.isDeleted()) {
            throw new RuntimeException("Restaurant deleted");
        }

        Table table = Table.builder().
                number(dto.getNumber())
                .capacity(dto.getCapacity())
                .isBooked(dto.isBooked())
                .restaurant(restaurant)
                .build();

        return tableRepository.save(table);
    }

    public Table updateTable(TableUpdate dto, Long id) {
        Table table = tableRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Table not found"));
        if (table.isDeleted()) {
            throw new RuntimeException("Table is deleted");
        }
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(() -> new ItemNotFoundException("Restaurant not found by id" + dto.getRestaurantId()));

        if (restaurant.isDeleted()) throw new RuntimeException("Restaurant deleted");

        table.setRestaurant(restaurant);
        table.setDeleted(dto.isDeleted());
        table.setNumber(dto.getNumber());
        table.setBooked(dto.isBooked());
        table.setCapacity(dto.getCapacity());
        return tableRepository.save(table);

    }
}
