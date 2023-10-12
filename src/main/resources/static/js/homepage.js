init();

function init() {
    initAutocomplete();
    initRadioLabels();
}

function addWaypoint() {
    const count = document.querySelectorAll('input[name^="waypoints"]').length + 1;
    const div = document.createElement('div');
    div.classList.add('form-group');

    const label = document.createElement('label');
    label.setAttribute('for', 'waypoint' + count);
    label.innerText = 'Waypoint ' + count + ': ';
    div.appendChild(label);

    const input = document.createElement('input');
    input.setAttribute('type', 'text');
    input.setAttribute('id', 'waypoint' + count);
    input.setAttribute('name', 'waypoints');
    input.setAttribute('autocomplete', 'off');
    div.appendChild(input);

    document.getElementById('waypoints').appendChild(div);

    setInputAutocomplete(input);
}

function initAutocomplete() {
    const originInput = document.querySelector('input[name="origin"]');
    const destinationInput = document.querySelector('input[name="destination"]');

    const options = {
        componentRestrictions: {country: ['us', 'ca']}
    };

    var autocompleteOrigin = new google.maps.places.Autocomplete(originInput, options);
    var autocompleteDestination = new google.maps.places.Autocomplete(destinationInput, options);

    autocompleteOrigin.setFields(['place_id', 'name', 'formatted_address']);
    autocompleteDestination.setFields(['place_id', 'name', 'formatted_address']);
}

function setInputAutocomplete(input) {
    const options = {
        componentRestrictions: {country: ['us', 'ca']}
    };

    new google.maps.places.Autocomplete(input, options);
}

function initRadioLabels() {
    const radioLabels = document.querySelectorAll('.radio-group label');

    radioLabels.forEach(label => {
        console.log("here");
        label.addEventListener('click', () => {
            radioLabels.forEach(label => label.classList.remove('checked'));
            label.classList.add('checked');
        });
    });
}
