function addWaypoint() {
    const numFields = document.querySelectorAll('input[type="text"]').length - 1;
    const div = document.createElement('div');

    const label = document.createElement('label');
    label.setAttribute('for', 'waypoint' + numFields);
    label.innerText = 'Waypoint ' + numFields + ': ';
    div.appendChild(label);

    const input = document.createElement('input');
    input.setAttribute('type', 'text');
    input.setAttribute('id', 'waypoint' + numFields);
    input.setAttribute('name', 'waypoint' + numFields);
    div.appendChild(input);

    const br1 = document.createElement('br');
    const br2 = document.createElement('br');
    div.appendChild(br1);
    div.appendChild(br2);

    document.getElementById('waypoints').appendChild(div);
}
