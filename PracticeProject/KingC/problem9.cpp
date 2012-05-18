#include "problems.h"
#include <stdlib.h>
#include <time.h>
#define N 10
#define UP 0
#define DOWN 1
#define LEFT 2
#define RIGHT 3
#define NUM_DIRS 4

bool outOfBounds(int position, int max){
	return position < 0 || position >= max;
}

void getRowColDirs(int dir, int* rowDir, int* colDir){
	switch(dir){
	case UP:
		*rowDir = -1; 
		*colDir = 0;
		break;
	case DOWN:
		*rowDir = +1; 
		*colDir = 0;
		break;
	case LEFT:
		*colDir = -1; 
		*rowDir = 0; 
		break;
	case RIGHT:
		*rowDir = 0; 
		*colDir = +1; 
		break;
	}
}

void alphabetRandomWalk(){
	char square[N][N] = {'.'};
	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++){
			square[i][j] = '.';
		}
	}

	srand((unsigned) time(NULL));

	square[0][0] = 'A';
	int curRow = 0;
	int curCol = 0;
	char curChar = 'B';

	while (curChar <= 'Z'){
		int dir = rand() % NUM_DIRS;
		int rowDir, colDir;
		getRowColDirs(dir, &rowDir, &colDir);

		int newRow = curRow + rowDir;
		int newCol = curCol + colDir;
		bool triedDirs[4] = {false};
		triedDirs[dir] = true;
		//printf ("Trying dir %d\n", dir);
		//printf("New row: %d, new col: %d\n", newRow, newCol);
		while ((outOfBounds(newRow, N) || outOfBounds(newCol, N) || square[newRow][newCol] != '.') && !(triedDirs[UP] && triedDirs[DOWN] && triedDirs[LEFT] && triedDirs[RIGHT])){
			dir = rand() % NUM_DIRS;
			if (triedDirs[dir])
				continue;
			triedDirs[dir] = true;
			getRowColDirs(dir, &rowDir, &colDir);
			newRow = curRow + rowDir;
			newCol = curCol + colDir;

			//printf ("Trying dir %d\n", dir);
			//printf("New row: %d, new col: %d\n", newRow, newCol);
		}
		if (triedDirs[UP] && triedDirs[DOWN] && triedDirs[LEFT] && triedDirs[RIGHT]){
			break;
		}else{
			//printf("Success!\n");
			square[newRow][newCol] = curChar;
			curRow = newRow;
			curCol = newCol;
			curChar++;
		}
	}

	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++){
			printf("%c ", square[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}