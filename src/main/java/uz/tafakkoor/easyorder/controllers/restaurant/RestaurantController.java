package uz.tafakkoor.easyorder.controllers.restaurant;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.RestaurantUpdateDto;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.services.restaurant.RestaurantService;

import java.util.List;

@RestController
@ParameterObject
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "Restaurant API")

public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @Operation(summary = "This API used for getting a restaurant by id")
    @GetMapping(value = "{id} ", produces = "application/json")
    public ResponseEntity<Restaurant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Restaurant not found with id " + id)));
    }

    @Operation(summary = "This API used for getting all restaurants")
    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAll() {
        return ResponseEntity.ok(restaurantRepository.findAll());
    }

    @Operation(summary = "This API used to create restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant Successfully Created",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Restaurant.class))}),
            @ApiResponse(responseCode = "500", description = "Not Found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))})
    })

    @PostMapping(produces = "application/json",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody RestaurantCreateDto dto) {
        Restaurant restaurant = restaurantService.saveRestaurant(dto);
        if (restaurant == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(201).body(restaurant);
    }

    @Operation(summary = "This API used to update restaurant")
    @PutMapping(value = "{id}", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Restaurant> update(@RequestBody RestaurantUpdateDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(dto, id));
    }

    @Operation(summary = "This API used to delete restaurant")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Restaurant not found with by " + id));
        restaurant.setDeleted(true);
        Restaurant save = restaurantRepository.save(restaurant);
        return ResponseEntity.ok("Successfully deleted by " + save.getId());
    }


}
