package uz.tafakkoor.easyorder.dtos.restaurant;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springdoc.core.annotations.ParameterObject;

@ParameterObject
public record ManyTableCreateDto(
        @Parameter(description = "Restaurant id")
                @NotBlank(message = "Restaurant id is required")
        long restaurantId,
        @Parameter(description = "Table count")
                @NotBlank(message = "Table count is required")
                @Min(value = 2, message = "Table count must be greater than 1")
        int tableCount,
        @Parameter(description = "Table capacity")
                @NotBlank(message = "Table capacity is required")
                @Min(value = 1, message = "Table capacity must be greater than 0")
        int tableCapacity,
        @Parameter(description = "Table number start")
                @NotBlank(message = "Table number start is required")
                @Min(value = 1, message = "Table number start must be greater than 0")
        int tableNumberStart,
        @Parameter(description = "Created by id")
                @NotBlank(message = "Created by id is required")
                @Min(value = 1, message = "Created by id must be greater than 0")
        long createdById
) {
}
