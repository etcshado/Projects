document.getElementById("userInput").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        var userInput = document.getElementById("userInput").value;

        var outputText = "";
        switch (userInput.toLowerCase()) { // Make input case-insensitive
            case "help":
                outputText = "Available commands: List of available commands:\n help, aboutme, projects, contact";
                break;
            case "aboutme":
                outputText = "TODO...";
                break;
            case "projects":
                outputText = "TODO...";
                break;
            case "contact":
                outputText = "TODO...";
                break;
            default:
                outputText = "Invalid command. Type 'help' for available options.";
        }


        document.getElementById("output").innerHTML += outputText + "\n";

        document.getElementById("userInput").value = "";
    }
});
