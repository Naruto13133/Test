/**
 * 
 */

	      

document.addEventListener('DOMContentLoaded', () => {
    const selectDrop = document.querySelector('#country');
	const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const errorSpan = document.getElementById('error');
    const form = document.getElementById('sign_up_form');
	
    form.addEventListener('submit', (event) => {
      event.preventDefault(); // Prevent form submission
	console.log(passwordInput);
	console.log(confirmPasswordInput);
	console.log(errorSpan);
	

      const password = passwordInput.value;
      const confirmPassword = confirmPasswordInput.value;

      if (password !== confirmPassword) {
        errorSpan.textContent = 'Passwords do not match.';
      } else if (password.length < 8) {
        errorSpan.textContent = 'Password should be at least 8 characters long.';
      } else {
        errorSpan.textContent = '';
        alert('Passwords match!');
        // Here you can perform further actions with the valid passwords
      }});


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