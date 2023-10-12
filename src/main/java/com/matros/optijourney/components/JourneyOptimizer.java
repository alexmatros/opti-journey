package com.matros.optijourney.components;

import com.matros.optijourney.dtos.DistanceResponseDTO;
import com.matros.optijourney.dtos.DistanceResponseDTO.*;
import com.matros.optijourney.services.GoogleMapsService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class JourneyOptimizer {
    @Autowired
    GoogleMapsService googleMapsService;

    public List<String> optimizeJourney(String origin, List<String> waypoints, String destination, String metric, String mode) {
        // Build ordered array of points: origin -> waypoints -> destination
        int numPoints = (waypoints != null) ? waypoints.size() + 2 : 2;

        String[] points = new String[numPoints];
        points[0] = origin;
        for (int i = 1; i < numPoints - 1; i++) { points[i] = waypoints.get(i - 1); };
        points[numPoints - 1] = destination;

        // Build distance matrix with the points
        int[][] distanceMatrix = buildMatrix(points, metric, mode);

        // Get order of "next closest" strategy
        int[] order = nextClosest(distanceMatrix);
        List<String> journey = getJourney(points, order);

        System.out.println(journey.size());
        for (String p : journey) { System.out.println(p); }

        return journey;
    }

    private List<String> getJourney(String[] points, int[] order) {
        List<String> journey = new ArrayList<>();

        for (int j : order) {
            journey.add(points[j]);
        }

        return journey;
    }

    // Naive strategy
    // Starts at end, simply gets the next closest node until it gets to destination
    public int[] nextClosest(int[][] distanceMatrix) {
        int[] visited = new int[distanceMatrix.length];
        visited[distanceMatrix.length - 1] = distanceMatrix.length - 1;

        int rIdx = 0; // row idx
        int vIdx = 1; // visited idx

        while (vIdx < distanceMatrix.length - 1) {
            int[] row = distanceMatrix[rIdx];

            int minIdx = 0;
            int minVal = Integer.MAX_VALUE;

            for (int i = 0; i < row.length; i++) {
                if (!arrayContains(visited, i) && row[i] != 0 && row[i] < minVal) {
                    minIdx = i;
                    minVal = row[i];
                }
            }

            visited[vIdx] = minIdx;
            vIdx++;
        }

        return visited;
    }

    private boolean arrayContains(int[] arr, int val) {
        for (int i : arr) {
            if (i == val) {
                return true;
            }
        }

        return false;
    }

    private int[][] buildMatrix(String[] points, String metric, String mode) {
        // build initial vars
        int numPoints = points.length;

        int[][] matrix = new int[numPoints][numPoints];

        DistanceResponseDTO dto = googleMapsService.getDistance(points, mode);
        List<Row> rows = dto.getRows();

        boolean toggle = (Objects.equals(metric, "distance"));

        int i = 0, j = 0;
        for (Row row : rows) {
            List<Element> elements = row.getElements();

            for (Element element : elements) {
                int val = 0;

                if (toggle && element.getDistance() != null) {
                    val = element.getDistance().getValue();
                } else if (!toggle && element.getDuration() != null) {
                    val = element.getDuration().getValue();
                }

                matrix[i][j] = val;

                j++;
            }

            j = 0;
            i++;
        }

        return matrix;
    }
}
