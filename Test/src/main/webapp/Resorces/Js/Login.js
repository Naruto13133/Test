/**
 * 
 */

	
/*	window.onload = function() {

// Get the URL parameters
const a=document.getElementById("authenticate");
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

// Get the value of the 'name' parameter
const athnt = urlParams.get('athnt');
console.log('athnt:', athnt); // Output: Name: John

if(athnt == 'true'){
	
	a.innerText('New Header Text!') ;
}

	};*/
	
	document.addEventListener('DOMContentLoaded', () => {
		
		const a=document.getElementById("authenticate");
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

// Get the value of the 'name' parameter
const athnt = urlParams.get('athnt');
console.log('athnt:', athnt); // Output: Name: John

if(athnt == 'true'){
	
	a.innerHTML('New Header Text!') ;
	}
		
	});