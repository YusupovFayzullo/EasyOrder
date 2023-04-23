package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.Document;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.domains.restaurant.Table;
import uz.tafakkoor.easyorder.dtos.restaurant.ManyTableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableUpdate;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.DocumentRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.TableRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TableService {

    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final DocumentRepository documentRepository;

    public Table saveRestaurant(TableCreateDto dto) {
        Optional<Restaurant> byId = restaurantRepository.findById(dto.getRestaurantId());
        if (!byId.isPresent()) {
            throw new RuntimeException("Restaurant not found");
        }


        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).get();
        if (restaurant.isDeleted()) {
            throw new RuntimeException("Restaurant deleted");
        }
        Optional<Document> byId1 = documentRepository.findById(dto.getImageID());
        if (!byId1.isPresent()) {
            throw new RuntimeException("Image id not found");
        }
        Document document = byId1.get();
        if (document.isDeleted()) {
            throw new RuntimeException("Image deleted");
        }
        Table table = Table.builder().
                number(dto.getNumber())
                .capacity(dto.getCapacity())
                .isBooked(dto.isBooked())
                .image(document)
                .restaurant(restaurant)
                .build();

        return tableRepository.save(table);
    }

    public Table updateRestaurant(TableUpdate dto, Long id) {
        Table table = tableRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Table not found"));
        if (table.isDeleted()) {
            throw new RuntimeException("Restaurant is deleted");
        }
        Optional<Restaurant> byId = restaurantRepository.findById(dto.getRestaurantId());
        if (!byId.isPresent()) {
            throw new RuntimeException("Restaurant not found");
        }
        Restaurant restaurant = byId.get();
        if (restaurant.isDeleted()) {
            throw new RuntimeException("Restaurant deleted");
        }
        Optional<Document> byId1 = documentRepository.findById(dto.getImageId());
        if (!byId1.isPresent()) {
            throw new RuntimeException("Image id not found");
        }
        Document document = byId1.get();
        if (document.isDeleted()) {
            throw new RuntimeException("Image deleted");
        }
        table.setRestaurant(restaurant);
        table.setDeleted(dto.isDeleted());
        table.setNumber(dto.getNumber());
        table.setBooked(dto.isBooked());
        table.setCapacity(dto.getCapacity());
        documentRepository.findById(dto.getImageId()).ifPresent(table::setImage);
        return tableRepository.save(table);

    }

    public String saveMultiple(ManyTableCreateDto dto) {
        int tableCapacity = dto.tableCapacity();
        int tableCount = dto.tableCount();
        int tableNumberStart = dto.tableNumberStart();
        long createdById = dto.createdById();
        long restaurantId = dto.restaurantId();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ItemNotFoundException("Restaurant not found"));
        validateRestaurant(restaurant, createdById);

        List<Table> tables = IntStream.rangeClosed(tableNumberStart, tableCount)
                .mapToObj(i -> Table.builder()
                        .number(String.valueOf(i))
                        .capacity(tableCapacity)
                        .restaurant(restaurant)
                        .build())
                .collect(Collectors.toList());
        tableRepository.saveAll(tables);

        return "Successfully created!";
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
