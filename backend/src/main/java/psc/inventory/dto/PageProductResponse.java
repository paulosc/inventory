package psc.inventory.dto;

import java.util.List;

public record PageProductResponse(
        List<ProductResponse> products,
        int pages
) {}
