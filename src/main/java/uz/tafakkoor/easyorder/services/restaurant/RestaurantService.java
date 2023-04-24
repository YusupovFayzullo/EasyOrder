package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.config.security.SessionUser;
import uz.tafakkoor.easyorder.domains.Document;
import uz.tafakkoor.easyorder.domains.restaurant.Address;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.AddressDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantUpdateDto;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.DocumentRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.AddressRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;
    private final AddressRepository addressRepository;
    private final DocumentRepository documentRepository;
    private final SessionUser sessionUser;


    public Restaurant saveRestaurant(RestaurantCreateDto dto) {


        AddressDto addressDto = dto.getDto();
         if(addressDto==null){
             throw new RuntimeException("Address can not be blank");
         }
        Document document = documentRepository.findById(dto.getImageID()).orElseThrow(() -> new ItemNotFoundException("Document not found"));

        Address address = Address.builder()
                .city(addressDto.getCity())
                .district(addressDto.getDistrict())
                .street(addressDto.getStreet())
                .house(addressDto.getHouse())
                .latitude(addressDto.getLatitude())
                .longitude(addressDto.getLongitude())
                .build();

        Address savedAddress = addressRepository.save(address);


         Restaurant restaurant = Restaurant.restaurantBuilder()
                .address(savedAddress)
                .email(dto.getEmail())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                 .createdBy(sessionUser.id())
                .description(dto.getDescription())
                .image(document)
                .openTime(LocalTime.of(dto.getOpenTime().getHour(),dto.getOpenTime().getMinute()))
                .closeTime(LocalTime.of(dto.getCloseTime().getHour(),dto.getCloseTime().getMinute()))
                .build();
        return repository.save(restaurant);
    }

    public Restaurant updateRestaurant(RestaurantUpdateDto dto, Long id) {

        Document document=documentRepository.findById(dto.getImageID()).orElseThrow(() -> new ItemNotFoundException("Document not found"));

        Restaurant restaurant = repository.findById(id).orElseThrow(() ->new ItemNotFoundException( "Restaurant not found"));

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
            restaurant.setCloseTime(LocalTime.of(dto.getCloseTime().getHour(), dto.getCloseTime().getMinute()));
            restaurant.setOpenTime(LocalTime.of(dto.getOpenTime().getHour(), dto.getOpenTime().getMinute()));
            restaurant.setPhoneNumber(dto.getPhoneNumber());
            restaurant.setEmail(dto.getEmail());
            restaurant.setDescription(dto.getDescription());
            restaurant.setStatus(dto.getStatus());
            restaurant.setImage(document);
            restaurant.setCreatedBy(sessionUser.id());
            return repository.save(restaurant);
    }

}
