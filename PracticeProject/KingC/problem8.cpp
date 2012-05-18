#include "problems.h"
#define NUM_STUDENTS 5
#define NUM_QUIZZES 5

void calculateGrades(){

	int grades[NUM_STUDENTS][NUM_QUIZZES] = {0};


	for (int i = 0; i < NUM_STUDENTS; i++){
		printf("Enter grades for student %d: ", i + 1);
		for (int j = 0; j < NUM_QUIZZES; j++){
			scanf("%d", &grades[i][j]);
		}
	}

	printf("\n\nAvg      Total \n");
	for (int i = 0; i < NUM_STUDENTS; i++){
		int sumOfGrades = 0;
		for (int j = 0; j < NUM_QUIZZES; j++){
			sumOfGrades += grades[i][j];
		}
		printf("Student %d: %5.2f %7d\n", i + 1, (float) sumOfGrades / NUM_QUIZZES, sumOfGrades);
	}
	
	printf("\n\nQuiz Score Statistics: \n");
	printf("Avg      High      Low\n");
	for (int j = 0; j < NUM_QUIZZES; j++){
		int maxQuizScore = grades[0][j];
		int minQuizScore = grades[0][j];
		int quizTotalPoints = 0;
		for (int i = 0; i < NUM_STUDENTS; i++){
			int grade = grades[i][j];
			quizTotalPoints += grade;
			if (grade > maxQuizScore)
				maxQuizScore = grade;
			if (grade < minQuizScore)
				minQuizScore = grade;
		}
		printf("Quiz %d: %5.2f %7d %7d\n", j + 1, (float) quizTotalPoints / NUM_QUIZZES, maxQuizScore, minQuizScore);
	}

}