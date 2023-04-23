package uz.tafakkoor.easyorder.controllers.menu;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryUpdateDTO;
import uz.tafakkoor.easyorder.services.menu.CategoryService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "This API used for creating a category", responses = {
            @ApiResponse(responseCode = "201", description = "Category created"),
            @ApiResponse(responseCode = "400", description = "Category not created")})
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@Valid CategoryCreateDTO dto) {
        Category category = categoryService.createCategory(dto);
        return ResponseEntity.status(201).body(category);
    }


    @Operation(summary = "This API used for getting a category by id", responses =
            {@ApiResponse(responseCode = "200", description = "Category found"), @ApiResponse(responseCode = "404", description = "Category not found")})
    @GetMapping(value = "/byId", produces = "application/json")
    public ResponseEntity<Category> getCategoryById(Long id, Long restaurantID) {
        Category category = categoryService.getCategoryById(id, restaurantID);
        return ResponseEntity.ok().body(category);
    }

    @Operation(summary = "This API used for getting a category by name", responses = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")})
    @GetMapping(value = "/byName", produces = "application/json")
    public ResponseEntity<Category> getCategoryByName(String name, Long restaurantID) {
        Category category = categoryService.getCategoryByName(name, restaurantID);
        return ResponseEntity.ok().body(category);
    }

    @Operation(summary = "This API used for getting all categories by restaurant id", responses = {@ApiResponse(responseCode = "200", description = "Categories found"), @ApiResponse(responseCode = "404", description = "Categories not found with restaurant id")})
    @GetMapping(value = "/restaurant/{restaurantID}", produces = "application/json")
    public ResponseEntity<List<Category>> getAllCategories(@PathVariable Long restaurantID) {
        List<Category> categories = categoryService.getAllCategories(restaurantID);
        return ResponseEntity.ok().body(categories);
    }


    @Operation(summary = "This API used for deleting a category by id", responses = {@ApiResponse(responseCode = "200", description = "Category deleted"), @ApiResponse(responseCode = "404", description = "Category not found")})
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Boolean> deleteCategory(Long id, Long restaurantID) {
        boolean categoryID = categoryService.deleteCategory(id, restaurantID);
        return ResponseEntity.status(202).body(categoryID);
    }

    @Operation(summary = "This API used for updating a category", responses = {
            @ApiResponse(responseCode = "200", description = "Category updated"),
            @ApiResponse(responseCode = "404", description = "Category not found")})
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@Valid CategoryUpdateDTO dto) {
        Category category = categoryService.updateCategory(dto);
        return ResponseEntity.status(200).body(category);
    }


}
