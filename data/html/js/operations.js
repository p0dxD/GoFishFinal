var date = new Date();
var hourNow = date.getHours();
var greeting = "Hello";

if(hourNow > 18){
greeting = 'Good evening';
}else if(hourNow > 12){
	greeting = 'Good afternoon!';
}else if (hourNow > 0) {
	greeting = 'Good morning';
}else{
	greeting = 'Welcome!';
}
document.write('<h1>' + greeting + '</h1>');