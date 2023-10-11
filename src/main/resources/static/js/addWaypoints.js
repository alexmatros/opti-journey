initAutocomplete();

function addWaypoint() {
    const idx = document.querySelectorAll('input[name^="waypoints"]').length + 1;
    const div = document.createElement('div');

    const label = document.createElement('label');
    label.setAttribute('for', 'waypoint' + idx);
    label.innerText = 'Waypoint ' + idx + ': ';
    div.appendChild(label);

    const input = document.createElement('input');
    input.setAttribute('type', 'text');
    input.setAttribute('id', 'waypoint' + idx);
    input.setAttribute('name', `waypoints[${idx}]`);
    input.setAttribute("autocomplete", "off");
    div.appendChild(input);

    const br1 = document.createElement('br');
    div.appendChild(br1);

    const br2 = document.createElement('br');
    div.appendChild(br2);

    document.getElementById('waypoints').appendChild(div);

    setInputAutocomplete(input);
}

function initAutocomplete() {
    const originInput = document.querySelector('input[name="origin"]');
    const destinationInput = document.querySelector('input[name="destination"]');

    const options = {
        componentRestrictions: {idxry: ['us', 'ca']} // Restrict to USA and Canada
    };

    var autocompleteOrigin = new google.maps.places.Autocomplete(originInput, options);
    var autocompleteDestination = new google.maps.places.Autocomplete(destinationInput, options);

    autocompleteOrigin.setFields(['place_id', 'name', 'formatted_address']);
    autocompleteDestination.setFields(['place_id', 'name', 'formatted_address']);
}

function setInputAutocomplete(input) {
    new google.maps.places.Autocomplete(input);
}
