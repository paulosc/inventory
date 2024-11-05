package psc.inventory.dto;

public record ProductResponse(
        Long id,
        Long categoryId,
        String name,
        String description,
        Double price,
        Boolean available,
        String category
) {}
