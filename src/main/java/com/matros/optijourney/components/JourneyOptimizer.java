package com.matros.optijourney.components;

import com.matros.optijourney.dtos.DistanceResponseDTO;
import com.matros.optijourney.dtos.DistanceResponseDTO.*;
import com.matros.optijourney.services.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JourneyOptimizer {
    @Autowired
    GoogleMapsService googleMapsService;

    ArrayList<String> optimizeJourney(String metric, String origin, ArrayList<String> waypoints, String destination) {
        ArrayList<String> journey = new ArrayList<String>(); // Start journey at start

        int[][] distanceMatrix = buildMatrix(metric, origin, waypoints, destination);

        return journey;
    }

    int[][] buildMatrix(String metric, String origin, ArrayList<String> waypoints, String destination) {
        // build initial vars
        int numPoints = waypoints.size() + 2;

        String[] points = new String[numPoints];
        points[0] = origin;
        for (int i = 1; i < numPoints - 1; i++) { points[i] = waypoints.get(i - 1); };
        points[numPoints - 1] = destination;

        int[][] matrix = new int[numPoints][numPoints];

        DistanceResponseDTO dto = googleMapsService.getDistance(points);
        List<Row> rows = dto.getRows();

        boolean toggle = (Objects.equals(metric, "distance"));

        int i = 0, j = 0;
        for (Row row : rows) {
            List<Element> elements = row.getElements();

            for (Element element : elements) {
                int val = (toggle) ? element.getDistance().getValue() : element.getDuration().getValue();
                matrix[i][j] = val;

                j++;
            }

            j = 0;
            i++;
        }

        return matrix;
    }
}
