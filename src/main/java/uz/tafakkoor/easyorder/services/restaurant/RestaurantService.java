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
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantUpdateDto;
import uz.tafakkoor.easyorder.mappers.menu.restaurant.ImageMapper;
import uz.tafakkoor.easyorder.repositories.restaurant.AddressRepository;
import uz.tafakkoor.easyorder.repositories.ImageRepository;
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

        RestaurantCreateDto.RestaurantTime openTime = dto.getOpenTime();
        RestaurantCreateDto.RestaurantTime closeTime = dto.getCloseTime();

        if(stringToIntParser(openTime.getHour())==-1 || stringToIntParser(openTime.getMinute())==-1){
            return null;
        }
        if(stringToIntParser(closeTime.getHour())==-1 || stringToIntParser(closeTime.getMinute())==-1){
            return null;
        }
        Collection<ImageDto> images = dto.getImageDtos();
        Collection<Image> imageCollection =new ArrayList<>();


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
                .openTime(LocalTime.of(stringToIntParser(openTime.getHour()), stringToIntParser(openTime.getMinute())))
                .closeTime(LocalTime.of(stringToIntParser(closeTime.getHour()), stringToIntParser(closeTime.getMinute())))
                .image(imageCollection)
                .build();
        return repository.save(restaurant);
    }

    public Restaurant updateRestaurant(RestaurantUpdateDto dto, Long id) {
        RestaurantCreateDto.RestaurantTime openTime = dto.getOpenTime();
        RestaurantCreateDto.RestaurantTime closeTime = dto.getCloseTime();


        Optional<Restaurant> byId = repository.findById(id);
        if (byId.isPresent()) {
            Restaurant restaurant = byId.get();
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
                return null;
            }
            restaurant.setAddress(dto.getAddress());
            restaurant.setName(dto.getName());
            restaurant.setCloseTime(LocalTime.of(stringToIntParser(closeTime.getHour()), stringToIntParser(closeTime.getMinute())));
            restaurant.setOpenTime(LocalTime.of(stringToIntParser(openTime.getHour()), stringToIntParser(openTime.getMinute())));
            restaurant.setPhoneNumber(dto.getPhoneNumber());
            restaurant.setEmail(dto.getEmail());
            restaurant.setDescription(dto.getDescription());
            restaurant.setStatus(dto.getStatus());
            return repository.save(restaurant);
        }
        return null;

    }

    public int stringToIntParser(String str) {
        try {
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            return -1;
        }
    }

}
