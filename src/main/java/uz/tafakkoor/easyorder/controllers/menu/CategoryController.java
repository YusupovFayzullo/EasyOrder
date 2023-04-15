package uz.tafakkoor.easyorder.controllers.menu;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;
import uz.tafakkoor.easyorder.services.menu.CategoryService;

import java.util.List;

@RestController
@ParameterObject
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "This API used for creating a category",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Category created"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Category not created")
            })

    @PostMapping(value = "/create", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Category> createCategory(@ModelAttribute CategoryCreateDTO dto) {
        Category category = categoryService.createCategory(dto);
        return ResponseEntity.status(201).body(category);
    }



    @Operation(summary = "This API used for getting a category by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Category found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id, Long restaurantID) {
        Category category = categoryService.getCategoryById(id, restaurantID);

        return ResponseEntity.ok().body(category);
    }

    @Operation(summary = "This API used for getting all categories by restaurant id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categories found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Categories not found with restaurant id")
    })
    @GetMapping(value = "/restaurant/{restaurantID}", produces = "application/json")
    public ResponseEntity<List<Category>> getAllCategories(@PathVariable Long restaurantID) {
        List<Category> categories = categoryService.getAllCategories(restaurantID);
        return ResponseEntity.ok().body(categories);
    }


    @DeleteMapping(value = "/{id}/delete", produces = "application/json")
    public Category deleteCategory(@PathVariable Long id, Long restaurantID) {
        return categoryService.deleteCategory(id, restaurantID);
    }


}
