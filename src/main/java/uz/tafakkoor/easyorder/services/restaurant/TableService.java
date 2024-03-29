package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.config.security.SessionUser;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.domains.restaurant.Table;
import uz.tafakkoor.easyorder.dtos.restaurant.ManyTableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableUpdate;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.DocumentRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.TableRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final SessionUser sessionUser;
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

    public String saveMultiple(ManyTableCreateDto dto) {
        int tableCapacity = dto.tableCapacity();
        int tableCount = dto.tableCount();
        int tableNumberStart = dto.tableNumberStart();
        long createdById = sessionUser.id();
        long restaurantId = dto.restaurantId();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ItemNotFoundException("Restaurant not found"));
        validateRestaurant(restaurant, createdById);

        List<Table> tables = new ArrayList<>(); /*IntStream.rangeClosed(tableNumberStart, tableCount)
                .mapToObj(i -> Table.builder()
                        .number(String.valueOf(i))
                        .capacity(tableCapacity)
                        .restaurant(restaurant)
                        .build())
                .collect(Collectors.toList());*/
        for (int i = tableNumberStart; i < tableNumberStart+tableCount; i++) {
            tables.add(Table.builder()
                            .number(String.valueOf(i))
                            .capacity(tableCapacity)
                            .restaurant(restaurant)
                            .build()
            );
        }
        tableRepository.saveAll(tables);

        return "Successfully created! by " + sessionUser.user().getPhoneNumber();
    }

    private void validateRestaurant(Restaurant restaurant, long createdById) {
        if (restaurant.isDeleted()) {
            throw new RuntimeException("Restaurant deleted");
        }
        if (restaurant.getCreatedBy() != createdById) {
            throw new RuntimeException("You do not have permission to create tables for this restaurant");
        }
    }

}
