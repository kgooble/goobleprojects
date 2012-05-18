#include <iostream>

int findNumLessThan(int element, int arr[], int startIndex, int length){
	int n = 0;
	for (int i = startIndex; i < length; i++){
		if (arr[i] < element)
			n++;
		else
			break;
	}
	
	printf("Found %d elements in first list less than %d starting at index %d\n", n, element, startIndex);
	return n;
}

int main(){


	int first[5] = {1, 2, 3, 10, 11};
	int second[10] = {2, 4, 6, 9, 10};

	int n = sizeof(first) / sizeof(first[0]);

	int j = 0, i = 0;
	int temp = second[0], sec_temp = second[0];

	while (j < 2 * n){
		if (first[i] <= temp){
			temp = second[j];
			second[j] = first[i];
			i++;
		}else{
			sec_temp = second[j];
			second[j] = temp;
			temp = sec_temp;
			j++;
		}
	}

	/*int lastSecondIndex = n - 1;
	int firstIndex = 0;
	int secondIndex = 0;

	while (secondIndex < 2 * n && secondIndex <= lastSecondIndex){
		int curElem = second[secondIndex];

		int numElementsLess = findNumLessThan(curElem, first, firstIndex, n);
		if (numElementsLess == 0){
			secondIndex++;
			continue;
		}

		for (int i = lastSecondIndex; i >= secondIndex; i--){
			printf("Assigning at second list index %d value %d \n", i + numElementsLess, second[i]);
			second[i + numElementsLess] = second[i];
		}
		for (int i = secondIndex; i < secondIndex + numElementsLess; i++){
			printf("Assigning at second list index %d value %d \n", i, first[firstIndex]);
			second[i] = first[firstIndex];
			firstIndex++;
		}
		
		lastSecondIndex += numElementsLess;
		secondIndex++;
	}
	lastSecondIndex++;

	for (int i = firstIndex; i < n; i++){
		second[lastSecondIndex] = first[i];
		lastSecondIndex++;
	}*/

	for (int i =0 ; i < 2 * n; i++){
		printf("Element in second list at index %d is %d\n", i, second[i]);
	}

	system("pause");
	return 0;
}