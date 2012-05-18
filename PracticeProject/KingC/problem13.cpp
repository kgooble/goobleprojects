#include "problems.h"
#include <ctype.h>


void calculateAverageWordLength(){
	printf("Enter a sentence: ");
	int numWords = 1;
	int numLetters = 0;
	
	char ch = getchar();
	while (ch != '\n'){
		if (ch == ' '){
			numWords++;
		}else{
			numLetters++;
		}
		ch = getchar();
	}

	float avg = (float) numLetters / numWords;
	printf("Average word length: %.1f\n", avg);

}