function initMap() {
    var directionsService = new google.maps.DirectionsService();
    var directionsRenderer = new google.maps.DirectionsRenderer();
    var mapOptions = {
        center: {lat: 37.7749, lng: -122.4194}, // San Francisco coordinates
        zoom: 10, // Adjust the zoom level as needed
    };
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
    directionsRenderer.setMap(map);

    var request = {
        origin: origin,
        destination: destination,
        waypoints: waypoints.map(function (waypoint) {
            console.log(waypoint);
            return {
                location: waypoint,
                stopover: true
            };
        }),
        travelMode: 'DRIVING'
    };

    directionsService.route(request, function (result, status) {
        if (status == 'OK') {
            directionsRenderer.setDirections(result);
        }
    });
}