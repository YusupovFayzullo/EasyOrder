package uz.tafakkoor.easyorder.controllers.menu;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.services.menu.CategoryService;

@RestController
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
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryCreateDTO dto) {
        Category category = categoryService.createCategory(dto);
        return ResponseEntity.status(201).body(category);
    }


    @Operation(summary = "This API used for getting a category by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Category found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new ItemNotFoundException("Category not found with id " + id));
        return ResponseEntity.ok().body(category);
    }

}
