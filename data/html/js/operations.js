var date = new Date();
var hourNow = date.getHours();
var greeting = "Hello";
var intructions = "The game consists in making pair of 4's of cards with the same numbers. \nThe player who wins is who ever scores the most points in pairing 4 cards.";
if(hourNow > 18){
greeting = 'Good \n\tevening';
}else if(hourNow > 12){
	greeting = 'Good \n\tafternoon!';
}else if (hourNow > 0) {
	greeting = 'Good \n\tmorning';
}else{
	greeting = 'Welcome!';
}
document.write('<h2 class="info" id="greeting">' + greeting + '</h2>');
document.write('<p class="info" id="instructions">' + intructions + '</p>');