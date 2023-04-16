package uz.tafakkoor.easyorder.controllers.restaurant;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantTime;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantUpdateDto;
import uz.tafakkoor.easyorder.dtos.user.ValidAppErrorDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.services.restaurant.RestaurantService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "Restaurant API")

public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @Operation(summary = "This API used for getting a restaurant by id")
    @GetMapping( value = "/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable Long id) {
        Optional<Restaurant> byId = restaurantRepository.findById(id);
        if(byId.isPresent()){
            Restaurant restaurant = byId.get();
            if(restaurant.isDeleted()){
                throw new RuntimeException("This restaurant not found");
            }
        }
        return ResponseEntity.ok(byId.orElseThrow(() -> new ItemNotFoundException("Restaurant not found with id " + id)));
    }

    @Operation(summary = "This API used for getting all restaurants")
    @GetMapping("/")
    public Page<Restaurant> getAll(@RequestParam(required = false, defaultValue = "5") Integer size,
                                   @RequestParam(required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return restaurantRepository.getAll(pageable);
    }

    @Operation(summary = "This API used to create restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant Successfully Created",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Restaurant.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Not Found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PostMapping
    public ResponseEntity<Restaurant> create(@NonNull @Valid  @RequestBody RestaurantCreateDto dto) {

        Restaurant restaurant = restaurantService.saveRestaurant(dto);
        if(restaurant==null){
             throw new RuntimeException();
        }
        return ResponseEntity.status(201).body(restaurant);
    }

    @Operation(summary = "This API used to update restaurant")
    @PutMapping(value = "/{id}" )
    public ResponseEntity<String> update(@NonNull  @Valid @RequestBody RestaurantUpdateDto dto, @PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Restaurant not found with by " + id));

        try {
            RestaurantTime closeTime = dto.getCloseTime();
            RestaurantTime openTime = dto.getOpenTime();

        }catch (Exception e){
             throw new RuntimeException();
         }

        if(restaurant.isDeleted()) return ResponseEntity.ok("Not found");
        Restaurant restaurant1 = restaurantService.updateRestaurant(dto, id);

        return ResponseEntity.ok("Successfully updated by " + restaurant1.getId());
    }

    @Operation(summary = "This API used to delete restaurant")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Restaurant not found with by " + id));
        restaurant.setDeleted(true);
        Restaurant save = restaurantRepository.save(restaurant);
        return ResponseEntity.ok("Successfully deleted by " + save.getId());
    }


}
