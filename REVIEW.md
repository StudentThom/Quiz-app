Code review by @DimitrivC (in Dutch)

-De method goToMain in LoggedInActivity doet nu niets. Zonder doel zou ik deze weghalen (of een doel toevoegen).
-In ViewScoresActivity kan de textView data weg.
-Daar zou ik ook de functie getFromDatabase weghalen.
-De twee methods falseAnswered en trueAnswered bevatten erg veel dubbele code, daarvan kan veel weg.
-De intent kan in je signIn method, maar hiervoor heb je getApplicationContext() nodig ipv this.

