/**
 * 
 */

	      
const  strongPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
document.addEventListener('DOMContentLoaded', () => {
	

	const searchParams = new URLSearchParams(window.location.search);
	if(searchParams.has('userpresent')){
	alert("User is present please use different email !");	
	}

	
    const selectDrop = document.querySelector('#country');
	const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const errorSpan = document.getElementById('error');
    const form = document.getElementById('sign_up_form');
	
	function isPasswordStrong(passwordInput){
		var a = strongPasswordRegex.test(passwordInput);
		return a;
	}
	
    form.addEventListener('submit', (event) => {
      event.preventDefault(); // Prevent form submission
	console.log(passwordInput);
	console.log(confirmPasswordInput);
	console.log(errorSpan);
		if(! isPasswordStrong){
			alert("A strong password typically includes a combination of uppercase and lowercase letters, numbers, and special characters.");
		}

      const password = passwordInput.value;
      const confirmPassword = confirmPasswordInput.value;
	   
      if (password !== confirmPassword) {
        errorSpan.textContent = 'Passwords do not match.';
        alert('Passwords do not match.');
      } else if (password.length < 8) {
        errorSpan.textContent = 'Password should be at least 8 characters long.';
      } else {
        errorSpan.textContent = 'Password Is Matched !';
        form.submit();
        // Here you can perform further actions with the valid passwords
      }
      
      });


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
