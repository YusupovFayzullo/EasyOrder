package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.restaurant.Address;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.AddressDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantUpdateDto;
import uz.tafakkoor.easyorder.repositories.restaurant.AddressRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.services.ImageService;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;
    private final AddressRepository addressRepository;
    private final ImageService imageService;


    public Restaurant saveRestaurant(RestaurantCreateDto dto) {

        RestaurantCreateDto.RestaurantTime openTime = dto.getOpenTime();
        RestaurantCreateDto.RestaurantTime closeTime = dto.getCloseTime();

        if (stringToIntParser(openTime.getHour()) == -1 || stringToIntParser(openTime.getMinute()) == -1) {
            return null;
        }
        if (stringToIntParser(closeTime.getHour()) == -1 || stringToIntParser(closeTime.getMinute()) == -1) {
            return null;
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

        Collection<String> imageURLs = imageService.saveImagesToAWS(dto.getImages());

        Restaurant restaurant = Restaurant.restaurantBuilder()
                .address(savedAddress)
                .email(dto.getEmail())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .description(dto.getDescription())
                .openTime(LocalTime.of(stringToIntParser(openTime.getHour()), stringToIntParser(openTime.getMinute())))
                .closeTime(LocalTime.of(stringToIntParser(closeTime.getHour()), stringToIntParser(closeTime.getMinute())))
                .imageURLs(imageURLs)
                .build();
        return repository.save(restaurant);
    }

    public Restaurant updateRestaurant(RestaurantUpdateDto dto, Long id) {
        RestaurantCreateDto.RestaurantTime openTime = dto.getOpenTime();
        RestaurantCreateDto.RestaurantTime closeTime = dto.getCloseTime();

        Collection<String> imageURLs = imageService.saveImagesToAWS(dto.getImages());


        Optional<Restaurant> byId = repository.findById(id);
        if (byId.isPresent()) {
            Restaurant restaurant = byId.get();

            restaurant.setAddress(dto.getAddress());
            restaurant.setName(dto.getName());
            restaurant.setCloseTime(LocalTime.of(stringToIntParser(closeTime.getHour()), stringToIntParser(closeTime.getMinute())));
            restaurant.setOpenTime(LocalTime.of(stringToIntParser(openTime.getHour()), stringToIntParser(openTime.getMinute())));
            restaurant.setPhoneNumber(dto.getPhoneNumber());
            restaurant.setEmail(dto.getEmail());
            restaurant.setDescription(dto.getDescription());
            restaurant.setStatus(dto.getStatus());
            restaurant.setImageURLs(imageURLs);
            return repository.save(restaurant);
        }
        return null;

    }

    public int stringToIntParser(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
