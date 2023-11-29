/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
    const selectDrop = document.querySelector('#country');

    fetch('https://restcountries.com/v2/all') // Fetching country data from the API
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok.');
            }
            return res.json();
        })
        .then(data => {
            let output = "";
            data.forEach(country => {
                output += `<option value="${country.name}">${country.name}</option>`;
            });
            selectDrop.innerHTML = output; // Populating the select element with country options
        })
        .catch(err => {
            console.error('Fetch error:', err);
        });
});