
Hooks into google map and geocoder APIs

uses geocoder API so you can just enter the name or address of a place instead of its coordinates

the program converts said place into coordinates which it uses to shift the map view

click the explosive buttons to get predicted blast radiuses!

you can also zoom in and out!

-------------------------

There is a lot of commented out code, only to show the first method I tried which was to put all the code in the MainActivity and send the data to the MapActivity. However, I couldn't avoid the null exception so I moved all relevant code to MapActivity.
