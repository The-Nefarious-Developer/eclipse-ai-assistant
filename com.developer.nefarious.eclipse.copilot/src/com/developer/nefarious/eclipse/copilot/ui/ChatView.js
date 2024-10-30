// Call from Java to set the random background color
function changeColor() {
	var x = Math.floor(Math.random() * 256);
	var y = Math.floor(Math.random() * 256);
	var z = Math.floor(Math.random() * 256);
	var bgColor = "rgb(" + x + "," + y + "," + z + ")";
	document.body.style.background = bgColor;
	document.getElementById("lastAction").innerText = "Background color set to " + bgColor;
}

// Call from Java to set the current selection
function setSelection(text) {
	document.getElementById("selection").innerText = text;
	document.getElementById("lastAction").innerText = "Selection set to " + text;
}

// Call to Java to open the preferences
function openPreferences() {
	try {
		var result = openEclipsePreferences(); // Java callback
		document.getElementById("lastAction").innerText = "Preferences were opened. Return value was: " + result;
	} catch (e) {
		document.getElementById("lastAction").innerText = "A Java error occured: " + e.message;
	}
}

// Call from java to say something
function say(something) {
	alert("Java says: " + something);
	document.getElementById("lastAction").innerText = "We said: " + something;
}
