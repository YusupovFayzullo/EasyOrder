package uz.tafakkoor.easyorder.services.restaurant;


import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.domains.restaurant.Address;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.AddressDto;
import uz.tafakkoor.easyorder.dtos.restaurant.ImageDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantTime;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantUpdateDto;
import uz.tafakkoor.easyorder.exceptions.TimeParseException;
import uz.tafakkoor.easyorder.mappers.menu.restaurant.ImageMapper;
import uz.tafakkoor.easyorder.repositories.ImageRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.AddressRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;
    private final AddressRepository addressRepository;
    private final ImageRepository imageRepository;


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
        Collection<ImageDto> images = dto.getImageDtos();
        Collection<Image> imageCollection = new ArrayList<>();


        ImageMapper mapper = Mappers.getMapper(ImageMapper.class);
        AddressDto addressDto = dto.getDto();

        for (ImageDto image : images) {
            Image image1 = mapper.toImage(image);
            imageCollection.add(imageRepository.save(image1));
        }

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
                .description(dto.getDescription())
                .openTime(open)
                .closeTime(close)
                .image(imageCollection)
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
            Collection<Image> images = restaurant.getImage();
            for (Image image : images) {
                Optional<Image> byId1 = imageRepository.findById(image.getId());
                if (byId1.isPresent()) {
                    Image image1 = byId1.get();
                    image1.setUrl(image.getUrl());
                    image1.setType(image.getType());
                    image1.setGeneratedName(image.getGeneratedName());
                    image1.setSize(image.getSize());
                    image1.setOriginalName(image.getOriginalName());
                    imageRepository.save(image1);
                }
            }
            restaurant.setAddress(savedAddress);
            restaurant.setName(dto.getName());
            restaurant.setCloseTime(close);
            restaurant.setOpenTime(open);
            restaurant.setPhoneNumber(dto.getPhoneNumber());
            restaurant.setEmail(dto.getEmail());
            restaurant.setDescription(dto.getDescription());
            restaurant.setStatus(dto.getStatus());
            return repository.save(restaurant);
        }
        return null;

    }

}
