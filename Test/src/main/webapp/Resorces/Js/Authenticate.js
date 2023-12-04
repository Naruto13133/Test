window.onload = function() {
  // Image URL for the QR code
  var imageUrl = 'path_to_your_qr_code_image.png';

  // Get the image element
  var qrCodeImage = document.getElementById('qrCodeImage');

  // Set the image source dynamically
  qrCodeImage.src = imageUrl;
};
