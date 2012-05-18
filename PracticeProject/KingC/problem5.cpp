#include "problems.h"
#include <ctype.h>

int pointsForCharacter(char ch){
	switch (ch){
	case 'D': case 'G':
		return 2;
	case 'B': case 'C': case 'M': case 'P':
		return 3;
	case 'F': case 'H': case 'V': case 'W': case 'Y':
		return 4;
	case 'K':
		return 5;
	case 'J': case 'X':
		return 8;
	case 'Q': case 'Z':
		return 10;
	default:
		return 1;
	}
}

void calculateScrabbleWordPoints(){
	printf("Enter a word: ");
	char ch = getchar();
	int score = 0;
	while (ch != '\n'){
		score += pointsForCharacter(toupper(ch));
		ch = getchar();
	}

	printf("Total score: %d\n", score);
}