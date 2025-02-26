package com.example.kit4_api.dto;

import java.util.UUID;

public record GameFilterDto(Boolean ended, UUID userId) {
}
