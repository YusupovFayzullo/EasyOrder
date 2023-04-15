package uz.tafakkoor.easyorder.controllers.restaurant;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.domains.restaurant.Table;
import uz.tafakkoor.easyorder.dtos.restaurant.NotBookedTableDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableCreateDto;
import uz.tafakkoor.easyorder.dtos.restaurant.TableUpdate;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.restaurant.TableRepository;
import uz.tafakkoor.easyorder.services.restaurant.TableService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/table")
@RequiredArgsConstructor
@Tag(name = "Table", description = "Table API")

public class TableController {

    private final TableRepository tableRepository;
    private final TableService tableService;

    @Operation(summary = "This API used for getting a table by id")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Table> getById(@PathVariable Long id) {
        Optional<Table> byId = tableRepository.findById(id);
        if(byId.isPresent()){
            Table table = byId.get();
            if(table.isDeleted()){
                return ResponseEntity.ok(null);
            }
        }
        return ResponseEntity.ok(byId.orElseThrow(() -> new ItemNotFoundException("Table not found with id " + id)));


    }

    @Operation(summary = "This API used for getting all tables")
    @GetMapping("/")
    public Page<Table> getAll(@RequestParam(required = false, defaultValue = "5") Integer size,
                              @RequestParam(required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return tableRepository.getAll(pageable);
    }
    @Operation(summary = "This API used for getting is not booked tables")
    @GetMapping("/notBooked/{restaurantId}")
    public ResponseEntity<List<NotBookedTableDto>> getAll(@PathVariable Long restaurantId) {
        List<NotBookedTableDto> infoNotBooked = tableRepository.getInfoNotBooked(restaurantId);
        return ResponseEntity.ok(infoNotBooked);
    }

    @Operation(summary = "This API used to create table")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Table Successfully Created",
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
    public ResponseEntity<Table> create(@RequestBody TableCreateDto dto) {
        Long restaurantId = dto.getRestaurantId();
        Optional<Table> byId = tableRepository.getById(restaurantId, dto.getNumber());
        if(byId.isPresent())  return ResponseEntity.status(404).body(null);
        Table table = tableService.saveRestaurant(dto);

        if(table==null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(201).body(table);
    }

    @Operation(summary = "This API used to update table")
    @PutMapping(value = "/{id}" )
    public ResponseEntity<String> update(@RequestBody TableUpdate dto, @PathVariable Long id) {
        Table table=tableRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Table not found with by " + id));
        Long restaurantId = dto.getRestaurantId();
        Optional<Table> byId = tableRepository.getById(restaurantId, table.getNumber());

        if(byId.isPresent())  return ResponseEntity.status(404).body(null);
            if(table.isDeleted()){
                return ResponseEntity.ok("Not found ");
            }
        Table table1 = tableService.updateRestaurant(dto, id);
        if(table1==null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok("Succesfully updated "+table.getId());
    }

    @Operation(summary = "This API used to delete table")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Table table=tableRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Table not found with by " + id));
        table.setDeleted(true);
        Table save =tableRepository.save(table);
        return ResponseEntity.ok("Successfully deleted by " + save.getId());
    }



}
