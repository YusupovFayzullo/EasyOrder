package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.config.security.SessionUser;
import uz.tafakkoor.easyorder.domains.Document;
import uz.tafakkoor.easyorder.domains.restaurant.Address;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.AddressDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantTime;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantUpdateDto;
import uz.tafakkoor.easyorder.exceptions.TimeParseException;
import uz.tafakkoor.easyorder.repositories.DocumentRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.AddressRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;

import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;
    private final AddressRepository addressRepository;
    private final DocumentRepository documentRepository;
    private final SessionUser sessionUser;


    public Restaurant saveRestaurant(RestaurantCreateDto dto) {

        RestaurantTime openTime = dto.getOpenTime();
        RestaurantTime closeTime = dto.getCloseTime();

        LocalTime open = null;
        LocalTime close = null;

        try {

            open = LocalTime.of(openTime.getHour(), openTime.getMinute());
            close = LocalTime.of(closeTime.getHour(), closeTime.getMinute());

        } catch (TimeParseException e) {
            throw new TimeParseException("Time is invalid");
        }

        AddressDto addressDto = dto.getDto();

        Address address = Address.builder()
                .city(addressDto.getCity())
                .district(addressDto.getDistrict())
                .street(addressDto.getStreet())
                .house(addressDto.getHouse())
                .latitude(addressDto.getLatitude())
                .longitude(addressDto.getLongitude())
                .build();

        Address savedAddress = addressRepository.save(address);

        Optional<Document> byId1 = documentRepository.findById(dto.getImageID());
        if (!byId1.isPresent()) {
            throw new RuntimeException("Document id not found");
        }

        Document document = byId1.get();

        Restaurant restaurant = Restaurant.restaurantBuilder()
                .address(savedAddress)
                .email(dto.getEmail())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .description(dto.getDescription())
                .image(document)
                .openTime(open)
                .closeTime(close)
                .createdBy(sessionUser.id())
                .build();
        return repository.save(restaurant);
    }

    public Restaurant updateRestaurant(RestaurantUpdateDto dto, Long id) {
        RestaurantTime openTime = dto.getOpenTime();
        RestaurantTime closeTime = dto.getCloseTime();
        LocalTime open = null;
        LocalTime close = null;


        open = LocalTime.of(openTime.getHour(), openTime.getMinute());
        close = LocalTime.of(closeTime.getHour(), closeTime.getMinute());
        Optional<Document> byId1 = documentRepository.findById(dto.getImageID());
        if (!byId1.isPresent()) {
            throw new RuntimeException("Document id not found");
        }
        Document document = byId1.get();

        Optional<Restaurant> byId = repository.findById(id);
        if (byId.isPresent()) {
            Restaurant restaurant = byId.get();
            Address address = restaurant.getAddress();
            address.setCity(dto.getDto().getCity());
            address.setDistrict(dto.getDto().getDistrict());
            address.setHouse(dto.getDto().getHouse());
            address.setStreet(dto.getDto().getStreet());
            address.setLatitude(dto.getDto().getLatitude());
            address.setLongitude(dto.getDto().getLongitude());
            Address savedAddress = addressRepository.save(address);

            restaurant.setAddress(savedAddress);
            restaurant.setName(dto.getName());
            restaurant.setCloseTime(close);
            restaurant.setOpenTime(open);
            restaurant.setPhoneNumber(dto.getPhoneNumber());
            restaurant.setEmail(dto.getEmail());
            restaurant.setDescription(dto.getDescription());
            restaurant.setStatus(dto.getStatus());
            restaurant.setImage(document);

            return repository.save(restaurant);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

}
