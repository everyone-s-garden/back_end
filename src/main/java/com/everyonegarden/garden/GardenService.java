package com.everyonegarden.garden;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GardenService {

    private final GardenRepository gardenRepository;

    public List<GardenResponse> getPublicGardenByRegion(String region) {
        return gardenRepository.getPublicGardenByRegion(region).stream()
                .map(garden -> GardenResponse.builder()
                        .id(garden.getId())
                        .name(garden.getName())
                        .type(GardenType.PUBLIC)
                        .address(garden.getAddress())
                        .longitude(garden.getLongitude())
                        .latitude(garden.getLatitude())
                        .link(garden.getLink())
                        .price(garden.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPublicGardenByCoordinate(double xStart, double xEnd,
                                                            double yStart, double yEnd) {
        return gardenRepository
                .getPublicGardenByCoordinateWithinRange(xStart, xEnd, yStart, yEnd)
                .stream()
                .map(g -> GardenResponse.builder()
                        .id(g.getId())
                        .name(g.getName())
                        .type(g.getType())
                        .address(g.getAddress())
                        .longitude(g.getLongitude())
                        .latitude(g.getLatitude())
                        .link(g.getLink())
                        .price(g.getPrice())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPrivateGardenByRegion(String region) {
        return List.of();
    }

    public List<GardenResponse> getPrivateGardenByCoordinate(String xStart, String xEnd,
                                                             String yStart, String yEnd) {
        return List.of();
    }

}
