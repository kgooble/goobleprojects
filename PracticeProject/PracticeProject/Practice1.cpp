#include <iostream>


int findMissingNumberIn(int numArray[], int length){
	std::cout << " The number of elements in the array is " << length << std::endl;

	int expectedSum = (length) * (length + 1) / 2;
	int actualSum = 0;


	for (int i = 0; i < length; i++){
		actualSum += numArray[i];
	}

	return expectedSum - actualSum;

}

int main(){

	int numArray[10] = {6, 2, 3, 4, 5, 9, 7, 8, 0, 10};
	int numElements = sizeof(numArray) / sizeof(int);
	int missingNumber = findMissingNumberIn(numArray, numElements);
	
	std::cout << "Missing number is : " << missingNumber << std::endl;
	system("pause");

	return 0;
}