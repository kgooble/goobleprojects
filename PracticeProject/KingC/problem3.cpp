#include "problems.h"
#define NUM_DIGITS 10

void printRepeatedDigits(){
	
	long number;
	printf("Enter a number: ");
	scanf("%ld", &number);

	while (number > 0) {
		int occurrences[NUM_DIGITS] = {0};
		int digit;

		while (number > 0){
			digit = (int) (number % 10);
			number = number / 10;
			occurrences[digit]++;
		}

		printf("Digit:       ");
		for (int i = 0; i < NUM_DIGITS; i++){
			printf("%2d", i);
		}
		printf("\n");
		printf("Occurrences: ");
		for (int i = 0; i < NUM_DIGITS; i++){
			printf("%2d", occurrences[i]);
		}
		printf("\n");

		printf("Enter a number: ");
		scanf("%ld", &number);
	}
}